package com.taotao.service.impl;

import com.taotao.dao.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Auther: yangyi  <br/>
 * Date: 2019/11/29:9:41  <br/>
 * Description:Item Service层
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    /**
     * <pre>
     * Description :  获取商品ID  <br/>
     * ChangeLog : 1. 创建 (2019/11/29 10:14 [yangyi]);
      * @param itemId
      * @return com.taotao.pojo.TbItem
     * </pre>
     */
    public TbItem getItemById(long itemId) {
        return tbItemMapper.findItemById(itemId);
    }
}
