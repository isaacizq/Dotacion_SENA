/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;
import Controllers.SexoJpaController;
import Logic.Sexo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isaac
 */
@WebServlet(name = "Sexo_Servlet", urlPatterns = {"/Sexo_Servlet"})
public class Sexo_Servlet extends HttpServlet {

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
        switch (button) {
            case "Guardar":
                guardarsexo(request, response);
                break;
            case "Editar":
                editarsexo(request, response);
                break;
            case "Eliminar":
                Eliminarsexo(request, response);
                break;
            default:
                break;
        }
    }

    protected void guardarsexo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idsexo"));
            String name = request.getParameter("nombresexo");
            Sexo s = new Sexo();
            SexoJpaController cs = new SexoJpaController();
            s.setIdsexo(codigo);
            s.setNombre(name);
            cs.create(s);
            String msjurl = "guardado_clima_sexo";
            response.sendRedirect("Views/Gestion_clima_sexo.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_clima_sexo.jsp?rta=" + msjurl);
        }
    }

    protected void editarsexo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idsexos"));
            String name = request.getParameter("nom_sexo");
            Sexo s = new Sexo();
            SexoJpaController cc = new SexoJpaController();
            s.setIdsexo(codigo);
            s.setNombre(name);
            cc.edit(s);
            String msjurl = "editado_clima_sexo";
            response.sendRedirect("Views/Gestion_clima_sexo.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_clima_sexo.jsp?rta=" + msjurl);
        }
    }

    protected void Eliminarsexo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idsexos"));
            SexoJpaController cs = new SexoJpaController();
            cs.destroy(codigo);
            String msjurl = "eliminado_clima_sexo";
            response.sendRedirect("Views/Gestion_clima_sexo.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Referenciado";
            response.sendRedirect("Views/Gestion_clima_sexo.jsp?rta=" + msjurl);

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
