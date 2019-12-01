package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/1:18:25  <br/>
 * Description:定义商品返回格式
 */
public class EasyUIResult implements Serializable {
    private long total;  //总页数
    private List<?> rows;   //商品信息结果集

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "EasyUIResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
