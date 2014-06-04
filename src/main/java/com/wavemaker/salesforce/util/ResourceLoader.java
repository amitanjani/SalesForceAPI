package com.wavemaker.salesforce.util;

import java.io.IOException;
import java.util.Properties;

public class ResourceLoader {

	static Properties props;
	static {
		props = new Properties();
		try {
			props.load(ResourceLoader.class.getResourceAsStream("/salesforce.cfg.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String get(String key) {
		return props.getProperty(key);
	}

}
