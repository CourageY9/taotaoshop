package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/2:12:34  <br/>
 * Description:easy数形结构图，返回格式
 */
public class EasyUITreeNode implements Serializable {
    public long id; //id
    public String text; //名称
    public String state;    //是否有子节点    closed有子节点   open没有子节点

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "EasyUITreeNode{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
