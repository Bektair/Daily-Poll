package arpa.home.springpoll.test_utils;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.DateFormatter;

import arpa.home.springpoll.persistence.orm.AlternativeORM;
import arpa.home.springpoll.persistence.orm.PollORM;
import arpa.home.springpoll.persistence.orm.QuestionORM;

public class PollORMTestUtils {
	

	public static List<PollORM> createPollORMListWithId(int size){
		
		List<PollORM> list = new ArrayList<>();
		
		for(int i = 0; i < size; i++) {
			list.add(createPollORMWithId(BigInteger.valueOf(i)));
		}
		return list;
		
	}
	
	//TODO randomize values
	public static PollORM createPollORMWithId() {
	
		LocalDate date = LocalDate.parse("1995-04-04");
		PollORM mockPoll1 = new PollORM(BigInteger.ONE, date, 
				createIdQuestionORMsWithAlternatives());
		return mockPoll1;
	}
	
	
	public static PollORM createPollORMWithId(BigInteger big) {
		LocalDate date = LocalDate.parse("1995-04-04");	
		PollORM mockPoll1 = new PollORM(big, date
				, createIdQuestionORMsWithAlternatives());
		return mockPoll1;
	}
	
	public static PollORM createPollORMNoId() {
		LocalDate date = LocalDate.parse("1995-04-04");	
		PollORM mockPoll1 = new PollORM(date
				, createNoIdQuestionORMsWithAlternatives());
		return mockPoll1;
	}
	
	private static Set<QuestionORM> createIdQuestionORMsWithAlternatives() {
		AlternativeORM alt = new AlternativeORM(1, "NO, me smart", ":smart:", BigInteger.ONE);
		AlternativeORM alt2 = new AlternativeORM(2, "Yes, me smart", ":ogre:", BigInteger.TWO);
		AlternativeORM alt3 = new AlternativeORM(3, "Sometimes", ":ok_hand:", BigInteger.TEN);
	
		QuestionORM quest1 = new QuestionORM(BigInteger.ONE,"Are you Dumb?", alt, alt2, alt3);
		QuestionORM quest2 = new QuestionORM(BigInteger.TWO, "Are you Smart?");
		QuestionORM quest3 = new QuestionORM(BigInteger.TEN, "Do you own a cat?");
	
		Set<QuestionORM> questions = new HashSet<QuestionORM>();
		questions.add(quest1);
		questions.add(quest2);
		questions.add(quest3);
		return questions;
	}
	
	private static Set<QuestionORM> createNoIdQuestionORMsWithAlternatives() {
		AlternativeORM alt = new AlternativeORM(1, "NO, me smart", ":smart:");
		AlternativeORM alt2 = new AlternativeORM(2, "Yes, me smart", ":ogre:");
		AlternativeORM alt3 = new AlternativeORM(3, "Sometimes", ":ok_hand:");
	
		QuestionORM quest1 = new QuestionORM("Are you Dumb?", alt, alt2, alt3);
		QuestionORM quest2 = new QuestionORM("Are you Smart?");
		QuestionORM quest3 = new QuestionORM("Do you own a cat?");
	
		Set<QuestionORM> questions = new HashSet<QuestionORM>();
		questions.add(quest1);
		questions.add(quest2);
		questions.add(quest3);
		return questions;
	
	}
	
}
