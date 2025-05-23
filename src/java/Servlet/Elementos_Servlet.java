/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controllers.DotacionJpaController;
import Controllers.ElementosJpaController;
import Logic.Elementos;
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
@WebServlet(name = "Elementos_Servlet", urlPatterns = {"/Elementos_Servlet"})
public class Elementos_Servlet extends HttpServlet {

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
                guardarelemen(request, response);
                break;
            case "Editar":
                editarelemen(request, response);
                break;
            case "Eliminar":
                Eliminarelemen(request, response);
                break;
            default:
                break;
        }
    }

    protected void guardarelemen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idelemen"));
            String name = request.getParameter("nombre_elemen");
            Elementos s = new Elementos();
            ElementosJpaController ce = new ElementosJpaController();
            s.setIdelemento(codigo);
            s.setNombre(name);
            Elementos buscar = ce.findElementos(codigo);
            if (buscar == null) {
                ce.create(s);
                String msjurl = "Guardado_elemento";
                response.sendRedirect("Views/Gestion_elementos.jsp?rta=" + msjurl);
            } else {
                String msjurl = "Encontrada";
                response.sendRedirect("Views/Gestion_elementos.jsp?rta=" + msjurl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_elementos.jsp?rta=" + msjurl);
        }
    }

    protected void editarelemen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idelem"));
            String name = request.getParameter("nom_elemen");
            Elementos s = new Elementos();
            ElementosJpaController ce = new ElementosJpaController();
            s.setIdelemento(codigo);
            s.setNombre(name);
            s.setCantidades(0);
            ce.edit(s);
            String msjurl = "Actualizado_elemento";
            response.sendRedirect("Views/Gestion_elementos.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_elementos.jsp?rta=" + msjurl);
        }
    }

    protected void Eliminarelemen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idelem"));
            ElementosJpaController cs = new ElementosJpaController();
            cs.destroy(codigo);
            String msjurl = "eliminado_elemento";
            response.sendRedirect("Views/Gestion_elementos.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Referenciado";
            response.sendRedirect("Views/Gestion_elementos.jsp?rta=" + msjurl);

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
