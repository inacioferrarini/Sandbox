package sandbox.inacio.maven.generator.engine;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import sandbox.inacio.maven.generator.model.ModuleType;
import sandbox.inacio.maven.generator.model.ProjectData;

/**
 * Centers the Project Generation.
 * 
 * @author Inácio Ferrarini <inacio.ferrarini@gmail.com>
 */
public class ProjectBuilder {

	private Map<ModuleType, AbstractModuleGenerator> generators = null;
	private VelocityContext context = null;

	public ProjectBuilder() {
		init();
		initVelocity();
	}

	protected void init() {
		generators = new HashMap<ModuleType, AbstractModuleGenerator>();

		generators.put(ModuleType.PARENT, new ParentModuleGenerator());
		generators.put(ModuleType.EJB, new EJBModuleGenerator());
		generators.put(ModuleType.CLIENT, new ClientModuleGenerator());
	}

	protected void initVelocity() {
		Velocity.init();
		context = new VelocityContext();
	}

	/**
	 * Updates the parameters that will be used on project generation.
	 */
	protected void configure(ProjectData project) {
		context.put("vtl_groupId", project.getGroupId());
		context.put("vtl_artifactId", project.getName());
		context.put("vtl_version", project.getVersion());
		context.put("vtl_parent", project);
	}

	/**
	 * Generates the project.
	 * 
	 * @param project
	 *            the project to be generated.
	 */
	public void generateProject(ProjectData project, PrintStream writer) {
		configure(project);
		context.put("vtl_project", project);
		generators.get(project.getType()).execute(project, writer, context);
		if (project.getModules() != null) {
			for (ProjectData p : project.getModules()) {
				context.put("vtl_project", p);
				generators.get(p.getType()).execute(p, writer, context);
			}
		}
	}

}
