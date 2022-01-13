package com.example.buttonreaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Random random = new Random();
    private MyView myView;
    int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        myView = findViewById(R.id.m);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        width = myView.getWidth();
        height = myView.getHeight();
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                myView.setCircleX(random.nextInt(width));
                myView.setCircleY(random.nextInt(height));
                myView.setCircleR((random.nextInt(3) + 1) * 100);
                myView.invalidate();
                break;
        }
    }
}