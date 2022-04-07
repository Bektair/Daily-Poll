package arpa.home.springpoll.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PollControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	

	public void shouldReturnDefaultMessage() throws Exception {
		//this.mockMvc.perform(get("/api/v1/poll")).andDo(print()).andExpect(status().isOk());
	}
	
	
	
}