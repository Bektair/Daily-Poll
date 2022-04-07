package arpa.home.springpoll.data;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import arpa.home.springpoll.data.repositories.PollRepository;
import arpa.home.springpoll.usecase.entities.Poll;
import arpa.home.springpoll.usecase.gateways.PollGateway;

@Component
public class PollGatewayImp implements PollGateway {

	private final PollRepository pollRepo;
	
	private final PollMapper pollMapper;
	
	@Autowired
	public PollGatewayImp(PollRepository pollRepo, PollMapper pollMapper) {
		this.pollRepo = pollRepo;
		this.pollMapper = pollMapper;
	}
	
	
	@Override
	public Optional<Poll> getPollById(String id) {

		return null;
	}

	@Override
	public List<Poll> getAllPolls() {
		
		return pollMapper.mapPolls(pollRepo.findAll());
	}

	@Override
	public void deletePollById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePoll(Poll poll, String id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void savePoll(Poll poll) {
		
		pollRepo.save(pollMapper.mapPollORM(poll));
		
	}


	@Override
	public boolean existsById(java.lang.String Id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Optional<Poll> findPollByDatePosted(String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
