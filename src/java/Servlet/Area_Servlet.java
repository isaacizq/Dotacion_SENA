/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controllers.*;
import Logic.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isaac
 */
@WebServlet(name = "Area_Servlet", urlPatterns = {"/Area_Servlet"})
public class Area_Servlet extends HttpServlet {

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
        String button = request.getParameter("action");
        PrintWriter n = response.getWriter();
        switch (button) {
            case "Guardar":
                guardararea(request, response);
                break;
            case "Editar":
                editarrarea(request, response);
                break;
            case "Eliminar":
                Eliminarea(request, response);
                break;
            default:
                break;
        }
    }

    protected void guardararea(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idarea"));
            String name = request.getParameter("nombre_area");
            int codigo_red = Integer.parseInt(request.getParameter("red"));
            RedJpaController cr = new RedJpaController();
            Red network = cr.findRed(codigo_red);
            Area s = new Area();
            AreaJpaController ca = new AreaJpaController();
            s.setIdarea(codigo);
            s.setNombre(name);
            s.setRedIdred(network);
            ca.create(s);
            String msjurl = "guardado_area_red";
            response.sendRedirect("Views/Gestion_area_red.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_area_red.jsp?rta=" + msjurl);
        }
    }

    protected void editarrarea(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idareas"));
            String name = request.getParameter("nombre_areas");
            int codigo_red = Integer.parseInt(request.getParameter("Redes"));
            RedJpaController cr = new RedJpaController();
            Red network = cr.findRed(codigo_red);
            Area s = new Area();
            AreaJpaController ca = new AreaJpaController();
            s.setIdarea(codigo);
            s.setNombre(name);
            s.setRedIdred(network);
            ca.edit(s);
            String msjurl = "editado_area_red";
            response.sendRedirect("Views/Gestion_area_red.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_area_red.jsp?rta=" + msjurl);
        }
    }

    protected void Eliminarea(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idareas"));
            AreaJpaController cs = new AreaJpaController();
            cs.destroy(codigo);
            String msjurl = "eliminado_area_red";
            response.sendRedirect("Views/Gestion_area_red.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Referenciado";
            response.sendRedirect("Views/Gestion_area_red.jsp?rta=" + msjurl);

        }

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
