package com.ltf.aplicativo.Activity;



public class News {

    private String title;
    private String desc;
    private String desc2;
    private String image;
    private String url;

    public News ( String title, String desc, String image, String url, String desc2 ) {
        this.title = title;
        this.desc = desc;
        this.desc = desc2;
        this.image = image;
        this.url = url;
    }

    public News () {

    }

    public String getTitle () {
        return title;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    public String getDesc () {
        return desc;
    }


    public void setDesc ( String desc ) {
        this.desc = desc;
    }

    public String getImage () {
        return image;
    }

    public void setImage ( String image ) {
        this.image = image;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl ( String url ) {
        this.url = url;
    }


    public String getDesc2 () {
        return  desc2;
    }
}