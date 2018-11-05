package smarthome.knowledge;
/**
 * Exception class that gets thrown when there are errors processing the input
 * Triple file.
 *
 * @author Diego Struk
 * @version 1.0
 *
 */
public class ImportException extends Exception {

	private static final long serialVersionUID = -4017865101129261286L;
	private int lineNum;
	private String errorCause;
	
	/**
	 * Creates a new import exception.
	 * @param lineNum line in which the issue was found.
	 * @param errorCause a description of the error.
	 */
	public ImportException(int lineNum, String errorCause) {
		this.lineNum = lineNum;
		this.errorCause = errorCause;
	}
	
	/**
	 * Get the line number where the exception is thrown.
	 * @return line num the line number in the input file where the exception was found.
	 */
	public int getLineNum() {
		return lineNum;
	}
	
	/**
	 * Get the cause of the exception.
	 * @return errorCause the cause of the exception.
	 */
	public String getErrorCause() {
		return errorCause;
	}
}
