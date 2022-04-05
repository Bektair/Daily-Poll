package arpa.home.springpoll.usecase;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arpa.home.springpoll.data.AlternativeRepository;
import arpa.home.springpoll.data.QuestionRepository;
import arpa.home.springpoll.entities.Alternative;
import arpa.home.springpoll.entities.Poll;
import arpa.home.springpoll.entities.Question;

@Service
public class QuestService {

private final QuestionRepository questRepository;
	
@Autowired
private AltService altService;

	@Autowired
	public QuestService(QuestionRepository questRepository) {
		this.questRepository=questRepository;
	}

	public void addQuestions(Question... questions) {
		for(Question q : questions) {
			altService.addAlternatives(q.getAlternatives());
			addQuestion(q);
		}
	}
	
	private boolean isQuestionInDB(Question quest) {
		if(quest.getQuestId()==null) return false;
		if(questRepository.findById(quest.getQuestId())
				.isPresent()) return true;
		else throw new IllegalArgumentException("There is no question with "
				+ "the ID specified either remove the id to create a new question"
				+ " or change it");
	}
	
	public void addQuestionWithAlternatives(Collection<Question> questions) {
		Collection<Question> questionsNotInDB = new HashSet<Question>();
		for(Question q : questions) {
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
	
	public void addQuestion(Question question) {
		questRepository.save(question);
	}
	
	
	
	
	

	
	
  public List<Question> getQuestions(){
  	return questRepository.findAll();

  }
  
	
	
	
}
