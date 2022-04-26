package arpa.home.springpoll.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import arpa.home.springpoll.WebMvcConfig;
import arpa.home.springpoll.persistence.PollMapperImp;
import arpa.home.springpoll.persistence.orm.PollORM;
import arpa.home.springpoll.persistence.orm.QuestionORM;
import arpa.home.springpoll.test_utils.PollORMTestUtils;
import arpa.home.springpoll.test_utils.PollTestUtils;
import arpa.home.springpoll.usecase.entities.Poll;
import arpa.home.springpoll.usecase.entities.Question;

@ExtendWith(MockitoExtension.class)
public class PollMapperTest {

	private PollMapperImp underTest = new PollMapperImp();
	private static final Logger LOGGER = Logger.getLogger(PollMapperTest.class);

	@Test
	public void emptyPollOptionalShouldReturnEmptyORMOptional() {
		
		//arrange
		Optional<Poll> optPoll = Optional.empty();
		//act
		Optional<PollORM> optPollORM = underTest.mapOptPollORM(optPoll);
		
		//assert
		assertThat(optPollORM.isEmpty());
	}
	
	@Test
	public void presentOptPollShouldMapToPresentOptORM() {
		//arrange
		Poll poll = PollTestUtils.createPollNoId();
		Optional<Poll> optPoll = Optional.of(poll);
		Optional<PollORM> optPollORM;
		PollMapperImp spy = Mockito.spy(PollMapperImp.class);
		
		//act
		optPollORM = spy.mapOptPollORM(optPoll);
		
		//assert
		assertThat(optPollORM.isPresent());
		
		//Verify that it called the other method
		verify(spy).mapPollORM(poll);
	}
	
	@Test
	public void emptyORMOptionalShouldReturnEmptyPollOptional() {
		
		//arrange
		Optional<PollORM> optPollORM = Optional.empty();
		//act
		Optional<Poll> optPoll = underTest.mapOptPoll(optPollORM);
		
		//assert
		assertThat(optPoll.isEmpty());
	}
	
	@Test
	public void presentOptORMShouldMapToPresentOptPoll() {
		//arrange
		PollORM pollORM = PollORMTestUtils.createPollORMWithId();
		Optional<Poll> optPoll;
		Optional<PollORM> optPollORM = Optional.of(pollORM);
		PollMapperImp spy = Mockito.spy(PollMapperImp.class);
		
		//act
		optPoll = spy.mapOptPoll(optPollORM);
		
		//assert
		assertThat(optPoll.isPresent());
		
		//Verify that it called the other method
		verify(spy).mapPoll(pollORM);
	}
	
	@Test
	public void mapListOfORMToListOfPoll() {
		PollMapperImp spy = Mockito.spy(PollMapperImp.class);
		List<Poll> polls;
		int size = 5;
		polls = spy.mapPolls(PollORMTestUtils.createPollORMListWithId(size));
		
		assertThat(polls).size().isEqualTo(size);
		
		verify(spy, times(size)).mapPoll(any(PollORM.class));
		
	}
	
	
	@Test
	public void compareCollectionSizesORMToPoll() {
		//arrange
		PollORM pollORM = PollORMTestUtils.createPollORMWithId();
		int altORMCount = 0;
		int altCount = 0;

		//act
		Poll poll = underTest.mapPoll(pollORM);
		for(QuestionORM qORM : pollORM.getQuestions()) {
			altORMCount += qORM.getAlternatives().size();
		}
		for(Question q: poll.getQuestions()) {
			altCount += q.getAlternatives().size();
		}
		
		assertThat(pollORM.getQuestions().size())
		.isEqualTo(poll.getQuestions().size());

		assertThat(altCount).isEqualTo(altORMCount);
	}
	
	@Test
	public void compareCollectionSizesPollToORM() {
		//arrange
		Poll poll = PollTestUtils.createPollNoId();
		int altORMCount = 0;
		int altCount = 0;

		//act
		PollORM pollORM = underTest.mapPollORM(poll);
		for(QuestionORM qORM : pollORM.getQuestions()) {
			altORMCount += qORM.getAlternatives().size();
		}
		for(Question q: poll.getQuestions()) {
			altCount += q.getAlternatives().size();
		}

		assertThat(poll.getQuestions().size())
		.isEqualTo(pollORM.getQuestions().size());

		assertThat(altORMCount).isEqualTo(altCount);
	}
	
	@Test
	public void compareNonCollectionAttributesORMToPoll() {
		//arrange
		PollORM pollORM = PollORMTestUtils.createPollORMWithId();

		//act
		Poll poll = underTest.mapPoll(pollORM);
		//assert
		assertThat(pollORM.getDatePosted().toString())
			.isEqualTo(poll.getDatePosted());
			
		assertThat(pollORM.getPollId().toString())
			.isEqualTo(poll.getId());
		
		assertThat(pollORM.getDiscordId()).isEqualTo(poll.getDiscordId());
	}
	
	@Test
	public void compareNonCollectionAttributesPollToORM() {
		Poll poll = PollTestUtils.createPollWithId();
		PollORM pollORM = underTest.mapPollORM(poll);

		//assert
		assertThat(LocalDate.parse(poll.getDatePosted(), 
				WebMvcConfig.retrieveDateFormat()))
			.isEqualTo(pollORM.getDatePosted());

		assertThat(poll.getPoster().getdiscordId()).isEqualTo(pollORM.getDiscordId());
		
	}
	
	
	
	
	
}
