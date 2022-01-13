package com.example.buttonreaction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Random random = new Random();
    private MyView myView;
    int width, height;
    Button game_bt_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myView = findViewById(R.id.myView);
        game_bt_start = findViewById(R.id.game_bt_start);
        game_bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.setCircleX(random.nextInt(width));
                myView.setCircleY(random.nextInt(height));
                myView.setCircleR(50);
                myView.invalidate();
//                for (int i = 0; i < 10; i++) {
//                    myView.setCircleX(random.nextInt(width));
//                    myView.setCircleY(random.nextInt(height));
//                    myView.setCircleR(80);
//                    myView.invalidate();
//                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        width = myView.getWidth();
        height = myView.getHeight();
    }
}
//    public void onClick(View v) {
//        if(v.getId()==R.id.game_bt_start){
//            for(int i=0;i<10;i++){
//                myView.setCircleX(random.nextInt(width));
//                myView.setCircleY(random.nextInt(height));
//                myView.setCircleR(80);
//                myView.invalidate();
//            }
//        }
//        switch (v.getId()) {
//            case R.id.game_bt_start:
//                for(int i=0;i<10;i++){
////                    onTouchEvent(MotionEvent event){
////
////                    }
//                    myView.setCircleX(random.nextInt(width));
//                    myView.setCircleY(random.nextInt(height));
//                    myView.setCircleR(80);
//                    myView.invalidate();
//                }
//                break;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN :
//                // invalidate()을 호출하면 화면을 갱신한다.
//                myView.invalidate();
//                break;
//        }
//        return true;
//        }

