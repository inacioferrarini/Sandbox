package sandbox.inacio.maven.generator.model;

import java.util.LinkedList;
import java.util.List;

public class ProjectData {

	private String name;
	private ModuleType type;
	private List<ProjectData> childrenProjects = new LinkedList<ProjectData>();
	
	public ProjectData(String name, ModuleType type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public ModuleType getType() {
		return type;
	}

	public List<ProjectData> getChildrenProjects() {
		return childrenProjects;
	}

}
