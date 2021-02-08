<%-- 
    Document   : estadisticaNacional
    Created on : Dec 9, 2020, 1:22:10 PM
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
        <title>Estadisticas Nacionales</title>
    </head>
    <body>
        <h1 style="color:purple">Estadísiticas Nacionales</h1>
        <table>
            <tr>
                <th>Cantidad <br>Localidades</th>
                <th>Temepratura <br>Máxima</th>
                <th>Temperatura<br> Mínima</th>
                <th>Temperatura <br>Promedio</th>
            </tr>
            <tr><%
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = builder.parse("http://localhost:8084/Clima/Localidades");
                XPath xPath = XPathFactory.newInstance().newXPath();
                String expression = "//temperatura";
                NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

                int cantidad = nodeList.getLength();
                float temperatura;
                float maximaTemp = -60;
                float minimaTemp = 60;
                float promedioTemp = 0;

                for (int i = 0; i < nodeList.getLength(); i++) {
                    temperatura = Float.parseFloat(nodeList.item(i).getTextContent());

                    promedioTemp += temperatura;

                    if (temperatura > maximaTemp) {
                        maximaTemp = temperatura;
                    }

                    if (temperatura < minimaTemp) {
                        minimaTemp = temperatura;
                    }
                }

                promedioTemp = promedioTemp / cantidad;
                %>
                <th> <%=cantidad %>  </th>
                <th> <%=maximaTemp %> </th>
                <th> <%=minimaTemp %> </th>
                <th> <%=promedioTemp %> </th>
            </tr>
        </table>
        
        <a href="index.html" style="text-align:center">Home</a>
    </body>
</html>
