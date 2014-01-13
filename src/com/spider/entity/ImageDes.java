package com.spider.entity;

public class ImageDes {
	private String url;
	private String name;
	private String suffix;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImageDes(String url, String name, String suffix) {
		super();
		this.url = url;
		this.name = name;
		this.suffix = suffix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public ImageDes() {
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof ImageDes){
			if(((ImageDes) obj).url.equals(this.url)){
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return url.hashCode();
	}
	
}
