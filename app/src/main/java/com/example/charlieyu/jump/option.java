package com.example.charlieyu.jump;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;
import java.lang.String;
import java.util.Observer;

/**
 * Created by charlieyu on 2018-05-05.
 */

public class option extends AppCompatActivity implements Observer {
    // Private vars
    Model model;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set content view
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.options);

        // get model instance
        model = Model.getInstance();
        model.addObserver(this);

        // the dark mode switch
        final Switch darkModeSwitch = findViewById(R.id.darkModeSwitch);
        boolean check = model.getDarkMode();
        darkModeSwitch.setChecked(check);

        // if the darkmode switch is pressed
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    model.setDarkMode(true);
                }else{
                    model.setDarkMode(false);
                }
            }
        });

        // the line switch
        final Switch lineSwitch = findViewById(R.id.lineSwitch);
        boolean checkLine = model.getLineMode();
        lineSwitch.setChecked(checkLine);

        // if the line switch is pressed
        lineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    model.setLineMode(true);
                }else{
                    model.setLineMode(false);
                }
            }
        });

        // the back button
        final ImageButton backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.layout.fade_in, R.layout.fade_out);
            }
        });

        //Init observers
        model.initObservers();
    }

    @Override
    public void update(Observable o, Object arg){}
}
