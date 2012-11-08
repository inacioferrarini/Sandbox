package sandbox.inacio.maven.generator.question;

import sandbox.inacio.maven.generator.validation.ValidationUtils;

/**
 * Question about the Parent Project Name.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class ParentProjectNameQuestion extends AbstractQuestion {

	/**
	 * Constructor.
	 * @param commandLineAnswer the answer to the question provided by command line.
	 */
	public ParentProjectNameQuestion(String commandLineAnswer) {
		super(QuestionId.PARENT_PROJECT_NAME, "Parent Project Name", null, commandLineAnswer);
	}

	/** {@inheritDoc} */
	@Override
	public void validate() throws QuestionValidationException {
		if (ValidationUtils.isEmpty(getAnswer())) {
			throw new QuestionValidationException("Parent Module Name is required.");
		}
	}
	
}
