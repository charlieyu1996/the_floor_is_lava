package com.example.charlieyu.jump;

import java.util.Observable;

import static com.example.charlieyu.jump.Point.lineLineIntersection;

/**
 * Created by charlieyu on 2018-05-04.
 */

public class Model extends Observable {
    // Create static instance of this model
    private static final Model instances = new Model();
    static Model getInstance(){return instances;}

    // Private Variables
    private boolean darkMode;

    private int maxX;
    private int maxY;
    private float middleX;
    private double theta;
    private float initialPointLeftX;
    private float initialPointRightX;
    private float initialPointY;
    private float x, x2 ;
    private float y, y2 ;
    private float initialLengthLeft;
    private float initalLengthRight;
    private double dec;

    // Lines of the cells
    private float[] xNum;
    private float[] yNum;
    private float[] xNum2;
    private float[] yNum2;

    // coordinates for the cells
    public Point[] points;

    // Constructor
    Model(){
        maxX = 0;
        maxY = 0;
        middleX = 0;
        theta = 0.11;
        dec = 0.01;
        xNum = new float[10];
        yNum = new float[10];
        xNum2 = new float[10];
        yNum2 = new float[10];
        darkMode = false;

        points = new Point[100];
    }

    //calculate the variables
    public void calcCoords(){
        middleX = maxX/2;
        initialPointLeftX=-middleX - middleX;
        initialPointRightX=middleX*3 + middleX;
        initialPointY=maxY - middleX - middleX - 750;
        x=x2=middleX;
        y=y2=maxY-200;
        initialLengthLeft = (float) Math.sqrt(Math.pow((middleX-initialPointLeftX),2)+Math.pow((maxY-200-initialPointY),2));
        initalLengthRight = (float) Math.sqrt(Math.pow((middleX-initialPointRightX),2)+Math.pow((maxY-200-initialPointY),2));
        xNum[0]=xNum2[0]=middleX;
        yNum[0]=yNum2[0]=maxY-200;
        xNum[1] = x;
        yNum[1] = y;
        xNum2[1] = x;
        yNum2[1] = y;

        for (int i = 0; i < 10; i++){
            float offsetLengthLeft = (float) (initialLengthLeft * Math.tan(theta));
            float newoffsetLeft = (float) (offsetLengthLeft / Math.sqrt(2));

            float offsetLengthRight = (float) (initalLengthRight * Math.tan(theta));
            float newoffsetRight = (float) (offsetLengthRight / Math.sqrt(2));

            if (i > 1) {
                //Log.d("counter num", String.valueOf(counter));
                xNum[i] = xNum[i - 1] + newoffsetLeft;
                yNum[i] = yNum[i - 1] - newoffsetLeft;
                xNum2[i] = xNum2[i - 1] - newoffsetRight;
                yNum2[i] = yNum2[i - 1] - newoffsetRight;
            } else if (i == 1){
                xNum[i] += newoffsetLeft;
                yNum[i] -= newoffsetLeft;
                xNum2[i] -= newoffsetRight;
                yNum2[i] -= newoffsetRight;
            }
            dec *= 0.95;
            theta = theta - dec;
        }

        // calculate the four coords
        Point C = new Point(initialPointLeftX, initialPointY);
        Point D = new Point(initialPointRightX, initialPointY);
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            Point A = new Point(xNum[i], yNum[i]);
            for (int j=0; j<10; j++) {
                Point B = new Point(xNum2[j], yNum2[j]);
                Point intersection = lineLineIntersection(A, C, B, D);
                points[counter] = intersection;
                counter++;
            }
        }



    }

    // getters for the arrays coords
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

    public Point[] getPoints(){
        return points;
    }

    // set maxX and maxY
    public void setMax(int x, int y){
        maxX = x;
        maxY = y;
    }

    // get maxX
    public int getMaxX(){
        return maxX;
    }

    // get maxY
    public int getMaxY(){
        return maxY;
    }

    // set the darkMode
    public void setDarkMode(boolean change){
        darkMode = change;
    }

    // get the darkMode boolean
    public boolean getDarkMode(){
        return darkMode;
    }


    // Observable methods
    public void initObservers(){
        setChanged();
        notifyObservers();
    }

    @Override
    public void notifyObservers(){
        super.notifyObservers();
    }
}
