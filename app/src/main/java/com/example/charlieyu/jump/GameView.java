package com.example.charlieyu.jump;

import android.content.Context;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewPropertyAnimator;
import android.widget.RelativeLayout;

import java.io.Console;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.example.charlieyu.jump.Point.lineLineIntersection;


/**
 * Created by charlieyu on 2018-05-06.
 */

public class GameView extends SurfaceView implements Runnable, Observer, SurfaceHolder.Callback {
    Model model;
    private startAnimation sa;
    private Player player;

    // check if the game is playing
    volatile boolean playing = true;

    // game thread
    private Thread gameThread = null;

    // For drawing
    private Paint paint;
    private Paint taint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    // array for color
    int[] colorArray = new int[17];
    int[] colorArray2 = new int[17];
    // Constructor
    public GameView(Context context){
        super(context);
        getHolder().addCallback(this);
        // Get model instance
        model = Model.getInstance();
        model.addObserver(this);

        //init start animation
        sa = new startAnimation(context);
        player = new Player();

        //Init observers
        model.initObservers();

        //initialize draw
        surfaceHolder = getHolder();
        paint = new Paint();
        taint =new Paint();

        for (int i = 0; i < 17; i++){
            if (i == 0){
                colorArray[i] = 255;
            }else{
                colorArray[i] = colorArray[i-1] - 15;
            }
        }

        for (int i = 0; i < 17; i++){
            if (i == 0){
                colorArray2[i] = 0;
            }else{
                colorArray2[i] = colorArray2[i-1] + 15;
            }
        }
    }

    @Override
    public void run() {
        // keep looping to update the game view
        while(playing){
            update();
            draw();
            control();
        }
    }

    // update the game view
    private void update(){
        sa.update();
    }

    // update to the model
    public void update(java.util.Observable o, Object arg){}

    // drawing the game view
    private void draw(){
        // check the surface if it is valid
        if (surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();

            // see if the user disabled line mode or not
            boolean lineMode = model.getLineMode();

            // change the background according to the darkMode variable
            boolean darkMode = model.getDarkMode();
            if (darkMode){
                canvas.drawColor(Color.BLACK);
                paint.setColor(Color.WHITE);
            }else{
                canvas.drawColor(Color.WHITE);
                paint.setColor(Color.BLACK);
            }

            // thickness of the lines
            paint.setStrokeWidth(5);

            // the starting vertical line in the middle
            canvas.drawLine(model.getMiddleX(),model.getMaxY()-200-sa.getOffset500(), model.getMiddleX(), model.getMaxY()-sa.getOffset500(),paint);

            //Left and Right Initial points with offset
            canvas.drawLine(model.getInitialPointLeftX(), model.getInitialPointY(),model.getMiddleX(), model.getMaxY()-200-sa.getOffset500(), paint);
            canvas.drawLine(model.getInitialPointRightX(),model.getInitialPointY(),model.getMiddleX(),model.getMaxY()-200-sa.getOffset500(),paint);

            //Lef and Right Initial points without offset
            canvas.drawLine(model.getInitialPointLeftX(), model.getInitialPointY(),model.getMiddleX(), model.getMaxY()-sa.getOffset500(), paint);
            canvas.drawLine(model.getInitialPointRightX(),model.getInitialPointY(),model.getMiddleX(),model.getMaxY()-sa.getOffset500(),paint);


            // the color background
            if(sa.getCounter()>=8) {
                Point points[] = model.getPoints();
                int colorOffset=0;
                int colorLimit = 9;
                int counter = 0;
                for (int i = 0; i < 90; i++){
                    if (i != 9 && i != 19 && i != 29 && i != 39 && i != 49 && i != 59 && i != 69 && i!= 79 && i!=89 ) {
                        Path path = new Path();
                        path.moveTo( points[i].x, points[i].y);
                        path.lineTo( points[i + 10].x, points[i + 10].y);
                        path.lineTo( points[i + 11].x, points[i + 11].y);
                        path.lineTo( points[i + 1].x, points[i + 1].y);
                        path.lineTo( points[i].x, points[i].y);

                        if (darkMode){
                            taint.setColor(Color.argb(255,colorArray2[colorOffset],0,0));
                        }else {
                            taint.setColor(Color.argb(255, 255, colorArray[colorOffset], colorArray[colorOffset]));
                        }
                        canvas.drawPath(path, taint);
                        colorOffset++;
                        if (colorLimit == colorOffset){
                            colorLimit++;
                            counter++;
                            colorOffset = counter;
                        }
                    }
                }
            }

            // draw the background lines
            if (!lineMode) {
                for (int i = 0; i < sa.getCounter(); i++) {
                    canvas.drawLine(model.getInitialPointLeftX(), model.getInitialPointY(), model.getxNum()[i], model.getyNum()[i]-sa.getOffset500(), paint);
                    canvas.drawLine(model.getInitialPointRightX(), model.getInitialPointY(), model.getxNum2()[i], model.getyNum2()[i]-sa.getOffset500(), paint);
                }
            }

            //draw the player sprite
            if (sa.getCounter()>=8){
                // if the player is holding, it will charge the sprite
                if (player.getHold()){
                    long time = Math.abs(player.getStart()-System.currentTimeMillis());
                    Log.d("Time: ", String.valueOf(time));
                    if (time < 500){
                        if (darkMode){
                            paint.setColor(Color.WHITE);
                        }else {
                            paint.setColor(Color.BLACK);
                        }
                    }else if (time >= 500 && time < 1000){
                        paint.setColor(Color.argb(255,235,140,0));
                    }else if (time >= 1000 && time < 1500){
                        paint.setColor(Color.argb(255,224,48,30));
                    }else if (time >= 1500){
                        paint.setColor(Color.argb(255,163,32,32));
                    }
                }

                // get the four coords and draw the cell for the sprite
                Point bot = player.getBot();
                Point left = player.getLeft();
                Point right = player.getRight();
                Point top = player.getTop();
                Path playerCell = new Path();
                playerCell.moveTo( bot.x, bot.y);
                playerCell.lineTo( right.x, right.y);
                playerCell.lineTo( top.x, top.y);
                playerCell.lineTo( left.x, left.y);
                playerCell.lineTo( bot.x, bot.y);
                canvas.drawPath(playerCell, paint);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    // the touch event, when the user touches the screen
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                player.setHold(false);
                player.update();
                player.setEnd(System.currentTimeMillis());
                update();
                break;
            case MotionEvent.ACTION_DOWN:
                if (!player.getHold()){
                    player.setStart(System.currentTimeMillis());
                    player.setHold(true);
                }
                break;
        }
        return true;
    }

    private void control(){
        try{
            gameThread.sleep(17);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void pause(){
        playing = false;
        try{
            gameThread.join();
        }catch(InterruptedException e){

        }
    }

    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas c = getHolder().lockCanvas();
        draw(c);
        getHolder().unlockCanvasAndPost(c);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}
}
