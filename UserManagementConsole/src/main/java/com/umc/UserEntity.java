package com.umc;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

@Entity
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int uid;
	@NotNull
	@NotEmpty
	private String password;
	@NotNull
	@NotEmpty
	private String username;
	@NotNull
	@NotEmpty
	private String email;
	
	@NotNull
	@NotEmpty
	private String address;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="user_activity",cascade = CascadeType.ALL)
	private Set<UserActivity> user_activity;
	
	public Set<UserActivity> getUser_activity() {
		return user_activity;
	}
	public void setUser_activity(Set<UserActivity> user_activity) {
		this.user_activity = user_activity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
}
