package org.yufan.bean;

import org.yufan.bean.Item;

import java.io.Serializable;
import java.util.List;
//返回结果的Pojo
public class SearchResult implements Serializable {
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    //商品列表
    private List<Item> itemList;
    //总记录数
    private  Long recordCount;
    //当前页
    private Long currentPage;

    private Long pageCount;

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }
}
