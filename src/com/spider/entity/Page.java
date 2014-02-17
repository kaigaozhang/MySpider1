package com.spider.entity;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Page {
  public static Types PAGETYPE;
  private String Httpurl;
  private String suffix; 
  private byte[] data;
  private String content;
  private Set<String> hrefUrl = new HashSet<String>();
  private File[] imageFiles;
public File[] getImageFiles() {
	return imageFiles;
}
public void setImageFiles(File[] imageFiles) {
	this.imageFiles = imageFiles;
}
public Set<String> getHrefUrl() {
	return hrefUrl;
}
public void setHrefUrl(Set<String> hrefUrl) {
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
