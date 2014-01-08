package com.spider.main;
import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.spider.core.HttpSpider;
import com.spider.entity.Page;
import com.spider.util.common.DateFormatter;

public class Launcher {
	public static Logger logger;
	static{
	  BasicConfigurator.configure();//默认配置   
	  PropertyConfigurator.configure("log4j.properties");  
	  logger = Logger.getLogger(Launcher.class.getName());
	}
	public static void main(String[] args){
	  logger.debug("system started!");
	  try{
		Page page = new HttpSpider().getHtmlContent(new URL("http://www.sina.com.cn/"), "utf-8");
	  }catch(Exception e){
		  logger.error(e.getMessage());
		  System.exit(-1);
	  }
	   logger.debug("system exit normally!");
	   System.exit(0);
	}
	 
   
}
