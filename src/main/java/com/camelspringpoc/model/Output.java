package com.camelspringpoc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Output for the Rest service")
public class Output {

	@ApiModelProperty(value="User ID", example="101")
	private int id;
	
	@ApiModelProperty(value="User Name", example="Test")
	private String name;
	
	@ApiModelProperty(value="User Email", example="Test123@gmail.com")
	private String email;
	
	@ApiModelProperty(value="User Country", example="India")
	private String country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Output() {};
	
	public Output(int id, String name, String email, String country) {
		System.out.println("User name :"+name);
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
	}
	
}
