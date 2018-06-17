package com.example.charlieyu.jump;

public class Ball extends Sprite{

    public Ball(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    private final float speedX= 0.02f;
    private float speedY= 0.02f;

    private float directionX=1;
    private float directionY=1;

    public void update(long elapsed ){

    }

    public void update(long elapsed,float x, float y){
        float xdir=x;
        float ydir=y;

        xdir+=directionX*speedX*elapsed;
        ydir+=directionY*speedY*elapsed;

        setX(xdir);
        setY(ydir);
    }
}
