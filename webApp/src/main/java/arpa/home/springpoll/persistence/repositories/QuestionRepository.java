package arpa.home.springpoll.persistence.repositories;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arpa.home.springpoll.persistence.orm.PollORM;
import arpa.home.springpoll.persistence.orm.QuestionORM;

@Repository
public interface QuestionRepository 
	extends JpaRepository<QuestionORM, BigInteger> {
 
	
	
}
