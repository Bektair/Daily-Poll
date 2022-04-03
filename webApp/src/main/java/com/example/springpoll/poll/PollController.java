package com.example.springpoll.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springpoll.entities.Poll;
import com.example.springpoll.entities.Question;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/poll")
public class PollController {

	private final PollService pollService;

	@Autowired
	public PollController(PollService pollService) {
		this.pollService = pollService;
	}
	
	@GetMapping
	public List<Poll> getPolls() {
		return pollService.getPolls();
	}
	
	@PostMapping
	public void registerNewPoll(@RequestBody Poll poll) {
		

		pollService.addNewPoll(poll);
	}
	
	@DeleteMapping(path = "{pollId}")
	public void deletePoll(@PathVariable("pollId") BigInteger pollId) {
		pollService.deletePollById(pollId);
	}
	
	
	@PutMapping(path = "{pollId}")
	public void updatePollLastAsked
	(@PathVariable("pollId") BigInteger pollId,
			@RequestParam LocalDate postedDate) {
		System.out.println(postedDate);
		pollService.updatePoll(pollId, postedDate);
	}
	
	
	
	

}
