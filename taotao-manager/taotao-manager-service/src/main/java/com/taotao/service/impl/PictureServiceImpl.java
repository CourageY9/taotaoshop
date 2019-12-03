package com.taotao.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/2:15:53  <br/>
 * Description:图片上传Service层
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS; //ftp地址
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;   //端口号
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;    //FTP名字
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;    //FTP密码
    @Value("${FILI_UPLOAD_PATH}")
    private String FILI_UPLOAD_PATH;    //上传路径
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;      //图片路径

    public PictureResult uploadPicture(byte[] bytes, String fileName) {
        //获取图片返回格式
        PictureResult pictureResult = new PictureResult();
        try {
            //图片名称生成
            String newName = IDUtils.genImageName() + fileName.substring(fileName.lastIndexOf("."));
            //获取当前时间生成文件路径
            String filepath = new DateTime().toString("yyyy/MM/dd");
            //
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            //执行上传图片
            FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FILI_UPLOAD_PATH, filepath, newName, bis);
            //设置返回状态为0     表示成功
            pictureResult.setError(0);
            //拼接返回图片路径（也是存储路径）   如：http://192.168.58.128/images/2019/12/02/
            pictureResult.setUrl(IMAGE_BASE_URL+"/"+filepath+"/"+newName);
            return pictureResult;

        }catch (Exception e){
            e.printStackTrace();
        }
        pictureResult.setError(1);
        pictureResult.setMessage("上传失败");
        return pictureResult;
    }
}
