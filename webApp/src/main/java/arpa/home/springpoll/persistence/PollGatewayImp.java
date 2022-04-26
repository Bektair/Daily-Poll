package arpa.home.springpoll.persistence;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import arpa.home.springpoll.persistence.orm.PollORM;
import arpa.home.springpoll.persistence.repositories.PollRepository;
import arpa.home.springpoll.usecase.entities.Poll;
import arpa.home.springpoll.usecase.gateways.PollGateway;

@Component
public class PollGatewayImp implements PollGateway {


	private static final Logger LOGGER = Logger.getLogger(PollGatewayImp.class);
	
	private final PollRepository pollRepo;
	
	private final PollMapper pollMapper;
	
	@Autowired
	public PollGatewayImp(PollRepository pollRepo, PollMapper pollMapper) {
		this.pollRepo = pollRepo;
		this.pollMapper = pollMapper;
	}
	
	public PollGatewayImp() {
		this.pollRepo = null;
		this.pollMapper = null;
	}
	
	
	@Override
	public Optional<Poll> getPollById(String id) {
		if(!isValidId(id)) { 
			LOGGER.warn("You sent getPollById an invalid id, use whole positive numbers");
			return Optional.empty();
		}
		BigInteger big = new BigInteger(id);
		return pollMapper.mapOptPoll(pollRepo.findById(big));
	}

	@Override
	public List<Poll> getAllPolls() {

		
		return pollMapper.mapPolls(pollRepo.findAll());
	}

	@Override
	public void deletePollById(String id) {
		
		if(!isValidId(id)) 			
			throw new NumberFormatException("You sent DeletePoll an invalid id, use whole positive numbers");

		BigInteger ORMId = new BigInteger(id);
		pollRepo.deleteById(ORMId);

	
	}
	
	public boolean isValidId(String id) {
		try {
			if(id==null || id.isBlank()) return false;
			BigInteger bigId = new BigInteger(id);
			if(bigId.compareTo(BigInteger.ZERO)<0) return false;
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
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
