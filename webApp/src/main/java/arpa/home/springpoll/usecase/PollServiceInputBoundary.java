package arpa.home.springpoll.usecase;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import arpa.home.springpoll.usecase.entities.Poll;

public interface PollServiceInputBoundary {

	List<Poll> getPolls();
	
	Optional<Poll> getPollById(BigInteger pollId);
	
	void addNewPoll(Poll poll);
	
	void deletePollById(String pollId);
	
	void updatePoll(String pollId, String datePosted);
	
	
	
	
	
	
	
	
}
