package org.easyj.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * º”‘ÿ≈‰÷√Œƒº˛
 * @author π∂Œ∞
 *
 */
public class PropertiesUtil {
	private final static String PROPERTIES_PATH = "/easyJ.properties";
	static{
		InputStream is = PropertiesUtil.class.getResourceAsStream(PROPERTIES_PATH);
		try {
			Prop.prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static class Prop{
		final static Properties prop = new Properties();
	}
	
	public static String getPropValue(String key){
		return Prop.prop.getProperty(key);
	}
}
