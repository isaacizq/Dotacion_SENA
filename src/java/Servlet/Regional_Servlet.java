/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;
import Controllers.RegionalJpaController;
import Logic.Regional;
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
@WebServlet(name = "Regional_Servlet", urlPatterns = {"/Regional_Servlet"})
public class Regional_Servlet extends HttpServlet {

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
                guardarregional(request, response);
                break;
            case "Editar":
                editarrregional(request, response);
                break;
            case "Eliminar":
                Eliminarregional(request, response);
                break;
            default:
                break;
        }
    }

    protected void guardarregional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idregional"));
            String name = request.getParameter("nombre_regional");
            Regional s = new Regional();
            RegionalJpaController ca = new RegionalJpaController();
            s.setIdregional(codigo);
            s.setNombre(name);
            Regional buscar = ca.findRegional(codigo);
            if (buscar == null) {
                ca.create(s);
                String msjurl = "Guardado_regional_centro";
                response.sendRedirect("Views/Gestion_regional_centro.jsp?rta=" + msjurl);
            } else {
                String msjurl = "Encontrado";
                response.sendRedirect("Views/Gestion_regional_centro.jsp?rta=" + msjurl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_regional_centro.jsp?rta=" + msjurl);
        }
    }

    protected void editarrregional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idregionales"));
            String name = request.getParameter("nombre_region");
            Regional s = new Regional();
            RegionalJpaController ca = new RegionalJpaController();
            s.setIdregional(codigo);
            s.setNombre(name);
            ca.edit(s);
            String msjurl = "editado_regional_centro";
            response.sendRedirect("Views/Gestion_regional_centro.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_regional_centro.jsp?rta=" + msjurl);
        }
    }

    protected void Eliminarregional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idregionales"));
            RegionalJpaController cs = new RegionalJpaController();
            cs.destroy(codigo);
            String msjurl = "eliminado_regional_centro";
            response.sendRedirect("Views/Gestion_regional_centro.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Referenciado";
            response.sendRedirect("Views/Gestion_regional_centro.jsp?rta=" + msjurl);

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