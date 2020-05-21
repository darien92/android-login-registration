package com.darien.androidloginregistration.repositories;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.darien.androidloginregistration.constants.Constants;
import com.darien.androidloginregistration.models.LoginRequestModel;
import com.darien.androidloginregistration.singletons.VolleySingleton;

import org.json.JSONObject;

public class LoginRepository {
    private Context context;
    private OnLoginRequestListener onLoginRequestListener;

    public void setOnLoginRequestListener(OnLoginRequestListener onLoginRequestListener) {
        this.onLoginRequestListener = onLoginRequestListener;
    }

    public LoginRepository(Context context) {
        this.context = context;
    }

    public void requestLogin(String email, String password){
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setEmail(email);
        loginRequestModel.setPassword(password);
        loginRequestModel.setChannel("2");
        JSONObject request;
        try {
            request = new JSONObject(loginRequestModel.toString());
        }catch (Exception e){
            e.printStackTrace();
            request = new JSONObject();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.LOGIN_URL,
                request,  response -> {
                        if (onLoginRequestListener != null){
                            try{
                                String globalMessage = response.getString("mensajeGlobal");
                                if (globalMessage.contains(Constants.LOGIN_SUCCESS_MSG)){
                                    onLoginRequestListener.onLoginSuccess();
                                }else {
                                    onLoginRequestListener.onLoginError(globalMessage);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                },
                error -> {
                    error.getMessage();
                    if (onLoginRequestListener != null){
                        onLoginRequestListener.onNetWorkError(error);
            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public interface OnLoginRequestListener{
        void onLoginError(String mensajeGlobal);
        void onLoginSuccess();
        void onNetWorkError(VolleyError error);
    }
}
