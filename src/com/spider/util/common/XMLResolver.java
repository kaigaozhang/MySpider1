package com.spider.util.common;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import com.spider.entity.BaseURL;
public class XMLResolver {
	public static List<BaseURL> parseClientXML() {
		try {
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
			return baseURLList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
