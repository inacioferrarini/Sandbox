package sandbox.inacio.maven.generator.model;

public class Resource {

	private String name;
	private String destinationName;
	private String unixFileMode;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            the Resource original name. The name will be used as
	 *            destination name as well.
	 */
	public Resource(String name) {
		this(name, name, null);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param name the Resource original name.
	 * @param destinationName the Destination name.
	 */
	public Resource(String name, String destinationName) {
		this(name, destinationName, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param name the Resource original name.
	 * @param destinationName the Destination name.
	 * @param unixFileMode the unix file mode.
	 */
	public Resource(String name, String destinationName, String unixFileMode) {
		super();
		this.name = name;
		if (destinationName == null || destinationName.trim().equals("")) {
			this.destinationName = name;
		} else {
			this.destinationName = destinationName;
		}
		this.unixFileMode = unixFileMode;
	}

	public String getName() {
		return name;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public String getUnixFileMode() {
		return unixFileMode;
	}

}
