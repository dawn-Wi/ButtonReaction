package com.example.buttonreaction;

import android.util.Log;

import com.example.buttonreaction.FirebaseDataSource;

import java.util.List;

public class UserRepository {
    private static volatile UserRepository INSTANCE = new UserRepository();

    public static UserRepository getInstance()
    {
        return INSTANCE;
    }
    private FirebaseDataSource firebaseDataSource;

    public void tryLogin(final String id, final String password, final FirebaseDataSource.DataSourceCallback<String> callback) {
        firebaseDataSource.tryLogin(id, password, result -> {
            if(result instanceof Result.Success) {
                callback.onComplete("Success");
                Log.d("repository", "tryLogin: success");
            }
            else{
                callback.onComplete(((Result.Error) result).getError().getMessage());
                Log.d("repository", "tryLogin: fail");
            }
        });
    }

    public void tryRegister(final String id, final String password, final String displayname, final FirebaseDataSource.DataSourceCallback<String> callback) {
        firebaseDataSource.tryRegister(id, password, displayname, result -> {
            if(result instanceof Result.Success)
                callback.onComplete("Success");
            else
                callback.onComplete(((Result.Error) result).getError().getMessage());
        });
    }


//    public void savemyrecode(final String id, final String recode, final FirebaseDataSource.DataSourceCallback<String> callback) {
//        firebaseDataSource.savemyrecode(id,recode, result -> {
//            if(result instanceof Result.Success)
//                callback.onComplete("Success");
//            else
//                callback.onComplete(((Result.Error) result).getError().getMessage());
//        });
//    }

    public void totalrecodes(final String id, final float recode, final FirebaseDataSource.DataSourceCallback<String> callback) {
        firebaseDataSource.totalrecodes(id,recode, result -> {
            if(result instanceof Result.Success)
                callback.onComplete("Success");
            else
                callback.onComplete(((Result.Error) result).getError().getMessage());
        });
    }

    public void getRecords(final UserRepositoryCallback callback){
        firebaseDataSource.getRecords(result -> {
            if(result instanceof Result.Success)
                callback.onComplete(result);
        });
    }

    public void setDataSource(FirebaseDataSource ds)
    {
        this.firebaseDataSource = ds;
    }
    public interface UserRepositoryCallback<T>
    {
        void onComplete(Result result);
    }
}
