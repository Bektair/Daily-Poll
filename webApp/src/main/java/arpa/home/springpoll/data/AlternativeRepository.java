package arpa.home.springpoll.data;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arpa.home.springpoll.entities.Alternative;
import arpa.home.springpoll.entities.Question;

@Repository
public interface AlternativeRepository 
	extends JpaRepository<Alternative, BigInteger> {
 
	
	
}
