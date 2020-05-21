package com.darien.androidloginregistration.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.darien.androidloginregistration.repositories.LoginRepository;

public class LoginViewModel extends ViewModel {
    private LoginRepository loginRepository;
    private OnLoginRequestListener onLoginRequestListener;

    public void setOnLoginRequestListener(OnLoginRequestListener onLoginRequestListener) {
        this.onLoginRequestListener = onLoginRequestListener;
    }

    public LoginViewModel(Context context) {
        loginRepository = new LoginRepository(context);
        loginRepository.setOnLoginRequestListener(new LoginRepository.OnLoginRequestListener() {
            @Override
            public void onLoginError(String mensajeGlobal) {
                if (onLoginRequestListener != null){
                    onLoginRequestListener.onLoginError(mensajeGlobal);
                }
            }

            @Override
            public void onLoginSuccess() {
                if (onLoginRequestListener != null){
                    onLoginRequestListener.onLoginSuccess();
                }
            }

            @Override
            public void onNetWorkError(VolleyError error) {
                if (onLoginRequestListener != null){
                    onLoginRequestListener.onNetWorkError(error);
                }
            }
        });
    }

    public void requestLogin(String email, String password){
        loginRepository.requestLogin(email, password);
    }

    public interface OnLoginRequestListener{
        void onLoginError(String mensajeGlobal);
        void onLoginSuccess();
        void onNetWorkError(VolleyError error);
    }
}
