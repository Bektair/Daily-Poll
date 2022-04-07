package arpa.home.springpoll;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import arpa.home.springpoll.data.orm.PollORM; 


@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	

	//Configures @RequestParam specifically
	@Override
  public void addFormatters(FormatterRegistry registry) {
      DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
      registrar.setDateFormatter(retrieveDateFormat());
      registrar.registerFormatters(registry);
  }
	
	public static DateTimeFormatter retrieveDateFormat() {
		//If changed change ORMs LocalDate formatters also
		return DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
	
	

	
	
}
