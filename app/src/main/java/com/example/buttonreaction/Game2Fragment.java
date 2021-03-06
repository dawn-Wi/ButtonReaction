package com.example.buttonreaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import java.util.Random;

public class Game2Fragment extends Fragment {
    MainViewModel mainViewModel;
    Random random = new Random();
    RealView realview;
    Button game2_bt_start;
    Button game2_bt_rank;
    ImageView game2_iv_bluedot;
    Chronometer game2_chronometer;
    boolean returnValue = false;
    private boolean running = false;
    private long pauseOffset;
    int[] XValue = new int[11];
    int[] YValue = new int[11];
    int clicknum = 0;
    String documentid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        realview = view.findViewById(R.id.realview);
        game2_iv_bluedot = view.findViewById(R.id.game2_iv_bluedot);
        game2_bt_start = view.findViewById(R.id.game2_bt_start);
        game2_bt_rank = view.findViewById(R.id.game2_bt_rank);
        game2_chronometer = view.findViewById(R.id.game2_chronometer);
        game2_chronometer.setFormat("%s");

        game2_iv_bluedot.setVisibility(game2_iv_bluedot.INVISIBLE);

        game2_bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: What's this?
                if (returnValue == true) {
                    game2_iv_bluedot.setVisibility(game2_iv_bluedot.INVISIBLE);
                    returnValue = true;
                    game2_bt_start.setEnabled(true);
                    game2_bt_rank.setEnabled(true);
                } else if (returnValue == false) {
                    for (int i = 0; i < 11; i++) {
                        //TODO: Different screen sizes?
                        XValue[i] = random.nextInt(900) + 60;
                        YValue[i] = random.nextInt(1050) + 150;
                    }
                    game2_iv_bluedot.setX(XValue[0]);
                    game2_iv_bluedot.setY(YValue[0]);
                    clicknum++;
                    game2_iv_bluedot.setVisibility(game2_iv_bluedot.VISIBLE);
                    game2_iv_bluedot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            game2_iv_bluedot.setVisibility(game2_iv_bluedot.INVISIBLE);
                            game2_iv_bluedot.setX(XValue[clicknum]);
                            game2_iv_bluedot.setY(YValue[clicknum]);
                            game2_iv_bluedot.setVisibility(game2_iv_bluedot.VISIBLE);
                            clicknum++;
                            Log.d("??? ??? ????????? ??????????", "onClick: " + clicknum);
                            if (clicknum == 11) {
                                //TODO: Use ViewModel to re-instantiate base state
                                game2_iv_bluedot.setVisibility(game2_iv_bluedot.INVISIBLE);
                                game2_chronometer.stop();
                                clicknum = 0;
                                game2_bt_rank.setEnabled(true);
                                game2_bt_start.setEnabled(true);
                                running = false;
                                mainViewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        if (s != null) {
                                            documentid = s;
                                        }
                                    }
                                });
//                                          Log.d("??????", "onClick: " + documentid + "??????????????????"+ game2_chronometer.getText().toString());
//                                            mainViewModel.savemyrecode(documentid, game2_chronometer.getText().toString());
                                mainViewModel.sendRecord(documentid, NumberParser.parseChronoTimeToSeconds(game2_chronometer.getText().toString()));
                            }
                        }
                    });
                    returnValue = false;
                    game2_bt_start.setEnabled(false);
                    game2_bt_rank.setEnabled(false);

                    //timer ???????????????
                    if (!running) {
                        game2_chronometer.setBase(SystemClock.elapsedRealtime());
                        game2_chronometer.start();
                        running = true;
                    }
                }
            }
        });
        game2_bt_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.loadRecords();
                mainViewModel.recordsLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean loaded) {
                        if (loaded == true) {
                            NavHostFragment.findNavController(Game2Fragment.this).navigate(R.id.action_game2Fragment_to_rankFragment);
                        }
                    }
                });
            }
        });

    }

}