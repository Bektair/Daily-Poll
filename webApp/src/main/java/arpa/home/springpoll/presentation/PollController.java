package arpa.home.springpoll.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import arpa.home.springpoll.data.PollMapper;
import arpa.home.springpoll.data.orm.PollORM;
import arpa.home.springpoll.data.orm.QuestionORM;
import arpa.home.springpoll.usecase.PollService;
import arpa.home.springpoll.usecase.entities.Poll;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/poll")
public class PollController {

	private final PollService pollService;
	private final PollMapper pollMapper;
	
	@Autowired
	public PollController(PollService pollService, PollMapper pollMapper) {
		this.pollService = pollService;
		this.pollMapper = pollMapper;
	}
	
	@GetMapping
	public List<Poll> getPolls() {
		
		return pollService.getPolls();
	}
	
	@PostMapping
	public void registerNewPoll(@RequestBody PollORM poll) {
		pollService.addNewPoll(pollMapper.mapPoll(poll));
	}
	
	@DeleteMapping(path = "{pollId}")
	public void deletePoll(@PathVariable("pollId") BigInteger pollId) {
		pollService.deletePollById(pollId.toString());
	}
	
	
	@PutMapping(path = "{pollId}")
	public void updatePollLastAsked
	(@PathVariable("pollId") BigInteger pollId,
			@RequestParam 
			LocalDate postedDate) {
		System.out.println(postedDate);
		pollService.updatePoll(pollId.toString(), postedDate.toString());
		
		System.out.println("debug error plz");
		
	}
	
	
	
	

}
