<%-- 
    Document   : display
    Created on : 20 Feb, 2016, 11:22:32 PM
    Author     : avadhesh
--%>
<%@page import="java.util.*"%>
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
    
    <%   
       // String s=request.getParameter("val");
        
        String Symptoms="";
		Model model=FileManager.get().loadModel("C:/Users/SUNIL/Documents/NetBeansProjects/GeriatricsWebApp/web/data/geriatrics.owl");
		
		
		
		
                //fetching symptoms
                String queryStringSymptoms=
	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
	"PREFIX bB: <http://purl.obolibrary.org/obo/geriatrics.owl#>"+
	"PREFIX : <http://xmlns.com/foaf/0.1/>"+
	"SELECT distinct * WHERE { " +
	"?x rdfs:subClassOf bB:Bodily_Process . ?y rdfs:subClassOf  ?x;"+
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
	//Symptoms+=printName(symp.substring(0, quit_positionSymp) , "_")+",";
        Symptoms+=symp.substring(0, quit_positionSymp)+",";
	}
	}
	finally
	{
	qexecSymp.close();
	}
        
        
        
		
		

%>


        <% 
                 StringTokenizer st = new StringTokenizer(Symptoms);  
		  String[] str = Symptoms.split(",");
                 /*      String[] str = new String[v.length];
		  for(int i=0;i<v.length;i++){
                      str[i] = v[i].trim();
                  }
                 // out.println(v[i].trim());}*/
                 
           String query = (String)request.getParameter("q");
 
        //  int cnt=1;
       for(int j=0;j<str.length;j++)
       {
         if(str[j].trim().toUpperCase().startsWith(query.toUpperCase().trim()))
          {
           
              out.print(str[j].trim()+"\n");
             
             // if(cnt>=10)// 5=How many results have to show while we are typing(auto suggestions)
             // break;
              //++;
            }
       }
             
        %>
