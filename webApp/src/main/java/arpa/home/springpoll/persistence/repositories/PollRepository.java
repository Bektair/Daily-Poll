package arpa.home.springpoll.persistence.repositories;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arpa.home.springpoll.persistence.orm.PollORM;

@Repository
public interface PollRepository
	extends JpaRepository<PollORM, BigInteger>{

	// Select * from poll where DatePosted = x
	Optional<PollORM> findPollByDatePosted(LocalDate datePosted);
	
	
	
}
