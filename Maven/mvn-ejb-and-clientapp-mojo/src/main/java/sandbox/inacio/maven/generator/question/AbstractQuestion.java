package sandbox.inacio.maven.generator.question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import sandbox.inacio.maven.generator.validation.ValidationUtils;

/**
 * Abstract class for every question that may be asked during plugin execution.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public abstract class AbstractQuestion {

	/** * Question's ID */
	private QuestionId questionId;	
	/** * The Question's Text */
	private String text;
	/** * The Default Answer */
	private String defaultAnswer;
	/** * The Question's Answer */
	private String answer;
	
	/**
	 * Constructor to be used by children's classes.
	 * 
	 * @param questionId The Question's Identifier.
	 * @param text The Question's Text (that will be asked to the user).
	 * @param defaultAnswer The Question's Default Answer (In case the user answer is null). 
	 */
	public AbstractQuestion(QuestionId questionId, String text, String defaultAnswer) {
		super();
		this.questionId = questionId;
		this.text = text;
		this.defaultAnswer = defaultAnswer;
	}

	public QuestionId getQuestionId() {
		return questionId;
	}

	public String getText() {
		return text;
	}

	public String getDefaultAnswer() {
		return defaultAnswer;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	protected void setAnswer(String answer) {
		this.answer = answer;
	}

	protected void cleanAnswer() {
		this.answer = null;
	}
	
	/**
	 * Asks the question using <tt>writter</tt> and <tt>reader</tt> to
	 * read input.
	 * 
	 * @param writer the PrintWriter used to written to the user.
	 * @param reader the BufferedReader use to read user input.
	 */
	public void ask(PrintStream writer, BufferedReader reader) {
		boolean done = false;
		do {
			StringBuilder prompt = new StringBuilder("");
			prompt.append(getText());
			
			if (ValidationUtils.isNotEmpty(getDefaultAnswer())) {
				prompt.append("[");
				prompt.append(getDefaultAnswer());
				prompt.append("]");
			}
			prompt.append(": ");
			if (ValidationUtils.isNotEmpty(getAnswer())) {
				prompt.append(getAnswer());
			}
			
			writer.print(prompt.toString());
			
			try {
				String userInput = reader.readLine();
				String currentAnswer = null;
				
				if (ValidationUtils.isEmpty(userInput)) {
					currentAnswer = getAnswer();
				} else {
					currentAnswer = userInput;
				}
				
				if (ValidationUtils.isNotEmpty(currentAnswer)) {
					answer = currentAnswer;
					try {
						validate();
						done = true;
					} catch (QuestionValidationException e) {
						writer.println(e.getMessage());
					}
				} else if (ValidationUtils.isNotEmpty(getDefaultAnswer())) {
					answer = getDefaultAnswer();
				}
			} catch (IOException e) {
			}
		} while (!done);
	}
	
	/**
	 * Validates if the question is valid
	 */
	public abstract void validate() throws QuestionValidationException;
	
}
