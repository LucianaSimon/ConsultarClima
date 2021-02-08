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
@WebServlet(name = "Provincias", urlPatterns = {"/Provincias"})
public class Provincias extends HttpServlet {
    
    String urlProvincias = "http://localhost:8081/provincias"; 

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
   
        String xml = requestXML(urlProvincias);
        System.out.println(xml);

        String json = XMLtoJSON(xml);
        System.out.println(json);

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            //response.getWriter().write(json.toString());
            out.println(json.toString());
        }
    }
    
    String XMLtoJSON(String xml){
        StringBuilder json = new StringBuilder();        
        json.append("[");
        
        String[] provincias = xml.split("<provincia>");
        
        for(int i=1; i<provincias.length; i++){ 
            String provincia = provincias[i].replace("</provincia>", "");
            System.out.println(provincia);

            if(i != (provincias.length - 1)){
                json.append("\"" + provincia + "\","); 
            }else{
                provincia = provincia.split("</provincias>")[0];
                json.append("\"" + provincia + "\"]");
            }            
        }
        
        return json.toString();
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
