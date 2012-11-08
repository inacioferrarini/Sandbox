package sandbox.inacio.maven.generator.question;

import sandbox.inacio.maven.generator.validation.ValidationUtils;

/**
 * Question about a Client Module Name.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class ClientModuleNameQuestion extends AbstractQuestion {
	
	/**
	 * Constructor.
	 * @param commandLineAnswer the answer to the question provided by command line.
	 */ 
	public ClientModuleNameQuestion(String commandLineAnswer) {
		super(QuestionId.CLIENT_MODULE_NAME, "Client Module Name", null, commandLineAnswer);
	}

	/** {@inheritDoc} */
	@Override
	public void validate() throws QuestionValidationException {
		if (ValidationUtils.isEmpty(getAnswer())) {
			throw new QuestionValidationException("Parent Module Name is required.");
		}		
	}

}
