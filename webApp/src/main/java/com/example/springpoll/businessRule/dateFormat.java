package com.example.springpoll.businessRule;

import java.time.format.DateTimeFormatter;

public class dateFormat {

	
	public static DateTimeFormatter retrieveDateFormat() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
	
}
