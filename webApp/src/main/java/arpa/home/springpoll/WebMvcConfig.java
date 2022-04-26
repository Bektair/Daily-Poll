package arpa.home.springpoll;


import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; 


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
		return DateTimeFormatter.ISO_DATE;
	}
	
	

	
	
}
