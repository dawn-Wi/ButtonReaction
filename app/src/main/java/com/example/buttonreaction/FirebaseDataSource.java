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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class FirebaseDataSource {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static String documentid;

    public void tryLogin(String id, String password, DataSourceCallback<Result> callback){
        db.collection("users")
                .whereEqualTo("userId",id)
                .whereEqualTo("Password",password)
                .get()
                .addOnCompleteListener(task->{
                    if(task.isSuccessful()){
                        Log.d("datasource", "onSuccess: firestore finish");
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            documentid = document.getId();
                            Log.d("id", document.getId());
                        }
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

//    public void saverecode(String id, String recode, DataSourceCallback<Result> callback){
//        Map<String, Object> user = new HashMap<>();
//        user.put("userId",id);
//        user.put("recode", recode);
//
//
////        db.collection("users").whereEqualTo("userId", id).get()
////        db.collection("users").document("test3").update("records", )
//
//
//        db.collection("users")
//                .document("recode")
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
//같은 문서 안에 넣고 싶은데 안돼
    public void saverecode(String id, String recode, DataSourceCallback<Result> callback){
        Map<String, Object> user = new HashMap<>();
        user.put("recode", recode);


        db.collection("users")
                .document(id)
                .set(user, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
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
