package com.example.charlieyu.jump;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by charlieyu on 2018-05-06.
 */

public class GameActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // set the window to no title, full-screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // the frame layout that includes everything
        FrameLayout game = new FrameLayout(this);

        // the game view that draws the lines
        gameView = new GameView(this);

        // the widgets that include the buttons
        LinearLayout gameWidgets = new LinearLayout(this);

        // the image button for pause
        ImageButton pauseButton = new ImageButton(this);
        pauseButton.setImageResource(R.drawable.pause_button);
        pauseButton.setBackgroundColor(Color.TRANSPARENT);
        pauseButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200,200);
        pauseButton.setLayoutParams(params);

        // add everything to game
        gameWidgets.addView(pauseButton);
        game.addView(gameView);
        game.addView(gameWidgets);
        setContentView(game);

        // what does the pause button do
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onPause();
                Intent intent = new Intent(v.getContext(), option.class);
                startActivity(intent);
                overridePendingTransition(R.layout.fade_in, R.layout.fade_out);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
}
