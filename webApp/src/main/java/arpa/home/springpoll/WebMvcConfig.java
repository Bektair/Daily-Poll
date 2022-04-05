package arpa.home.springpoll;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import arpa.home.springpoll.businessRule.dateFormat;
import arpa.home.springpoll.entities.Poll;
import arpa.home.springpoll.resourcelibrary.uploadReplayServlet; 


@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{


	//Configures @RequestParam specifically
	@Override
  public void addFormatters(FormatterRegistry registry) {
      DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
      registrar.setDateFormatter(dateFormat.retrieveDateFormat());
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
