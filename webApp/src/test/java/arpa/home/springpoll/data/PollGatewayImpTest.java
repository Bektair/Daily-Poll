package arpa.home.springpoll.data;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

import arpa.home.springpoll.persistence.PollGatewayImp;
import arpa.home.springpoll.persistence.PollMapper;
import arpa.home.springpoll.persistence.orm.PollORM;
import arpa.home.springpoll.persistence.repositories.PollRepository;
import arpa.home.springpoll.test_utils.PollORMTestUtils;
import arpa.home.springpoll.test_utils.PollTestUtils;
import arpa.home.springpoll.usecase.entities.Poll;
import arpa.home.springpoll.usecase.gateways.PollGateway;

@ExtendWith(MockitoExtension.class)
public class PollGatewayImpTest {

	private PollGatewayImp underTest;

	private static final Logger LOGGER = Logger.getLogger(PollGatewayImpTest.class);
	
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
	
	@Test
	public void getAnExistingPollByItsId() {
		//arrange
		String id = "22";
		BigInteger ORMId;
		Optional<PollORM> optPollORM;
					
		Optional<Poll> optPoll;
		
		when(pollRepo.findById(ORMId = any()))
			.thenReturn(optPollORM = Optional.of(PollORMTestUtils.createPollORMWithId(ORMId)));
		
		when(pollMapper.mapOptPoll(optPollORM))
			.thenReturn(Optional.of(PollTestUtils.createPollWithId(id)));
		
		//act
		optPoll = underTest.getPollById(id);
		
		//assert
		assertThat(optPoll.isPresent());
		verify(pollMapper).mapOptPoll(any());
		verify(pollRepo).findById(any());

		assertThat(optPoll.get().getId().equals(id));
	}
	
	
	@Test
	public void emptyOptionalWhenBadIdFormat() {
		String badIdNegative = "-30";
		String badIdDecimal = "22.22";
		String badIdAlphaNumeric = "22ABD";
		String badIdEmpty = "";
		String badIdNull = null;
		
		Optional<Poll> pollNeg = underTest.getPollById(badIdNegative);	
		Optional<Poll> pollDeci = underTest.getPollById(badIdDecimal);
		Optional<Poll> pollAlpha = underTest.getPollById(badIdAlphaNumeric);
		Optional<Poll> pollEmpty = underTest.getPollById(badIdEmpty);
		Optional<Poll> pollNull = underTest.getPollById(badIdNull);
		
		assertThat(pollNeg).isEmpty();
		assertThat(pollDeci).isEmpty();
		assertThat(pollAlpha).isEmpty();
		assertThat(pollEmpty).isEmpty();
		assertThat(pollNull).isEmpty();
		
		verify(pollRepo, never()).findById(any());
		
		
	}
	
	@Test
	public void emptyOptWhenNonExistingPollById() {
		
		//arrange
		Optional<Poll> optPoll;
		String id = "300";
		when(pollRepo.findById(any()))
			.thenReturn(Optional.empty());
		
		//act
		optPoll = underTest.getPollById(id);
		
		//assert
		assertThat(optPoll).isEmpty();
	}
	
	@Test
	public void getsAllPollsFromPollRepo() {
		
		//arrange
		int size = 5;
		List<Poll> pollExpect = PollTestUtils.createPolls(size);
		List<PollORM> pollORMs = PollORMTestUtils.createPollORMListWithId(size);
		
		when(pollRepo.findAll()).thenReturn
			(pollORMs);
		when(pollMapper.mapPolls(pollORMs)).thenReturn(pollExpect);
		
		//act
		List<Poll> pollsActual = underTest.getAllPolls();
		
		assertThat(pollsActual).isEqualTo(pollExpect);

	}
	
	@Test
	public void deleteExistingPollById() {
		String id = "22";
		BigInteger ORMId = BigInteger.valueOf(Long.parseLong(id));
		
		underTest.deletePollById(id);
		verify(pollRepo).deleteById(ORMId);
		
	}
	
	@Test
	public void deletePollBadIdShouldThrowException() {
		String badIdNegative = "-30";
		String badIdDecimal = "22.22";
		String badIdAlphaNumeric = "22ABD";
		String badIdEmpty = "";
		String badIdNull = null;
		
		
		assertThatThrownBy(() -> underTest.deletePollById(badIdNegative))
			.isInstanceOf(Exception.class)
			.hasMessage("You sent DeletePoll an invalid id, use whole positive numbers");
		assertThatThrownBy(() -> underTest.deletePollById(badIdDecimal))
			.isInstanceOf(Exception.class)
			.hasMessage("You sent DeletePoll an invalid id, use whole positive numbers");
		assertThatThrownBy(() -> underTest.deletePollById(badIdAlphaNumeric))
		.isInstanceOf(Exception.class)
		.hasMessage("You sent DeletePoll an invalid id, use whole positive numbers");
		assertThatThrownBy(() -> underTest.deletePollById(badIdEmpty))
		.isInstanceOf(Exception.class)
		.hasMessage("You sent DeletePoll an invalid id, use whole positive numbers");
		assertThatThrownBy(() -> underTest.deletePollById(badIdNull))
		.isInstanceOf(Exception.class)
		.hasMessage("You sent DeletePoll an invalid id, use whole positive numbers");
		
				
	}
	
	@Test
	public void validationHelperShouldReturnFalseWhenBadId() {
		String badIdNegative = "-30";
		String badIdDecimal = "22.22";
		String badIdAlphaNumeric = "22ABD";
		String badIdEmpty = "";
		String badIdNull = null;
		
		boolean badNeg = underTest.isValidId(badIdNegative);
		boolean badDeci = underTest.isValidId(badIdDecimal);
		boolean badAlpha = underTest.isValidId(badIdAlphaNumeric);
		boolean badEmpty = underTest.isValidId(badIdEmpty);
		boolean badNull = underTest.isValidId(badIdNull);

		assertThat(badNull).isFalse();
		assertThat(badNeg).isFalse();
		assertThat(badDeci).isFalse();
		assertThat(badAlpha).isFalse();
		assertThat(badEmpty).isFalse();
	}
	
	
	@Test
	public void validationHelperShouldReturnTrueWhenGoodId() {
		String goodId = "22";
		String goodIdZero = "0";
		
		boolean isGoodId = underTest.isValidId(goodId);
		boolean isGoodZeroId = underTest.isValidId(goodIdZero);
		assertThat(isGoodId).isTrue();
		assertThat(isGoodZeroId).isTrue();
		

	}
	
	
	
	
	
	
}
