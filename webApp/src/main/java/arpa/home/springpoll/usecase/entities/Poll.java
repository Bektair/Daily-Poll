package arpa.home.springpoll.usecase.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Poll implements Serializable{

	private String datePosted;
	private User poster;
	private Collection<Question> questions;
	private String id="-1";
	
	public Poll() {
		super();
	}

	public Poll (String datePosted, 
			User poster, 
			Collection<Question> questions, 
			String id) {
		super();
		this.datePosted=datePosted;
		this.poster = poster;
		this.questions = questions;
		this.id = id;
	}
	
	public Poll(String datePosted, User poster,
			Collection<Question> questions) {
		this.datePosted=datePosted;
		this.poster = poster;
		this.questions = questions;
	}

	public String getId() {
		return id;
	}

	public String getDatePosted() {
		return datePosted;
	}


	public User getPoster() {
		return poster;
	}
	
	public String getDiscordId() {
		return poster.getdiscordId();
	}



	public Collection<Question> getQuestions() {
		return new HashSet<Question>(questions);
	}


	
	

	
}
