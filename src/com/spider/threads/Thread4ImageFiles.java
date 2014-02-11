package com.spider.threads;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spider.entity.ImageDes;
public class Thread4ImageFiles extends Thread{
	public static long totalCount;
	public static long finishedCount;
	private ImageDes imageDes;
	public static Set<ImageDes> ImageDesRestartSet = new HashSet<ImageDes>();
	//public static Map<String,Set<ImageDes>> restartSetMap = new HashMap<String,Set<ImageDes>>();
	public static List<Thread4ImageFiles> threadPool = new ArrayList<Thread4ImageFiles>();
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
	    finishedCount++;
	    System.out.println("download :"+imageDes.getUrl()+" is success and the progress is:  "+finishedCount+"/"+totalCount );
	    }catch(Exception e){
	    	/*
	    	 * 在这里可以restart,但是未下好的图片应该删除掉
	    	 */
	    	System.out.println("download :"+imageDes.getUrl()+" is failed! waiting seconds it will restart!" );
	    	threadPool.add(this);
	    	restartDownload();
	    //	System.out.println(imageDes.getUrl());
	    //	ImageDesRestartSet.add(imageDes);
	    //	restartSetMap.put(urlName, ImageDesRestartSet);
	    }
	}
	
	public void restartDownload() {
		System.out.println("download file :"+System.getProperty("user.dir")+"/images/"+"_"+urlName+imageDes.getName()+"."+imageDes.getSuffix()+" is restart!");
		this.run();
	}
	
}
