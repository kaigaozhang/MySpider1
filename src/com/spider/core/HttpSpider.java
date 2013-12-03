package com.spider.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.spider.entity.HtmlPage;
import com.spider.entity.Page;

public class HttpSpider {
	
	public  Page getHtmlContent(URL url, String encode) {  
		Page page = new HtmlPage();
		
        StringBuffer contentBuffer = new StringBuffer();  
        
        int responseCode = -1;  
        HttpURLConnection con = null;  
        try {  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");// IE代理进行下载  
            con.setConnectTimeout(60000);  
            con.setReadTimeout(60000);  
            // 获得网页返回信息码  
            responseCode = con.getResponseCode();  
            if (responseCode == -1) {  
                System.out.println(url.toString() + " : connection is failure...");  
                con.disconnect();  
                return null;  
            }  
            if (responseCode >= 400) // 请求失败  
            {  
                System.out.println("请求失败:get response code: " + responseCode);  
                con.disconnect();  
                return null;  
            }  
  
            InputStream inStr = con.getInputStream();  
            InputStreamReader istreamReader = new InputStreamReader(inStr, encode);  
            BufferedReader buffStr = new BufferedReader(istreamReader);  
  
            String str = null;  
            while ((str = buffStr.readLine()) != null)  
                contentBuffer.append(str+'\n');  
            inStr.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
            contentBuffer = null;  
            System.out.println("error: " + url.toString());  
        } finally {  
            con.disconnect();  
        }  
        page.setContent(contentBuffer.toString());
        return page;  
    }  
  
}
