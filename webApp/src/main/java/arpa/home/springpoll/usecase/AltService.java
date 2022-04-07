package arpa.home.springpoll.usecase;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arpa.home.springpoll.data.orm.AlternativeORM;
import arpa.home.springpoll.data.repositories.AlternativeRepository;
import arpa.home.springpoll.data.repositories.QuestionRepository;

@Service
public class AltService {

	
private final AlternativeRepository altRepository;
	
	@Autowired
	public AltService(AlternativeRepository altRepository) {
		this.altRepository=altRepository;
	}
	
	public void addAlternative(AlternativeORM alt) {
		altRepository.save(alt);
	}
	
	public void addAlternatives(AlternativeORM...alts) {
		for(AlternativeORM a : alts) {
			addAlternative(a);
		}
	}
	
	public void addAlternatives(Set<AlternativeORM> alts) {
		for(AlternativeORM a : alts) {
			addAlternative(a);
		}
	}
	
	
}
