/*package com.example.charlieyu.jump;

import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import android.os.Handler;

/**
 * Created by charlieyu on 2018-05-04.
 */
/*
public class background extends View implements Observer{
    // Private Variables
    Model model;
    Paint paint = new Paint();

    public background(Context context) {
        super(context);
        model = Model.getInstance();
        model.addObserver(this);
    }

    @Override
    public void onDraw(final Canvas canvas) {
        boolean darkMode = model.getDarkMode();
        if (darkMode){
            setBackgroundColor(Color.BLACK);
            paint.setColor(Color.WHITE);
        }else{
            setBackgroundColor(Color.WHITE);
            paint.setColor(Color.BLACK);
        }
        paint.setStrokeWidth(5);

        float maxX = model.getMaxX();
        float maxY = model.getMaxY();
        float middleX = maxX/2;
        float middleY = maxY/2;
        double theta=.11;
        float offset=200;
        final float initialPointLeftX=-middleX - middleX;
        final float initialPointRightX=middleX*3 + middleX;
        final float initialPointY=maxY - middleX - middleX - 750;

        canvas.drawLine(middleX,maxY-200, middleX, maxY,paint);

        //Left and Right Initial points with offset
        canvas.drawLine(initialPointLeftX, initialPointY,middleX, maxY-200, paint);
        canvas.drawLine(initialPointRightX,initialPointY,middleX,maxY-200,paint);

        //Lef and Right Initial points without offset
        canvas.drawLine(initialPointLeftX, initialPointY,middleX, maxY, paint);
        canvas.drawLine(initialPointRightX,initialPointY,middleX,maxY,paint);

        canvas.drawRect(middleX, maxY-middleX,middleX,maxY-200,paint);

        float x, x2 ;
        float y, y2 ;

        x=x2=middleX;
        y=y2=maxY-200;

        float initialLengthLeft = (float) Math.sqrt(Math.pow((middleX-initialPointLeftX),2)+Math.pow((maxY-200-initialPointY),2));
        float initalLengthRight = (float) Math.sqrt(Math.pow((middleX-initialPointRightX),2)+Math.pow((maxY-200-initialPointY),2));

        double dec = .01;

        for (int i = 0; i < 9; i++){
            float offsetLengthLeft= (float) (initialLengthLeft*Math.tan(theta));
            float newoffsetLeft= (float) (offsetLengthLeft/Math.sqrt(2));

            float offsetLengthRight= (float) (initalLengthRight*Math.tan(theta));
            float newoffsetRight =(float) (offsetLengthRight/Math.sqrt(2));

            x+=newoffsetLeft;
            y-=newoffsetLeft;
            x2-=newoffsetRight;
            y2-=newoffsetRight;

            dec *= 0.95;
            theta = theta - dec;
            canvas.drawLine(initialPointLeftX,initialPointY,x,y, paint);
            canvas.drawLine(initialPointRightX,initialPointY,x2,y2, paint);

            //postInvalidateDelayed(TimeUnit.SECONDS.toMillis(10000000));
        }
    }


    @Override
    public void update(Observable o, Object arg){}
}


<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">





    <ImageButton
        android:id="@+id/resumeButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"


        android:layout_marginStart="36dp"
        android:layout_marginTop="180dp"
        android:adjustViewBounds="true"
        android:background="@android:color/transparent"
        android:contentDescription="@string/backButton"
        android:maxHeight="150dp"
        android:maxWidth="90dp"
        android:scaleType="fitCenter"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/back_button" />


    <Button
        android:id="@+id/resumeButton2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="148dp"
        android:layout_marginTop="68dp"
        android:text="Resume"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/PauseTitle" />


    <TextView
        android:id="@+id/PauseTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true"
        android:layout_marginStart="56dp"

        android:layout_marginTop="116dp"
        android:contentDescription="@string/app_name"


        android:text="@string/gamePause"
        android:textColor="@color/colorAccent"
        android:textSize="40sp"
        android:textStyle="bold"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />




    <ImageButton
        android:id="@+id/buttonBack"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="16dp"


        android:adjustViewBounds="true"
        android:background="@android:color/transparent"
        android:contentDescription="@string/backButton"
        android:maxHeight="150dp"
        android:maxWidth="90dp"
        android:scaleType="fitCenter"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.066"
        app:layout_constraintStart_toStartOf="parent"
        app:srcCompat="@drawable/back_button" />

    <!--android:fontFamily="@font/bungee_hairline"-->



</android.support.constraint.ConstraintLayout>


*/