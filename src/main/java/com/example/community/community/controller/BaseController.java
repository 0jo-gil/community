package com.example.community.community.controller;

import com.example.community.utils.PageUtil;
import org.springframework.data.domain.Pageable;

public class BaseController {
    public String getPagerHtml(long totalCount, long pageSize, long pageIndex, String queryString){
        PageUtil pageUtil = new PageUtil(totalCount, pageSize, pageIndex, queryString);
        return pageUtil.pager();
    }
}
