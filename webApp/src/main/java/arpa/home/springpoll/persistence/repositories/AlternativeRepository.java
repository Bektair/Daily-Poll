package arpa.home.springpoll.persistence.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arpa.home.springpoll.persistence.orm.AlternativeORM;
import arpa.home.springpoll.persistence.orm.QuestionORM;

@Repository
public interface AlternativeRepository 
	extends JpaRepository<AlternativeORM, BigInteger> {
 
	
	
}
