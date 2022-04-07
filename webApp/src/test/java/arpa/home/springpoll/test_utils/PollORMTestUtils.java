package arpa.home.springpoll.test_utils;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.swing.text.DateFormatter;

import arpa.home.springpoll.data.orm.AlternativeORM;
import arpa.home.springpoll.data.orm.PollORM;
import arpa.home.springpoll.data.orm.QuestionORM;

public class PollORMTestUtils {
	

	//TODO randomize values
	public static PollORM createPollORMWithId() {
		AlternativeORM alt = new AlternativeORM(1, "NO, me smart", ":smart:");
		AlternativeORM alt2 = new AlternativeORM(2, "Yes, me smart", ":ogre:");
		AlternativeORM alt3 = new AlternativeORM(3, "Sometimes", "ok_hand");
		
		QuestionORM quest1 = new QuestionORM("Are you Dumb?", alt, alt2, alt3);
		QuestionORM quest2 = new QuestionORM("Are you Smart?");
		QuestionORM quest3 = new QuestionORM("Do you own a cat?");

		Set<QuestionORM> questions = new HashSet<QuestionORM>();
		LocalDate date = LocalDate.parse("1995-04-04");
		
		PollORM mockPoll1 = new PollORM(BigInteger.ONE, date, questions);
		
		return mockPoll1;
	}
	
	public static PollORM createPollORMWithId(BigInteger big) {
		LocalDate date = LocalDate.parse("1995-04-04");	
		PollORM mockPoll1 = new PollORM(big, date
				, createQuestionORMsWithAlternatives());
		return mockPoll1;
	}
	
	public static PollORM createPollNoId() {
		LocalDate date = LocalDate.parse("1995-04-04");	
		PollORM mockPoll1 = new PollORM(date
				, createQuestionORMsWithAlternatives());
		return mockPoll1;
	}
	
	private static Set<QuestionORM> createQuestionORMsWithAlternatives() {
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
