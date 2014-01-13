package com.spider.threads;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.spider.entity.ImageDes;
public class Thread4ImageFiles implements Runnable{
	private ImageDes imageDes;
	private String urlName;
	 public Thread4ImageFiles(ImageDes imageDes,String urlName){
		 this.imageDes = imageDes;
		 this.urlName = urlName;
	 }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
	   URL url = new URL(imageDes.getUrl());
	   URLConnection urlConnection=url.openConnection();
	   urlConnection.setConnectTimeout(100000);
	    DataInputStream dis = new DataInputStream(url.openStream());
	    File file = new File(System.getProperty("user.dir")+"/images/"+"_"+urlName);
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
	    }catch(Exception e){
	    	System.out.println(imageDes.getUrl());
	    	e.printStackTrace();
	    }
	}
	
}
