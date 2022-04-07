package arpa.home.springpoll.data;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import arpa.home.springpoll.data.orm.PollORM;
import arpa.home.springpoll.data.repositories.PollRepository;
import arpa.home.springpoll.test_utils.PollTestUtils;
import arpa.home.springpoll.usecase.entities.Poll;
import arpa.home.springpoll.usecase.gateways.PollGateway;

@ExtendWith(MockitoExtension.class)
public class PollGatewayImpTest {

	private PollGatewayImp underTest;
	
	@Mock
	PollRepository pollRepo;
	
	@Mock
	PollMapper pollMapper;
	
	
	@BeforeEach
	void start() {
		underTest = new PollGatewayImp(pollRepo, pollMapper);
	}
	
	@Test
	public void shoulSaveORMInRepo() {
		
		Poll poll = PollTestUtils.createPollNoId();
		//arrange
		when(pollMapper.mapPollORM(poll)).thenReturn(new PollORM());
		
		//act
		underTest.savePoll(poll);
		
		//assert
		verify(pollRepo).save(any(PollORM.class));
		
	}
	
	
	
	
}
