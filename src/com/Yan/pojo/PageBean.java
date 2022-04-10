package com.Yan.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Yan
 * @Date: 2022/4/9 - 04 - 09 - 10:50
 * @Description: com.Yan.pojo
 * @version: 1.0
 */
public class PageBean<T> implements Serializable {
    //当前页数据
    private List<T> data;
    //总记录数
    private int totalSize;
    //总页数
    private int totalPage;
    //当前页记录数
    private int pageSize;
    //当前页数
    private int currentPage;

    public List<T> getData()  {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public PageBean() {
    }

    public PageBean(List<T> data, int totalSize,int totalPage, int pageSize, int currentPage) {
        this.data = data;
        this.totalSize = totalSize;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }
}
