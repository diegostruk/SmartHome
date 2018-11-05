package smarthome.knowledge;

/**
 * Exception class that gets thrown when there are errors processing the files
 * that contains queries for the knowledge graph.
 * 
 * @author Diego Struk
 * @version 1.0
 *
 */
public class QueryEngineException extends Exception {

	private static final long serialVersionUID = -6898426025688413924L;
	private String query;
	private String errorCause;

	/**
	 * Initializes a new QueryEngineException object with a specified query and
	 * error cause.
	 * 
	 * @param query
	 *            the query that caused the exception.
	 * @param errorCause
	 *            the description of the exception.
	 */
	public QueryEngineException(String query, String errorCause) {
		this.query = query;
		this.errorCause = errorCause;
	}
	
	/**
	 * Gets the query responsible for throwing the exception.
	 * @return query the query responsible for throwing the exception.
	 */
	public String getQuery() {
		return query;
	}
	
	/**
	 * Gets the cause of the exception.
	 * @return errorCause the cause of the exception.
	 */
	public String getErrorCause() {
		return errorCause;
	}
}
