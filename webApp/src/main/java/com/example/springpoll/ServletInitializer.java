package com.example.springpoll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

//test
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//start
		return application.sources(SpringpollApplication.class);
	}

}
