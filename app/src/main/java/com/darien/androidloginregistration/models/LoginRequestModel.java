package com.darien.androidloginregistration.models;

import com.google.gson.annotations.SerializedName;

public class LoginRequestModel{

	@SerializedName("password")
	private String password;

	@SerializedName("channel")
	private String channel;

	@SerializedName("email")
	private String email;

	public String getPassword(){
		return password;
	}

	public String getChannel(){
		return channel;
	}

	public String getEmail(){
		return email;
	}
}