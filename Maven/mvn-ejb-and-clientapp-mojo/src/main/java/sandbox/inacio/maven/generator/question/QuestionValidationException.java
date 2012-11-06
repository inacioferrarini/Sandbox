package sandbox.inacio.maven.generator.question;

/**
 * Exception to be thrown whenever the validation fails.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class QuestionValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestionValidationException() {
		super();
	}

	public QuestionValidationException(String message) {
		super(message);
	}

	public QuestionValidationException(Throwable cause) {
		super(cause);
	}

	public QuestionValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuestionValidationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
