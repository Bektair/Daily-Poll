package com.example.springpoll.alternative;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springpoll.entities.Alternative;
import com.example.springpoll.question.QuestionRepository;

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
