package com.example.buttonreaction;

import android.util.Log;

import com.example.buttonreaction.UserRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();
    private MutableLiveData<Boolean> loggedIn = new MutableLiveData<>(false);

    public void tryRegister(String id, String password, String displayname){
        userRepository.tryRegister(id, password, displayname, result->{
            if(result.equals("Success"))
            {
                Log.d("DEBUG", "Register Success");
            }
            else
            {
                Log.d("DEBUG", "Register Failed");
            }
        });
    }
    public void tryLogin(String id, String password) {
        userRepository.tryLogin(id, password, result ->{
            if(result.equals("Success")){
                Log.d("viewmodel", "tryLogin: success");
                loggedIn.postValue(true);
            }
            else{
                Log.d("viewmodel", "tryLogin: fail");
            }
        });
    }
    public LiveData<Boolean> isLoggedIn(){return loggedIn;}
}
