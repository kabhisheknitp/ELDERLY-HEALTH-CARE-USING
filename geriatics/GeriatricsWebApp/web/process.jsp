<%-- 
    Document   : newjsp
    Created on : 18 Feb, 2016, 10:48:44 PM
    Author     : avadhesh
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
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
    <head>
        
        <%!
            public String printName(String value ,String delims){
              
               StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.split(delims);
		  String  name="";
		  for(int i=0;i<v.length;i++){
             name += v[i].trim()+" ";}
             return name;
               }
               %>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title> 
        <link rel="shortcut icon" href="img/symbol.png">
      <!--     <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
            <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
             <script src="bootstrap/js/bootstrap.min.js" ></script>
             
        <script type="text/javascript" src="scripts/jquery-1.12.0.min.js"></script>
         <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="stanley/theme/assets/js/hover.zoom.js"></script>
    <script src="stanley/theme/assets/js/hover.zoom.conf.js"></script>
   <link href="stanley/theme/assets/css/main.css" rel="stylesheet">
    <link href="stanley/theme/assets/css/bootstrap.css" rel="stylesheet"> -->
      
      <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
            
             <script src="bootstrap/js/bootstrap.min.js" ></script>
      <link href="stanley/theme/assets/css/bootstrap.css" rel="stylesheet">


    <!-- Custom styles for this template -->
    <link href="stanley/theme/assets/css/main.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/hover.zoom.js"></script>
    <script src="assets/js/hover.zoom.conf.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>	
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.0/jquery-ui.min.js"></script>
    <script type="text/javascript" src="../../scripts/jquery.tooltip.js"></script>


<script type="text/javascript">                  
 /*   $(document).ready(function(){
    $('#disease a').click(function(){
        var me = $(this);
        var k=me.text();        
    ///  alert(k);
      sendInfo(k);     
    }); 
});
*/
    
var request;  
function sendInfo()  
{  
var v= arguments[0]; 
var url="details.jsp?val="+v;  
  
if(window.XMLHttpRequest){  
request=new XMLHttpRequest();  
}  
else if(window.ActiveXObject){  
request=new ActiveXObject("Microsoft.XMLHTTP");  
}  
  
try{  
request.onreadystatechange=getInfo;  
request.open("GET",url,true);  
request.send();  
}catch(e){alert("Unable to connect to server");}  
}  
  
function getInfo(){  
if(request.readyState==4){  
var val=request.responseText;  
document.getElementById('avadhesh').innerHTML=val;  
}  
}  
  
</script>  
        
        
        
        <style>
.center {
    margin: auto;
    border: 3px solid #73AD21;
    padding: 10px;
}
</style>
</head>
<body>
      
  <% 
   String []symptoms= request.getParameterValues("p_new");
   String squery="";
   String disease="";
   String diseaseNames="";
   
   Model model=FileManager.get().loadModel("C:/Users/SUNIL/Documents/NetBeansProjects/GeriatricsWebApp/web/data/geriatrics.owl");
		 /* for(int i=1;i<symptoms.length;i++)                    
                squery ="bB:HasSymptoms bB:"+symptoms[i]+";";
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
                squery +="union {?s  OL:HasSymptoms  OL:"+symptoms[i]+"}";
                String  queryString=
"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
"PREFIX OL: <http://purl.obolibrary.org/obo/geriatrics.owl#>"+
"PREFIX : <http://xmlns.com/foaf/0.1/>"+
"SELECT * WHERE { " +
"   {?s  OL:HasSymptoms  OL:"+symptoms[0]+"}"+squery+
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
                         disease=st.nextToken();
                         
                         diseaseNames+=disease+",";
		          }
	                      }catch(Exception e){ out.print("Something is wrong ");}
	              finally
	{
		qexec.close();
	}
		

  %>
    <!-- Static navbar -->
    <div  class="navbar navbar-inverse navbar-static-top">
      <div class="container">
        <div class="navbar-header">
            
            <div class="row">
		<div class="col-lg-2"><img class="img-circle" height="70" width="70" src="stanley/theme/assets/img/green.jpg" alt="Stanley"></div>
		<div class="col-lg-10">
            <a class="navbar-brand" href="stanley/theme/index.html">Geriatrics HealthCare<br><h6>(A Healthcare System For Elderly People)</h6></a>
		</div>
       </div>
            
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
      <!--    <a class="navbar-brand" href="stanley/theme/index.html">Geriatrics Ontology</a> -->
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="stanley/theme/work.html">Symptom Checker</a></li>
            <li><a href="ontoBrowser.html">Ontology Browser</a></li>
            <li><a href="stanley/theme/contact.html">Contact</a></li>
          </ul>
        </div>
      </div>
    </div><!-- nav-collapse -->
 
    <div class="container pt">
        
        <div class="row mt">
            <div class="col-lg-6 col-lg-offset-3 centered">
                    <h3>RESULT :  Diseases and their Treatment</h3>
                    <hr>
                    <p><b>Your Symptoms:</b>
                        <% 
                    out.print(" "+printName(symptoms[0],"_"));
                    for(int i=1;i<symptoms.length;i++)
                       out.print(", "+printName(symptoms[i],"_"));
                     %>
                    </p>
            </div>
        </div>
        
     <div class="row">                      
     <div class="col-md-4">
        <div class="panel panel-success">
            <div class="panel-heading"> 
                <div class="panel-title text-center">
                    <h4>          
                    Probable Disease
                     </h4>
                </div>
            </div>
            <div class="list-group">
                 
                         <%			
                            StringTokenizer st = new StringTokenizer(diseaseNames);  
                            String[] v = diseaseNames.split(",");
                            List asList = Arrays.asList(v);
                            Set<String> mySet = new HashSet<String>(asList);
                            for(int i=symptoms.length;i>0;i--){
                            for(String s: mySet){
                            //for(int i=0;i<v.length;i++){ 
                            String d=s.trim();
                            if(i==Collections.frequency(asList,s)) {
                                 if(i>1&&i==symptoms.length){
                                  %>
                                   <a  href="#resultShow" onclick="sendInfo('<%=d %>')" class="list-group-item">
                                  <%                                              
                                  out.print(printName(s.trim(),"_")+ "<sup style='color:red;'>★</sup>");}
                                else if(i>1&&i<symptoms.length){
                                  %>
                                   <a  href="#resultShow" onclick="sendInfo('<%=d %>')" class="list-group-item">
                                  <%                                              
                                  out.print(printName(s.trim(),"_")+ "<sup style='color:brown;'>★</sup>");}
                                  else{
                                  %>
                                   <a  href="#resultShow" onclick="sendInfo('<%=d %>')" class="list-group-item">
                                  <%                                              
                                  out.print(printName(s.trim(),"_"));}



                         } //end outer if
                      }}//end outer For loop 
                        %>
                           
                         </a> 
            </div>
                        
              </div>    
            </div>
                     
           <div class="col-md-8">
                        
                          <div class="panel panel-success">
                            <div class="panel-heading">
                                <h4 class="text-center">Disease Information</h4>                           
                           </div>                              
                              <div class="panel-body">
                                   <span id="avadhesh" class="panel-body"> </span>
                               </div>
                          </div>
             </div>
        </div>
           </div>
               <br><br><br><br><br><br>       
        <div id="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-4">
					<h4>Developed At</h4>
					<p>
						NIT Patna,<br/>
						Ashok Rajpath, <br/>
						Patna, Bihar,<br/>
						800005.
					</p>
				</div><!-- /col-lg-4 -->
				
				<div class="col-lg-4">
					<h4>Developers</h4>
					<p>
						<a href="#">Avadhesh Singh</a><br/>
						<a href="#">Sunil Gupta</a><br/>
						<a href="#">Vivek Raj</a><br/>
						<a href="#">Shubhnkar Upadhyay</a>
					</p>
				</div><!-- /col-lg-4 -->
				
				<div class="col-lg-4">
					<h4>About Project</h4>
					<p>This project was created to enhance the diagnosis process for elderly people. The site is powered by a Decision Support System based on an Ontology Framework.</p>
				</div><!-- /col-lg-4 -->
			
			</div>
		
		</div>
	</div>
    
    </body>
</html>
