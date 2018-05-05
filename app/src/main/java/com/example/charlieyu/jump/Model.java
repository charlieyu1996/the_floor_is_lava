package com.example.charlieyu.jump;

import java.util.Observable;

/**
 * Created by charlieyu on 2018-05-04.
 */

public class Model extends Observable {
    // Create static instance of this model
    private static final Model instances = new Model();
    static Model getInstance(){return instances;}

    // Private Variables
    private int maxX;
    private int maxY;

    // Constructor
    Model(){
        maxX = 0;
        maxY = 0;
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
