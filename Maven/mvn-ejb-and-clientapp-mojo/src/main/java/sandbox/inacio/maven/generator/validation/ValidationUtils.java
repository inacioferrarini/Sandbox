package sandbox.inacio.maven.generator.validation;

public class ValidationUtils {

	private ValidationUtils() {}

	public static boolean isNull(final String value) {
		return (value == null);
	}
	
	public static boolean isEmpty(final String value) {
		if (isNull(value)) {
			return true;
		} else {
			return !(value.trim().length() > 0);
		}
	}
	
	public static boolean isNotEmpty(final String value) {
		return !isEmpty(value);
	}
	
}
