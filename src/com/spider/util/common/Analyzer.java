package com.spider.util.common;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.spider.entity.ImageDes;
import com.spider.entity.Page;

public class Analyzer {
	
	public static void analyzeHttpUrl(Page p,String prefix){
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
		
	}
	public static List<ImageDes> analyzeImages(Page p){
		List<String> httpUrl = p.getHrefUrl();
		List<ImageDes> imageDeses = new ArrayList<ImageDes>();
		for(String url: httpUrl){
			String suffix = url.substring(url.lastIndexOf('.')+1, url.length());
			
		if(	suffix.equalsIgnoreCase("bmp")||suffix.equalsIgnoreCase("jpg")||suffix.equalsIgnoreCase("tiff")||suffix.equalsIgnoreCase("gif")||suffix.equalsIgnoreCase("pcx")||suffix.equalsIgnoreCase("tga")||suffix.equalsIgnoreCase("exif")||suffix.equalsIgnoreCase("jpeg")){
			//imageUrl.add(url);
			ImageDes imageDes = new ImageDes(url,url.substring(url.lastIndexOf('/'), url.lastIndexOf('.')),suffix);
			imageDeses.add(imageDes);
		}
		}
		return imageDeses;
	}
	
	
	
	public static int generateImageFiles (List<ImageDes> imageDeses){
		int count =0;
		try{
		
		   for(ImageDes imageDes : imageDeses){
			   
			   
			   if(imageDes.getName().indexOf(';')!=-1||imageDes.getName().indexOf('=')!=-1||imageDes.getName().indexOf(':')!=-1){
			    	continue;
			    }
			   
			URL url = new URL(imageDes.getUrl());
		    //打开网络输入流
		    DataInputStream dis = new DataInputStream(url.openStream());
		    //建立一个新的文件
		    File file = new File(System.getProperty("user.dir")+"/images/");
		    if(!file.exists()){
		    file.mkdirs();
		    }
		    
		  
		    
		    File f1=new File(file,imageDes.getName()+"."+imageDes.getSuffix());
		    f1.createNewFile();
		    FileOutputStream fos = new FileOutputStream(f1);
		    byte[] buffer = new byte[1024];
		    int length;
		    while( (length = dis.read(buffer))>0){
		    fos.write(buffer,0,length);
		    }
		    dis.close();
		    fos.close();
		    count++;
		   }
		}catch(Exception e){
			e.printStackTrace();
		}
		   
		return count;
	}
	
	
}
