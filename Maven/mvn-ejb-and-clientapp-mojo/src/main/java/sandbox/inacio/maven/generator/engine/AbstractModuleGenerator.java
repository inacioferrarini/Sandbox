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
import java.nio.file.Files;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import sandbox.inacio.maven.generator.model.ProjectData;
import sandbox.inacio.maven.generator.model.Resource;

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
	 * @return List<Resource>
	 */
	public abstract List<Resource> getTemplateFileList();

	/**
	 * Executes the engine.
	 */
	public void execute(ProjectData project, PrintStream writer,
			VelocityContext parameters) {
		String templatesRootDir = getRootPath();
		for (Resource templateFile : getTemplateFileList()) {

			String templateFilePath = templatesRootDir + "/"
					+ templateFile.getName();
			String unixFileMode = templateFile.getUnixFileMode();
			String parentName = "";
			if (project.getParent() != null) {
				parentName = project.getParent().getName() + "/";
			}
			File destinationFile = getDestinationFileName(parentName
					+ project.getName() + "/"
					+ templateFile.getDestinationName());
			InputStream templateIs = AbstractModuleGenerator.class
					.getResourceAsStream(templateFilePath);
			Reader reader = new InputStreamReader(templateIs);
			StringWriter buffer = new StringWriter();
			Velocity.evaluate(parameters, buffer, project.getName(), reader);
			writeToFile(buffer, destinationFile);
			if (unixFileMode != null) {
				setFileMode(destinationFile, unixFileMode);
			}
		}
	}

	protected File getDestinationFileName(String originalTemplateFilePath) {
		String fileName = originalTemplateFilePath.replace(".vm", "");
		return new File(new File(".").getAbsolutePath() + "/" + fileName);
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

	protected void setFileMode(File destinationFile, String unixFileMode) {
		if (destinationFile != null) {
			PosixFileAttributeView view = null;
			try {
				view = Files.getFileAttributeView(destinationFile.toPath(), PosixFileAttributeView.class);
				Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
				
				byte owner = unixFileMode.getBytes()[0];
				byte group = unixFileMode.getBytes()[1];
				byte all = unixFileMode.getBytes()[2];

				int read = 4;
				int write = 2;
				int execute = 1;
				
				if ((owner & read) == read) {
					perms.add(PosixFilePermission.OWNER_READ);
				}
				if ((owner & write) == write) {
					perms.add(PosixFilePermission.OWNER_WRITE);
				}
				if ((owner & execute) == execute) {
					perms.add(PosixFilePermission.OWNER_EXECUTE);
				}
				
				if ((group & read) == read) {
					perms.add(PosixFilePermission.GROUP_READ);
				}
				if ((group & write) == write) {
					perms.add(PosixFilePermission.GROUP_WRITE);
				}
				if ((group & execute) == execute) {
					perms.add(PosixFilePermission.GROUP_EXECUTE);
				}
				
				if ((all & read) == read) {
					perms.add(PosixFilePermission.OTHERS_READ);
				}
				if ((all & write) == write) {
					perms.add(PosixFilePermission.OTHERS_WRITE);
				}
				if ((all & execute) == execute) {
					perms.add(PosixFilePermission.OTHERS_EXECUTE);
				}
				view.setPermissions(perms);
			
			} catch (IOException e) {
			}
			
		}
	}
}
