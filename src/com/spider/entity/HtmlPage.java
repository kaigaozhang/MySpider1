package com.spider.entity;

import java.util.ArrayList;
import java.util.List;

public class HtmlPage extends Page{
	
	public boolean baseNode = false;
	public boolean leafNode = false;
	
	public List<HtmlPage> sonPages = new ArrayList<HtmlPage>();

	public HtmlPage fatherPage;
	
	public HtmlPage getFatherPage() {
		return fatherPage;
	}

	public void setFatherPage(HtmlPage fatherPage) {
		this.fatherPage = fatherPage;
	}

	public boolean isBaseNode() {
		return baseNode;
	}

	public void setBaseNode(boolean baseNode) {
		this.baseNode = baseNode;
	}

	public boolean isLeafNode() {
		return leafNode;
	}

	public void setLeafNode(boolean leafNode) {
		this.leafNode = leafNode;
	}

	public List<HtmlPage> getSonPages() {
		return sonPages;
	}

	public void setSonPages(List<HtmlPage> sonPages) {
		this.sonPages = sonPages;
	}
	
}
