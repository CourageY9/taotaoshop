package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JedisUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.jedis.JedisClient;
import com.taotao.content.service.ContentService;
import com.taotao.dao.TbContentMapper;
import com.taotao.pojo.TbContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/4:10:46  <br/>
 * Description:内容信息Service层
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY; //设置默认的大广告

    /**
     * <pre>
     * Description :  通过categoryId查询内容信息 <br/>
     * ChangeLog : 1. 创建 (2019/12/4 10:49 [yangyi]);
      * @param categoryId
      * @return com.taotao.common.pojo.EasyUIResult
     * </pre>
     */
    public EasyUIResult getContent(long categoryId) {
        List<TbContent> list = tbContentMapper.getContentByCategoryId(categoryId);
        EasyUIResult result = new EasyUIResult();
        result.setTotal(list.size());   //总条数
        result.setRows(list);   //data数据
        return result;
    }


    /**
     * <pre>
     * Description :  添加content信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 11:26 [yangyi]);
      * @param tbContent
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult addContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        int resultNum = tbContentMapper.addContent(tbContent);
        if (resultNum == 1){
            JedisUtils.del(CONTENT_KEY);//后面记得更改，暂时只针对大广告
            return TaotaoResult.ok(date);
        }
        return null;
    }

    /**
     * <pre>
     * Description :  删除Content内容  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 11:44 [yangyi]);
      * @param ids
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult deleteContent(long[] ids) {
        int resultNum = tbContentMapper.deleteContent(ids);
        if (resultNum == 1){
            JedisUtils.del(CONTENT_KEY);//后面记得更改，暂时只针对大广告
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500, "删除失败");
    }

    /**
     * <pre>
     * Description : 修改Content对象  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 15:04 [yangyi]);
      * @param tbContent
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult updateContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setUpdated(date);
        int num = tbContentMapper.updateContent(tbContent);
        if (num == 1){
            JedisUtils.del(CONTENT_KEY);    //后面记得更改，暂时只针对大广告
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500, "修改失败");
    }


    public List<TbContent> findContent(long categoryId) {
        //先去缓存中获取获取数据，如有数据就直接用
        String resultJson = JedisUtils.get(CONTENT_KEY);
        //判断resultJson不为空
        if (StringUtils.isNotBlank(resultJson)){
            List<TbContent> resultList = JsonUtils.jsonToList(resultJson, TbContent.class); //将缓存中获取的String类型数据转换成list
            System.out.println("从redis缓存中获取数据，不进行数据库查询");
            return resultList;
        }

        List<TbContent> list = tbContentMapper.getContentByCategoryId(categoryId);

        //向redis存数据
        JedisUtils.set(CONTENT_KEY, JsonUtils.objectToJson(list));
        System.out.println("从数据库中获取数据存入redis缓存");
        return list;
    }
}
