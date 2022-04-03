package com.example.springpoll;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springpoll.poll.PollExampleLoader;

@Configuration
public class InitConfig {

	@Bean
	public PollExampleLoader applicationStartupRunner() {
	    return new PollExampleLoader();
	}
	
}
