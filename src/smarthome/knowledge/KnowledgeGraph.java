package smarthome.knowledge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Manages a set of active triples. Per requirements no persistence is necessary
 * and can assume that the graph fits within memory.
 * 
 * @author Diego Struk
 *
 */
public class KnowledgeGraph {
	
	private static final KnowledgeGraph INSTANCE = new KnowledgeGraph();
	private Map<String, Node> nodeMap;
	private Map<String, Predicate> predicateMap;
	private Map<String, Triple> tripleMap;
	private Map<String, Set<Triple>> queryMapSet;
	
	/**
	 * Singleton constructor.
	 */
	private KnowledgeGraph() {
		nodeMap = new HashMap<>();
		predicateMap = new HashMap<>();
		tripleMap = new HashMap<>();
		queryMapSet = new HashMap<>();
	}
	
	/**
	 * Gets a reference to the single static instance of the graph.
	 */
	public static KnowledgeGraph getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Adds a triple into the knowledge graph.
	 * @param subject the triple's subject. 
	 * @param predicate the triple's predicate. 
	 * @param object the triple's object.
	 */
	public void importTriple(String subject, String predicate, String object) {
		getTriple(getNode(subject), getPredicate(predicate), getNode(object));
	}
	
	/**
	 * Gets a Node instance for the given identifier. If there are no nodes with
	 * that identifier it creates it and adds it to the NodeMap.
	 * 
	 * @param identifier the identifier of the node.
	 * @return The Node that matches the identifier.
	 */
	public Node getNode(String identifier) {
		identifier = setToUpper(identifier);
		if(nodeMap.containsKey(identifier)) {
			return nodeMap.get(identifier);
		} else {
			Node newNode = new Node(identifier);
			nodeMap.put(identifier, newNode);
			return newNode;
		}
	}
	
	/**
	 * Gets a Predicate instance for the given identifier. If there is no Predicate
	 * with that identifier it creates it and adds it to the PredicateMap.
	 * 
	 * @param identifier the identifier of the Predicate.
	 * @return The Predicate that matches the identifier.
	 */
	public Predicate getPredicate(String identifier) {
		identifier = setToUpper(identifier);
		if(predicateMap.containsKey(identifier)) {
			return predicateMap.get(identifier);
		} else {
			Predicate newPredicate = new Predicate(identifier);
			predicateMap.put(identifier, newPredicate);
			return newPredicate;
		}
	}
	
	/**
	 * Gets the Triple instance for the given subject, predicate and object. If
	 * there is no Triple instance with those parameters it creates it and adds it
	 * to the tripleMap and updates the queryMapSet.
	 * 
	 * @param subject the subject of the Triple.
	 * @param predicate the predicate of the Triple.
	 * @param object the object of the Triple.
	 * @return The Triple that matches the subject, predicate and object parameters.
	 */
	public Triple getTriple(Node subject, Predicate predicate, Node object) {
		
		String identifier = Triple.createTripleIdentifier(subject, predicate, object);
		Triple triple = this.tripleMap.get(identifier);
		
		if(triple == null) {
			triple =  new Triple(predicate, subject, object);
			tripleMap.put(identifier, triple);
		}
		
		addPermutationsToQueryMap(triple);
		
		return triple;
	}
	
	/**
	 * Adds all possible permutations of Triple data to queryMapSet
	 * @param triple 
	 */
	private void addPermutationsToQueryMap(Triple triple) {
		List<String>permutations = new ArrayList<String>();
		String subject = triple.getSubject().getIdentifier();
		String predicate = triple.getPredicate().getIdentifier();
		String object = triple.getObject().getIdentifier();
		String space = " ";
		String question = "?";
		
		permutations.add(subject + space + predicate + space + object);
		permutations.add(question + space + predicate + space + object);
		permutations.add(subject + space + question + space + object);
		permutations.add(subject + space + predicate + space + question);
		permutations.add(question + space + question + space + object);
		permutations.add(question + space + predicate + space + question);
		permutations.add(subject + space + question + space + question);
		permutations.add(question + space + question + space + question);
		
		for(String permutation : permutations) {
			Set<Triple>tripleSet = queryMapSet.get(permutation);
			
			if(tripleSet == null) {
				tripleSet = new HashSet<Triple>();
			}
			tripleSet.add(triple);
			
			queryMapSet.put(permutation, tripleSet);
		}
	}
	
	/**
	 * Executes a query and returns the set of triples that match the query. If
	 * there is no match, return an empty set.
	 * 
	 * @param subject The triple's subject.
	 * @param predicate The triple's predicate.
	 * @param object The triple's object.
	 * @return A set of triples matching the query or an empty set if there's no
	 *         match.
	 */
	public Set<Triple> executeQuery(String subject, String predicate, String object) {
		String queryStr = setToUpper(subject) + " " + setToUpper(predicate) + " " + setToUpper(object);
		if (queryMapSet.containsKey(queryStr)) {
			return queryMapSet.get(queryStr);
		} else {
			return new HashSet<Triple>();
		}
	}
	/**
	 * Sets the identifier to uppercase letters in order to abide by the
	 * case-insensitive requirement.
	 */
	private String setToUpper(String identifier) {
		return identifier == null ? "" : identifier.toUpperCase();
	}
}	
