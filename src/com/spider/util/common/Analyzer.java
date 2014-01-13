package com.spider.util.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.spider.entity.ImageDes;
import com.spider.entity.Page;
import com.spider.threads.Thread4ImageFiles;

public class Analyzer {
	public static Logger logger;
	private static DateFormatter dateHelper;
	static
	{
		logger = Logger.getLogger(Analyzer.class.getName());
		dateHelper = DateFormatter.getInstance();
	}
	public static void analyzeHttpUrl(Page p,String prefix){
		logger.debug("Analyzer begin to analyse the entire page code!  -----------------------------------------");
		dateHelper.start();
		String str = p.getContent();
		int index = str.indexOf(prefix);
		int end = str.lastIndexOf("\"");
		while(index!=end&& index+8<str.length()){
			String test = str.substring(index+1, index+8);
			if(test.equals("http://")){
				p.getHrefUrl().add(str.substring(index+1,str.indexOf(prefix, index+1)));
			}
			index = str.indexOf(prefix, index+1);
		}
		logger.debug("Analyse lasts"+dateHelper.end()+"! and "+p.getHrefUrl().size()+" urls and founded! ------------------------------------------------");
	}
	/*
	 * in order to insert the different imageDes . use Set in stand of List
	 */
	public static Set<ImageDes> analyzeImages(Page p){
		logger.debug("find the imageUrls  analyzeImages begins! -----------------------------------------");
		dateHelper.start();
		List<String> httpUrl = p.getHrefUrl();
		Set<ImageDes> imageDeses = new HashSet<ImageDes>();
		for(String url: httpUrl){
			String suffix = url.substring(url.lastIndexOf('.')+1, url.length());
			
		if(	suffix.equalsIgnoreCase("bmp")||suffix.equalsIgnoreCase("jpg")||suffix.equalsIgnoreCase("tiff")||suffix.equalsIgnoreCase("gif")||suffix.equalsIgnoreCase("pcx")||suffix.equalsIgnoreCase("tga")||suffix.equalsIgnoreCase("exif")||suffix.equalsIgnoreCase("jpeg")){
			//imageUrl.add(url);
			ImageDes imageDes = new ImageDes(url,url.substring(url.lastIndexOf('/')+1, url.lastIndexOf('.')),suffix);
			imageDeses.add(imageDes);
		}
		}
		logger.debug("find the imageUrls  analyzeImages end! lasts"+dateHelper.end()+"   "+imageDeses.size()+" images url is found!-----------------------------------------");
		return imageDeses;
	}
	
	
	
	public static int generateImageFiles (Set<ImageDes> imageDeses,String urlName){
		logger.debug("start to generate the images! -----------------------------------------");
		dateHelper.start();
		int count =0;
		try{
		   for(ImageDes imageDes : imageDeses){
			   if(imageDes.getName().indexOf(';')!=-1||imageDes.getName().indexOf('=')!=-1||imageDes.getName().indexOf(':')!=-1){
			    	continue;
			    }
			   Thread imageFileThread = new Thread(new Thread4ImageFiles(imageDes,urlName));
			   imageFileThread.start();
		    count++;
		   }
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.debug("generate end! lasts"+dateHelper.end()+"-----------------------------------------");
		return count;
	}
	
	
}
