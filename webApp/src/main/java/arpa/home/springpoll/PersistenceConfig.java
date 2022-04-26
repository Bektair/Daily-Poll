package arpa.home.springpoll;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import arpa.home.springpoll.persistence.PollMapperImp;

@Configuration
public class PersistenceConfig {

	
	@Bean
	public PollMapperImp pollMapper() {
		return new PollMapperImp();
	}
	
	
	
	
	
}
