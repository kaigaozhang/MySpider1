package com.spider.main;
import java.net.URL;

import com.spider.core.HttpSpider;
import com.spider.entity.Page;

public class Launcher {
	public static void main(String[] args) throws Exception {
		Page page = new HttpSpider().getHtmlContent(new URL("http://www.baidu.com"), "utf-8");
		 System.out.println(page.getContent()) ; 
	}
	 
   
}
