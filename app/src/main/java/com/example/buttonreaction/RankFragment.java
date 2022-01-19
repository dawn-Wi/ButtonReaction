package com.example.buttonreaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RankFragment extends Fragment {

    MainViewModel mainViewModel;
    ConstraintLayout myFrame;
    List<Record> rankList;

    public RankFragment() {
        // Required empty public constructor
    }

    public static RankFragment newInstance(String param1, String param2) {
        RankFragment fragment = new RankFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        myFrame = view.findViewById(R.id.rank_fl_mainFrame);
        rankList = mainViewModel.getRecordList();

        FragmentManager fm = getChildFragmentManager();
        Fragment myFrag = RankListFragment.newInstance(1, rankList);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(myFrame.getId(), myFrag);
        transaction.commit();

//        mainViewModel.loadTaskList();
//
//        mainViewModel.getSelectedTask().observe(getViewLifecycleOwner(), new Observer<TaskObject>() {
//            @Override
//            public void onChanged(TaskObject taskObject)
//            {
//                NavHostFragment.findNavController(TaskFragment.this).navigate(R.id.action_navigation_tasks_to_taskDetail);
//            }
//        });
//
//        mainViewModel.taskListLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean isLoaded) {
//                if(isLoaded == true){
//                    taskList = mainViewModel.getTaskList().getValue();
//                    FragmentManager fm = getChildFragmentManager();
//                    Fragment myFrag = TaskListFragment.newInstance(1, taskList);
//                    FragmentTransaction transaction = fm.beginTransaction();
//                    transaction.replace(myFrame.getId(), myFrag);
//                    transaction.commit();
//                }
//            }
//        });
    }
}