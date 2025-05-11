package com.example.scrollshield;

public class RecentActivityData {

    private int iconSrc;
    private String recentActivityName;
    private String recentTimestamp;

    public RecentActivityData(String recentActivityName, String recentTimestamp) {
        this.recentActivityName = recentActivityName;
        this.recentTimestamp = recentTimestamp;
    }

    public RecentActivityData(int iconSrc, String recentActivityName, String recentTimestamp) {
        this.iconSrc = iconSrc;
        this.recentActivityName = recentActivityName;
        this.recentTimestamp = recentTimestamp;
    }

    public int getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(int iconSrc) {
        this.iconSrc = iconSrc;
    }

    public String getRecentActivityName() {
        return recentActivityName;
    }

    public void setRecentActivityName(String recentActivityName) {
        this.recentActivityName = recentActivityName;
    }

    public String getRecentTimestamp() {
        return recentTimestamp;
    }

    public void setRecentTimestamp(String recentTimestamp) {
        this.recentTimestamp = recentTimestamp;
    }
}
