package com.example.buttonreaction;

import android.util.Log;

import com.example.buttonreaction.UserRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public void savemyrecode(String id, String recode){
        userRepository.savemyrecode(id,recode, result->{
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

    //TODO: naming
    public void totalrecodes(String id, int recode){
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
        //TODO: How to structure data?
        userRepository.getRecords(result -> {
            if(result instanceof Result.Success){
                //List<Record> resultList = ((Result.Success<List<Record>>)result).getData();
               recordList = ((Result.Success<List<Record>>)result).getData(); //선물 포장 뜯은거

                Map<String, Integer> namesOccurrencesMap = new HashMap<>();
                for(int i=0; i<recordList.size(); i++)
                {
                    String userId = recordList.get(i).getUserId();
                    if(namesOccurrencesMap.containsKey(userId))
                    {
                        int currNumber = namesOccurrencesMap.get(userId);
                        namesOccurrencesMap.put(userId,currNumber+1);
                    }
                    else
                    {
                        namesOccurrencesMap.put(userId, 1);
                    }
                }

                Map<String,Integer> totalTimesMap = new HashMap<>();
                for(int i=0;i<recordList.size();i++){
                    String userId = recordList.get(i).getUserId();
                    if(totalTimesMap.containsKey(userId)){
                        int currTotal=totalTimesMap.get(userId);
                        totalTimesMap.put(userId,currTotal+recordList.get(i).getRecord());
                    }
                    else {
                        totalTimesMap.put(userId,recordList.get(i).getRecord());
                    }
                }
                List<Record> tempList = new ArrayList<>();

                for(Map.Entry<String, Integer> entry : totalTimesMap.entrySet())
                {
                    String userId = entry.getKey();
                    int totalTime = entry.getValue();
                    int numTimes = namesOccurrencesMap.get(userId);
                    int avgTime = totalTime/numTimes;
                    Record toAdd = new Record(avgTime,userId);
                    tempList.add(toAdd);
                }
                this.recordList=tempList;

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
