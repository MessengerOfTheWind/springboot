package com.itheima.domain;

import java.util.List;

public class PageResult2 {
    long current;
    long size;
    long pages;
    long total;
    List<Signature> signatures;

    public PageResult2(long current, long size, long pages, long total, List<Signature> signatures) {
        this.current = current;
        this.size = size;
        this.pages = pages;
        this.total = total;
        this.signatures = signatures;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "current=" + current +
                ", size=" + size +
                ", pages=" + pages +
                ", total=" + total +
                ", records=" + signatures +
                '}';
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Signature> getRecords() {
        return signatures;
    }

    public void setRecords(List<Signature> signatures) {
        this.signatures = signatures;
    }
}
