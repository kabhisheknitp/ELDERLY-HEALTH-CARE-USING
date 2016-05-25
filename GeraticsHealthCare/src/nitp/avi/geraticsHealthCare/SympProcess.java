package nitp.avi.geraticsHealthCare;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;
public class SympProcess {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		sparqlTest();	
		}
		
		
		
		static void sparqlTest()
		{
			FileManager.get().addLocatorClassLoader(SympProcess.class.getClassLoader());
		    
			Model model=FileManager.get().loadModel("C:/Users/vivek raj/workspace/hello/src/data.rdf");
			String queryString=
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
							"PREFIX bB: <http://purl.obolibrary.org/obo/bfo.owl#>"+
							"SELECT * WHERE { " +
			"bB:Hypertension bB:HasSymptoms ?x." +
			"?x rdfs:label  ?label."+
							"}";
			
			
		
			Query query=QueryFactory.create(queryString);
			QueryExecution qexec=QueryExecutionFactory.create(query,model);
		try
		{
			ResultSet results=qexec.execSelect();
			while(results.hasNext())
			{
				QuerySolution soln=results.nextSolution();
				Literal name=soln.getLiteral("label");
				System.out.println(name);
			}
		}
		finally
		{
			qexec.close();
		}
			
			
		
		}
			

	

}
