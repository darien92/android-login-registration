package com.darien.androidloginregistration.repositories;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.darien.androidloginregistration.constants.Constants;
import com.darien.androidloginregistration.models.RegisterRequestModel;
import com.darien.androidloginregistration.singletons.VolleySingleton;

import org.json.JSONObject;

public class RegisterRepository {
    private Context context;
    private OnRegisterRequestListener onRegisterRequestListener;

    public void setOnRegisterRequestListener(OnRegisterRequestListener onRegisterRequestListener) {
        this.onRegisterRequestListener = onRegisterRequestListener;
    }

    public RegisterRepository(Context context) {
        this.context = context;
    }

    public void requestRegister(String name, String lastName, String cardNumber, String registrationNumber,
                                String phone, String email, String password){
        RegisterRequestModel registerRequestModel = new RegisterRequestModel();
        registerRequestModel.setName(name);
        registerRequestModel.setLastName(lastName);
        registerRequestModel.setCard(cardNumber);
        registerRequestModel.setNumReg(registrationNumber);
        registerRequestModel.setPhone(phone);
        registerRequestModel.setEmail(email);
        registerRequestModel.setPassword(password);
        JSONObject request;
        try {
            request = new JSONObject(registerRequestModel.toString());
        }catch (Exception e){
            e.printStackTrace();
            request = new JSONObject();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.REGISTER_URL,
                request, response -> {
            Log.d("a" , response.toString());
            if (onRegisterRequestListener != null) {
                try {
                    String globalMessage = response.getString("mensajeGlobal");
                    if (globalMessage.contains(Constants.LOGIN_SUCCESS_MSG)){
                        onRegisterRequestListener.onSuccess();
                    }else {
                        onRegisterRequestListener.onRegisterError(globalMessage);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
                error.getMessage();
                if (onRegisterRequestListener != null){
                    onRegisterRequestListener.onNetworkError(error);
                }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
    public interface OnRegisterRequestListener{
        void onRegisterError(String message);
        void onSuccess();
        void onNetworkError(VolleyError error);
    }
}
