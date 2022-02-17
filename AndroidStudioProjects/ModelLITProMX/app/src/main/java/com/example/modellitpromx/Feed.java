package com.example.modellitpromx;

import android.widget.ImageView;

public class Feed {
    private int imgUrl;
    private String lapTime;
    private int lapCount;
    private String sessionLength;
    private int ranking;

    public Feed(int imgUrl, String lapTime, int lapCount, String sessionLength, int ranking) {
        this.imgUrl = imgUrl;
        this.lapTime = lapTime;
        this.lapCount = lapCount;
        this.sessionLength = sessionLength;
        this.ranking = ranking;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLapTime() {
        return lapTime;
    }

    public void setLapTime(String lapTime) {
        this.lapTime = lapTime;
    }

    public int getLapCount() {
        return lapCount;
    }

    public void setLapCount(int lapCount) {
        this.lapCount = lapCount;
    }

    public String getSessionLength() {
        return sessionLength;
    }

    public void setSessionLength(String sessionLength) {
        this.sessionLength = sessionLength;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "imgUrl='" + imgUrl + '\'' +
                ", lapTime='" + lapTime + '\'' +
                ", lapCount='" + lapCount + '\'' +
                ", sessionLength='" + sessionLength + '\'' +
                ", ranking=" + ranking +
                '}';
    }
}
