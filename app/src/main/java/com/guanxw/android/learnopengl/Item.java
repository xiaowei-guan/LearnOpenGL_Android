package com.guanxw.android.learnopengl;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("title")
    private String title;
    @SerializedName("className")
    private String className;

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getClassName() { return className; }
    public void setClassName(String value) { this.className = value; }
}
