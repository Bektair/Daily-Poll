package arpa.home.springpoll.usecase;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arpa.home.springpoll.data.AlternativeRepository;
import arpa.home.springpoll.data.QuestionRepository;
import arpa.home.springpoll.entities.Alternative;

@Service
public class AltService {

	
private final AlternativeRepository altRepository;
	
	@Autowired
	public AltService(AlternativeRepository altRepository) {
		this.altRepository=altRepository;
	}
	
	public void addAlternative(Alternative alt) {
		altRepository.save(alt);
	}
	
	public void addAlternatives(Alternative...alts) {
		for(Alternative a : alts) {
			addAlternative(a);
		}
	}
	
	public void addAlternatives(Set<Alternative> alts) {
		for(Alternative a : alts) {
			addAlternative(a);
		}
	}
	
	
}
