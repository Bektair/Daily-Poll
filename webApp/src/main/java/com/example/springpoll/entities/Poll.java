package com.example.springpoll.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;



import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Poll")
public class Poll implements Serializable {
	@Id
	@GeneratedValue(generator="pollSequenceGenerator")
	@SequenceGenerator(
      name = "pollSequenceGenerator",
      sequenceName="pollSequence", 
      allocationSize=1, initialValue=4
  )
	private BigInteger pollId;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column
	private LocalDate datePosted;
	
	@ManyToMany(
			cascade = {
      		CascadeType.PERSIST, 
      		CascadeType.DETACH,
      		CascadeType.MERGE,
      		CascadeType.REFRESH
      })
	@JoinTable(
		  name ="poll_question",
			joinColumns=
				@JoinColumn(name="pollId"),
			inverseJoinColumns=
			  @JoinColumn(name="questId")
		)
	@OrderBy("questId ASC")
	@JsonIgnoreProperties("polls")
	private Set<Question> questions = new LinkedHashSet<>(); 

	@Transient //Not it's own column in db table
	private Integer monthsSincePosted;
	
	public Poll(LocalDate datePosted, Set<Question> questions) {
		this.datePosted=datePosted;
		for(Question q : questions) {
			addQuestion(q);
		}
	}
	public Poll(BigInteger id, LocalDate datePosted, Set<Question> questions) {
		this.pollId = id;
		this.datePosted=datePosted;
		for(Question q : questions) {
			addQuestion(q);
		}
	}
	
	public Poll(LocalDate datePosted, Question... questions) {
		this.datePosted=datePosted;
		for(Question q : questions) {
			addQuestion(q);
		}
	}
	
	
	public Integer getMonthsSincePosted() {
		Period p = Period.between(this.datePosted, LocalDate.now());
		return p.getYears()*12 + p.getMonths();
	}
	
	//Maintains manyToMany relation
	public void addQuestion(Question quest) {
		if(questions.contains(quest)) return;
		questions.add(quest);
		quest.addPoll(this);
	}
	
	public void removeQuestion(Question quest) {
		if(!questions.contains(quest)) return;
		questions.remove(quest);
		quest.removePoll(this);
	}
	
	@Override
	public boolean equals(Object x) {
		if(x == null) return false;
		if(this.getClass() != x.getClass()) return false;
		Poll that = ((Poll)x);		
		if(this.getPollId()!=null)
			return this.getPollId().equals(that.getPollId());
		else
			return (this.datePosted.equals(that.getDatePosted())) 
					&& this.getQuestions().size() == that.getQuestions().size();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(pollId, datePosted);
	}

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	
	public Set<Question> getQuestions() {
		return new LinkedHashSet<Question>(questions);
	}

	public BigInteger getPollId() {
		return pollId;
	}
	

	


}
