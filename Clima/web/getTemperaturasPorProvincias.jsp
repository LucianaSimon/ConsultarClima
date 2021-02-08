<%-- 
    Document   : getTemperaturasPorProvincias
    Created on : Dec 9, 2020, 2:35:12 PM
    Author     : Luciana
--%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="javax.xml.xpath.XPathConstants"%>
<%@page import="javax.xml.xpath.XPathFactory"%>
<%@page import="javax.xml.xpath.XPath"%>
<%@page import="javax.xml.xpath.XPath"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="org.w3c.dom.Document"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get Temperaturas por Localidad</title>
    </head>
    <body>
        <table>
            <tbody>

                <tr>
                    <th  sytle="color:purple">Localidad</th>
                    <th  sytle="color:purple">Temperatura</b></th>
                </tr>
                <%
                    String provincia = request.getParameter("provincia");
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document d = db.parse("http://localhost:8084/Clima/Localidades?Provincia=" + provincia);

                    XPath xp = XPathFactory.newInstance().newXPath();

                    NodeList nl = (NodeList) xp.compile("//localidad").evaluate(d, XPathConstants.NODESET);
                    
                    for (int i = 0; i < nl.getLength(); i++) {
                        String localidad = xp.compile("./nombre").evaluate(nl.item(i));
                        String temperatura = xp.compile("./temperatura").evaluate(nl.item(i));
                        
                        out.print("<tr><th>" + localidad + "</th><th>" + temperatura + "</td></tr>");
                    }
                %>
            <tbody>
        </table>

    </body>
</html>
