package smarthome.knowledge;

/**
 * Instances of the Node class represent subjects and/or objects. Node contains
 * a unique String identifier and a created date.
 * 
 * @author Diego Struk
 *
 */
public class Node {

	private String identifier;
	private long createDate;

	/**
	 * Creates a new instance of the the Node class with a specified identifier.
	 * 
	 * @param identifier The specified identifier of the new node.
	 */
	public Node(String identifier) {
		this.identifier = identifier;
		createDate = System.currentTimeMillis();
	}

	/**
	 * Gets the node identifier.
	 * 
	 * @return A string representing a node's identifier.
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Gets the node's creation date.
	 * 
	 * @return A Unix time stamp representing the node's date.
	 */
	public long getCreateDate() {
		return createDate;
	}
}
