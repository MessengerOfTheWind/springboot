package com.itheima.domain;

public class MovieCondition {


    public MovieCondition(String moviename, int uid) {
        this.moviename = moviename;
        this.uid = uid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "MovieCondition{" +
                "moviename='" + moviename + '\'' +
                ", uid=" + uid +
                '}';
    }

    public MovieCondition(String moviename) {
        this.moviename = moviename;
    }

    public MovieCondition(int uid) {
        this.uid = uid;
    }

    String moviename;
    int uid;

}
