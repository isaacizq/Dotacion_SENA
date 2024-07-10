/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controllers.CentroJpaController;
import Controllers.CoordinadorJpaController;
import Controllers.UsuariosJpaController;
import Logic.Centro;
import Logic.Coordinador;
import Logic.Usuarios;
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
@WebServlet(name = "Coordinador_Servlet", urlPatterns = {"/Coordinador_Servlet"})
public class Coordinador_Servlet extends HttpServlet {

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
                guardarCoordinador(request, response);
                break;
            case "Editar":
                editarCoordinador(request, response);
                break;
            case "Eliminar":
                Eliminarcoordinador(request, response);
                break;
            default:
                break;
        }
    }

    protected void guardarCoordinador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idcor"));
            String name = request.getParameter("nombre_cor");
            String lastaname = request.getParameter("lastname");
            String correo = request.getParameter("correo_cor");
            int codigo_centro = Integer.parseInt(request.getParameter("cent"));
            CentroJpaController cct = new CentroJpaController();
            Centro centro = cct.findCentro(codigo_centro);
            Coordinador s = new Coordinador();
            CoordinadorJpaController ccor = new CoordinadorJpaController();
            s.setIdcoordinador(codigo);
            s.setNombres(name);
            s.setApellidos(lastaname);
            s.setCorreo(correo);
            s.setCentroIdcentro(centro);
            Coordinador buscar = ccor.findCoordinador(codigo);
            if (buscar == null) {
                Usuarios u = new Usuarios();
                UsuariosJpaController cu = new UsuariosJpaController();
                u.setIdusuario(codigo);
                u.setNombreCompleto(name+" "+lastaname);
                u.setClave(u.EncryptarClave(String.valueOf(codigo)));
                u.setRol(2);
                u.setEstado(1);
                ccor.create(s);
                cu.create(u);
                String msjurl = "Guardado_coordinador";
                response.sendRedirect("Views/Gestion_coordinador.jsp?rta=" + msjurl);
            } else {
                String msjurl = "Encontrado";
                response.sendRedirect("Views/Gestion_coordinador.jsp?rta=" + msjurl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_coordinador.jsp?rta=" + msjurl);
        }
    }

    protected void editarCoordinador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idcoor"));
            String name = request.getParameter("nombre_coor");
            String lastaname = request.getParameter("lastname_");
            String correo = request.getParameter("correo_coor");
            int codigo_centro = Integer.parseInt(request.getParameter("center"));
            CentroJpaController cct = new CentroJpaController();
            Centro centro = cct.findCentro(codigo_centro);
            Coordinador s = new Coordinador();
            CoordinadorJpaController ccor = new CoordinadorJpaController();
            s.setIdcoordinador(codigo);
            s.setNombres(name);
            s.setApellidos(lastaname);
            s.setCorreo(correo);
            s.setCentroIdcentro(centro);
            ccor.edit(s);
            String msjurl = "Actualizado_coordinador";
            response.sendRedirect("Views/Gestion_coordinador.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_coordinador.jsp?rta=" + msjurl);
        }
    }

    protected void Eliminarcoordinador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idcoor"));
            CoordinadorJpaController cs = new CoordinadorJpaController();
            UsuariosJpaController cu = new UsuariosJpaController();
            cu.destroy(codigo);
            cs.destroy(codigo);
            String msjurl = "eliminado_coordinador";
            response.sendRedirect("Views/Gestion_coordinador.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Referenciada";
            response.sendRedirect("Views/Gestion_coordinador.jsp?rta=" + msjurl);

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
