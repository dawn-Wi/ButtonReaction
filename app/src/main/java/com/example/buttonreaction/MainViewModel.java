package com.example.buttonreaction;

import android.util.Log;

import com.example.buttonreaction.UserRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();
    private MutableLiveData<Boolean> loggedIn = new MutableLiveData<>(false);
    private MutableLiveData<String> loggedname = new MutableLiveData<>();
    private MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();
    private MutableLiveData<Boolean> doingWork = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> recordsLoaded = new MutableLiveData<>(false);
    private List<Record> recordList;

    public void tryRegister(String id, String password, String displayname){
        doingWork.setValue(true);
        userRepository.tryRegister(id, password, displayname, result->{
            if(result.equals("Success"))
            {
                registerSuccess.postValue(true);
                Log.d("DEBUG", "Register Success");
            }
            else
            {
                registerSuccess.postValue(false);
                Log.d("DEBUG", "Register Failed");
            }
            doingWork.postValue(false);
        });
    }
    public void tryLogin(String id, String password) {
        doingWork.setValue(true);
        userRepository.tryLogin(id, password, result ->{
            if(result.equals("Success"))
                loggedIn.postValue(true);
            doingWork.postValue(false);
        });
    }

//    public void savemyrecode(String id, String recode){
//        userRepository.savemyrecode(id,recode, result->{
//            if(result.equals("Success"))
//            {
//                Log.d("DEBUG", "Register Success");
//            }
//            else
//            {
//                Log.d("DEBUG", "Register Failed");
//            }
//        });
//    }

    public void totalrecodes(String id, String recode){
        userRepository.totalrecodes(id,recode, result->{
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

    public void loadRecords()
    {
        userRepository.getRecords(result -> {
            if(result instanceof Result.Success){
                recordList = ((Result.Success<List<Record>>)result).getData();
                recordsLoaded.setValue(true);
            }
        });
    }


    public LiveData<String> getName(){
        return loggedname;
    }
    public void setName(String name){
        loggedname.setValue(name);
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public LiveData<Boolean> getDoingWork(){return doingWork;}
    public LiveData<Boolean> isLoggedIn(){return loggedIn;}
    public LiveData<Boolean> registerSuccess(){return registerSuccess;}
    public LiveData<Boolean> recordsLoaded(){return recordsLoaded;}
}
