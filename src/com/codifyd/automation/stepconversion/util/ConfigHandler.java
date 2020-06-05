package com.codifyd.automation.stepconversion.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class ConfigHandler extends LinkedHashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2049440605887683984L;

	protected ConfigHandler defaults;

	public ConfigHandler() {
		this(null);
	}

	public ConfigHandler(ConfigHandler defaults) {
		this.defaults = defaults;
	}

	public synchronized void load(InputStream inStream) throws IOException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
			String st = new String();
			while ((st = br.readLine()) != null) {
				st = st.trim();
				if (!st.startsWith("#") && !st.isEmpty()) {
					String[] stArr = st.split("=");
					this.put(stArr[0], stArr[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void load(FileInputStream inStream) throws IOException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
			String st = new String();
			while ((st = br.readLine()) != null) {
				st = st.trim();
				if (!st.startsWith("#") && !st.isEmpty()) {
					String[] stArr = st.split("=");
					this.put(stArr[0], stArr[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
