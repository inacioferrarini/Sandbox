package sandbox.inacio.maven.generator.engine;

import java.util.ArrayList;
import java.util.List;

import sandbox.inacio.maven.generator.model.Resource;

public class ClientModuleGenerator extends AbstractModuleGenerator {

	public ClientModuleGenerator() {
	}

	/** {@inheritDoc} */
	@Override
	public String getRootPath() {
		return "/templates/client";
	}

	/** {@inheritDoc} */
	@Override
	public List<Resource> getTemplateFileList() {
		List<Resource> files = new ArrayList<Resource>();

		files.add(new Resource("src/main/assembly/append-to-distribution/run.bat.vm", null,"755"));
		files.add(new Resource("src/main/assembly/append-to-distribution/run.sh.vm", null, "755"));
		files.add(new Resource("src/main/assembly/assembly.xml.vm"));
		files.add(new Resource("src/main/filters/filter.properties.vm"));
		files.add(new Resource("src/main/java/delete.me"));
		files.add(new Resource("src/main/resources/jndi.properties.vm"));
		files.add(new Resource("src/main/resources/logback.xml.vm"));
		files.add(new Resource("src/test/java/delete.me"));		
		files.add(new Resource("src/test/resources/jndi.properties.vm"));
		files.add(new Resource("src/test/resources/logback.xml.vm"));
		files.add(new Resource("pom.xml.vm"));

		return files;
	}

}
