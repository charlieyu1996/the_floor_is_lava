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
    Model models;
    // coordinates for the player cell
    Point bot;
    Point left;
    Point right;
    Point top;

    int counter;

    //start and end time
    long start;
    long end;

    // if the user is holding down or not
    boolean hold;

    // constructor
    public Player(){
        bot = new Point(0,0);
        left = new Point(0,0);
        right = new Point(0,0);
        top = new Point(0,0);
        counter = 1;
        start = 0;
        end = 0;
        // Get model instance
        models = Model.getInstance();
        models.addObserver(this);

        // Starting position
        bot.y = Model.getInstance().getPoints()[0].y;
        bot.x=Model.getInstance().getPoints()[0].x;
        left.x = Model.getInstance().getPoints()[1].x;
        left.y = Model.getInstance().getPoints()[1].y;
        top.x = Model.getInstance().getPoints()[11].x;
        top.y = Model.getInstance().getPoints()[11].y;
        right.x = Model.getInstance().getPoints()[10].x;
        right.y = Model.getInstance().getPoints()[10].y;
      //  models.initObservers();
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

    public void setStart(long time){
        start = time;
    }

    public void setEnd(long time){
        end = time;
    }

    public long getStart(){
        return start;
    }

    public long getEnd(){
        return end;
    }

    public void setHold(boolean check){
        hold = check;
    }

    public boolean getHold(){
        return hold;
    }

    public void update(){
        if (counter != 9 && counter != 19 && counter != 29 && counter != 39 && counter != 49 && counter != 59 && counter != 69 && counter != 79 && counter !=89 && counter !=99) {
            bot.x = models.getPoints()[counter].x;
            bot.y = models.getPoints()[counter].y;
            left.x = models.getPoints()[counter+1].x;
            left.y = models.getPoints()[counter+1].y;
            top.x = models.getPoints()[counter+11].x;
            top.y = models.getPoints()[counter+11].y;
            right.x = models.getPoints()[counter+10].x;
            right.y = models.getPoints()[counter+10].y;
            Log.d("Checking:", String.valueOf(counter));
        }
        counter++;
    }

    @Override
    public void update(Observable o, Object arg){
        Log.d("TEST", "updated");
    }
}
