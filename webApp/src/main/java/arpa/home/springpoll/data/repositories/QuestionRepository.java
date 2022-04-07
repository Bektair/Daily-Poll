package arpa.home.springpoll.data.repositories;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arpa.home.springpoll.data.orm.PollORM;
import arpa.home.springpoll.data.orm.QuestionORM;

@Repository
public interface QuestionRepository 
	extends JpaRepository<QuestionORM, BigInteger> {
 
	
	
}
