package com.example.buttonreaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

public class SignupFragment extends Fragment {
    MainViewModel mainViewModel;
    EditText signup_et_name;
    EditText signup_et_phone;
    EditText signup_et_email;
    EditText signup_et_password;
    Button signup_bt_go;

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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        signup_et_name = view.findViewById(R.id.signup_et_name);
        signup_et_phone = view.findViewById(R.id.signup_et_phone);
        signup_et_email = view.findViewById(R.id.signup_et_email);
        signup_et_password =view.findViewById(R.id.signup_et_password);
        signup_bt_go = view.findViewById(R.id.signup_bt_go);

        signup_bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean returnValue = false;
                String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(signup_et_email.getText().toString());
                if(m.matches()) {
                    returnValue = true;
                }
                if(returnValue == false) {
                    Toast.makeText(getActivity().getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                }
                //password에 4글자 이상인지
                else if(signup_et_password.getText().toString().trim().length() < 4) {
                    //실패
                    Toast.makeText(getActivity().getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                }
                else{
                    mainViewModel.tryRegister(signup_et_email.getText().toString(),signup_et_password.getText().toString(), signup_et_name.getText().toString());
                    NavHostFragment.findNavController(SignupFragment.this).navigate(R.id.action_signupFragment_to_loginFragment);
                }
            }
        });
    }
}