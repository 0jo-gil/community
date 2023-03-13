package com.example.community.utils;

public class PageUtil {
    private long totalCount;

    private long pageSize = 10;

    private long pageBlockSize = 10;

    private long pageIndex;

    private long totalBlockCount;

    private long startPageNum;

    private long endPageNum;


    private String queryString;


    public PageUtil(long totalCount, long pageSize, long pageIndex, String queryString) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex + 1;
        this.queryString = queryString;
    }


    public String pager(){
        init();

        StringBuilder sb = new StringBuilder();

        long previousPageIndex = pageIndex > 1 ? pageIndex - 1 : 1;
        long nextPageIndex = pageIndex < totalBlockCount ? pageIndex + 1 : totalBlockCount;


        System.out.println("index : " + pageIndex);
        System.out.println("이전 : " + previousPageIndex);
        System.out.println("다음 : " + nextPageIndex);

        System.out.println("total : " + totalBlockCount);

        String addQueryString = "";

        if(queryString != null && queryString.length() > 0){
            addQueryString = "&" + queryString;
        }

        sb.append(String.format("<a href='pageIndex=%d%s' class='arrow'>&lt;&lt;</a>", 1, addQueryString));
        sb.append(System.lineSeparator());
        sb.append(String.format("<a href='?pageIndex=%d%s' class='arrow'>&lt;</a>", previousPageIndex, addQueryString));
        sb.append(System.lineSeparator());

        for (long i = startPageNum; i <= endPageNum; i++) {
            if( i == pageIndex ){
                sb.append(String.format("<a class='number on' href='?pageIndex=%d%s'>%d</a>", i, addQueryString, i));
            } else {
                sb.append(String.format("<a class='number' href='?pageIndex=%d%s'>%d</a>", i, addQueryString, i));
            }
            sb.append(System.lineSeparator());
        }

        sb.append(String.format("<a href='?pageIndex=%d%s' class='arrow'>&gt;</a>", nextPageIndex, addQueryString));
        sb.append(System.lineSeparator());
        sb.append(String.format("<a href='?pageIndex=%d%s' class='arrow'>&gt;&gt;</a>", totalBlockCount, addQueryString));
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    private void init() {
        if(pageIndex < 1){
            pageIndex = 1;
        }

        if(pageSize < 1){
            pageSize = 1;
        }

        totalBlockCount = totalCount / pageSize + (totalCount % pageSize > 0 ? 1: 0);

        startPageNum = ((pageIndex - 1) / pageBlockSize) * pageBlockSize + 1;

        endPageNum = startPageNum + pageBlockSize - 1;

        if(endPageNum > totalBlockCount){
            endPageNum = totalBlockCount;
        }
    }

}
