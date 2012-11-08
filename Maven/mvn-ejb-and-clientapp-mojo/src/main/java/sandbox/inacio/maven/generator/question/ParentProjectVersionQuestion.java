package sandbox.inacio.maven.generator.question;

import sandbox.inacio.maven.generator.validation.ValidationUtils;

/**
 * Question about the Parent Project Version.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class ParentProjectVersionQuestion extends AbstractQuestion {

	/**
	 * Constructor.
	 * @param commandLineAnswer the answer to the question provided by command line.
	 */
	public ParentProjectVersionQuestion(String commandLineAnswer) {
		super(QuestionId.PARENT_PROJECT_VERSION, "Parent Project Version", null, commandLineAnswer);
	}

	/** {@inheritDoc} */
	@Override
	public void validate() throws QuestionValidationException {
		if (ValidationUtils.isEmpty(getAnswer())) {
			throw new QuestionValidationException("Parent Project Version is required.");
		}
	}
	
}
