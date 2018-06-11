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
    int counter;
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

        // animation moving down
        if (offset500 > 0 && counter >= 3){
            //offset500-=10;
            offset500/=1.1;
        }

        if (counter < 10 && offset500 == 0){
            Timer timer = new Timer();
            ExampleTask task = new ExampleTask();
            // Executes the task in 500 milliseconds
            timer.schedule(task, 400);
        }
    }

    public int getOffset500(){
        return offset500;
    }

    public int getCounter(){
        return counter;
    }

    @Override
    public void update(Observable o, Object arg){}

}
