package com.spider.util.common;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import com.spider.entity.BaseURL;
import com.spider.main.Launcher;
public class XMLResolver {
	public static Logger logger;
	static{
		  BasicConfigurator.configure();//默认配置   
		  PropertyConfigurator.configure("log4j.properties");  
		  logger = Logger.getLogger(XMLResolver.class.getName());
		}
	public static List<BaseURL> parseClientXML() {
		try {
			logger.debug("prase the client.xml");
			List<BaseURL> baseURLList = new ArrayList<BaseURL>();
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build("client.xml");
			Element root = doc.getRootElement();
			List<Element> messList = root.getChildren("url");
			Element childrenRoot = null;
			for (int i = 0; i < messList.size(); i++) {
				BaseURL baseURL = new BaseURL();
				childrenRoot = messList.get(i);
				String name = childrenRoot.getAttributeValue("name");
				baseURL.setName(name);
				String value = childrenRoot.getAttributeValue("value");
				baseURL.setValueURL(value);
				baseURLList.add(baseURL);
			}
			logger.debug("finishing prase the client.xml"+baseURLList.size()+" urls are found!");
			return baseURLList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
