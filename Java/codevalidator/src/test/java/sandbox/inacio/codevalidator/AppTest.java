package sandbox.inacio.codevalidator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	static final Logger logger = LoggerFactory.getLogger(AppTest.class);
	
	@Test
	public void test() {
		logger.trace("Trace Level Message");
		logger.debug("Debug Level Message");
		logger.info("Info Level Message");
		logger.warn("Warn Level Message");
		logger.error("Error Level Message");		
	}
	
}
