package com.example.charlieyu.jump;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {

    public float xdir;
    public float ydir;

    private int screenWidth;
    private int screenHeight;
    private final float speedX = 0.002f;
    private float speedY = 0.002f;

    private float directionX = 1;
    private float directionY = 1;

    private Bitmap image;

    public Sprite(int screenWidth, int screenHeight) {
        this.xdir = 30;
        this.ydir = 30;

        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void init(Bitmap image) {
        this.image = image;
    }

    public void draw(Canvas canvas, float x, float y) {
        canvas.drawBitmap(image, x, y, null);
    }

    public float getX() {
        return xdir;
    }

    public float getY() {
        return ydir;
    }

    public void update(int speed,float x, float y ) {
        
        if(!(xdir>x)){
            xdir = xdir + speed;
        }else{
            xdir=xdir-speed;
        }

        if(!(ydir>y)){
            ydir = ydir + speed;
        }else {
            ydir = ydir - speed;
        }
    }
}