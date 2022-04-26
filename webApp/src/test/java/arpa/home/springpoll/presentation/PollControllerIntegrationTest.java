package arpa.home.springpoll.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import arpa.home.springpoll.persistence.orm.PollORM;
import arpa.home.springpoll.persistence.repositories.PollRepository;
import arpa.home.springpoll.test_utils.PollORMTestUtils;
import arpa.home.springpoll.usecase.entities.Poll;

@SpringBootTest
@AutoConfigureMockMvc
public class PollControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	PollRepository pollRepo;
	
	
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		
		PollORM test = PollORMTestUtils.createPollORMNoId();
		pollRepo.save(test);
		
		this.mockMvc.perform(get("/api/v1/poll"))
			.andDo(print())
			 .andExpect(content().contentType(
			  		MediaType.APPLICATION_JSON))
			  .andExpect(jsonPath("$[*].datePosted")
			  		.value(test.getDatePosted().toString()))
				.andExpect(jsonPath("$[*].id")
					.value(test.getPollId().toString()))
			.andExpect(status().isOk());
	

		
	}
	
	
	
}
