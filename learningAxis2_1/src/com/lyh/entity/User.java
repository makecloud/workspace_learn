package com.lyh.entity;

import java.io.Serializable;


public class User implements Serializable{
	private final long serialVersionUID=677484458789332877L;
	private String name ;
	private String email;
	private String address2;
	private int id ;
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
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address) {
		this.address2 = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "#id:"+id+"\n#name:"+name+"\n#address:"+address2+"\n#email:"+email;
	}
	
}
