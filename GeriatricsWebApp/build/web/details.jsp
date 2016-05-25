<%-- 
    Document   : display
    Created on : 20 Feb, 2016, 11:22:32 PM
    Author     : avadhesh
--%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="com.hp.hpl.jena.rdf.model.Literal"%>
<%@page import="com.hp.hpl.jena.query.QuerySolution"%>
<%@page import="com.hp.hpl.jena.query.ResultSet"%>
<%@page import="com.hp.hpl.jena.query.QueryExecutionFactory"%>
<%@page import="com.hp.hpl.jena.query.QueryExecution"%>
<%@page import="com.hp.hpl.jena.query.QueryFactory"%>
<%@page import="com.hp.hpl.jena.query.Query"%>
<%@page import="com.hp.hpl.jena.rdf.model.Model"%>
<%@page import="com.hp.hpl.jena.util.FileManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%!
            public String printName(String value,String delim){              
               StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.split(delim);
		  String  name="";
		  for(int i=0;i<v.length;i++){
             name += v[i].trim()+" ";}
             return name;
               }
               %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       <style>
.center {
    margin: auto;
    width: 30%;
    border: 3px solid #73AD21;
    padding: 10px;
}
</style>
    </head>
    <body>
    <%   
        String s=request.getParameter("val");
        session.setAttribute("disease", s.trim());
        String dname=printName(s,"_");
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
		qexec.close();
	}

%>
<h4><%= dname %></h4>
        <br>
        <b>Symptoms :</b>
        <div class="col-md-offset-1">
        <% 
                 StringTokenizer st = new StringTokenizer(Symptoms);  
		  String[] v = Symptoms.split(",");		 
		  for(int i=0;i<v.length;i++){
                      if(i%2==0){
                      %>         
                      <br>
                        <span class="col-md-5">                       
                      <%}
                      else{
                        %>         
                      <span class="col-md-5" > 
                      <%}

                  out.println(i+1+") "+v[i].trim());
                      %>         
                      </span> 
                      <%

                 }
             
        %>
                                   
        </div>
        <br><br>
        <b>Diagnosis :</b>
        <div class="col-md-offset-1">
        <% 
                  StringTokenizer st1 = new StringTokenizer(Diagnosis);  
		  String[] v1 = Diagnosis.split("\\.\\s+[0-9]+\\.|\\s*[0-9]+\\.");		 
		  for(int j=1;j<v1.length;j++){
                       %>         
                      <br>  
                      <%
                  out.println(j+")  "+v1[j].trim()+".");}
             
        %>
        </div>  
        <br>      
<div class="col-md-offset-9"><a href="main.jsp" target="_blank">Read more on..!!</a></div>
    </body>
</html>
