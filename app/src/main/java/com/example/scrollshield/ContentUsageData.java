package com.example.scrollshield;

public class ContentUsageData {

    private int iconSrc;
    private String subjectName;
    private String duration;

    public ContentUsageData(String subjectName, String duration) {
        this.subjectName = subjectName;
        this.duration = duration;
    }

    public ContentUsageData(int iconSrc, String subjectName, String duration) {
        this.iconSrc = iconSrc;
        this.subjectName = subjectName;
        this.duration = duration;
    }

    public int getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(int iconSrc) {
        this.iconSrc = iconSrc;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
