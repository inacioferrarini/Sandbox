package sandbox.inacio.maven.generator.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import sandbox.inacio.maven.generator.model.ProjectData;

/**
 * Abstract class for every Generator.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public abstract class AbstractModuleGenerator {

	/**
	 * Returns the root path for the templates. All files in the
	 * {@link #getTemplateFileList()} must be relative to this root path.
	 * 
	 * @return
	 */
	public abstract String getRootPath();

	/**
	 * Returns the list of template files that the engine will replace.
	 * 
	 * @return List<String>
	 */
	public abstract List<String> getTemplateFileList();
	
	/**
	 * Executes the engine.
	 */
	public void execute(ProjectData project, PrintStream writer, VelocityContext parameters) {
		String templatesRootDir = getRootPath();
		for (String templateFile : getTemplateFileList()) {
			String templateFilePath = templatesRootDir + "/" + templateFile;
			String parentName = "";
			if (project.getParent() != null) {
				parentName = project.getParent().getName() + "/";
			}
			File destinationFile = getDestinationFileName(parentName + project.getName() + "/" + templateFile);
			
			InputStream templateIs = AbstractModuleGenerator.class.getResourceAsStream(templateFilePath);
			Reader reader = new InputStreamReader(templateIs);
			StringWriter buffer = new StringWriter();
			Velocity.evaluate(parameters, buffer, project.getName(), reader);
			writeToFile(buffer, destinationFile);
		}
	}
	
	protected File getDestinationFileName(String originalTemplateFilePath) {
		String fileName = originalTemplateFilePath.replace(".vm", "");
		return new File (new File(".").getAbsolutePath() + "/" + fileName);
	}
	
	protected void writeToFile(Writer buffer, File destinationFile) {
		createPath(destinationFile);
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(destinationFile);
			fileWriter.write(buffer.toString());
		} catch (IOException e) {
			
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException ignoredException) {
				}
			}
		}
	}
	
	protected void createPath(File destinationFile) {
		File parentDir = destinationFile.getParentFile();
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
	}
	
}
