package com.example.buttonreaction;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class FirebaseDataSource {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void tryLogin(String id, String password, DataSourceCallback<Result> callback) {
        db.collection("users")
                .whereEqualTo("userId", id)
                .whereEqualTo("Password", password)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("datasource", "onSuccess: firestore finish");
                        callback.onComplete(new Result.Success<String>("Success"));
                    } else {
                        Log.d("datasource", "onSuccess: firestore not finish");
                        callback.onComplete(new Result.Error(task.getException()));
                    }
                });
    }

    public void tryRegister(String id, String password, String displayname, DataSourceCallback<Result> callback) {
        Map<String, Object> user = new HashMap<>();
        user.put("userId", id);
        user.put("Password", password);
        user.put("Name", displayname);

        db.collection("users")
                .document(id)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("datasource", "onSuccess: firestore finish");
                        callback.onComplete(new Result.Success<String>("Success"));
                    }
                })
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("datasource", "onSuccess: firestore finish");
//                        callback.onComplete(new Result.Success<String>("Success"));
//                    }
//                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("datasource", "onSuccess: firestore not finish");
                        callback.onComplete(new Result.Error(new Exception("Failed")));
                    }
                });

    }

//    public void savemyrecode(String id, String recode, DataSourceCallback<Result> callback){
//        Map<String, Object> user = new HashMap<>();
//        user.put("recode", recode);
//
//
//        db.collection("users")
//                .document(id)
//                .set(user, SetOptions.merge())
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d("datasource", "onSuccess: firestore finish");
//                        callback.onComplete(new Result.Success<String>("Success"));
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("datasource", "onSuccess: firestore not finish");
//                        callback.onComplete(new Result.Error(new Exception("Failed")));
//                    }
//                });
//
//    }

    public void totalrecodes(String id, String recode, DataSourceCallback<Result> callback) {
//        Map<String, Object> user = new HashMap<>();
        ArrayList<Map<String, Object>> user = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userId", id);
        map.put("record", recode);

        db.collection("totalrecords")
                .add(map);
    }

    public void getRecords(DataSourceCallback<Result> callback) {
        List<Record> toReturn = new ArrayList<>();
        db.collection("totalrecords")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<DocumentSnapshot> snaps = task.getResult().getDocuments();
                        for(int i=0;i<snaps.size();i++){
                            Record toAdd = new Record( snaps.get(i).getString("record"), snaps.get(i).getString("userId"));
                            toReturn.add(toAdd);
                        }
                        Log.d("datasource", "onSuccess: firestore finish");
                        callback.onComplete(new Result.Success<List<Record>>(toReturn));

                    } else {
                        Log.d("datasource", "onSuccess: firestore not finish");
                        callback.onComplete(new Result.Error(task.getException()));
                    }
                });
    }

    public interface DataSourceCallback<T> {
        void onComplete(T result);
    }

}
