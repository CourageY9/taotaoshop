package com.taotao.search.listener;

import com.taotao.common.pojo.SearchItem;
import com.taotao.dao.SearchMapper;
import com.taotao.search.service.SolrItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/12:14:45  <br/>
 * Description: 处理ActiveMQ  接收消息函数
 */
public class MyMessageListener implements MessageListener {

    @Autowired
    private SearchMapper searchMapper;

    @Autowired
    private SolrItemService solrItemService;

    public void onMessage(Message message) {
        try {
            TextMessage resultText = (TextMessage) message; //Message 订阅的消息(因为发送的是TextMessage类型数据，强转)
            String text = resultText.getText();
            List<SearchItem> item = searchMapper.getItem(Long.valueOf(text));
            solrItemService.addSolrItemDocument(item);  //向solr中添加商品信息
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
