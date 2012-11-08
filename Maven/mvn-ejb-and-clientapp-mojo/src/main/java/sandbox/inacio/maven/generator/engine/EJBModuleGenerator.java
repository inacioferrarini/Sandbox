package sandbox.inacio.maven.generator.engine;

import java.util.ArrayList;
import java.util.List;

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
	public List<String> getTemplateFileList() {
		List<String> files = new ArrayList<String>();
		
		files.add("src/main/java/delete.me");
		files.add("src/main/resources/META-INF/ejb-jar.xml.vm");
		files.add("src/main/resources/META-INF/persistence.xml.vm");
		files.add("src/main/resources/logback.xml.vm");
		files.add("src/test/java/delete.me");
		files.add("src/test/resources/logback.xml.vm");
		files.add("pom.xml.vm");
		
		return files;
	}

}
