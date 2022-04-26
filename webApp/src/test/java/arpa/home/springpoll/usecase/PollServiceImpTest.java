package arpa.home.springpoll.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import arpa.home.springpoll.persistence.orm.AlternativeORM;
import arpa.home.springpoll.persistence.orm.QuestionORM;
import arpa.home.springpoll.test_utils.PollTestUtils;
import arpa.home.springpoll.usecase.PollServiceImp;
import arpa.home.springpoll.usecase.entities.Poll;
import arpa.home.springpoll.usecase.gateways.PollGateway;

import org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class PollServiceImpTest {

	private PollServiceImp underTest;
	
	
	@Mock
	private PollGateway pollGate;
	
	@BeforeEach
	void setUp() {
		underTest = new PollServiceImp(pollGate);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	@Test
	void canGetAllPolls() {
		//Given
		
		//When
		underTest.getPolls();
		//Then(verifies that a method was invoked)
		verify(pollGate).getAllPolls();
	}
	
	
	

	@Test
	void canAddPoll() {
		//given
		Poll poll = PollTestUtils.createPollWithId();
		//when
		underTest.addNewPoll(poll);
		//then 
		ArgumentCaptor<Poll> pollArgumentCaptor = 
				ArgumentCaptor.forClass(Poll.class);
		verify(pollGate) //captures the value that is sent to the repo
			.savePoll(pollArgumentCaptor.capture());
	
		Poll capturedPoll = pollArgumentCaptor.getValue();
		
		assertThat(capturedPoll).isEqualTo(poll);
		
	}
	
	@Test
	void canDeleteExistingPollById() {
		//given
		String id = "1";
		when(pollGate.existsById(id)).thenReturn(true);	
		
		//when
		underTest.deletePollById(id);

		ArgumentCaptor<String> pollIdArgumentCaptor = 
				ArgumentCaptor.forClass(String.class);
		verify(pollGate) //captures the value that is sent to the repo
			.deletePollById(pollIdArgumentCaptor.capture());
		String capturedId = pollIdArgumentCaptor.getValue();
		//then
		assertThat(id).isEqualTo(capturedId);
	
	}
	
	@Test
	void throwsWhenDeleteByNonExistentId() {
		//given
		String id = "1";
		when(pollGate.existsById(id)).thenReturn(false);	
		//
		assertThatThrownBy(() -> underTest.deletePollById(id))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("poll with id" + id + "does not exist");
		
	}
	
	
	
	
	@Test
	void willThrowWhenPollDateIsTaken() {
		//given
		Poll poll = PollTestUtils.createPollWithId();
		when(pollGate.findPollByDatePosted(poll.getDatePosted()))
			.thenReturn(Optional.of(poll));		
		//when
		//then
		assertThatThrownBy(() -> underTest.addNewPoll(poll))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Maximum one poll is sent a day: "
	  				+ poll.getDatePosted());	
		verify(pollGate, never()).savePoll(any());
	}
	
	@Test
	void canUpdatePoll() {
		
	}
 
}
