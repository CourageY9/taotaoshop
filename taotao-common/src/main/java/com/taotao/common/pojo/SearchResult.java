package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/11:11:27  <br/>
 * Description:Search返回类
 */
public class SearchResult implements Serializable {
    private List<SearchItem> itemList;
    private long recordCount;
    private long pageCount;

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "itemList=" + itemList +
                ", recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                '}';
    }
}
