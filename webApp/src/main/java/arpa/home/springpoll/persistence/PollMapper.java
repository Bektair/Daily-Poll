package arpa.home.springpoll.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import arpa.home.springpoll.persistence.orm.PollORM;
import arpa.home.springpoll.usecase.entities.Poll;


public interface PollMapper {
	
	public Poll mapPoll(PollORM pollORM);
	
	public List<Poll> mapPolls(List<PollORM> pollORMs);
	
	public PollORM mapPollORM(Poll poll);
	
	public Optional<Poll> mapOptPoll(Optional<PollORM> optPollORM);
	
	public Optional<PollORM> mapOptPollORM(Optional<Poll> optPollORM);

}
