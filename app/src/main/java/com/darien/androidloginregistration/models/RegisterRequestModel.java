package com.darien.androidloginregistration.models;

import com.google.gson.annotations.SerializedName;

public class RegisterRequestModel{

	@SerializedName("repitaPassword")
	private String repitaPassword;

	@SerializedName("idUser")
	private int idUser;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("password")
	private String password;

	@SerializedName("phone")
	private String phone;

	@SerializedName("numReg")
	private String numReg;

	@SerializedName("name")
	private String name;

	@SerializedName("card")
	private String card;

	@SerializedName("email")
	private String email;

	public String getRepitaPassword(){
		return repitaPassword;
	}

	public int getIdUser(){
		return idUser;
	}

	public String getLastName(){
		return lastName;
	}

	public String getPassword(){
		return password;
	}

	public String getPhone(){
		return phone;
	}

	public String getNumReg(){
		return numReg;
	}

	public String getName(){
		return name;
	}

	public String getCard(){
		return card;
	}

	public String getEmail(){
		return email;
	}
}