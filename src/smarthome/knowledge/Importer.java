package smarthome.knowledge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


/**
 * Reads triples from input files using N-Triple format. Creates a Triple
 * instance for each line read from the input file and adds them to the
 * KnowledgeGraph.
 * 
 * @author Diego Struk
 * @version 1.0
 */
public class Importer {

	public void importTripleFile(String filename) {
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		
		try {
			List<String> allLines = Files.readAllLines(Paths.get(filename));
			String[] lineArr;
			String parsedLine;
			for (int line = 0; line < allLines.size(); line++) {
				parsedLine = allLines.get(line);
				
				if(!parsedLine.trim().equals("")) {
					
					if(!parsedLine.contains("\\.")) {
						// throw exception
						throw new ImportException(line, "Lines should end with a period.");
					}
					if(parsedLine.contains("?")) {
						// throw exception
						throw new ImportException(line, "Input triple lines cannot contain question marks.");
					}
					
					// ignore everything that is on the same line but after the period. assuming
					// predicates and nodes do not have '.'.
					String queryLine = parsedLine.split("\\.")[0];
					lineArr = queryLine.split(" ");
					
					if(lineArr.length != 3) {
						// throw exception
						throw new ImportException(line, "Input triple has to contain 3 components.");
					}
					
					knowledgeGraph.importTriple(lineArr[0], lineArr[1], lineArr[2]);
					
				}
				
			}
			
		} catch (IOException e) {
			System.out.println("Issues reading file");
			System.exit(1);
		} catch (ImportException e) {
			System.out.println("Issue importing file at line " + e.getLineNum() + ": " + e.getErrorCause());
			System.exit(1);
		}
		
		
	}
}
