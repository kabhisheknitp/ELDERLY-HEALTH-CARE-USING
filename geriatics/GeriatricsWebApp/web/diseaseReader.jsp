 <%-- 
    Document   : diseaseReader
    Created on : Apr 15, 2016, 12:32:32 PM
    Author     : Avadhesh
--%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="org.w3c.dom.Element"%>
<%@page import="org.w3c.dom.Node"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%
     
     String dname =(String)request.getParameter("val");
       session.setAttribute("disease", dname.trim());
     String Symptoms="";
      String Diagnosis="";
 try {	
         File inputFile = new File("C:/Users/SUNIL/Documents/NetBeansProjects/GeriatricsWebApp/web/data/diseasedetail.xml");
         DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
        // out.println("Root element :" 
          //  + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("disease");
        
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
           
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
 
               if( dname.trim().equalsIgnoreCase(
                  eElement
                  .getElementsByTagName("name")
                  .item(0)
                  .getTextContent())){
               Symptoms = eElement
                  .getElementsByTagName("symptoms")
                  .item(0)
                  .getTextContent();               
               
               Diagnosis = eElement
                  .getElementsByTagName("diagnosis")
                  .item(0)
                  .getTextContent();
               
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         out.print("Some thing Is Wrong ..Try again.");
      }
        %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <h4><%= dname %></h4>
        <br>
        <b>Symptoms :</b>
        <div class="col-md-offset-1">
        <% 
                 StringTokenizer st = new StringTokenizer(Symptoms);  
		  String[] v = Symptoms.split(",");		 
		  for(int i=0;i<v.length;i++){
                      %>         
                      <br>  
                      <%
                  out.println(i+1+") "+v[i].trim());}
             
        %>
        </div>
        <br>
        <b>Diagnosis :</b>
        <div class="col-md-offset-1">
        <% 
                  StringTokenizer st1 = new StringTokenizer(Diagnosis);  
		  String[] v1 = Diagnosis.split("\\.\\s+[0-9]+\\.|\\s+[0-9]+\\.");		 
		  for(int j=1;j<v1.length;j++){
                       %>         
                      <br>  
                      <%
                  out.println(j+")  "+v1[j].trim()+".");}
             
        %>
        </div> 
        <br>
        <div class="col-md-offset-9"><a href="../../main.jsp" target="_blank">Read more on..!!</a></div>
    </body>
</html>
