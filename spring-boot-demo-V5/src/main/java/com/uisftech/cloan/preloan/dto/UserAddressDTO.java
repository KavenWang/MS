package com.uisftech.cloan.preloan.dto;

import java.io.Serializable;

public class UserAddressDTO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

	private String username;
	private String password;
	private String province;
	private String city;
	private String stree;

	public UserAddressDTO(String username, String password, String province,
			String city, String stree) {
		super();
		this.username = username;
		this.password = password;
		this.province = province;
		this.city = city;
		this.stree = stree;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStree() {
		return stree;
	}

	public void setStree(String stree) {
		this.stree = stree;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}





}
