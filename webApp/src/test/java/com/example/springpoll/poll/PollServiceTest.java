package com.example.springpoll.poll;

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

import arpa.home.springpoll.data.PollRepository;
import arpa.home.springpoll.entities.Alternative;
import arpa.home.springpoll.entities.Poll;
import arpa.home.springpoll.entities.Question;
import arpa.home.springpoll.usecase.PollService;

import org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class PollServiceTest {

	private PollService underTest;
	
	
	@Mock
	private PollRepository pollRepo;
	
	@BeforeEach
	void setUp() {
	
		underTest = new PollService(pollRepo);
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
		verify(pollRepo).findAll();
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
		verify(pollRepo) //captures the value that is sent to the repo
			.save(pollArgumentCaptor.capture());
	
		Poll capturedPoll = pollArgumentCaptor.getValue();
		
		assertThat(capturedPoll).isEqualTo(poll);
		
	}
	
	@Test
	void canDeleteExistingPollById() {
		//given
		BigInteger id = BigInteger.ONE;
		when(pollRepo.existsById(id)).thenReturn(true);	
		
		//when
		underTest.deletePollById(id);

		ArgumentCaptor<BigInteger> pollIdArgumentCaptor = 
				ArgumentCaptor.forClass(BigInteger.class);
		verify(pollRepo) //captures the value that is sent to the repo
			.deleteById(pollIdArgumentCaptor.capture());
		BigInteger capturedId = pollIdArgumentCaptor.getValue();
		//then
		assertThat(id).isEqualTo(capturedId);
	
	}
	
	@Test
	void throwsWhenDeleteByNonExistentId() {
		//given
		BigInteger id = BigInteger.ONE;
		when(pollRepo.existsById(id)).thenReturn(false);	
		//
		assertThatThrownBy(() -> underTest.deletePollById(id))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("poll with id" + id + "does not exist");
		
	}
	
	
	
	
	@Test
	void willThrowWhenPollDateIsTaken() {
		//given
		Poll poll = PollTestUtils.createPollWithId();
		when(pollRepo.findPollByDatePosted(poll.getDatePosted()))
			.thenReturn(Optional.of(poll));		
		//when
		//then
		assertThatThrownBy(() -> underTest.addNewPoll(poll))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Maximum one poll is sent a day: "
	  				+ poll.getDatePosted());	
		verify(pollRepo, never()).save(any());
	}
	
	@Test
	void canUpdatePoll() {
		
	}
 
}
