package arpa.home.springpoll;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.format.Formatter;

import arpa.home.springpoll.dev.PollExampleLoader;

@Configuration
@EnableConfigurationProperties(ValidateConfigProperties.class)
public class MainConfig {
	
	@Bean
	public PollExampleLoader applicationStartupRunner() {
	    return new PollExampleLoader();
	}
	
	

}