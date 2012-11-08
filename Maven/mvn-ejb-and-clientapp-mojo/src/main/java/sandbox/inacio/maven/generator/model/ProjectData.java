package sandbox.inacio.maven.generator.model;

import java.util.LinkedList;
import java.util.List;

public class ProjectData {
	private String groupId;
	private String name;
	private String version;
	private ModuleType type;
	private List<ProjectData> modules = new LinkedList<ProjectData>();
	private ProjectData parent;
	private String persistenceUnitName;
	
	public ProjectData(String groupId, String name, String version, ModuleType type) {
		this(groupId, name, version, type, null, null);
	}

	public ProjectData(String groupId, String name, String version, ModuleType type, ProjectData parent) {
		this(groupId, name, version, type, parent, null);
	}
	
	public ProjectData(String groupId, String name, String version, ModuleType type, ProjectData parent, String persistenceUnitName) {
		super();
		this.groupId = groupId;
		this.name = name;
		this.version = version;
		this.type = type;
		this.parent = parent;
		this.persistenceUnitName = persistenceUnitName;
	}

	public String getName() {
		return name;
	}

	public ModuleType getType() {
		return type;
	}

	public List<ProjectData> getModules() {
		return modules;
	}

	public ProjectData getParent() {
		return parent;
	}

	public String getGroupId() {
		return groupId;
	}

	public String getVersion() {
		return version;
	}

	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}
	
}
