package arpa.home.springpoll.data.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arpa.home.springpoll.data.orm.AlternativeORM;
import arpa.home.springpoll.data.orm.QuestionORM;

@Repository
public interface AlternativeRepository 
	extends JpaRepository<AlternativeORM, BigInteger> {
 
	
	
}
