package arpa.home.springpoll.usecase;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arpa.home.springpoll.data.orm.AlternativeORM;
import arpa.home.springpoll.data.orm.PollORM;
import arpa.home.springpoll.data.orm.QuestionORM;
import arpa.home.springpoll.data.repositories.AlternativeRepository;
import arpa.home.springpoll.data.repositories.QuestionRepository;

@Service
public class QuestService {

private final QuestionRepository questRepository;
	
@Autowired
private AltService altService;

	@Autowired
	public QuestService(QuestionRepository questRepository) {
		this.questRepository=questRepository;
	}

	public void addQuestions(QuestionORM... questions) {
		for(QuestionORM q : questions) {
			altService.addAlternatives(q.getAlternatives());
			addQuestion(q);
		}
	}
	
	private boolean isQuestionInDB(QuestionORM quest) {
		if(quest.getQuestId()==null) return false;
		if(questRepository.findById(quest.getQuestId())
				.isPresent()) return true;
		else throw new IllegalArgumentException("There is no question with "
				+ "the ID specified either remove the id to create a new question"
				+ " or change it");
	}
	
	public void addQuestionWithAlternatives(Collection<QuestionORM> questions) {
		Collection<QuestionORM> questionsNotInDB = new HashSet<QuestionORM>();
		for(QuestionORM q : questions) {
			System.out.println(q.getQuestTxt() + " -------------------------");
			if(!isQuestionInDB(q)) {
				System.out.println(q.getAlternatives());
				//altService.addAlternatives(q.getAlternatives());
				questionsNotInDB.add(q);
			}
		}
		if(questionsNotInDB.size()>0)
		  questRepository.saveAll(questionsNotInDB);
	}
	
	public void addQuestion(QuestionORM question) {
		questRepository.save(question);
	}
	
	
	
	
	

	
	
  public List<QuestionORM> getQuestions(){
  	return questRepository.findAll();

  }
  
	
	
	
}
