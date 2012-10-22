package sandbox.inacio.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		logger.trace("Trace Level Message");
		logger.debug("Debug Level Message");
		logger.info("Info Level Message");
		logger.warn("Warn Level Message");
		logger.error("Error Level Message");
	}

}
