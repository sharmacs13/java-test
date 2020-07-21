package com.umc.dao;

public interface SendEmail {
	
	public void send(String to, String from, String subj, String message, String pwd);
}
