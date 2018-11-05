package smarthome.knowledge;

/**
 * A predicate is the connection between 2 nodes. I.e: Bob lives_in House1,
 * lives_in represents the predicate.
 * 
 * @author Diego Struk
 *
 */
class Predicate {
	
	private String identifier; 
	private long createdDate;
	
	/**
	 * Initializes a new predicate. 
	 * @param identifier the identifier for the predicate.
	 */
	public Predicate(String identifier) {
		this.identifier = identifier;
		this.createdDate = System.currentTimeMillis();
	}
	
	/**
	 * @return the predicate identifier.
	 */
	public String getIdentifier() {
		return this.identifier;
	}
	
	/**
	 * @return the predicate created date.
	 */
	public long getCreatedDate() {
		return this.createdDate;
	}
}
