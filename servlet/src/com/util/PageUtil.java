package com.util;

import com.beans.PageInfo;

public class PageUtil {
    public static PageInfo getPageInfo(int pageSize,int pageIndex,int rowCount){

        PageInfo pageInfo=new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageIndex(pageIndex);
        pageInfo.setRowCount(rowCount);
        pageInfo.setBeginRow(pageSize*(pageIndex-1));
        pageInfo.setPageCount((rowCount+pageSize-1)/pageSize+1);

        pageInfo.setHasPre(!(pageIndex==1||rowCount==0));
        pageInfo.setHasNext(!(pageIndex>=pageInfo.getPageCount()));
        return pageInfo;
    }
}
