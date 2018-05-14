package com.example.charlieyu.jump;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by charlieyu on 2018-05-06.
 */

public class startAnimation implements Observer{
    Model model;

    float maxX;
    float maxY;
    float middleX;
    double theta;
    float initialPointLeftX;
    float initialPointRightX;
    float initialPointY;
    float x, x2 ;
    float y, y2 ;
    float initialLengthLeft;
    float initalLengthRight;
    double dec;
    int limit;
    int counter;
    float[] xNum;
    float[] yNum;
    float[] xNum2;
    float[] yNum2;
    int offset500;



    private class ExampleTask extends TimerTask {

        @Override
        public void run() {

            if (counter < 10){
                counter++;
            }


        }
    }


    public startAnimation(Context context){
        // Get model instance
        model = Model.getInstance();
        model.addObserver(this);


// start point of V
        offset500 = 500;

        //Init observers
        model.initObservers();
    }



    public void update(){
        if (counter < 3){
            counter++;
        }

        if (offset500 > 0 && counter >= 3){
            offset500-=10;
        }

        if (counter < 10 && offset500 == 0){
            Timer timer = new Timer();
            ExampleTask task = new ExampleTask();
            // Executes the task in 500 milliseconds
            timer.schedule(task, 200);
        }
    }

    public int getOffset500(){
        return offset500;
    }


    public float getX(){
        return x;
    }

    public float getX2(){
        return x2;
    }

    public float getY(){
        return y;
    }

    public float getY2(){
        return y2;
    }

    public float[] getxNum(){
        return xNum;
    }

    public float[] getyNum(){
        return yNum;
    }

    public float[] getxNum2(){
        return xNum2;
    }

    public float[] getyNum2(){
        return yNum2;
    }


    public int getCounter(){
        return counter;
    }


    @Override
    public void update(Observable o, Object arg){}

}
