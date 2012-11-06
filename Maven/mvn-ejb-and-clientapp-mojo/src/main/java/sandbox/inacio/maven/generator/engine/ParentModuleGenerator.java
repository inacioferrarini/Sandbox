package sandbox.inacio.maven.generator.engine;

import java.util.ArrayList;
import java.util.List;

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
	public List<String> getTemplateFileList() {
		List<String> files = new ArrayList<String>();

		files.add("pom.xml.vm");
		
		return files;
	}

}
