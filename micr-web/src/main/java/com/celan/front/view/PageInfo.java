package com.celan.front.view;

/*
* 分页数据类
* */
public class PageInfo {
    // 当前页
    private Integer pageNo;
    // 每页显示的记录数
    private Integer pageSize;
    // 总页数
    private Integer toalPage;

    // 总记录数
    private Integer totalRecord;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getToalPage() {
        return toalPage;
    }

    public void setToalPage(Integer toalPage) {
        this.toalPage = toalPage;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public PageInfo() {
    }

    public PageInfo(Integer pageNo, Integer pageSize, Integer totalRecord) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;

        // 计算总页数
        if ( this.totalRecord % this.pageSize == 0) {
            this.toalPage = this.totalRecord / this.pageSize;
        } else {
            this.toalPage = this.totalRecord / this.pageSize + 1;
        }
    }
}
