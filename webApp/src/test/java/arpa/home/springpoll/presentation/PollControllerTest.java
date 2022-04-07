package arpa.home.springpoll.presentation;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

import arpa.home.springpoll.WebMvcConfig;
import arpa.home.springpoll.data.PollMapper;
import arpa.home.springpoll.data.orm.PollORM;
import arpa.home.springpoll.presentation.PollController;
import arpa.home.springpoll.test_utils.PollORMTestUtils;
import arpa.home.springpoll.test_utils.PollTestUtils;
import arpa.home.springpoll.usecase.PollService;
import arpa.home.springpoll.usecase.entities.Poll;
import arpa.home.springpoll.usecase.gateways.PollGateway;

@WebMvcTest(PollController.class) //limited to web layer and class
class PollControllerTest {
	

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PollService pollService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PollMapper pollMapper;
	



	@BeforeEach
	void start() {
	}
	
	@Test 
	public void getShouldReturnPollAsJsonContent() throws Exception {
		//arrange
		Poll poll = PollTestUtils.createPollWithId();
		String datePosted = poll.getDatePosted();
		when(pollService.getPolls()).thenReturn(List.of(poll));
		
		//act
		this.mockMvc.perform(get("/api/v1/poll")) 
			.andDo(print()) 
		//assert
		  .andExpect(status().isOk()) 
		  .andExpect(content().contentType(
		  		MediaType.APPLICATION_JSON))
		  .andExpect(jsonPath("$[*].datePosted")
		  		.value(datePosted))
			.andExpect(jsonPath("$[*].id")
				.value(poll.getId()));
	}
	
	
	@Test
	public void shouldBeAbleToPostPollInRequestBody() throws Exception {
		//arrange
		PollORM poll = PollORMTestUtils.createPollNoId();
		String content = objectMapper.writeValueAsString(poll);
		
		//act
		this.mockMvc.perform(post("/api/v1/poll")
				.contentType("application/json")
				.content(content))
				.andDo(print()) 
				.andExpect(status().isOk()); //assert
	}
	
	@Test
	public void shouldReceiveAndDelegateDeletePollRequest() throws Exception {
		//arrange
		String pollId = "1";

		//act
		mockMvc.perform(delete("/api/v1/poll/"+pollId))
		.andDo(print()); 
			
		ArgumentCaptor<String> pollIdArgumentCaptor=
				ArgumentCaptor.forClass(String.class);
		verify(pollService)
			.deletePollById(pollIdArgumentCaptor.capture());
		
		//assert
		assertThat(pollIdArgumentCaptor.getValue())
				.isEqualTo(pollId);
	}
	
	@Test
	public void putPollRequest() throws Exception {
		//arrenge
		BigInteger pollId = BigInteger.ONE;
		String postedDateParam = "11/11/2000";
		LocalDate postedDate = LocalDate.parse(postedDateParam, 
				WebMvcConfig.retrieveDateFormat());
		
		//act
		MvcResult result = mockMvc.perform(put("/api/v1/poll/"+pollId.intValue())
				.param("postedDate", postedDateParam)
				)
				.andDo(print()) 
				.andReturn();
		
		ArgumentCaptor<String> pollIdArgumentCaptor=
				ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> postedDateArgumentCaptor=
				ArgumentCaptor.forClass(String.class);
		verify(pollService)
			.updatePoll(pollIdArgumentCaptor.capture()
				, postedDateArgumentCaptor.capture());
		//assert
		assertThat(result.getResponse().getStatus())
			.isEqualTo(200);
		
		assertThat(pollIdArgumentCaptor.getValue())
			.isEqualTo(pollId.toString());
		assertThat(postedDateArgumentCaptor.getValue())
			.isEqualTo(LocalDate.parse(postedDateParam, WebMvcConfig.retrieveDateFormat()).toString());
		
		
		
	}
	

}
