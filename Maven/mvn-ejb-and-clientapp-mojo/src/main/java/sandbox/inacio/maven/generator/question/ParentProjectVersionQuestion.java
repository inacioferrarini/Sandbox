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
	 */
	public ParentProjectVersionQuestion() {
		super(QuestionId.PARENT_PROJECT_VERSION, "Parent Project Version", null);
	}

	/** {@inheritDoc} */
	@Override
	public void validate() throws QuestionValidationException {
		if (ValidationUtils.isEmpty(getAnswer())) {
			throw new QuestionValidationException("Parent Project Version is required.");
		}
	}
	
}
