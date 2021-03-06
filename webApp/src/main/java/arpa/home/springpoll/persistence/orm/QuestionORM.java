package arpa.home.springpoll.persistence.orm;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;




@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Question")
public class QuestionORM implements Serializable {
	@Column
	private String questTxt;
	@Id
	@GeneratedValue(generator="questSequenceGenerator")
	@SequenceGenerator(
      name = "questSequenceGenerator",
      sequenceName="questSequence", 
      allocationSize=1, initialValue=4
  )
	private BigInteger questId;

	@ManyToMany(fetch = FetchType.LAZY,
			targetEntity= PollORM.class,
      mappedBy="questions")
	@JsonIgnoreProperties("questions")
	private Set<PollORM> polls = new HashSet<>();
	

	@OneToMany(cascade = {
      		CascadeType.PERSIST, 
      		CascadeType.DETACH,
      		CascadeType.MERGE,
      		CascadeType.REFRESH
      })
	@JoinTable
	(
			name="Quest_Alt",
			joinColumns= {@JoinColumn(name="questId")},
			inverseJoinColumns= {@JoinColumn(name="altId")}
	)
	private Set<AlternativeORM> alternatives = new LinkedHashSet<>();

	public QuestionORM(String questionTxt, AlternativeORM...alternatives) {
		this.questTxt= questionTxt;

		for(AlternativeORM a : alternatives) {
			this.alternatives.add(a);
		}
	}
	
	public QuestionORM(BigInteger questId, String questionTxt, AlternativeORM... alternatives) {
		this.questTxt= questionTxt;
		this.questId = questId;
		for(AlternativeORM a : alternatives) {
			this.alternatives.add(a);
		}
	}
	
	public QuestionORM(String questionTxt, Set<AlternativeORM> alternatives) {
		this.questTxt= questionTxt;
		for(AlternativeORM a : alternatives) {
			this.alternatives.add(a);
		}
	}
	
	public QuestionORM(BigInteger questId, String questionTxt, Set<AlternativeORM> alternatives) {
		this.questTxt= questionTxt;
		this.questId = questId;
		for(AlternativeORM a : alternatives) {
			this.alternatives.add(a);
		}
	}
	
	
	
	public void addPoll(PollORM poll) {
		polls.add(poll);
	}
	
	public void removePoll(PollORM poll) {
		polls.remove(poll);
	}
	
	
	@Override
	public boolean equals(Object x) {
		if(x == null) return false;
		if(this.getClass() != x.getClass()) return false;
		QuestionORM that = ((QuestionORM)x);
		if(this.getQuestId()!=null)
			return (this.getQuestId().compareTo(that.getQuestId()))==0;
		else 
			return (this.getQuestTxt().equals(that.getQuestTxt()));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(questId, questTxt);
	}
	
	@Override
	public String toString() {
		String questString = questTxt;
		for(AlternativeORM a : alternatives) {
			questString+="\n";
			questString+=a.getAltTxt();
		}
		return questString;
	}

	public String getQuestTxt() {
		return questTxt;
	}

	public void setQuestTxt(String questTxt) {
		this.questTxt = questTxt;
	}

	public Set<AlternativeORM> getAlternatives() {
		return new LinkedHashSet<AlternativeORM>(alternatives);
	}

	public BigInteger getQuestId() {
		return questId;
	}

}
