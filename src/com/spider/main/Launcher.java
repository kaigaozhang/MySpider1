package com.spider.main;
import java.net.URL;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.spider.core.HttpSpider;
import com.spider.entity.BaseURL;
import com.spider.entity.Page;
import com.spider.util.common.DateFormatter;
import com.spider.util.common.XMLResolver;

public class Launcher {
	public static String lock="asd";
	public static Logger logger;
	public static  List<BaseURL> BaseURLList;
	static{
	  BasicConfigurator.configure();//默认配置   
	  PropertyConfigurator.configure("log4j.properties");  
	  logger = Logger.getLogger(Launcher.class.getName());
	  BaseURLList = XMLResolver.parseClientXML();
	}
	public static void main(String[] args){
	  logger.debug("system started!");
	  try{
		  for(BaseURL baseURL : BaseURLList){
		  Page page = new HttpSpider().getHtmlContent(new URL(baseURL.getValueURL()), "UTF-8",baseURL.getName());
		  }
	  }catch(Exception e){
		  e.printStackTrace();
		  logger.error(e.getMessage());
	  }
	   logger.debug("system exit normally!");
	}
}
