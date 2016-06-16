package data;

import lyh.test.FileOperUtil;


public class User {
	private String name ;
	private String email;
	private String address;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "#id:"+id+"\n#name:"+name+"\n#address:"+address+"\n#email:"+email;
	}
	
}
