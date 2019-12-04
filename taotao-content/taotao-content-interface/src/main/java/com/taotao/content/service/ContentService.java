package com.taotao.content.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

    public EasyUIResult getContent(long categoryId);    //通过categoryId查询内容信息

    public TaotaoResult addContent(TbContent tbContent);    //添加Content

    public TaotaoResult deleteContent(long[] ids);      //删除content

    public TaotaoResult updateContent(TbContent tbContent);     //根据ID修改content
}
