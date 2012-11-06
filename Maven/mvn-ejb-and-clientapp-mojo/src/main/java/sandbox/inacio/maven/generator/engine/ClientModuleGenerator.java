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

		files.add("pom.xml.vm");

		return files;
	}

}
