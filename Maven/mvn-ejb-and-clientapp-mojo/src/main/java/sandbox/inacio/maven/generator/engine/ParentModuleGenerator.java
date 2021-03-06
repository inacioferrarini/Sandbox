package sandbox.inacio.maven.generator.engine;

import java.util.ArrayList;
import java.util.List;

import sandbox.inacio.maven.generator.model.Resource;

public class ParentModuleGenerator extends AbstractModuleGenerator {

	public ParentModuleGenerator() {
	}
	
	/** {@inheritDoc} */
	@Override
	public String getRootPath() {
		return "/templates/parent";
	}	
	
	/** {@inheritDoc} */
	@Override
	public List<Resource> getTemplateFileList() {
		List<Resource> files = new ArrayList<Resource>();

		files.add(new Resource("jboss-start.sh.vm", null, "755"));
		files.add(new Resource("jboss-stop.sh.vm", null, "755"));
		files.add(new Resource("run-clients.sh.vm", null, "755"));		
		files.add(new Resource("deploy-ejb-modules.sh.vm", null, "755"));
		files.add(new Resource("undeploy-ejb-modules.sh.vm", null, "755"));		
		files.add(new Resource("pom.xml.vm"));
		files.add(new Resource("dotgitignore.vm", ".gitignore"));
		
		return files;
	}

}
