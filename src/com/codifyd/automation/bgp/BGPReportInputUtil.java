package com.codifyd.automation.bgp;

import java.io.File;

public class BGPReportInputUtil {
	private String inputServerPath;
	private String username;
	private String password;
	private File inputFile;
	private File outputFile;
	private String contextID;
	private String workspaceID;
	/**
	 * @return the inputServerPath
	 */
	public String getInputServerPath() {
		return inputServerPath;
	}
	/**
	 * @param inputServerPath the inputServerPath to set
	 */
	public void setInputServerPath(String inputServerPath) {
		this.inputServerPath = inputServerPath;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the inputFile
	 */
	public File getInputFile() {
		return inputFile;
	}
	/**
	 * @param inputFile the inputFile to set
	 */
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	/**
	 * @return the outputFile
	 */
	public File getOutputFile() {
		return outputFile;
	}
	/**
	 * @param outputFile the outputFile to set
	 */
	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}
	/**
	 * @return the contextID
	 */
	public String getContextID() {
		return contextID;
	}
	/**
	 * @param contextID the contextID to set
	 */
	public void setContextID(String contextID) {
		this.contextID = contextID;
	}
	/**
	 * @return the workspaceID
	 */
	public String getWorkspaceID() {
		return workspaceID;
	}
	/**
	 * @param workspaceID the workspaceID to set
	 */
	public void setWorkspaceID(String workspaceID) {
		this.workspaceID = workspaceID;
	}
	

}
