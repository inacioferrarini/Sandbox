package sandbox.inacio.maven.generator.question;

/**
 * Question about the information provided by the user.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class ConfirmConfigurationQuestion extends AbstractQuestion {
	
	/**
	 * Constructor.
	 */ 
	public ConfirmConfigurationQuestion() {
		super(null, "Proceed", null);
	}

	/** {@inheritDoc} */
	@Override
	public void validate() throws QuestionValidationException {
		if (!getAnswer().equalsIgnoreCase("Y") && !getAnswer().equalsIgnoreCase("N")) {
			cleanAnswer();
			throw new QuestionValidationException("Please answer Y or N.");
		}				
	}

}
