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
public class AndiProcess extends HttpServlet {

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
         try{
        String t=request.getParameter("name");
      
         StringTokenizer stk = new StringTokenizer(t);  
        String[] symptoms = t.split(",");
       
         String squery="";
        // jena code
        // FileManager.get().addLocatorClassLoader(hi.class.getClassLoader());
     
		Model model=FileManager.get().loadModel("C:/Users/SUNIL/Documents/NetBeansProjects/GeriatricsWebApp/web/data/geriatrics.owl");
	/*	for(int i=1;i<symptoms.length;i++)
                  squery ="bB:HasSymptoms bB:"+symptoms[i].trim()+";";
		String queryString=
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
						"PREFIX bB: <http://purl.obolibrary.org/obo/geriatrics.owl#>"+
						"SELECT * WHERE { " +
		"?x bB:HasSymptoms bB:"+symptoms[0]+";"+squery+
		"rdfs:label  ?label."+
						"}";
		*/
        for(int i=1;i<symptoms.length;i++)                    
                squery +="union {?s  OL:HasSymptoms  OL:"+symptoms[i].trim()+"}";
                String  queryString=
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "PREFIX OL: <http://purl.obolibrary.org/obo/geriatrics.owl#>"+
                "PREFIX : <http://xmlns.com/foaf/0.1/>"+
                "SELECT * WHERE { " +
                "   {?s  OL:HasSymptoms  OL:"+symptoms[0].trim()+"}"+squery+
                "?s rdfs:label ?label"+
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
                        String m=name.toString();                        
                         StringTokenizer st = new StringTokenizer(m,"^^");
                         String disease=st.nextToken();
                       
			out.println(printName(disease)+",");
                       
		          }
	                      }catch(Exception e){ out.print("Something is wrong ");}
	              finally
	{
		qexec.close();
	}
        
        
        
        
     }catch(Exception e){out.print("Something is wrong");}  
    }

     public String printName(String value){
              
               StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.split("_");
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
