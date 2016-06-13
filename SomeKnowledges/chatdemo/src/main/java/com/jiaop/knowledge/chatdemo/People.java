package com.jiaop.knowledge.chatdemo;

import android.graphics.Bitmap;

/**
 * Created by jiaop on 2016/6/13.
 */
public class People {

    private int type;
    private String text;
    private Bitmap img;

    public People() {
    }

    public People(int type, String text, Bitmap img) {
        this.type = type;
        this.text = text;
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
