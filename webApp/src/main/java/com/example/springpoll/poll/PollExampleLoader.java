package com.example.springpoll.poll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.example.springpoll.alternative.AltService;
import com.example.springpoll.alternative.AlternativeRepository;
import com.example.springpoll.entities.Alternative;
import com.example.springpoll.entities.Poll;
import com.example.springpoll.entities.Question;
import com.example.springpoll.question.QuestService;
import com.example.springpoll.question.QuestionRepository;

@Profile("dev")
@Component
public class PollExampleLoader implements CommandLineRunner {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private PollRepository pollRepository;
	
	@Autowired
	private AlternativeRepository altRepository;
	
	@Autowired
	private Environment env;
	
	@Override
	public void run(String... args) throws Exception {
		String profile = env.getProperty("spring.profiles.active");
		if(profile.equals("test")) return;
		pollRepository.deleteAll();
		questionRepository.deleteAll();
		altRepository.deleteAll();

		Alternative alt = new Alternative(1, "NO, me smart");
		Alternative alt2 = new Alternative(2, "Yes, me smart");
		Alternative alt3 = new Alternative(3, "Sometimes");

		Question quest1 = new Question("Are you Dumb?", alt, alt2, alt3);
		Question quest2 = new Question("Are you Smart?");
		Question quest3 = new Question("Do you own a cat?");

		Poll mockPoll1 = new Poll(LocalDate.parse("1995-04-04"), quest1, 
				quest2, quest3);
		
		pollRepository.save(mockPoll1);

		System.out.println("Runs once ++++++++++");
	}
	

	

	
}