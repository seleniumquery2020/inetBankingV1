package com.inetbanking.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig() {
		
		File src = new File("./Configurations/config.properties");
		try {
			
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	public String getApplicationURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}
	public String getUserName() {
		String username = pro.getProperty("userName");
		return username;
	}
	public String getPassWord() {
		String password = pro.getProperty("passWord");
		return password;
	}
	public String getChromePath() {
		String Chromepath = pro.getProperty("chromepath");
		return Chromepath;
	}
	public String getMsEdgePath() {
		String Msedgepath = pro.getProperty("msedgepath");
		return Msedgepath;
	}
	public String getFireFoxpath() {
		String Firefoxpath = pro.getProperty("firefoxpath");
		return Firefoxpath;
	}
}
