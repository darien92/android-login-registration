package com.darien.androidloginregistration.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.darien.androidloginregistration.R;
import com.darien.androidloginregistration.UseCases.Validator;

public class RegisterActivity extends AppCompatActivity {
    EditText inputName, inputLastName, inputCardNumber, inputRegistrationNumber, inputPhone, inputEmail,
            inputPassword, inputConfirmPassword;
    Button btnRegister;
    ProgressBar pbLoading;

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
        btnRegister = findViewById(R.id.btn_register);

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
                .setPositiveButton(getResources().getString(R.string.accept),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
        try {
            alert = builder.create();
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
