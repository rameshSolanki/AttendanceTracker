package com.fridayapp.attendancetracker.Fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fridayapp.attendancetracker.Activity.MainActivity;
import com.fridayapp.attendancetracker.Extras.InternetConnection;
import com.fridayapp.attendancetracker.Model.User;
import com.fridayapp.attendancetracker.R;
import com.fridayapp.attendancetracker.Services.MyInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private MyInterface loginFromActivityListener;
    private TextView registerTV;

    private EditText emailInput, passwordInput;
    private Button loginBtn;

    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        // for login
        emailInput = view.findViewById(R.id.emailInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        loginBtn = view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        // for register
        registerTV = view.findViewById(R.id.registerTV);
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFromActivityListener.register();
            }
        });
        return view;
    } //ending onCreateView

    private void loginUser() {
        if (InternetConnection.checkConnection(getContext())) {
            String Email = emailInput.getText().toString();
            String Password = passwordInput.getText().toString();

            if (TextUtils.isEmpty(Email)) {
                MainActivity.appPreference.showToast("Your email is required.");
                emailInput.setError("Email cannot be Blank");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                MainActivity.appPreference.showToast("Invalid email");
                emailInput.setError("Email cannot be Blank");
            } else if (TextUtils.isEmpty(Password)) {
                MainActivity.appPreference.showToast("Password required");
                passwordInput.setError("Email cannot be Blank");
            } else if (Password.length() < 6) {
                MainActivity.appPreference.showToast("Password  may be at least 6 characters long.");
                passwordInput.setError("Email cannot be Blank");
            } else {
                final ProgressDialog dialog;
                dialog = new ProgressDialog(getContext());
                dialog.setTitle("Login");
                dialog.setMessage("Please Wait...");
                dialog.show();
                Call<User> userCall = MainActivity.serviceApi.doLogin(Email, Password);
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        dialog.dismiss();
                        if (response.body().getResponse().equals("data")) {
                            MainActivity.appPreference.setLoginStatus(true); // set login status in sharedPreference
                            loginFromActivityListener.login(
                                    response.body().getName(),
                                    response.body().getEmail(),
                                    response.body().getImage(),
                                    response.body().getCreated_at());
                        } else if (response.body().getResponse().equals("login_failed")) {
                            MainActivity.appPreference.showToast("Error. Login Failed");
                            emailInput.setText("");
                            passwordInput.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        dialog.dismiss();
                    }
                });
            }
        }
        else {
                Toast.makeText(getContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
            }
    } //ending loginUser()

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFromActivityListener = (MyInterface) activity;
    }

}
