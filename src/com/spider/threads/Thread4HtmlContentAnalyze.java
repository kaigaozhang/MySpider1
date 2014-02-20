package com.spider.threads;

import com.spider.core.HttpSpider;
import com.spider.entity.HtmlPage;

public class Thread4HtmlContentAnalyze extends Thread{
	private HtmlPage page;
	public Thread4HtmlContentAnalyze(HtmlPage page){
		this.page = page;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpSpider.getInstance().getHtmlContent(page);
	}
	
}
