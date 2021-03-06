package sandbox.inacio.maven.generator.question;

import sandbox.inacio.maven.generator.validation.ValidationUtils;

/**
 * Question about a EJB Module Name.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class EJBModuleNameQuestion extends AbstractQuestion {

	/**
	 * Constructor.
	 * @param commandLineAnswer the answer to the question provided by command line.
	 */
	public EJBModuleNameQuestion(String commandLineAnswer) {
		super(QuestionId.EJB_MODULE_NAME, "EJB Module Name", null, commandLineAnswer);
	}

	/** {@inheritDoc} */
	@Override
	public void validate() throws QuestionValidationException {
		if (ValidationUtils.isEmpty(getAnswer())) {
			throw new QuestionValidationException("EJB Module Name is required.");
		}
	}
	
}
