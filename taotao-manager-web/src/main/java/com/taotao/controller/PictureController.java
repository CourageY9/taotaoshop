package com.taotao.controller;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/2:16:11  <br/>
 * Description:图片上传Controller层
 */
@Controller
@RequestMapping("/pic")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    /**
     * <pre>
     * Description :  上传图片  <br/>
     * ChangeLog : 1. 创建 (2019/12/2 16:28 [yangyi]);
      * @param uploadFile
      * @return com.taotao.common.pojo.PictureResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("/upload")
    public PictureResult uploadPicture(MultipartFile uploadFile){

        try {
            byte[] bytes = uploadFile.getBytes();
            String flieName = uploadFile.getOriginalFilename(); //获取文件名
            PictureResult pictureResult = pictureService.uploadPicture(bytes, flieName);
            return pictureResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
