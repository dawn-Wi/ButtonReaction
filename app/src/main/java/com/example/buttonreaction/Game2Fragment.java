package com.example.buttonreaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class Game2Fragment extends Fragment {

    Random random= new Random();
    RealView realview;
    Button game2_bt_start;
    Button game2_bt_rank;
    ImageView game2_iv_bluedot;
    Chronometer game2_chronometer;
    boolean returnValue = false;
    private boolean running;
    private long pauseOffset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game2, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        realview = view.findViewById(R.id.realview);
        game2_iv_bluedot = view.findViewById(R.id.game2_iv_bluedot);
        game2_bt_start = view.findViewById(R.id.game2_bt_start);
        game2_bt_rank = view.findViewById(R.id.game2_bt_rank);
        game2_chronometer = view.findViewById(R.id.game2_chronometer);
        game2_chronometer.setFormat("시간: %s");

        game2_iv_bluedot.setVisibility(game2_iv_bluedot.INVISIBLE);

        game2_bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(returnValue==false){
                    game2_iv_bluedot.setVisibility(game2_iv_bluedot.INVISIBLE);
                    returnValue=true;
                    game2_bt_start.setEnabled(true);
                    game2_bt_rank.setEnabled(true);
                }
                else if(returnValue==true){
                    game2_iv_bluedot.setVisibility(game2_iv_bluedot.VISIBLE);
                    returnValue=false;
                    game2_bt_start.setEnabled(false);
                    game2_bt_rank.setEnabled(false);
                    if(!running){
                        game2_chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
                        game2_chronometer.start();
                        running = true;
                    }
                    game2_iv_bluedot.getX(random.nextInt());
                    game2_iv_bluedot.getY(random.nextInt());
                }
            }
        });
        game2_bt_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Game2Fragment.this).navigate(R.id.action_game2Fragment_to_rankFragment);
            }
        });
    }

}