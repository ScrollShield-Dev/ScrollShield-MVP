package com.example.scrollshield;

import android.net.Uri;

public class ShortsData {

    private Uri shortsPath;
    private String shortsUser, shortsTitle;
    private int shortsImg;

    public ShortsData(Uri shortsPath, String shortsUser, String shortsTitle, int shortsImg) {
        this.shortsPath = shortsPath;
        this.shortsUser = shortsUser;
        this.shortsTitle = shortsTitle;
        this.shortsImg = shortsImg;
    }

    public Uri getShortsPath() {
        return shortsPath;
    }

    public String getShortsUser() {
        return shortsUser;
    }

    public String getShortsTitle() {
        return shortsTitle;
    }

    public int getShortsImg() {
        return shortsImg;
    }
}
