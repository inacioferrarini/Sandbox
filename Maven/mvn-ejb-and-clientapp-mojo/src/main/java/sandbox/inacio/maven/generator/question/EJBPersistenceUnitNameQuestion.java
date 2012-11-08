package sandbox.inacio.maven.generator.question;

import sandbox.inacio.maven.generator.validation.ValidationUtils;

/**
 * Question about the Parent Project Version.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class EJBPersistenceUnitNameQuestion extends AbstractQuestion {

	/**
	 * Constructor.
	 * @param commandLineAnswer the answer to the question provided by command line.
	 */
	public EJBPersistenceUnitNameQuestion(String commandLineAnswer) {
		super(QuestionId.EJB_PERSISTENCE_UNIT_NAME, "Persistence Unit Name", null, commandLineAnswer);
	}

	/** {@inheritDoc} */
	@Override
	public void validate() throws QuestionValidationException {
		if (ValidationUtils.isEmpty(getAnswer())) {
			throw new QuestionValidationException("Persistence Unit Name is required.");
		}
	}
	
}
