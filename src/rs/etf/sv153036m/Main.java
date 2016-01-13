package rs.etf.sv153036m;

import org.apache.jena.query.* ;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.*;

public class Main {

	public static void main(String[] args) {
		FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
		Model model = FileManager.get().loadModel("C:\\Users\\Wolfy\\Desktop\\beverages.owl");
		  String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				  			   "PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
				  			   "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				  			   "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
				  			   "PREFIX bev: <http://www.semanticweb.org/etf/rs/sv153036m/beverages.owl#> " +
				  			   "SELECT * " +
	                           "WHERE { " +
	                           "?myClass bev:hasBase bev:EspressoBase . " +
	                           "?myClass bev:hasIngredient bev:Whipped_cream . " + 
	                           "?myClass bev:hasIngredient bev:Cold_milk . " +
	                           "?myClass rdf:type bev:Warm . }";
		  Query query = QueryFactory.create(queryString) ;
		  try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      // RDFNode x = soln.get("varName") ;       // Get a result variable by name.
		      Resource r = soln.getResource("myClass") ; // Get a result variable - must be a resource
		      // Literal l = soln.getLiteral("myClass") ;   // Get a result variable - must be a literal
		      System.out.println(r.getLocalName());
		    }
		  }
	}

}
