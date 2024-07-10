/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controllers.AreaJpaController;
import Controllers.ClimaJpaController;
import Controllers.RedJpaController;
import Logic.Clima;
import Logic.Red;
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
@WebServlet(name = "Red_Servlet", urlPatterns = {"/Red_Servlet"})
public class Red_Servlet extends HttpServlet {

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
                guardarred(request, response);
                break;
            case "Editar":
                editarrred(request, response);
                break;
            case "Eliminar":
                Eliminared(request, response);
                break;
            default:
                break;
        }
    }

    protected void guardarred(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idred"));
            String name = request.getParameter("nombre_red");
            Red s = new Red();
            RedJpaController cr = new RedJpaController();
            s.setIdred(codigo);
            s.setNombre(name);
            cr.create(s);
            String msjurl = "guardado_area_red";
            response.sendRedirect("Views/Gestion_area_red.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_area_red.jsp?rta=" + msjurl);
        }
    }

    protected void editarrred(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idredes"));
            String name = request.getParameter("nombre_redes");
            Red s = new Red();
            RedJpaController cr = new RedJpaController();
            s.setIdred(codigo);
            s.setNombre(name);
            cr.edit(s);
            String msjurl = "editado_area_red";
            response.sendRedirect("Views/Gestion_area_red.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_area_red.jsp?rta=" + msjurl);
        }
    }

    protected void Eliminared(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idredes"));
            RedJpaController cs = new RedJpaController();
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
