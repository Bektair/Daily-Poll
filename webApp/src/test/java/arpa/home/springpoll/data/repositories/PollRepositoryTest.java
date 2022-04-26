package arpa.home.springpoll.data.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import arpa.home.springpoll.persistence.orm.AlternativeORM;
import arpa.home.springpoll.persistence.orm.PollORM;
import arpa.home.springpoll.persistence.orm.QuestionORM;
import arpa.home.springpoll.persistence.repositories.PollRepository;

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
		AlternativeORM alt = new AlternativeORM(1, "I do", ":smart:");
		AlternativeORM alt2 = new AlternativeORM(2, "I don't", ":jay:"); 
		QuestionORM quest = new QuestionORM("Do you love me?", alt, alt2);
		PollORM poll = new PollORM(pollPosted, quest);
		underTest.save(poll);
		//when
		Optional<PollORM> pollResults = underTest
				.findPollByDatePosted(pollPosted);
		//then
		assertThat(poll.equals(pollResults.get()));
	}
	
	
	
	@Test
	void itShouldCheckThatWeDoNotFindPollWithNoData() {
		//given
		LocalDate pollPosted = LocalDate.of(2022, 5, 5);
		//when
		Optional<PollORM> pollResults = underTest
				.findPollByDatePosted(pollPosted);
		//then
		assertThat(pollResults).isEmpty();

	}
	
	
	
}
