package com.example.springpoll;


import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.springpoll.businessRule.dateFormat;
import com.example.springpoll.entities.Poll;
import com.example.springpoll.resourcelibrary.uploadReplayServlet; 


@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer{



	//Configures @RequestParam specifically
	@Override
  public void addFormatters(FormatterRegistry registry) {
      DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
      registrar.setTimeFormatter(dateFormat.retrieveDateFormat());
      registrar.registerFormatters(registry);
  }

	
	@Bean
	public ServletRegistrationBean exampleServletBean() {
	    ServletRegistrationBean bean = new ServletRegistrationBean(
	      new uploadReplayServlet(), "/lib/upload");
	    bean.setLoadOnStartup(1);
	    
	    bean.setMultipartConfig(new MultipartConfigElement
	    		("/tmp", 20971520L,41943040L, 52428080)); 
	    return bean;
	}
	
	
}
