package com.example.springpoll.poll;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import arpa.home.springpoll.data.PollRepository;
import arpa.home.springpoll.entities.Alternative;
import arpa.home.springpoll.entities.Poll;
import arpa.home.springpoll.entities.Question;

@DataJpaTest
public class PollRepositoryTest {

	@Autowired
	private PollRepository underTest;
	
	@AfterEach
	public void tearDown() {
		underTest.deleteAll();
	}
	
	@Test
	void itShouldCheckThatWeFindPollWithDatePosted() {
		//given
		LocalDate pollPosted = LocalDate.of(2022, 5, 5);
		Alternative alt = new Alternative(1, "I do");
		Alternative alt2 = new Alternative(2, "I don't"); 
		Question quest = new Question("Do you love me?", alt, alt2);
		Poll poll = new Poll(pollPosted, quest);
		underTest.save(poll);
		//when
		Optional<Poll> pollResults = underTest
				.findPollByDatePosted(pollPosted);
		//then
		assertThat(poll.equals(pollResults.get()));
	}
	
	
	
	@Test
	void itShouldCheckThatWeDoNotFindPollWithNoData() {
		//given
		LocalDate pollPosted = LocalDate.of(2022, 5, 5);
		//when
		Optional<Poll> pollResults = underTest
				.findPollByDatePosted(pollPosted);
		//then
		assertThat(pollResults).isEmpty();

	}
	
	
	
}
