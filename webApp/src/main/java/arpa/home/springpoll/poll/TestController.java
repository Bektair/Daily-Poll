package arpa.home.springpoll.poll;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arpa.home.springpoll.entities.Poll;

@RestController
@RequestMapping(path = "api/v1/test")
public class TestController {

	
	@GetMapping
	public List<String> getTest() {
		return List.of("fun", "times", "are", "coming");
	}
	
}
