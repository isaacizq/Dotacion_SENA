/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controllers.UsuariosJpaController;
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
@WebServlet(name = "Usuario_Servlet", urlPatterns = {"/Usuario_Servlet"})
public class Usuario_Servlet extends HttpServlet {

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
            case "Confirmar":
                Changepassword(request, response);
                break;
            default:
                break;
        }
    }

    protected void guardardotacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo_usuario = Integer.parseInt(request.getParameter("idusu"));
            String nombrecompleto = request.getParameter("nombre_usu");
            String clave = request.getParameter("clave_usu");
            int rol = Integer.parseInt(request.getParameter("rol_usu"));
            int estado = Integer.parseInt(request.getParameter("estado_usu"));
            Usuarios s = new Usuarios();
            UsuariosJpaController cdot = new UsuariosJpaController();
            s.setIdusuario(codigo_usuario);
            s.setNombreCompleto(nombrecompleto);
            String Password = s.EncryptarClave(clave);
            s.setClave(Password);
            s.setRol(rol);
            s.setEstado(estado);
            cdot.create(s);
            String msjurl = "Guardado_usuario";
            response.sendRedirect("Views/Gestion_usuarios.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_usuarios.jsp?rta=" + msjurl);
        }
    }

    protected void editardotacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo_usuario = Integer.parseInt(request.getParameter("idusua"));
            String nombrecompleto = request.getParameter("nombre_usua");
            String clave = request.getParameter("clave_usua");
            int rol = Integer.parseInt(request.getParameter("rol_usua"));
            int estado = Integer.parseInt(request.getParameter("estado_usua"));
            Usuarios s = new Usuarios();
            UsuariosJpaController cdot = new UsuariosJpaController();
            s.setIdusuario(codigo_usuario);
            s.setNombreCompleto(nombrecompleto);
            s.setClave(clave);
            s.setRol(rol);
            s.setEstado(estado);
            cdot.edit(s);
            String msjurl = "editado_usuario";
            response.sendRedirect("Views/Gestion_usuarios.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_usuarios.jsp?rta=" + msjurl);
        }
    }

    protected void Eliminardotacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idusua"));
            UsuariosJpaController cs = new UsuariosJpaController();
            cs.destroy(codigo);
            String msjurl = "Eliminado_usuario";
            response.sendRedirect("Views/Gestion_usuarios.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Referenciado";
            response.sendRedirect("Views/Gestion_usuarios.jsp?rta=" + msjurl);

        }

    }

    protected void Changepassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("Id"));
            String password = request.getParameter("clave");
            UsuariosJpaController cs = new UsuariosJpaController();
            Usuarios buscar = cs.findUsuarios(codigo);
            if (buscar != null) {
                String new_clave = buscar.EncryptarClave(password);
                buscar.setClave(new_clave);
                cs.edit(buscar);
                String msjurl = "Change_password";
                response.sendRedirect("index.jsp?rta=" + msjurl);
            }

        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("index.jsp?rta=" + msjurl);

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
