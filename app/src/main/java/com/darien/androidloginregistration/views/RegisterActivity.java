package com.darien.androidloginregistration.views;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.darien.androidloginregistration.R;
import com.darien.androidloginregistration.UseCases.Validator;
import com.darien.androidloginregistration.viewmodels.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputName, inputLastName, inputCardNumber, inputRegistrationNumber, inputPhone, inputEmail,
            inputPassword, inputConfirmPassword;
    private Button btnRegister;
    private ProgressBar pbLoading;

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initVars();
    }

    private void initVars(){
        inputName = findViewById(R.id.input_name_register);
        inputLastName = findViewById(R.id.input_last_name_register);
        inputCardNumber = findViewById(R.id.input_card_number_register);
        inputRegistrationNumber = findViewById(R.id.input_registration_number_register);
        inputPhone = findViewById(R.id.input_phone_number_register);
        inputEmail = findViewById(R.id.input_email_register);
        inputPassword = findViewById(R.id.input_password_register);
        inputConfirmPassword = findViewById(R.id.input_confirm_password_register);
        pbLoading = findViewById(R.id.register_progress_bar);
        btnRegister = findViewById(R.id.btn_register);
        registerViewModel = new RegisterViewModel(getApplicationContext());

        registerViewModel.setOnRegisterRequestListener(new RegisterViewModel.OnRegisterRequestListener() {
            @Override
            public void onRegisterError(String message) {
                showModal(message);
                hideLoading();
            }

            @Override
            public void onSuccess() {
                Intent goToMainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(goToMainIntent);
                hideLoading();
                finish();
            }

            @Override
            public void onNetworkError(VolleyError error) {
                showModal(getResources().getString(R.string.something_went_wrong));
                hideLoading();
            }
        });

        btnRegister.setOnClickListener(view -> {
            if (!Validator.isName(inputName.getText().toString() + inputLastName.getText().toString())){
                showModal(getResources().getString(R.string.error_name));
            }else if (!Validator.isCardNumber(inputCardNumber.getText().toString())){
                showModal(getResources().getString(R.string.error_card_number));
            }else if (!Validator.isRegNumber(inputRegistrationNumber.getText().toString())){
                showModal(getResources().getString(R.string.error_registration_number));
            }else if (!Validator.isPhoneNumber(inputPhone.getText().toString())){
                showModal(getResources().getString(R.string.error_phone_number));
            }else if (!Validator.isEmail(inputEmail.getText().toString())){
                showModal(getResources().getString(R.string.error_email));
            }else if (!Validator.isPassword(inputPassword.getText().toString())){
                showModal(getResources().getString(R.string.error_password));
            }else if (!inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString())){
                showModal(getResources().getString(R.string.passwords_dont_match));
            }else {
                showLoading();
                //do request
                registerViewModel.requestRegister(inputName.getText().toString(), inputLastName.getText().toString(),
                        inputCardNumber.getText().toString(), inputRegistrationNumber.getText().toString(),
                        inputPhone.getText().toString(), inputEmail.getText().toString(), inputPassword.getText().toString());
            }
        });
    }

    private void showLoading(){
        btnRegister.setVisibility(View.INVISIBLE);
        pbLoading.setVisibility(View.VISIBLE);
        pbLoading.animate();
    }

    private void hideLoading(){
        btnRegister.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.INVISIBLE);
        pbLoading.clearAnimation();
    }


    public void showModal(String message){
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(getResources().getString(R.string.alert))
                .setCancelable(true)
                .setPositiveButton(getResources().getString(R.string.accept), (dialog,  id) -> {
                    dialog.dismiss();
                });
        try {
            alert = builder.create();
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
