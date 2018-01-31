package util;

import java.io.IOException;
import java.util.Properties;

public class CommonUtils {

	/**
	 * 
	 * @param key 传入一个properties文件中的键
	 * @return instance 返回一个key的值的实例
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Object reflectInstanceFromProp(String key) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		String className = getValueFromProp(key);
		
		return reflectInstanceFromClassName(className);
	}
/**
 * 
 * @param key 传入一个properties文件中键
 * @return value 返回properties文件中的值
 * @throws IOException
 */
	public static String getValueFromProp(String key) throws IOException {
		
		Properties prop = new Properties();
		
		prop.load(CommonUtils.class.getClassLoader().getResourceAsStream("config.properties"));
		
		String value = prop.getProperty(key);
		
		return value;
	}
	/**
	 * 
	 * @param className 把properties文件中的值传入
	 * @return	instance 用properties文件中的值来创建实例
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Object reflectInstanceFromClassName(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Object instance = Class.forName(className).newInstance();
		
		return instance;
	}

	
}
