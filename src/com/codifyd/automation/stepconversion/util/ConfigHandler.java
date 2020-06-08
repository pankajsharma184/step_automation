package com.codifyd.automation.stepconversion.util;

import java.io.BufferedReader;
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
				if (st.startsWith("<!--")) {
					if (!st.endsWith("->")) {
						while ((st = br.readLine()) != null && !st.endsWith("->")) {
							continue;
						}
					}
				} else if (!st.startsWith("#") && !st.isEmpty()) {
					String[] stArr = st.split("=");
					String key = stArr[0] != null ? stArr[0].trim() : "";

					String val = stArr.length > 1 ? stArr[1].trim() : "";

					this.put(key, val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
