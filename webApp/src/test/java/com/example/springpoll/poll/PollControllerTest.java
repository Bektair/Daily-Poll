package com.example.springpoll.poll;


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
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.springpoll.businessRule.dateFormat;
import com.example.springpoll.entities.Poll;
import com.example.springpoll.poll.PollController;
import com.example.springpoll.poll.PollService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PollController.class) //limited to web layer and class
class PollControllerTest {
	

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PollService pollService;
	
	@Autowired
	private ObjectMapper objectMapper;
	


	
	@Test 
	public void getShouldReturnPollAsJsonContent() throws Exception {
		//arrange
		Poll poll = PollTestUtils.createPollWithId();
		LocalDate datePosted = poll.getDatePosted();
		when(pollService.getPolls()).thenReturn(List.of(poll));
		
		//act
		this.mockMvc.perform(get("/api/v1/poll")) 
			.andDo(print()) 
		//assert
		  .andExpect(status().isOk()) 
		  .andExpect(content().contentType(
		  		MediaType.APPLICATION_JSON))
		  .andExpect(jsonPath("$[*].datePosted")
		  		.value(datePosted
		  				.format(dateFormat.retrieveDateFormat())))
			.andExpect(jsonPath("$[*].pollId")
				.value(poll.getPollId().intValue()));
	}
	
	
	@Test
	public void shouldBeAbleToPostPollInRequestBody() throws Exception {
		//arrange
		Poll poll = PollTestUtils.createPollNoId();
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
		BigInteger pollId = BigInteger.ONE;

		//act
		mockMvc.perform(delete("/api/v1/poll/"+pollId.intValue()))
		.andDo(print()); 
			
		ArgumentCaptor<BigInteger> pollIdArgumentCaptor=
				ArgumentCaptor.forClass(BigInteger.class);
		verify(pollService)
			.deletePollById(pollIdArgumentCaptor.capture());
		
		//assert
		assertThat(pollIdArgumentCaptor.getValue())
				.isEqualByComparingTo(pollId);
	}
	
	@Test
	public void putPollRequest() throws Exception {
		
		//arrenge
		BigInteger pollId = BigInteger.ONE;
		String postedDateParam = "1600-01-01";
		
		//act
		MvcResult result = mockMvc.perform(put("/api/v1/poll/"+pollId.intValue()
			+"?postedDate="+postedDateParam))
				.andDo(print()) 
				.andReturn();
		
		ArgumentCaptor<BigInteger> pollIdArgumentCaptor=
				ArgumentCaptor.forClass(BigInteger.class);
		ArgumentCaptor<LocalDate> postedDateArgumentCaptor=
				ArgumentCaptor.forClass(LocalDate.class);
		verify(pollService)
			.updatePoll(pollIdArgumentCaptor.capture()
				, postedDateArgumentCaptor.capture());
		//assert
		assertThat(result.getResponse().getStatus())
			.isEqualTo(200);
		assertThat(pollIdArgumentCaptor.getValue())
			.isEqualTo(pollId);
		assertThat(postedDateArgumentCaptor.getValue())
			.isEqualTo(postedDateParam);
	}
	

}
