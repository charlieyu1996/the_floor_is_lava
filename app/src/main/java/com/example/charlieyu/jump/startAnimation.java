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
            // This method is called once the time is elapsed
//            float offsetLengthLeft = (float) (initialLengthLeft * Math.tan(theta));
//            float newoffsetLeft = (float) (offsetLengthLeft / Math.sqrt(2));
//
//            float offsetLengthRight = (float) (initalLengthRight * Math.tan(theta));
//            float newoffsetRight = (float) (offsetLengthRight / Math.sqrt(2));
//
//
//
//            x += newoffsetLeft;
//            y -= newoffsetLeft;
//            x2 -= newoffsetRight;
//            y2 -= newoffsetRight;
//            if (counter < 10) {
//                Log.d("counter num", String.valueOf(counter));
//                if (counter > 1) {
//                    //Log.d("counter num", String.valueOf(counter));
//                    xNum[counter] = xNum[counter - 1] + newoffsetLeft;
//                    yNum[counter] = yNum[counter - 1] - newoffsetLeft;
//                    xNum2[counter] = xNum2[counter - 1] - newoffsetRight;
//                    yNum2[counter] = yNum2[counter - 1] - newoffsetRight;
//                } else if (counter==1){
//                    xNum[counter] += newoffsetLeft;
//                    yNum[counter] -= newoffsetLeft;
//                    xNum2[counter] -= newoffsetRight;
//                    yNum2[counter] -= newoffsetRight;
//                }
//
//                dec *= 0.95;
//                theta = theta - dec;
//                counter++;
//            }


            if (counter < 10){
                counter++;
            }


        }
    }


    public startAnimation(Context context){
        // Get model instance
        model = Model.getInstance();
        model.addObserver(this);



//        maxX = model.getMaxX();
//        maxY = model.getMaxY();
//        middleX = maxX/2;
//        theta=.11;
//        initialPointLeftX=-middleX - middleX;
//        initialPointRightX=middleX*3 + middleX;
//        initialPointY=maxY - middleX - middleX - 750;
//        x=x2=middleX;
//        y=y2=maxY-200;
//
//        initialLengthLeft = (float) Math.sqrt(Math.pow((middleX-initialPointLeftX),2)+Math.pow((maxY-200-initialPointY),2));
//        initalLengthRight = (float) Math.sqrt(Math.pow((middleX-initialPointRightX),2)+Math.pow((maxY-200-initialPointY),2));
//        dec = .01;
//        limit = 9;

        // count how many lines should we draw
        counter = 0;


        // array of x y
//        xNum = new float[10];
//        yNum = new float[10];
//        xNum2 = new float[10];
//        yNum2 = new float[10];
//        xNum[0]=xNum2[0]=middleX;
//        yNum[0]=yNum2[0]=maxY-200;
//
//
//        xNum[1] = x;
//        yNum[1] = y;
//        xNum2[1] = x;
//        yNum2[1] = y;


        // start point of V
        offset500 = 500;

        //Init observers
        model.initObservers();
    }



    public void update(){
        if (counter < 3){



            // This method is called once the time is elapsed
//            float offsetLengthLeft = (float) (initialLengthLeft * Math.tan(theta));
//            float newoffsetLeft = (float) (offsetLengthLeft / Math.sqrt(2));
//
//            float offsetLengthRight = (float) (initalLengthRight * Math.tan(theta));
//            float newoffsetRight = (float) (offsetLengthRight / Math.sqrt(2));



//            x += newoffsetLeft;
//            y -= newoffsetLeft;
//            x2 -= newoffsetRight;
//            y2 -= newoffsetRight;
            //if (counter < 9) {
//                if (counter > 1) {
//                    Log.d("counter num", String.valueOf(counter));
//                    xNum[counter] = xNum[counter - 1] + newoffsetLeft;
//                    yNum[counter] = yNum[counter - 1] - newoffsetLeft;
//                    xNum2[counter] = xNum2[counter - 1] - newoffsetRight;
//                    yNum2[counter] = yNum2[counter - 1] - newoffsetRight;
//                } else if(counter==1) {
//                    xNum[counter] += newoffsetLeft;
//                    yNum[counter] -= newoffsetLeft;
//                    xNum2[counter] -= newoffsetRight;
//                    yNum2[counter] -= newoffsetRight;
//                }
//
//                dec *= 0.95;
//                theta = theta - dec;

           // }

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
