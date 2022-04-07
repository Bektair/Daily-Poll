package arpa.home.springpoll.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arpa.home.springpoll.data.orm.QuestionORM;
import arpa.home.springpoll.usecase.QuestService;

@RestController
@RequestMapping(path = "api/v1/quest")
public class QuestController {

	private final QuestService questService;

	@Autowired
	public QuestController(QuestService questService) {
		this.questService = questService;
	}
	
	@GetMapping
	public List<QuestionORM> getQuestions(){
		return questService.getQuestions();
	}
	
	
}
