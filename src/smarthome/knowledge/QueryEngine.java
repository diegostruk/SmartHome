package smarthome.knowledge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;


/**
 * Supports the execution of knowledge graph queries.
 * 
 * @author Diego Struk
 *
 */
public class QueryEngine {

	/**
	 * Executes a single query in the knowledge graph.
	 * @param query the string query to execute.
	 * @throws QueryEngineException 
	 */
	public void executeQuery(String query) throws QueryEngineException {
		String queryTm = query.split(".")[0].trim();
		if (!query.contains(".")) {
			throw new QueryEngineException(queryTm + ".", "A query should end in a period.");
		} else {
			String[] querySplit = queryTm.split(" ");
			if (querySplit.length != 3) {
				throw new QueryEngineException(queryTm + ".", "A query should have 3 arguments.");
			}
			KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
			Set<Triple> queryResult = knowledgeGraph.executeQuery(querySplit[0], querySplit[1], querySplit[2]);

			// print the original query
			System.out.println(queryTm + ".");

			if (queryResult.isEmpty()) {
				System.out.println("<null>");
			} else {
				for (Triple triple : queryResult) {
					System.out.println(triple.getIdentifier());
				}
			}
		}
	}
		
		/**
		 * Executes a set of queries read from a file. Delegates to executeQuery for
		 * processing individual queries.
		 * 
		 * @param filename
		 *            The name of the file to read the queries from.
		 * @throws QueryEngineException
		 *             if there are malformed queries or errors accessing the input
		 *             file.
		 */
		public void executeQueryFile(String filename) {
			try {
				List<String> allLines = Files.readAllLines(Paths.get(filename));
				for (String line : allLines) {
					this.executeQuery(line);
				}
			} catch (IOException e) {
				System.err.println("Issues reading file " + filename + ".");
			} catch (QueryEngineException qe) {
				System.err.println("Query" + qe.getQuery() + ":" + qe.getErrorCause());
			}
		}
}
