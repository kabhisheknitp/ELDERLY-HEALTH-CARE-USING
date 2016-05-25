<%-- 
    Document   : menu
    Created on : 18 April, 2016, 10:48:44 PM
    Author     : avadhesh
--%>
<%@page import="java.util.StringTokenizer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Disease Info</title> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
            <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
           <script src="bootstrap/js/bootstrap.min.js" ></script>
               
              
           <%
               String value=(String)session.getAttribute("disease");
               StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.split("_");
		  String  disease="";
		  for(int i=0;i<v.length;i++){
             disease += v[i].trim()+" ";}
               %>

       
    </head>
    <body class="container">
                        
                <ul class="nav nav-pills nav-stacked" >
                    <li>
                        <div class="text-center">
			<div style="background-color:#26a69a !important" class="jumbotron">
                            <h3><b><%=disease %></b></h3><span class="glyphicon glyphicon-search"></span>
                            Find more on
                             
		        </div>		
		       </div>
                    </li>
                    <li role="presentation"><a href="https://en.wikipedia.org/w/index.php?search=<%=disease %>" target="main_page"> <img src="img/wikipedia.png" alt="Wikipedia tutorial"/> Wikipedia</a></li>
                    <li role="presentation"><a href="http://www.webmd.com/search/search_results/default.aspx?query=<%=disease %>" target="main_page"><img src="img/webmd.png" alt="WebMD"> WebMD</a></li>
                    <li role="presentation"><a href="http://www.mayo.edu/research/search/search-results?q=<%=disease %>" target="main_page"><img src="img/mayo.jpg" alt="Mayo Clinic"> Mayo Clinic</a></li>
                    <li role="presentation"><a href="https://vsearch.nlm.nih.gov/vivisimo/cgi-bin/query-meta?v%3Aproject=medlineplus&v%3Asources=medlineplus-bundle&query=<%=disease %>" target="_blank"><img src="img/medlineplus.png" alt="Medilineplus"> Medilineplus</a> </li>
                    <li role="presentation"><a href="https://www.google.co.in/#q=<%=disease %>" target="_blank"><img src="img/google.png" alt="google"> google</a></li>
                    <li role="presentation"><a href="https://www.youtube.com/results?search_query=<%=disease %>" target="_blank"><img src="img/youtube.png" alt="Youtube" onclick="sendInfo()"> Youtube</a></li>
               </ul>
            
           
        
    </body>
</html>
