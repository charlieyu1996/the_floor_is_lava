package com.example.charlieyu.jump;


import android.util.Log;

import java.io.Console;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * Created by charlieyu on 2018-05-11.
 */

public class Player implements Observer{
    Model model;
    // coordinates

    Point bot;
    Point left;
    Point right;
    Point top;

    int counter;

    // constructor
    public Player(){

        counter = 0;
        // Get model instance
        model = Model.getInstance();
        model.addObserver(this);

        Log.d("LUL: ", String.valueOf(model.points[0]));

        // Starting position
//        bot.x = model.points[0].x;
//        bot.y = model.points[0].y;
//        left.x = model.points[1].x;
//        left.y = model.points[1].y;
//        top.x = model.points[11].x;
//        top.y = model.points[11].y;
//        right.x = model.points[10].x;
//        right.y = model.points[10].y;

        model.initObservers();
    }

    // getter for the four points
    public Point getBot(){
        return bot;
    }

    public Point getLeft(){
        return left;
    }

    public Point getRight(){
        return right;
    }

    public Point getTop(){
        return top;
    }

    public void update(){
        //Set<Integer> set = new HashSet<Integer>();
        //set.add(9);
//        if (counter != 9 && counter != 19 && counter != 29 && counter != 39 && counter != 49 && counter != 59 && counter != 69 && counter != 79 && counter !=89 && counter !=99) {
//            bot.x = model.points[counter].x;
//            bot.y = model.points[counter].y;
//            left.x = model.points[counter+1].x;
//            left.y = model.points[counter+1].y;
//            top.x = model.points[counter+11].x;
//            top.y = model.points[counter+11].y;
//            right.x = model.points[counter+10].x;
//            right.y = model.points[counter+10].y;
//            counter++;
//        }
    }

    @Override
    public void update(Observable o, Object arg){
        Log.d("TEST", "updated");
    }
}
