package sandbox.inacio.maven.generator.engine;

/**
 * Exception to be thrown whenever the Engine's Execution fails.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class EngineExecutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EngineExecutionException() {
		// TODO Auto-generated constructor stub
	}

	public EngineExecutionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EngineExecutionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EngineExecutionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EngineExecutionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
