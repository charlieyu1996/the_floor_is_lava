package com.example.charlieyu.jump;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements Observer {
    // Private Variables
    background backGround;
     GameView gameView;
    dynamicBackground dB;
    Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        gameView = new GameView(this);
        dB = new dynamicBackground(this);
        setContentView(R.layout.activity_main);

        addContentView(dB,  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //dB.update();

        //gameView = new GameView(this);
//        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT,
//                        LinearLayout.LayoutParams.MATCH_PARENT);
//        addContentView(gameView,linearLayoutParams);

//        LayoutInflater inflater = getLayoutInflater();
//        getWindow().addContentView(inflater.inflate(R.layout.options, null), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));

      //  addContentView(gameView,new ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT ));


//            FrameLayout rootLayout = findViewById(android.R.id.content);
//        View.inflate(this, R.layout.activity_main, rootLayout);
//



        // Get model instance
        model = Model.getInstance();
        model.addObserver(this);

        // obtain the phone boundaries and save it
        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        int maxX = mdispSize.x;
        int maxY = mdispSize.y;
        model.setMax(maxX, maxY);

        // draw the background
//        backGround = new background(this);
//        backGround.setBackgroundColor(Color.WHITE);
//        //setContentView(backGround);
//        // draw the GameView
//        gameView = new GameView(this);
//        gameView.setBackgroundColor(Color.WHITE);




        //if the start button is pressed
        final ImageButton startButton = findViewById(R.id.buttonPlay);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        //if the option button is pressed
        final ImageButton optionButton = findViewById(R.id.buttonOption);
        optionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), option.class);
                startActivity(intent);
            }

        });

        model.initObservers();

    }


    @Override
    public void update(Observable o, Object arg){
        Log.d("TEST", "updated");
    }
}
