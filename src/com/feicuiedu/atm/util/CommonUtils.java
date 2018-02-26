package com.feicuiedu.atm.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.feicuiedu.atm.core.AbstractView;


public class CommonUtils {
	

    public static AbstractView setDispatherPathReturnAbstractView (String dispatherPath) {
    	
    	AbstractView av = null;

        try {
            av = (AbstractView) CommonUtils.reflectInstanceFromProp(dispatherPath);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return av;
    }

    
	/**
	 * 传入properties键，返回以className创建的实例
	 * @param key 
	 * @return  
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Object reflectInstanceFromProp(String key) throws  InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		String className = getValueFromProp(key);
		
		return reflectInstanceFromClassName(className);
	}
	

	/**
	 * 
	 * @param key 
	 * @return
	 * @throws IOException
	 */
	public static String getValueFromProp(String key)  {
		
		Properties prop = new Properties();
		
		try {
			prop.load(CommonUtils.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String value = prop.getProperty(key);
		
		return value;
	}
	
	/**
	 * 从config_menu_admin/config_menu_user文件中取出key的值
	 * @param key 
	 * @return
	 * @throws IOException
	 */
	public static String getMenuValueFromProp(String key,String propFile) throws IOException {
		
		Properties prop = new Properties();
		
		InputStream fis= CommonUtils.class.getClassLoader().getResourceAsStream("config_"+ propFile +".properties");
		
		if(fis==null) {
			
			return null;
		}
		
		prop.load(fis);
		
		String value = prop.getProperty(key);
		
		return value;
	}
	/**
	 * 
	 * @param className 
	 * @return	 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Object reflectInstanceFromClassName(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Object instance = Class.forName(className).newInstance();
		
		return instance;
	}
	
	/**
	 *  传入properties文本内键，返回file对象
	 * @param fileName
	 * @return
	 */
	private static File readFileFromRecources(String fileName)  {

		String filePath = CommonUtils.class.getClassLoader().getResource(fileName).getFile();
        //System.out.println("write="+filePath);
        File file = new File(filePath);
       
        if (!file.exists()) {
        	
        	printFromProperties("M107");
        }

        return file;
        
    }
	
    /**
     * 被显示文件的名字
     *
     * @param fileName
     */
    public static void printFromFile(String fileName) throws  IOException {
    	
        File file = readFileFromRecources(fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        while (true) {
        	
            String strReader = br.readLine();

            if (strReader != null) {
                System.out.println(strReader);
               
            }

            if (strReader == null) {
                break;
            }
        }
        br.close();
    }
    
    /**
     * 被显示文件的名字
     *
     * @param fileName
     */
    public static void printFromProperties(String key)  {
    
		System.out.println(getValueFromProp(key));
		
    }
    /**
     * 正则表达式，要求身份证号位十八位
     * @param idnum
     * @return
     */
    public static boolean checkCardNum(String idnum) {
    	
		return idnum.matches("^[0-9]{18}$");
	}
    /**
     * 正则表达式，要求密码位6位，数字或字母组合
     * @param passwd
     * @return
     */
    public static boolean checkPasswd(String passwd) {
    	
		return passwd.matches("^[0-9A-Za-z]{6}$");
	}
    /**
     * 正则表达式，要求birthday满足 yyyy-mm-dd 格式
     * @param birthday
     * @return
     */
    public static boolean checkBirthday(String birthday) {
    	
		return birthday.matches("^\\d{4}-\\d{2}-\\d{2}$");
	}
    /**
     * 判断金钱正确与否
     * @param str
     * @return
     */
    public static boolean isMoney(String str){  
    	
        Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
        
        Matcher match=pattern.matcher(str);   
        
        if(match.matches()==false){   
        	
           return false;   
           
        }else{   
        	
           return true;   
           
        }   
    }  
 
}

