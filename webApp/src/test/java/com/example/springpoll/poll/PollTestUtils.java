package com.example.springpoll.poll;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.swing.text.DateFormatter;

import com.example.springpoll.entities.Alternative;
import com.example.springpoll.entities.Poll;
import com.example.springpoll.entities.Question;

public class PollTestUtils {
	

	//TODO randomize values
	static Poll createPollWithId() {
		Alternative alt = new Alternative(1, "NO, me smart");
		Alternative alt2 = new Alternative(2, "Yes, me smart");
		Alternative alt3 = new Alternative(3, "Sometimes");
		
		Question quest1 = new Question("Are you Dumb?", alt, alt2, alt3);
		Question quest2 = new Question("Are you Smart?");
		Question quest3 = new Question("Do you own a cat?");

		Set<Question> questions = new HashSet<Question>();
		LocalDate date = LocalDate.parse("1995-04-04");
		
		Poll mockPoll1 = new Poll(BigInteger.ONE, date, questions);
		
		return mockPoll1;
	}
	
	static Poll createPollWithId(BigInteger big) {
		LocalDate date = LocalDate.parse("1995-04-04");	
		Poll mockPoll1 = new Poll(big, date
				, createQuestionsWithAlternatives());
		return mockPoll1;
	}
	
	static Poll createPollNoId() {
		LocalDate date = LocalDate.parse("1995-04-04");	
		Poll mockPoll1 = new Poll(date
				, createQuestionsWithAlternatives());
		return mockPoll1;
	}
	
	private static Set<Question> createQuestionsWithAlternatives() {
		Alternative alt = new Alternative(1, "NO, me smart");
		Alternative alt2 = new Alternative(2, "Yes, me smart");
		Alternative alt3 = new Alternative(3, "Sometimes");
	
		Question quest1 = new Question("Are you Dumb?", alt, alt2, alt3);
		Question quest2 = new Question("Are you Smart?");
		Question quest3 = new Question("Do you own a cat?");
	
		Set<Question> questions = new HashSet<Question>();
		questions.add(quest1);
		questions.add(quest2);
		questions.add(quest3);
		return questions;
	}
	
	
}
