package arpa.home.springpoll.dev;

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

import arpa.home.springpoll.data.orm.AlternativeORM;
import arpa.home.springpoll.data.orm.PollORM;
import arpa.home.springpoll.data.orm.QuestionORM;
import arpa.home.springpoll.data.repositories.AlternativeRepository;
import arpa.home.springpoll.data.repositories.PollRepository;
import arpa.home.springpoll.data.repositories.QuestionRepository;
import arpa.home.springpoll.usecase.AltService;
import arpa.home.springpoll.usecase.QuestService;

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

		AlternativeORM alt = new AlternativeORM(1, "NO, me smart", ":smart:");
		AlternativeORM alt2 = new AlternativeORM(2, "Yes, me smart", ":lul:");
		AlternativeORM alt3 = new AlternativeORM(1, "Sometimes", ":unsure:");
		AlternativeORM alt4 = new AlternativeORM(1, "Never", ":jay:");

		QuestionORM quest1 = new QuestionORM("Are you Dumb?", alt, alt2);
		QuestionORM quest2 = new QuestionORM("Are you Smart?", alt3);
		QuestionORM quest3 = new QuestionORM("Do you own a cat?", alt4);

		PollORM mockPoll1 = new PollORM(LocalDate.parse("1995-04-04"), quest1, 
				quest2, quest3);
		
		pollRepository.save(mockPoll1);

		System.out.println("Runs once ++++++++++");
	}
	

	

	
}