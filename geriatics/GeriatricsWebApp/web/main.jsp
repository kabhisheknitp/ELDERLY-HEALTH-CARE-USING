<%-- 
    Document   : main
    Created on : 18 Feb, 2016, 10:48:44 PM
    Author     : avadhesh
--%>

<%@page import="java.util.StringTokenizer"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Disease Info</title>
        <link rel="shortcut icon" href="img/symbol.png">
        <%
               String value=(String)session.getAttribute("disease");
               StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.split("_");
		  String  disease="";
		  for(int i=0;i<v.length;i++){
             disease += v[i].trim()+" ";}
               %>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <frameset cols="250px, *">
   <frame src="menu.jsp" name="menu_page" noresize="noresize" />
   <frame src="https://en.wikipedia.org/w/index.php?search=<%=disease %>" name="main_page" />
   <noframes>
   <body>
      Your browser does not support frames.
   </body>
   </noframes>
</frameset>
</html>
