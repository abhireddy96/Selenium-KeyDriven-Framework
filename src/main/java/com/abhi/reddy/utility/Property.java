package com.abhi.reddy.utility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Class Property.
 */
public class Property {

	/**
	 * Gets the properties.
	 *
	 * @param ConfigPropertiesFile the config properties file
	 * @return the properties
	 */
	public static Properties getProperties(String ConfigPropertiesFile){

		try {
			InputStream fileReader;
			Properties property;

			fileReader = new FileInputStream(ConfigPropertiesFile);
			property = new Properties();

			property.load(fileReader);

			return property;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}


	}
}
