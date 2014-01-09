package com.spider.util.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	public static SimpleDateFormat sdf;
	public static DateFormatter dateFormatter ;
	private long start; //time of start 
	private long end;//end-start is the time of druation
	private DateFormatter(){
		sdf = new SimpleDateFormat("yyyy-MM-DD HH-mm-SS");
	}
	public  String currentTime(){
		return sdf.format(new Date());
	}
	public static DateFormatter getInstance(){
		if(dateFormatter==null){
			dateFormatter = new DateFormatter();
		}
		return dateFormatter;
	}
	public long start(){
		start = new Date().getTime();
		return start;
	}
	public long end(){ 
		end = new Date().getTime();
		return end-start;
	}
}
