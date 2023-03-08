package com.vominh.example.thread.fx.dto;

public class MediaInfo {
    private String url;
    private boolean urlValid;
    private String name;
    private String contentType;
    private long contentLength;
    private int duration;

    public MediaInfo() {
    }

    public MediaInfo(String contentType, int contentLength) {
        this.contentType = contentType;
        this.contentLength = contentLength;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUrlValid() {
        return urlValid;
    }

    public void setUrlValid(boolean urlValid) {
        this.urlValid = urlValid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

}
