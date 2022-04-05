package arpa.home.springpoll;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import arpa.home.springpoll.businessRule.dateFormat;
import arpa.home.springpoll.poll.PollExampleLoader;

@Configuration
public class MainConfig {
	
	@Bean
	public PollExampleLoader applicationStartupRunner() {
	    return new PollExampleLoader();
	}

}