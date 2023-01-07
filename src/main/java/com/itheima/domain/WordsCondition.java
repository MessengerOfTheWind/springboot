package com.itheima.domain;

public class WordsCondition {
    int uid;

    @Override
    public String toString() {
        return "WordsCondition{" +
                "uid=" + uid +
                ", content='" + content + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public WordsCondition(int uid, String content, String year) {
        this.uid = uid;
        this.content = content;
        this.year = year;
    }

    String content;
    String year;

    public WordsCondition(String content, String year) {
        this.content = content;
        this.year = year;
    }

    public WordsCondition(String content) {
        this.content = content;
    }

    public WordsCondition() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
