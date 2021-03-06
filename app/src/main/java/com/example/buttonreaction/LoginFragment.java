package com.example.buttonreaction;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment {
    MainViewModel mainViewModel;
    EditText login_et_email;
    EditText login_et_password;
    Button login_bt_login;
    Button login_bt_signup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        FirebaseDataSource ds = new FirebaseDataSource();
        UserRepository.getInstance().setDataSource(ds);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        login_et_email = view.findViewById(R.id.login_et_email);
        login_et_password =view.findViewById(R.id.login_et_password);
        login_bt_login = view.findViewById(R.id.login_bt_login);
        login_bt_signup = view.findViewById(R.id.login_bt_signup);

        mainViewModel.getDoingWork().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isWorking) {
                if(isWorking==true){
                    login_bt_login.setEnabled(false);
                    login_bt_signup.setEnabled(false);
                    login_et_email.setEnabled(false);
                    login_et_password.setEnabled(false);
                }else{
                    login_bt_login.setEnabled(true);
                    login_bt_signup.setEnabled(true);
                    login_et_email.setEnabled(true);
                    login_et_password.setEnabled(true);
                    login_et_email.setText(null);
                    login_et_password.setText(null);
                    //Toast.makeText(getActivity().getApplicationContext(), "Login failed Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });


        mainViewModel.isLoggedIn().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoggedIn) {
                if(isLoggedIn==true){
                    NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_game2Fragment);
                    mainViewModel.setName(login_et_email.getText().toString());
                }
            }
        });

        login_bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("login","signin bt push");
                //ID??? @???.??? ?????????
                boolean returnValue = false;
                String regex =  "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(login_et_email.getText().toString());
                if(m.matches()){
                    returnValue = true;
                }
                if(returnValue == false){
                    Toast.makeText(getActivity().getApplicationContext(), "fail",Toast.LENGTH_SHORT).show();
                }
                //password??? 4?????? ????????????
                else if(login_et_password.getText().toString().trim().length()<4){
                    //??????
                    Toast.makeText(getActivity().getApplicationContext(), "fail",Toast.LENGTH_SHORT).show();
                }
                else{
                    mainViewModel.tryLogin(login_et_email.getText().toString(),login_et_password.getText().toString());
                }
            }
        });


        login_bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_signupFragment);
            }
        });
    }
}