package smarthome.knowledge;

/**
 * A triple is composed of 2 nodes (subject and object) and a predicate. The
 * triple contains a unique identifier which is the concatenation of the Node,
 * Subject and Predicate identifiers.
 * 
 * @author Diego Struk
 * @version 1.0
 */
public class Triple {

	private String identifier;
	private long createDate;
	private Predicate predicate;
	private Node subject;
	private Node object;

	/**
	 * Creates a new instance of Triple with a specified subject, predicate and
	 * object.
	 * 
	 * @param predicate The predicate of the new Triple.
	 * @param subject The subject of the new Triple.
	 * @param object The object of the new Triple.
	 */
	public Triple(Predicate predicate, Node subject, Node object) {
		this.predicate = predicate;
		this.subject = subject;
		this.object = object;
		this.createDate = System.currentTimeMillis();
		this.identifier = subject.getIdentifier() + " " + predicate.getIdentifier() + " " + object.getIdentifier();
	}
	
	/**
	 * Creates the identifier of a triple in a static manner.
	 * @param subject
	 * @param predicate
	 * @param object
	 * @return the identifier for the triple. 
	 */
	public static String createTripleIdentifier(Node subject, Predicate predicate, Node object) {
		return subject.getIdentifier() + " " + predicate.getIdentifier() + " " + object.getIdentifier();
	}
	
	/**
	 * Gets the triple's identifier.
	 * 
	 * @return A string representing the triple's identifier.
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Gets the triple's created date.
	 * 
	 * @return A Unix time stamp representing the triple's date.
	 */
	public long getCreateDate() {
		return createDate;
	}
	
	/**
	 * Gets the triple's predicate.
	 * 
	 * @return predicate.
	 */
	public Predicate getPredicate() {
		return this.predicate;
	}
	
	/**
	 * Gets the triple's subject.
	 * 
	 * @return subject
	 */
	public Node getSubject() {
		return this.subject;
	}
	
	/**
	 * Gets the triple's object.
	 * 
	 * @return object
	 */
	public Node getObject() {
		return this.object;
	}
	

}
