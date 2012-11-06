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
	
	public void execute() throws MojoExecutionException {
		PrintStream writer = System.out;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		printInfo(writer);

		Map<QuestionId, AbstractQuestion> q = getQuestions();
		getProjectDataFromUser(q, writer, reader);
		
		ProjectData project = parseProjectData(q);
		
		ProjectBuilder builder = new ProjectBuilder();
		builder.generateProject(project, writer);
	}
	
	protected void getProjectDataFromUser(Map<QuestionId, AbstractQuestion> questions, PrintStream writer, BufferedReader reader) {
		askQuestions(questions, writer, reader);
		printSummary(questions, writer);
		if (!confirmInformation(writer, reader)) {
			getProjectDataFromUser(questions, writer, reader);
		}		
	}
	
	protected void printInfo(PrintStream writer) {
		writer.println(" **************************************************************************** ");
		writer.println(" * This plugin generates EJB Modules (with their clients) and a Client App. * ");
		writer.println(" * To make the project customizable, some questions will be asked.          * ");
		writer.println(" **************************************************************************** ");
	}
	
	protected Map<QuestionId, AbstractQuestion> getQuestions() {
		Map<QuestionId, AbstractQuestion> q = new LinkedHashMap<QuestionId, AbstractQuestion>();

		q.put(QuestionId.PARENT_PROJECT_GROUP_ID, new ParentProjectGroupIdQuestion());
		q.put(QuestionId.PARENT_PROJECT_NAME, new ParentProjectNameQuestion());
		q.put(QuestionId.PARENT_PROJECT_VERSION, new ParentProjectVersionQuestion());
		q.put(QuestionId.EJB_MODULE_NAME, new EJBModuleNameQuestion());
		q.put(QuestionId.CLIENT_MODULE_NAME, new ClientModuleNameQuestion());

		return q;
	}
	
	protected void askQuestions(Map<QuestionId, AbstractQuestion> questions, PrintStream writer, BufferedReader reader) {
		for (AbstractQuestion q : questions.values()) {
			q.ask(writer, reader);
		}
	}

	protected void printSummary(Map<QuestionId, AbstractQuestion> questions, PrintStream writer) {
		for (AbstractQuestion q : questions.values()) {
			writer.println(q.getText() + ": " + q.getAnswer());
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
		
		ProjectData parentProject = new ProjectData(parentProjectGroupId, parentProjectName, parentProjectVersion, ModuleType.PARENT);
		
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
