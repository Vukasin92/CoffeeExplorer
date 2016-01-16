package rs.etf.sv153036m;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.jena.query.* ;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.*;

public class CoffeeFinder {
	
	private static final HashMap<String, String> mappings;
	static
    {
		mappings = new HashMap<String, String>();
		mappings.put("FilterCoffeeBase", "FilterCoffee");
		mappings.put("ChocolateBase", "Mocha");
    }
	
	private Model model;
	private String prefixes = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			   				  "PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
			   				  "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			   				  "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
			   				  "PREFIX bev: <http://www.semanticweb.org/etf/rs/sv153036m/beverages.owl#> ";

	public CoffeeFinder()
	{
		FileManager.get().addLocatorClassLoader(CoffeeFinder.class.getClassLoader());
		model = FileManager.get().loadModel("beverages.owl");
	}
	
	private String[] Query(String queryString, String resourceName)
	{
		ArrayList<String> resultSet = new ArrayList<String>();
		Query query = QueryFactory.create(queryString) ;
		  try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution();
		      Resource r = soln.getResource(resourceName);
		      String name = r.getLocalName();
		      if (mappings.containsKey(name))
		      {
		    	  name = mappings.get(name);
		      }
		      resultSet.add(name);
		    }
		  }
		  return resultSet.toArray(new String[resultSet.size()]);
	}
	
	public String[] getSubClassOf(String className)
	{
		String queryString = prefixes + "SELECT ?subClass WHERE { " +
				"?subClass rdfs:subClassOf bev:" + className + " " +
				"}";
		String[] result = Query(queryString, "subClass");

		return result;
	}
	
	public String[] getBases()
	{
		return getSubClassOf("Base");
	}
	
	public String[] getKinds()
	{
		return getSubClassOf("Beverage");
	}
	
	public String[] getIngredients()
	{
		return getSubClassOf("Ingredients");
	}

	public String[] get(String base, String kind, String[] includedIngr, String[] excludedIngr)
	{
		StringBuilder query = new StringBuilder(prefixes + "SELECT * " +
                												 "WHERE { " +
                									(base.equals("") ? "" : "?myClass bev:hasBase bev:" + base + " . " ) +
                									(kind.equals("") ? "{?myClass rdf:type bev:Warm} UNION {?myClass rdf:type bev:Cold} " : "?myClass rdf:type bev:" + kind + " ")
                						);
		if (includedIngr != null)
		{
			for (String incl : includedIngr)
			{
				query.append(" . ?myClass bev:hasIngredient bev:");
				query.append(incl);
			}
		}
		
		if (excludedIngr != null)
		{
			for (String excl : excludedIngr)
			{
				query.append(". FILTER (  not exists { ?myClass bev:hasIngredient bev:");
				query.append(excl);
				query.append(" }  ) ");
			}
		}
		
		query.append("}");
		
		String queryString = query.toString();
		
		return Query(queryString, "myClass");
	}
}
