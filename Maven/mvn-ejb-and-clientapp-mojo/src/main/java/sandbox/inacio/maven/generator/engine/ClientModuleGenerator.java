package sandbox.inacio.maven.generator.engine;

import java.util.ArrayList;
import java.util.List;

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
	public List<String> getTemplateFileList() {
		List<String> files = new ArrayList<String>();

		files.add("src/main/assembly/append-to-distribution/run.bat.vm");
		files.add("src/main/assembly/append-to-distribution/run.sh.vm");
		files.add("src/main/assembly/assembly.xml.vm");
		files.add("src/main/filters/filter.properties.vm");
		files.add("src/main/java/delete.me");
		files.add("src/main/resources/jndi.properties.vm");
		files.add("src/main/resources/logback.xml.vm");
		files.add("src/test/java/delete.me");		
		files.add("src/test/resources/jndi.properties.vm");
		files.add("src/test/resources/logback.xml.vm");
		files.add("pom.xml.vm");

		return files;
	}

}
