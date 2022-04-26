package arpa.home.springpoll.usecase.entities;

import java.util.Collection;
import java.util.HashSet;

public class Question {

	
	private String questTxt;
	private Collection<Alternative> alternatives;
	private String id;
	
	public Question() {
		super();
	}

	public Question(String questTxt, 
			Collection<Alternative> alternatives, String id) {
		this.questTxt=questTxt;
		this.alternatives=alternatives;
		this.id = id;
	}

	
	public String getId() {
		return id;
	}


	public String getQuestTxt() {
		return questTxt;
	}


	public Collection<Alternative> getAlternatives() {
		return new HashSet<Alternative>(alternatives);
	}

	
}
