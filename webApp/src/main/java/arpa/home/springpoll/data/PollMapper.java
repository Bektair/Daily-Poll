package arpa.home.springpoll.data;

import java.util.List;

import org.springframework.stereotype.Component;

import arpa.home.springpoll.data.orm.PollORM;
import arpa.home.springpoll.usecase.entities.Poll;

@Component
public interface PollMapper {
	
	public Poll mapPoll(PollORM pollORM);
	
	public List<Poll> mapPolls(List<PollORM> pollORMs);
	
	public PollORM mapPollORM(Poll poll);
	
	
}
