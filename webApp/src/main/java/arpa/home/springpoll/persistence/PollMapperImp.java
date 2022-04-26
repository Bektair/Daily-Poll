package arpa.home.springpoll.persistence;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import arpa.home.springpoll.WebMvcConfig;
import arpa.home.springpoll.persistence.orm.AlternativeORM;
import arpa.home.springpoll.persistence.orm.PollORM;
import arpa.home.springpoll.persistence.orm.QuestionORM;
import arpa.home.springpoll.usecase.entities.Alternative;
import arpa.home.springpoll.usecase.entities.Poll;
import arpa.home.springpoll.usecase.entities.Question;
import arpa.home.springpoll.usecase.entities.User;



public class PollMapperImp implements PollMapper, Serializable{

	public PollMapperImp() {
		
	}
	
	@Override
	public Poll mapPoll(PollORM pollORM) {
		Collection<Question> questions = new HashSet<Question>();
		
		for(QuestionORM qORM : pollORM.getQuestions()) {
			
			Collection<Alternative> alternatives = new HashSet<Alternative>();
			
			for(AlternativeORM aORM : qORM.getAlternatives()) {
				
				Alternative a = new Alternative(aORM.getAltTxt(), 
						aORM.getAltOrder(), aORM.getEmojiId(), aORM.getAltId().toString());
				alternatives.add(a);
				
			}
			Question q = new Question(qORM.getQuestTxt(), alternatives, 
					qORM.getQuestId().toString());
			questions.add(q);
			
		}
		System.out.println(questions.toString());
		
		return new Poll(pollORM.getDatePosted().toString()
				, new User("coolName", pollORM.getDiscordId())
				, questions, pollORM.getPollId().toString());
		
	}

	@Override
	public List<Poll> mapPolls(List<PollORM> pollORMs) {
		List<Poll> polls = new ArrayList<>();
		for(PollORM pollORM : pollORMs) {
			polls.add(mapPoll(pollORM));
		}
		
		return polls;
	}

	@Override
	public PollORM mapPollORM(Poll poll) {
		
		Set<QuestionORM> questionsORM = new HashSet<>();
		for(Question quest : poll.getQuestions()) {
			Set<AlternativeORM> alternativesORM = new HashSet<>();
			for(Alternative alt : quest.getAlternatives()) {
				alternativesORM.add(new AlternativeORM(alt.getAlternativeOrder(), 
						alt.getAlternativeTxt(), alt.getEmoji()));
			}
			questionsORM.add(new QuestionORM(quest.getQuestTxt(), alternativesORM));
		}
		
		
		
		return (new PollORM(poll.getDiscordId(), LocalDate.parse
				(poll.getDatePosted(), WebMvcConfig.retrieveDateFormat()),
				questionsORM));
	}

	@Override
	public Optional<Poll> mapOptPoll(Optional<PollORM> optPollORM) {
		if(optPollORM.isEmpty()) return Optional.empty();
		
		Optional<Poll> optPoll = 
				Optional.of(mapPoll(optPollORM.get()));
		
		return optPoll;
	}

	@Override
	public Optional<PollORM> mapOptPollORM(Optional<Poll> optPoll) {
		if(optPoll.isEmpty()) return Optional.empty();
		
		Optional<PollORM> optPollORM = 
				Optional.of(mapPollORM(optPoll.get()));
		return optPollORM;
	}
}
