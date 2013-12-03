package com.spider.entity;

public abstract class Page {
  public static Type PAGETYPE;
  private String Httpurl;
  private String suffix;
  private byte[] data;
  private String content;
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
