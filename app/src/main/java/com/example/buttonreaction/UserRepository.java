package com.example.buttonreaction;

import android.util.Log;

import com.example.buttonreaction.FirebaseDataSource;

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

//    public void saverecode(final String recode,  final FirebaseDataSource.DataSourceCallback<String> callback) {
//        firebaseDataSource.saverecode(recode, result -> {
//            if(result instanceof Result.Success)
//                callback.onComplete("Success");
//            else
//                callback.onComplete(((Result.Error) result).getError().getMessage());
//        });
//    }
//같은 문서 안에 넣고싶ㅇ느데 안돼
    public void saverecode(final String id, final String recode, final FirebaseDataSource.DataSourceCallback<String> callback) {
        firebaseDataSource.saverecode(id,recode, result -> {
            if(result instanceof Result.Success)
                callback.onComplete("Success");
            else
                callback.onComplete(((Result.Error) result).getError().getMessage());
        });
    }

    public void setDataSource(FirebaseDataSource ds)
    {
        this.firebaseDataSource = ds;
    }
    public interface UserRepositoryCallback<T>
    {
        void onComplete(String result);
    }
}
