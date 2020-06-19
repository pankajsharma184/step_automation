package com.codifyd.automation.bgp;

import java.io.File;

public class BGPReportInputUtil {
	private String inputServerPath;
	private String username;
	private String password;
	private String contextID;
	private File inputFile;
	public String getInputServerPath() {
		return inputServerPath;
	}
	public void setInputServerPath(String inputServerPath) {
		this.inputServerPath = inputServerPath;
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
	public String getContextID() {
		return contextID;
	}
	public void setContextID(String contextID) {
		this.contextID = contextID;
	}
	public File getInputFile() {
		return inputFile;
	}
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	
	

}
