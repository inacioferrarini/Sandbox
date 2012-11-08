package sandbox.inacio.maven.generator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import sandbox.inacio.maven.generator.engine.ProjectBuilder;
import sandbox.inacio.maven.generator.model.ModuleType;
import sandbox.inacio.maven.generator.model.ProjectData;
import sandbox.inacio.maven.generator.question.AbstractQuestion;
import sandbox.inacio.maven.generator.question.ClientModuleNameQuestion;
import sandbox.inacio.maven.generator.question.ConfirmConfigurationQuestion;
import sandbox.inacio.maven.generator.question.EJBModuleNameQuestion;
import sandbox.inacio.maven.generator.question.EJBPersistenceUnitNameQuestion;
import sandbox.inacio.maven.generator.question.ParentProjectGroupIdQuestion;
import sandbox.inacio.maven.generator.question.ParentProjectNameQuestion;
import sandbox.inacio.maven.generator.question.ParentProjectVersionQuestion;
import sandbox.inacio.maven.generator.question.QuestionId;

/**
 * @goal generate
 * @requiresProject false
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class Main extends AbstractMojo {
	
	/** @parameter expression="${groupId}" */
	private String groupId;
	/** @parameter expression="${artifactId}" */	
	private String artifactId;
	/** @parameter expression="${version}" */
	private String version;
	/** @parameter expression="${ejbModule}" */
	private String ejbModuleName;
	/** @parameter expression="${persistenceUnitName}" */
	private String ejbPersistenceUnitName;
	/** @parameter expression="${clientModule}" */
	private String clientName;
		
	public void execute() throws MojoExecutionException {
		PrintStream writer = System.out;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		printInfo(writer);

		Map<QuestionId, AbstractQuestion> q = getQuestionsAndSuppliedAnswers();
		if (!wereAllQuestionsAnsweredThroughCommandLine()) {
			askQuestions(q, writer, reader);
		}
		printSummaryAndValidateAnswers(q, writer, reader);
		
		ProjectData project = parseProjectData(q);
		
		ProjectBuilder builder = new ProjectBuilder();
		builder.generateProject(project, writer);
	}
		
	protected void printInfo(PrintStream writer) {
		writer.println(" **************************************************************************** ");
		writer.println(" * This plugin generates EJB Modules (with their clients) and a Client App. * ");
		writer.println(" * To make the project customizable, some questions will be asked.          * ");
		writer.println(" **************************************************************************** ");
	}
	
	protected Map<QuestionId, AbstractQuestion> getQuestionsAndSuppliedAnswers() {
		Map<QuestionId, AbstractQuestion> q = new LinkedHashMap<QuestionId, AbstractQuestion>();
		
		q.put(QuestionId.PARENT_PROJECT_GROUP_ID, new ParentProjectGroupIdQuestion(groupId));
		q.put(QuestionId.PARENT_PROJECT_NAME, new ParentProjectNameQuestion(artifactId));
		q.put(QuestionId.PARENT_PROJECT_VERSION, new ParentProjectVersionQuestion(version));
		q.put(QuestionId.EJB_MODULE_NAME, new EJBModuleNameQuestion(ejbModuleName));
		q.put(QuestionId.EJB_PERSISTENCE_UNIT_NAME, new EJBPersistenceUnitNameQuestion(ejbPersistenceUnitName));
		q.put(QuestionId.CLIENT_MODULE_NAME, new ClientModuleNameQuestion(clientName));

		return q;
	}
	
	protected boolean wereAllQuestionsAnsweredThroughCommandLine() {
		if (groupId == null)
			return false;
		if (artifactId == null)
			return false;
		if (version == null)
			return false;
		if (ejbModuleName == null)
			return false;
		if (ejbPersistenceUnitName == null)
			return false;
		if (clientName == null)
			return false;
		return true;
	}
	
	protected void printSummary(Map<QuestionId, AbstractQuestion> questions, PrintStream writer) {
		for (AbstractQuestion q : questions.values()) {
			writer.println(q.getText() + ": " + q.getAnswer());
		}
	}
	
//	protected void getProjectDataFromUser(Map<QuestionId, AbstractQuestion> questions, PrintStream writer, BufferedReader reader) {
	//	askQuestions(questions, writer, reader);
//	}
	
	protected void printSummaryAndValidateAnswers(Map<QuestionId, AbstractQuestion> questions, PrintStream writer, BufferedReader reader) {
		printSummary(questions, writer);
		if (!confirmInformation(writer, reader)) {
			askQuestions(questions, writer, reader);
		}		
	}
	
	protected void askQuestions(Map<QuestionId, AbstractQuestion> questions, PrintStream writer, BufferedReader reader) {
		for (AbstractQuestion q : questions.values()) {
			q.ask(writer, reader);
		}
	}
	
	protected boolean confirmInformation(PrintStream writer, BufferedReader reader) {
		AbstractQuestion q = new ConfirmConfigurationQuestion();
		q.ask(writer, reader);
		return q.getAnswer().equalsIgnoreCase("Y");
	}
	
	protected ProjectData parseProjectData(Map<QuestionId, AbstractQuestion> questionsAndAnswers) {
		String parentProjectGroupId = 
				questionsAndAnswers.get(QuestionId.PARENT_PROJECT_GROUP_ID).getAnswer();
		String parentProjectName = 
				questionsAndAnswers.get(QuestionId.PARENT_PROJECT_NAME).getAnswer();
		String parentProjectVersion =
				questionsAndAnswers.get(QuestionId.PARENT_PROJECT_VERSION).getAnswer();
		String persistenceUnitName =
				questionsAndAnswers.get(QuestionId.EJB_PERSISTENCE_UNIT_NAME).getAnswer();
		
		ProjectData parentProject = new ProjectData(parentProjectGroupId, parentProjectName, parentProjectVersion, ModuleType.PARENT, null, persistenceUnitName);
		
		for (AbstractQuestion q : questionsAndAnswers.values()) {
			if (!q.getQuestionId().equals(QuestionId.PARENT_PROJECT_NAME)) {
				
				ModuleType moduleType = null;
				if (q.getQuestionId().equals(QuestionId.CLIENT_MODULE_NAME)) {
					moduleType = ModuleType.CLIENT;
				} else if (q.getQuestionId().equals(QuestionId.EJB_MODULE_NAME)) {
					moduleType = ModuleType.EJB;
				}
				if (moduleType != null) {
					ProjectData module = new ProjectData(parentProjectGroupId, q.getAnswer(), parentProjectVersion, moduleType, parentProject);
					parentProject.getModules().add(module);
				}
			}
		}	
		return parentProject;
	}	
	
}
