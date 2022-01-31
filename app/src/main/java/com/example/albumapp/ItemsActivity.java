package com.example.albumapp;

public class ItemsActivity {

    private String urlImg;
    private String title;

    public ItemsActivity(String urlImg, String title, int userId) {
        this.urlImg = urlImg;
        this.title = title;
    }

    public ItemsActivity() {
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
