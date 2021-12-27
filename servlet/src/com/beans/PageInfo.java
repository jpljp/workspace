package com.beans;

public class PageInfo {
    private int pageSize;
    private int pageIndex;
    private int pageCount;
    private int rowCount;
    private int beginRow;

    private boolean hasPre;  //是否有上一页，true代表有
    private boolean hasNext;  //是否有下一页，true代表有

    @Override
    public String toString() {
        return "PagrInfo{" +
                "pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", pageCount=" + pageCount +
                ", rowCount=" + rowCount +
                ", beginRow=" + beginRow +
                ", hasPre=" + hasPre +
                ", hasNext=" + hasNext +
                '}';
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getBeginRow() {
        return beginRow;
    }

    public void setBeginRow(int beginRow) {
        this.beginRow = beginRow;
    }

    public boolean isHasPre() {
        return hasPre;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
