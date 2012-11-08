package sandbox.inacio.maven.generator.engine;

import java.util.ArrayList;
import java.util.List;

import sandbox.inacio.maven.generator.model.Resource;

public class EJBModuleGenerator extends AbstractModuleGenerator {

	public EJBModuleGenerator() {
	}

	/** {@inheritDoc} */
	@Override
	public String getRootPath() {
		return "/templates/ejb";
	}

	/** {@inheritDoc} */
	@Override
	public List<Resource> getTemplateFileList() {
		List<Resource> files = new ArrayList<Resource>();
		
		files.add(new Resource("src/main/java/delete.me"));
		files.add(new Resource("src/main/resources/META-INF/ejb-jar.xml.vm"));
		files.add(new Resource("src/main/resources/META-INF/persistence.xml.vm"));
		files.add(new Resource("src/main/resources/logback.xml.vm"));
		files.add(new Resource("src/test/java/delete.me"));
		files.add(new Resource("src/test/resources/logback.xml.vm"));
		files.add(new Resource("pom.xml.vm"));
		
		return files;
	}

}
