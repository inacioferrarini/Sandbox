package sandbox.inacio.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {

	static final Logger logger = LoggerFactory.getLogger(LogbackTest.class);
	
	@Test
	public void testLogBack() {
		logger.trace("Trace Level Message");
		logger.debug("Debug Level Message");
		logger.info("Info Level Message");
		logger.warn("Warn Level Message");
		logger.error("Error Level Message");		
	}

}
