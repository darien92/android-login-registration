package com.darien.androidloginregistration.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.darien.androidloginregistration.repositories.RegisterRepository;

public class RegisterViewModel extends ViewModel {
    private RegisterRepository registerRepository;
    private OnRegisterRequestListener onRegisterRequestListener;

    public void setOnRegisterRequestListener(OnRegisterRequestListener onRegisterRequestListener) {
        this.onRegisterRequestListener = onRegisterRequestListener;
    }

    public RegisterViewModel(Context context) {
        registerRepository = new RegisterRepository(context);
        registerRepository.setOnRegisterRequestListener(new RegisterRepository.OnRegisterRequestListener() {
            @Override
            public void onRegisterError(String message) {
                if (onRegisterRequestListener != null){
                    onRegisterRequestListener.onRegisterError(message);
                }
            }

            @Override
            public void onSuccess() {
                if (onRegisterRequestListener != null){
                    onRegisterRequestListener.onSuccess();
                }
            }

            @Override
            public void onNetworkError(VolleyError error) {
                if (onRegisterRequestListener != null){
                    onRegisterRequestListener.onNetworkError(error);
                }
            }
        });
    }

    public void requestRegister(String name, String lastName, String cardNumber, String registrationNumber,
                                String phone, String email, String password){
        registerRepository.requestRegister(name, lastName, cardNumber, registrationNumber, phone, email, password);
    }

    public interface OnRegisterRequestListener{
        void onRegisterError(String message);
        void onSuccess();
        void onNetworkError(VolleyError error);
    }
}
