package com.umc;

import org.springframework.stereotype.Component;

public class UserActivityResults {
	
	private String username;
	private String email;
	public UserActivityResults(String username, String email, String activity_name, int amount, String first,
			String last) {
		super();
		this.username = username;
		this.email = email;
		this.activity_name = activity_name;
		this.amount = amount;
		this.first = first;
		this.last = last;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserActivityResults(String username, String activity_name, int amount, String first, String last) {
		super();
		this.username = username;
		this.activity_name = activity_name;
		this.amount = amount;
		this.first = first;
		this.last = last;
	}
	private String activity_name;
	private int amount;
	private String first;
	private String last;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	

}
