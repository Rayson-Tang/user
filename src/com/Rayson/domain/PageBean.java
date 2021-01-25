package com.Rayson.domain;

import java.util.List;

public class PageBean <T>{
    //  总记录条数
    private int totalCount;

    //  总页数
    private int totalPage;

    //  每页显示的记录数
    private int rows;

    //  每页的数据
    private List<T> list;

    // 当前页码
    private int currentPage;



    public int getTotalCount() {

        return totalCount;

    }



    public void setTotalCount(int totalCount) {

        this.totalCount = totalCount;

    }



    public int getTotalPage() {

        return totalPage;

    }



    public void setTotalPage(int totalPage) {

        this.totalPage = totalPage;

    }



    public int getRows() {

        return rows;

    }



    public void setRows(int rows) {

        this.rows = rows;

    }



    public List<T> getList() {

        return list;

    }



    public void setList(List<T> list) {

        this.list = list;

    }



    public int getCurrentPage() {

        return currentPage;

    }



    public void setCurrentPage(int currentPage) {

        this.currentPage = currentPage;

    }



    @Override

    public String toString() {

        return "PageBean{" +

                "totalCount=" + totalCount +

                ", totalPage=" + totalPage +

                ", rows=" + rows +

                ", list=" + list +

                ", currentPage=" + currentPage +

                '}';

    }
}
