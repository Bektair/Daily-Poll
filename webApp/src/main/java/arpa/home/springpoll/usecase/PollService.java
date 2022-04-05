package arpa.home.springpoll.usecase;


import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arpa.home.springpoll.data.PollRepository;
import arpa.home.springpoll.entities.Poll;

//Service is business logic
//@Service or @Component tells that this is a bean which can be injected
@Service
public class PollService {

	private final PollRepository pollRepository;
	
	@Autowired
	public PollService(PollRepository pollRepository) {
		this.pollRepository=pollRepository;
	}
	
	
  public List<Poll> getPolls(){
  	return pollRepository.findAll();

  }
  
  public Optional<Poll> getPollById(BigInteger pollId) {
  	return pollRepository.findById(pollId);
  }
  
  public void addNewPoll(Poll poll) {
  	Optional<Poll> PollsWithSamedatePostedDate = pollRepository
  			.findPollByDatePosted(poll.getDatePosted());
  	Boolean isPresent = PollsWithSamedatePostedDate.isPresent(); 
  	if(isPresent) {
  		throw new IllegalArgumentException("Maximum one poll is sent a day: "
  				+ poll.getDatePosted());
  	}
  	pollRepository.save(poll);
  	
  }

  
  public void deletePollById(BigInteger pollId) {
  	boolean exists = pollRepository.existsById(pollId);
  	if(!exists) {
  		throw new IllegalArgumentException
  		("poll with id" + pollId + "does not exist");
  	}
  	pollRepository.deleteById(pollId);
  	
  }
  
  //TODO add rollback
  @Transactional
  public void updatePoll(BigInteger pollId, LocalDate datePosted) {
  	Poll poll = pollRepository.getById(pollId);
  	if(poll == null) {
  		throw new IllegalStateException
  		("poll with id" + pollId + "does not exist");
  	}
    //Set methods put entities into persistent state
  	poll.setDatePosted(datePosted);
  	
  }
  
}
