package com.example.buttonreaction;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class FirebaseDataSource {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void tryLogin(String id, String password, DataSourceCallback<Result> callback){
        db.collection("users")
                .whereEqualTo("userId",id)
                .whereEqualTo("Password",password)
                .get()
                .addOnCompleteListener(task->{
                    if(task.isSuccessful()){
                        Log.d("datasource", "onSuccess: firestore finish");
                        callback.onComplete(new Result.Success<String>("Success"));
                    }
                    else{
                        Log.d("datasource", "onSuccess: firestore not finish");
                        callback.onComplete(new Result.Error(task.getException()));
                    }
                });
    }

    public void tryRegister(String id, String password, String displayname, DataSourceCallback<Result> callback){
        Map<String, Object> user = new HashMap<>();
        user.put("userId", id);
        user.put("Password", password);
        user.put("Name", displayname);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("datasource", "onSuccess: firestore finish");
                        callback.onComplete(new Result.Success<String>("Success"));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("datasource", "onSuccess: firestore not finish");
                        callback.onComplete(new Result.Error(new Exception("Failed")));
                    }
                });

    }

    public interface DataSourceCallback<T>{
        void onComplete(T result);
    }

}
