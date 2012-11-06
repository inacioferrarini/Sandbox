package sandbox.inacio.maven.generator.question;

import sandbox.inacio.maven.generator.validation.ValidationUtils;

/**
 * Question about the Parent Project GroupId.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class ParentProjectGroupIdQuestion extends AbstractQuestion {

	/**
	 * Constructor.
	 */
	public ParentProjectGroupIdQuestion() {
		super(QuestionId.PARENT_PROJECT_GROUP_ID, "Parent Project Group Id", null);
	}

	/** {@inheritDoc} */
	@Override
	public void validate() throws QuestionValidationException {
		if (ValidationUtils.isEmpty(getAnswer())) {
			throw new QuestionValidationException("Parent Project Group Id is required.");
		}
	}
	
}
