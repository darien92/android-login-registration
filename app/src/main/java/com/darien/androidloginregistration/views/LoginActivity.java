package com.darien.androidloginregistration.views;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.darien.androidloginregistration.R;
import com.darien.androidloginregistration.UseCases.Validator;
import com.darien.androidloginregistration.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    TextView tvGoToRegister;
    Button btnLogin;
    ProgressBar pbLoading;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initVars();
    }

    private void initVars(){
        inputEmail = findViewById(R.id.input_email_login);
        inputPassword = findViewById(R.id.input_password_login);
        tvGoToRegister = findViewById(R.id.tv_go_register);
        btnLogin = findViewById(R.id.btn_login);
        pbLoading = findViewById(R.id.login_progress_bar);
        loginViewModel = new LoginViewModel(getApplicationContext());
        loginViewModel.setOnLoginRequestListener(new LoginViewModel.OnLoginRequestListener() {
            @Override
            public void onLoginError(String mensajeGlobal) {
                showModal(mensajeGlobal);
                hideLoading();
            }

            @Override
            public void onLoginSuccess() {
                Intent goToMainIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(goToMainIntent);
                hideLoading();
                finish();
            }

            @Override
            public void onNetWorkError(VolleyError error) {
                showModal(getResources().getString(R.string.something_went_wrong));
                hideLoading();
            }
        });
        tvGoToRegister.setOnClickListener(view -> {
            Intent goToRegisterIntent = new Intent(this, RegisterActivity.class);
            startActivity(goToRegisterIntent);
        });
        btnLogin.setOnClickListener(view -> {
            if (!Validator.isEmail(inputEmail.getText().toString())){
                showModal(getResources().getString(R.string.error_email));
            }else if (!Validator.isPassword(inputPassword.getText().toString())){
                showModal(getResources().getString(R.string.error_password));
            }else{
                //do Login
                showLoading();
                loginViewModel.requestLogin(inputEmail.getText().toString(), inputPassword.getText().toString());
            }
        });
    }

    public void showModal(String message){
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(getResources().getString(R.string.alert))
                .setCancelable(true)
                .setPositiveButton(getResources().getString(R.string.accept),(dialog, id) -> {
                    dialog.dismiss();
                });
        try {
            alert = builder.create();
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLoading(){
        btnLogin.setVisibility(View.INVISIBLE);
        pbLoading.setVisibility(View.VISIBLE);
        pbLoading.animate();
    }

    private void hideLoading(){
        btnLogin.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.INVISIBLE);
        pbLoading.clearAnimation();
    }
}
