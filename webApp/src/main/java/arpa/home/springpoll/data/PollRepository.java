package arpa.home.springpoll.poll;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arpa.home.springpoll.entities.Poll;

@Repository
public interface  PollRepository
	extends JpaRepository<Poll, BigInteger>{

	// Select * from poll where DatePosted = x
	Optional<Poll> findPollByDatePosted(LocalDate datePosted);
	
	
	
}
