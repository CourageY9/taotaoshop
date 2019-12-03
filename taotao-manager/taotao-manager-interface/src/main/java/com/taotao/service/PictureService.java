package com.taotao.service;

import com.taotao.common.pojo.PictureResult;

public interface PictureService {

    public PictureResult uploadPicture(byte[] bytes,String fileName);   //图片上传
}
