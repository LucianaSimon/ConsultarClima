/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luciana
 */
@WebServlet(name = "Localidades", urlPatterns = {"/Localidades"})
public class Localidades extends HttpServlet {
    
    String urlLocalidades = "http://localhost:8081/localidades";

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
        
        String xml = requestXML(urlLocalidades);
        System.out.println(xml);
        
        String provincia = request.getParameter("Provincia");
        
        //Me solicitan las localidades de una determinada provincia
        if(provincia!=null){
            xml = filter(xml, provincia);
        }
        
        response.setContentType("text/xml;charset=UTF-8"); 
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(xml);
        }
    }
    
    String requestXML(String url_in) throws MalformedURLException, IOException{
        
        StringBuilder xml = new StringBuilder();
        
        URL url = new URL(url_in);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputXML;
        
              while ((inputXML = in.readLine()) != null) {
            xml.append(inputXML);
        }

        return xml.toString();

    }

    String filter(String xml, String provincia) {

        String localidades[] = xml.split("<localidad>");        
        StringBuilder xmlProvincia = new StringBuilder();

        xmlProvincia.append("<localidades>");

        for (int i = 1; i < localidades.length; i++) {
            if (localidades[i].contains(provincia)) {

                xmlProvincia.append("<localidad>" + localidades[i]);
                System.out.println("<localidad>" + localidades[i]);
            }
        }

        if (!xmlProvincia.toString().contains("</localidades>")) {
            xmlProvincia.append("</localidades>");
        }

        return xmlProvincia.toString();
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
