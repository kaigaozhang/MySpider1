package com.spider.main;
import java.net.URL;

import com.spider.core.HttpSpider;
import com.spider.entity.Page;

public class Launcher {
	public static void main(String[] args) throws Exception {
		Page page = new HttpSpider().getHtmlContent(new URL("http://www.sina.com.cn/"), "utf-8");
	System.exit(0);
	}
	 
   
}
