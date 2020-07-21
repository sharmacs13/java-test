package com.umc;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserActivity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ua_id;
	
	public int getUa_id() {
		return ua_id;
	}

	public void setUa_id(int ua_id) {
		this.ua_id = ua_id;
	}

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ACTIVITY_ID")
	private Activity user_a;
	
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Activity getUser_a() {
		return user_a;
	}

	public void setUser_a(Activity user_a) {
		this.user_a = user_a;
	}

	public UserEntity getUser_activity() {
		return user_activity;
	}

	public void setUser_activity(UserEntity user_activity) {
		this.user_activity = user_activity;
	}

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "UID")
	private UserEntity user_activity;
}
