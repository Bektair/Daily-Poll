package arpa.home.springpoll.test_utils;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import arpa.home.springpoll.data.orm.AlternativeORM;
import arpa.home.springpoll.data.orm.PollORM;
import arpa.home.springpoll.data.orm.QuestionORM;
import arpa.home.springpoll.usecase.entities.Alternative;
import arpa.home.springpoll.usecase.entities.Poll;
import arpa.home.springpoll.usecase.entities.Question;
import arpa.home.springpoll.usecase.entities.User;

public class PollTestUtils {

	
	public static Poll createPollWithId() {
		Alternative alt = new Alternative("No me smart", 1, ":smart:","1");
		Alternative alt2 = new Alternative("Yes, me smart", 2, ":lul:", "2");
		Alternative alt3 = new Alternative("Sometimes", 3, ":unsure:", "3");
		
		Alternative alt4 = new Alternative("Yes", 1, ":thumbsup:", "4");
		Alternative alt5 = new Alternative("No", 2, ":thumbsup:", "5");
		Alternative alt6 = new Alternative("OFC", 1, ":jay:", "6");
		Alternative alt7 = new Alternative("I am a doger person", 2, ":dog:", "7");
		
		Question quest1 = new Question("Are you Dumb?", List.of(alt, alt2, alt3), "1");
		Question quest2 = new Question("Are you Smart?", List.of(alt4, alt5), "2");
		Question quest3 = new Question("Do you own a cat?", List.of(alt6, alt7), "3");
		Set<Question> questions = new HashSet<Question>();
		questions.add(quest1);
		questions.add(quest2);
		questions.add(quest3);
		
		User user1 = new User("Tamujin", "123453545");
		String date = "1995-04-04";	

		Poll mockPoll1 = new Poll(date, user1, questions, "1");
		return mockPoll1;
				
	}
	
	public static Poll createPollNoId(){
		Alternative alt = new Alternative("No me smart", 1, ":smart:","1");
		Alternative alt2 = new Alternative("Yes, me smart", 2, ":lul:", "2");
		Alternative alt3 = new Alternative("Sometimes", 3, ":unsure:", "3");
		
		Alternative alt4 = new Alternative("Yes", 1, ":thumbsup:", "4");
		Alternative alt5 = new Alternative("No", 2, ":thumbsup:", "5");
		Alternative alt6 = new Alternative("OFC", 1, ":jay:", "6");
		Alternative alt7 = new Alternative("I am a doger person", 2, ":dog:", "7");
		
		Question quest1 = new Question("Are you Dumb?", List.of(alt, alt2, alt3), "1");
		Question quest2 = new Question("Are you Smart?", List.of(alt4, alt5), "2");
		Question quest3 = new Question("Do you own a cat?", List.of(alt6, alt7), "3");
		Set<Question> questions = new HashSet<Question>();
		questions.add(quest1);
		questions.add(quest2);
		questions.add(quest3);
		
		User user1 = new User("Tamujin", "123453545");
		String date = "1995-04-04";	

		Poll mockPoll1 = new Poll(date, user1, questions);
		return mockPoll1;
		
	}
	
	
	
}
