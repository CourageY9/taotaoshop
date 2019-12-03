package com.taotao.common.pojo;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class ABCTestTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void PictureTest() throws IOException {
        //创建FTPClient对象
        FTPClient ftpClient = new FTPClient();
        //建立连接
        ftpClient.connect("192.168.58.128");
        //连接ftp服务器，账号，密码(不是linux密码)
        ftpClient.login("ftpuser", "ftpuser");
        //从本地读取一张想要上传的图片
        FileInputStream inputStream = new FileInputStream(new File("D:\\IDEA\\SSM_Client\\web\\statics\\img\\t1.jpg"));
        //解决上传图片有时候会出现 0 KB情况
        ftpClient.enterLocalActiveMode();
        //规定上传的类型
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //路径要为linux下的路径 才能够吧图片上传到指定路径执行
        ftpClient.storeFile("/home/ftpuser/www/images/125.jpg", inputStream);

        inputStream.close();
        ftpClient.logout();

    }
}