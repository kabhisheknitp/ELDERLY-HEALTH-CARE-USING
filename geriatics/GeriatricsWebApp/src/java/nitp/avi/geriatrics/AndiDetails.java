/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nitp.avi.geriatrics;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//jena
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;
import java.util.StringTokenizer;

/**
 *
 * @author DELL
 */
public class AndiDetails extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
           
            //jena code
        //String s="Hypertension";
         String s=request.getParameter("name");
        //request.getParameter("val");
       // session.setAttribute("disease", s.trim());
        
        String Diagnosis="";
        String Symptoms="";
		Model model=FileManager.get().loadModel("C:/Users/SUNIL/Documents/NetBeansProjects/GeriatricsWebApp/web/data/geriatrics.owl");
		String k="bB:"+s.trim()+" ";
		String queryString=
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
						"PREFIX bB: <http://purl.obolibrary.org/obo/geriatrics.owl#>"+
		"PREFIX : <http://xmlns.com/foaf/0.1/>"+
						"SELECT * WHERE { " +
		k+"rdfs:"+
		"label ?label"+";"+"rdfs:comment ?com"+
						"}";
		
		
                //fetching symptoms
                String queryStringSymptoms=
	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
	"PREFIX bB: <http://purl.obolibrary.org/obo/geriatrics.owl#>"+
	"PREFIX : <http://xmlns.com/foaf/0.1/>"+
	"SELECT * WHERE { " +
	"?x bB:IsSymptomOf bB:"+s.trim() +";"+
	"rdfs:label ?label"+
	"}";
             Query querySymp=QueryFactory.create(queryStringSymptoms);
	QueryExecution qexecSymp=QueryExecutionFactory.create(querySymp,model);
        
        try
	{
	ResultSet resultsSymp=qexecSymp.execSelect();
	while(resultsSymp.hasNext())
	{
	QuerySolution solnSymp=resultsSymp.nextSolution();
	Literal nameSymp=solnSymp.getLiteral("label");	
	String symp=nameSymp.toString();
	int quit_positionSymp = symp.indexOf("^");
	Symptoms+=printName(symp.substring(0, quit_positionSymp) , "_")+",";
         
                 
       	}
        
	}
	finally
	{
	qexecSymp.close();
	}
        
        
        
		
		Query query=QueryFactory.create(queryString);
		QueryExecution qexec=QueryExecutionFactory.create(query,model);
	try
	{
		ResultSet results=qexec.execSelect();
		while(results.hasNext())
		{
			QuerySolution soln=results.nextSolution();
			Literal name=soln.getLiteral("label");
			Literal nu=soln.getLiteral("com");
			String hu=name.toString();
			
			
			int quit_position = hu.indexOf("^^");
                        
			int quit_position1 = nu.toString().indexOf("^^");                    
                                                                              
			//out.print(hu.substring(0, quit_position));  //disease name      
    
                       Diagnosis= nu.toString().substring(0, quit_position1);  //Diagnosis
                       
			
		}
	}
        catch(Exception e){out.print("Somthing goes wrong..plz try again.");}
	finally
	{
            out.print(Symptoms+"AviDiagnosis"+Diagnosis);
		qexec.close();
	}

    }

    public String printName(String value,String delim){              
               StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.split(delim);
		  String  name="";
		  for(int i=0;i<v.length;i++){
                   name += v[i].trim()+" ";}
             return name;
               }
    

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
