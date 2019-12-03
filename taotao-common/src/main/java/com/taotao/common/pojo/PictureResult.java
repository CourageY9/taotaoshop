package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/2:15:37  <br/>
 * Description:图片上传返回格式类
 */
public class PictureResult implements Serializable {
    private int error;  //返回状态成功0，失败1
    private String url; //存储图片的路径地址
    private String message;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PictureResult{" +
                "error=" + error +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
