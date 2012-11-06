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

		files.add("pom.xml.vm");

		return files;
	}

}
