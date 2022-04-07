package arpa.home.springpoll.usecase.gateways;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import arpa.home.springpoll.usecase.entities.Poll;

public interface PollGateway {

	public Optional<Poll> getPollById(String id);
	
	public List<Poll> getAllPolls();
	
	public void deletePollById(String id);
	
	public void updatePoll(Poll poll, String id);
	
	public void savePoll(Poll poll);
	
	public boolean existsById(String id);
	
	public Optional<Poll> findPollByDatePosted(String date);
	
}
