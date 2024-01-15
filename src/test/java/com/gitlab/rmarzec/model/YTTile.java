package com.gitlab.rmarzec.model;

public class YTTile {
    String title;
    String channel;
    String length;
    boolean live;

    public YTTile(String title, String channel, String length, boolean live) {
        this.title = title;
        this.channel = channel;
        this.length = length;
        this.live = live;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return "YTTile {" +
                "title='" + title + '\'' +
                ", channel='" + channel + '\'' +
                ", length='" + formatLength(length) + '\'' +
                ", live='" + live + '\'' +
                '}';
    }

    private String formatLength(String length) {
        if (this.live) return "LIVE";
        return length == null ? "" : length;
    }
}
