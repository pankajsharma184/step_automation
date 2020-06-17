package com.codifyd.automation.stepconversion.user;

import java.util.ArrayList;
import java.util.List;

public class UserExcelInfo {
	
	private String userID;
	private List<String> userGroup = new ArrayList<>();
	private String userName;	
	private String userEmail;
	private String password;
	private String userInfo;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public List<String> getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(List<String> userGroup) {
		this.userGroup = userGroup;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	
	

}
