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
	 */
	public EJBModuleNameQuestion() {
		super(QuestionId.EJB_MODULE_NAME, "EJB Module Name", null);
	}

	/** {@inheritDoc} */
	@Override
	public void validate() throws QuestionValidationException {
		if (ValidationUtils.isEmpty(getAnswer())) {
			throw new QuestionValidationException("EJB Module Name is required.");
		}
	}
	
}
