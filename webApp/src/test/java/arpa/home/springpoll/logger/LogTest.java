package arpa.home.springpoll.logger;

import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LogTest {

	private static final Logger LOGGER = Logger.getLogger(LogTest.class);
	
	@BeforeAll
	public static void initialize() {
		 
	}

	@Test
	public void logsInfo() {
		LOGGER.info("funTest");
	}
	
}
