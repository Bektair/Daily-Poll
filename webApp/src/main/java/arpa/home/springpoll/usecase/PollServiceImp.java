package arpa.home.springpoll.usecase;


import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arpa.home.springpoll.persistence.PollGatewayImp;
import arpa.home.springpoll.persistence.orm.PollORM;
import arpa.home.springpoll.persistence.repositories.PollRepository;
import arpa.home.springpoll.usecase.entities.Poll;
import arpa.home.springpoll.usecase.gateways.PollGateway;

//Service is business logic
//@Service or @Component tells that this is a bean which can be injected
@Service
public class PollServiceImp implements PollServiceInputBoundary {

	
	private final PollGateway pollGate;
	
	@Autowired
	public PollServiceImp(PollGateway pollGate) {
		this.pollGate=pollGate;
	}
	
	
  public List<Poll> getPolls(){
  	
  	List<Poll> polls = pollGate.getAllPolls();
  	
  	return polls;

  }
  
  public Optional<Poll> getPollById(BigInteger pollId) {
  	return null;
  	//return pollRepository.findById(pollId);
  }
  
  public void addNewPoll(Poll poll) {
  	Optional<Poll> PollsWithSamedatePostedDate = pollGate
  			.findPollByDatePosted(poll.getDatePosted());
  	Boolean isPresent = PollsWithSamedatePostedDate.isPresent(); 
  	if(isPresent) {
  		throw new IllegalArgumentException("Maximum one poll is sent a day: "
  				+ poll.getDatePosted());
  	}
  	
  	pollGate.savePoll(poll);
  	
  }

  
  public void deletePollById(String pollId) {
  	boolean exists = pollGate.existsById(pollId);
  	if(!exists) {
  		throw new IllegalArgumentException
  		("poll with id" + pollId + "does not exist");
  	}
  	pollGate.deletePollById(pollId);

  }
  
  //TODO add rollback
  @Transactional
  public void updatePoll(String pollId, String datePosted) {
//  	
//  	PollORM poll = pollRepository.getById(pollId);
//  	if(poll == null) {
//  		throw new IllegalStateException
//  		("poll with id" + pollId + "does not exist");
//  	}
//    //Set methods put entities into persistent state
//  	poll.setDatePosted(datePosted);
//  	
  }
  
}
