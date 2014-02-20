package com.spider.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.spider.entity.HtmlPage;
import com.spider.entity.Page;
import com.spider.main.Launcher;
import com.spider.util.common.Analyzer;

public class HttpSpider {
	public static Logger logger;
	private HttpSpider(){
		
	}
	public static HttpSpider instance;
	
	public static HttpSpider getInstance(){
		if (instance==null)
			instance = new HttpSpider();
		return instance;
	}
	
	static{
		  logger = Logger.getLogger(HttpSpider.class.getName());
		}
	public  HtmlPage getHtmlContent(HtmlPage page){
		if(!condition1(page))
			return null;
		try {
			return getHtmlContent(new URL(page.getHttpurl()), "UTF-8",page.getHttpurl(),page);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * 
	 */
	private boolean condition1(HtmlPage page) {
		// TODO Auto-generated method stub
		HtmlPage $page = page;
		int count=0;
		while(!$page.getFatherPage().isBaseNode()){
			$page = $page.getFatherPage();
			count++;
			if(count>5){
				return false;
			}
		}
		return true;
	}
	public  HtmlPage getHtmlContent(URL url, String encode,String urlName ,HtmlPage page) {  
        StringBuffer contentBuffer = new StringBuffer();  
        int responseCode = -1;  
        HttpURLConnection con = null;  
        try {  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");// IE代理进行下载  
            con.setConnectTimeout(10000);  
            con.setReadTimeout(10000);  
            responseCode = con.getResponseCode();  
            if (responseCode == -1) {  
               logger.error("responseCode=-1, request is failed!"); 
                con.disconnect();  
                return null;  
            }  
            if (responseCode >= 400) // 
            {  
                logger.error("responseCode =" + responseCode+" and request is failed!");  
                con.disconnect();  
                return null;  
            }  
            InputStream inStr = con.getInputStream();  
            InputStreamReader istreamReader = new InputStreamReader(inStr, encode);  
            BufferedReader buffStr = new BufferedReader(istreamReader);  
            String str = null;  
            while ((str = buffStr.readLine()) != null)  
                contentBuffer.append(str);  
            inStr.close();  
        } catch (Exception e) {  
        	 con.disconnect();  
            if(e instanceof java.net.SocketTimeoutException){
            	System.out.println(" when read "+url+" is timeout! restart 0.02 second later");
            	contentBuffer = null;
            	getHtmlContent(url, encode,urlName ,page);
            }else{
            	contentBuffer = null;
            }
        } 
        if(contentBuffer!=null)
        page.setContent(contentBuffer.toString());
        else
        	return null;
        con.disconnect(); 
        Analyzer.analyzeHttpUrl(page, "\"");
       // System.out.println(Analyzer.generateImageFiles(Analyzer.analyzeImages(page),urlName)); 
        return page;  
    }  
  
}
