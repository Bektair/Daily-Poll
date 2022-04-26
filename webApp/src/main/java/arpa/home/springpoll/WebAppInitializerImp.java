package arpa.home.springpoll;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;



//scans for components and autoconfigures dispatcher++
@SpringBootApplication
@ImportResource({"classpath:/beanconfig/data.xml", "classpath:/beanconfig/usecase.xml", 
	"classpath:/beanconfig/interface-adapters.xml", "classpath:/beanconfig/logging.xml"})
//The extended class implements WebApplicationInitializer enabling web scopes
public class WebAppInitializerImp extends SpringBootServletInitializer    {
	
	
	
	public static void main(String[] args) {

		//Add listeners and Initializers before run
		ConfigurableApplicationContext ctx = SpringApplication.run(WebAppInitializerImp.class, args);
		
		Environment env = ctx.getEnvironment();
		
		
	}
	
	
	

}
