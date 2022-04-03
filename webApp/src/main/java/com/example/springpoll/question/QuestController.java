package com.example.springpoll.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springpoll.entities.Question;

@RestController
@RequestMapping(path = "api/v1/quest")
public class QuestController {

	private final QuestService questService;

	@Autowired
	public QuestController(QuestService questService) {
		this.questService = questService;
	}
	
	@GetMapping
	public List<Question> getQuestions(){
		return questService.getQuestions();
	}
	
	
}
