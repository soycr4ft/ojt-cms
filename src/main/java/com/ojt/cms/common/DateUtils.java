package com.ojt.cms.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	//db에서 가져온 localdate를 스트링으로
	public static String format(LocalDate date) {
		return date != null ? date.format(FORMATTER) : null;
	}
	
	//스트링으로 넘어온 날짜값을 localdate로
	public static LocalDate parse(String text) {
		return (text!=null && !text.isBlank()) ?LocalDate.parse(text,FORMATTER): null;
	}
}
