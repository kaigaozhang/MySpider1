package com.spider.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Page {
  public static Types PAGETYPE;
  private String Httpurl;
  private String suffix; 
  private byte[] data;
  private String content;
  private List<String> hrefUrl = new ArrayList<String>();
  private File[] imageFiles;
public File[] getImageFiles() {
	return imageFiles;
}
public void setImageFiles(File[] imageFiles) {
	this.imageFiles = imageFiles;
}
public List<String> getHrefUrl() {
	return hrefUrl;
}
public void setHrefUrl(List<String> hrefUrl) {
	this.hrefUrl = hrefUrl;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getHttpurl() {
	return Httpurl;
}
public void setHttpurl(String httpurl) {
	Httpurl = httpurl;
}
public String getSuffix() {
	return suffix;
}
public void setSuffix(String suffix) {
	this.suffix = suffix;
}
public byte[] getData() {
	return data;
}
public void setData(byte[] data) {
	this.data = data;
}
  
}
