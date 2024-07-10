/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controllers.AreaJpaController;
import Controllers.ClimaJpaController;
import Controllers.DotacionJpaController;
import Controllers.ElementosJpaController;
import Controllers.SexoJpaController;
import Logic.Area;
import Logic.Clima;
import Logic.Dotacion;
import Logic.Elementos;
import Logic.Sexo;
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
@WebServlet(name = "Dotacion_Servlet", urlPatterns = {"/Dotacion_Servlet"})
public class Dotacion_Servlet extends HttpServlet {

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
                guardardotacion(request, response);
                break;
            case "Editar":
                editardotacion(request, response);
                break;
            case "Eliminar":
                Eliminardotacion(request, response);
                break;
            default:
                break;
        }
    }

    protected void guardardotacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo_elem = Integer.parseInt(request.getParameter("elemento"));
            ElementosJpaController cele = new ElementosJpaController();
            Elementos obj_e = cele.findElementos(codigo_elem);
            int codigo_sex = Integer.parseInt(request.getParameter("Sexos"));
            SexoJpaController csex = new SexoJpaController();
            Sexo obj_s = csex.findSexo(codigo_sex);
            int codigo_clim = Integer.parseInt(request.getParameter("Climas"));
            ClimaJpaController cclim = new ClimaJpaController();
            Clima obj_c = cclim.findClima(codigo_clim);
            int codigo_area = Integer.parseInt(request.getParameter("areas"));
            AreaJpaController carea = new AreaJpaController();
            Area obj_a = carea.findArea(codigo_area);
            int cantidad = Integer.parseInt(request.getParameter("cant"));
            Dotacion s = new Dotacion();
            DotacionJpaController cdot = new DotacionJpaController();
            s.setAreaIdarea(obj_a);
            s.setClimaIdclima(obj_c);
            s.setElementosIdelemento(obj_e);
            s.setSexoIdsexo(obj_s);
            s.setCantidad(cantidad);
            cdot.create(s);
            String msjurl = "Guardado_dotacion";
            response.sendRedirect("Views/Gestion_dotacion.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_dotacion.jsp?rta=" + msjurl);
        }
    }

    protected void editardotacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("iddotacion"));
            int codigo_elem = Integer.parseInt(request.getParameter("elementos"));
            ElementosJpaController cele = new ElementosJpaController();
            Elementos obj_e = cele.findElementos(codigo_elem);
            int codigo_sex = Integer.parseInt(request.getParameter("Sexo"));
            SexoJpaController csex = new SexoJpaController();
            Sexo obj_s = csex.findSexo(codigo_sex);
            int codigo_clim = Integer.parseInt(request.getParameter("Clima"));
            ClimaJpaController cclim = new ClimaJpaController();
            Clima obj_c = cclim.findClima(codigo_clim);
            int codigo_area = Integer.parseInt(request.getParameter("areass2"));
            AreaJpaController carea = new AreaJpaController();
            Area obj_a = carea.findArea(codigo_area);
            int cantidad = Integer.parseInt(request.getParameter("canti"));
            Dotacion s = new Dotacion();
            DotacionJpaController cdot = new DotacionJpaController();
            s.setIddotacion(id);
            s.setAreaIdarea(obj_a);
            s.setClimaIdclima(obj_c);
            s.setElementosIdelemento(obj_e);
            s.setSexoIdsexo(obj_s);
            s.setCantidad(cantidad);
            cdot.edit(s);
            String msjurl = "Editado_dotacion";
            response.sendRedirect("Views/Gestion_dotacion.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_dotacion.jsp?rta=" + msjurl);
        }
    }

    protected void Eliminardotacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("iddotacion"));
            DotacionJpaController cs = new DotacionJpaController();
            cs.destroy(codigo);
            String msjurl = "Eliminado_dotacion";
            response.sendRedirect("Views/Gestion_dotacion.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_dotacion.jsp?rta=" + msjurl);

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
