package com.example.myapplication;

import android.graphics.Bitmap;

import java.io.InputStream;

public class PicDataModel {

    String id,name;

    Bitmap bitmap;

    public PicDataModel(String id, String name, Bitmap bitmap) {
        this.id = id;
        this.name = name;
        this.bitmap = bitmap;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
