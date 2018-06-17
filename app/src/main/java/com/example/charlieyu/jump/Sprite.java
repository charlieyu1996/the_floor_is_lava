package com.example.charlieyu.jump;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {

    private float x;
    private float y;

    private int screenWidth;
    private int screenHeight;

    private Bitmap image;

    public Sprite(int screenWidth, int screenHeight){
        this.x=30;
        this.y=30;

        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
    }

    public void init(Bitmap image){
        this.image=image;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
