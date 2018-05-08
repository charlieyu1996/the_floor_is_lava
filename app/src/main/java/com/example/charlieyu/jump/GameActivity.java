package com.example.charlieyu.jump;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by charlieyu on 2018-05-06.
 */

public class GameActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        gameView = new GameView(this);

        setContentView(gameView);
    }

    @Override
    protected  void onPause(){
        super.onPause();
        gameView.pause();

    }

    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }

}