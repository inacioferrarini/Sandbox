package ${package};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	
 	static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.trace("Trace Level Message");
		logger.debug("Debug Level Message");
		logger.info("Info Level Message");
		logger.warn("Warn Level Message");
		logger.error("Error Level Message");
    }
	
}
