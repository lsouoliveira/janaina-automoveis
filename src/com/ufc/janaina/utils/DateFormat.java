package com.ufc.janaina.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public static String getFullDate(Date d) {
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		SimpleDateFormat month = new SimpleDateFormat("MM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		
		return day.format(d) + month.format(d) + year.format(d);
	}
	
	public static String getFullTime(Date d) {
		SimpleDateFormat h = new SimpleDateFormat("hh");
		SimpleDateFormat m = new SimpleDateFormat("mm");
		SimpleDateFormat s = new SimpleDateFormat("ss");
		
		return h.format(d) + m.format(d) + s.format(d);
	}
	
	public static String getTime(Date d) {
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
		
		return time.format(d);
	}

	public static String getDate(Date d) {
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		
		return date.format(d);
	}
}
