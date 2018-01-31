package util;

import java.io.IOException;
import java.util.Properties;

public class CommonUtils {

	/**
	 * 
	 * @param key ����һ��properties�ļ��еļ�
	 * @return instance ����һ��key��ֵ��ʵ��
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
 * @param key ����һ��properties�ļ��м�
 * @return value ����properties�ļ��е�ֵ
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
	 * @param className ��properties�ļ��е�ֵ����
	 * @return	instance ��properties�ļ��е�ֵ������ʵ��
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Object reflectInstanceFromClassName(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Object instance = Class.forName(className).newInstance();
		
		return instance;
	}

	
}
