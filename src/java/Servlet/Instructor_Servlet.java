/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;
import Controllers.CentroJpaController;
import Controllers.CoordinadorJpaController;
import Controllers.InstructorJpaController;
import Logic.Centro;
import Logic.Coordinador;
import Logic.Instructor;
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
@WebServlet(name = "Instructor_Servlet", urlPatterns = {"/Instructor_Servlet"})
public class Instructor_Servlet extends HttpServlet {

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
                guardarInstructor(request, response);
                break;
            case "Editar":
                editarInstructor(request, response);
                break;
            case "Eliminar":
                EliminarInstructor(request, response);
                break;
            default:
                break;
        }
    }

    protected void guardarInstructor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idins"));
            String name = request.getParameter("nombre_ins");
            String lastaname = request.getParameter("lastname_instructor");
            String tel = request.getParameter("tel_instructor");
            String correo = request.getParameter("correo_ins");
            int codigo_centro = Integer.parseInt(request.getParameter("centros"));
            CentroJpaController cct = new CentroJpaController();
            Centro centro = cct.findCentro(codigo_centro);
            int codigo_coor = Integer.parseInt(request.getParameter("coor"));
            CoordinadorJpaController ccd = new CoordinadorJpaController();
            Coordinador coor = ccd.findCoordinador(codigo_coor);
            Instructor s = new Instructor();
            InstructorJpaController cins = new InstructorJpaController();
            s.setIdinstructor(codigo);
            s.setNombres(name);
            s.setApellidos(lastaname);
            s.setCorreo(correo);
            s.setTelefono(tel);
            s.setCentroIdcentro(centro);
            s.setCoordinadorIdcoordinador(coor);
            Instructor buscar = cins.findInstructor(codigo);
            if (buscar == null) {
                cins.create(s);
                String msjurl = "Guardado_instructor";
                response.sendRedirect("Views/Gestion_instructor.jsp?rta=" + msjurl);
            } else {
                String msjurl = "Encontrado";
                response.sendRedirect("Views/Gestion_instructor.jsp?rta=" + msjurl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_instructor.jsp?rta=" + msjurl);
        }
    }

    protected void editarInstructor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idinst"));
            String name = request.getParameter("nombre_inst");
            String lastaname = request.getParameter("lastname_inst");
            String tel = request.getParameter("tel_inst");
            String correo = request.getParameter("correo_inst");
            int codigo_centro = Integer.parseInt(request.getParameter("centross"));
            CentroJpaController cct = new CentroJpaController();
            Centro centro = cct.findCentro(codigo_centro);
            int codigo_coor = Integer.parseInt(request.getParameter("coord"));
            CoordinadorJpaController ccd = new CoordinadorJpaController();
            Coordinador coor = ccd.findCoordinador(codigo_coor);
            Instructor s = new Instructor();
            InstructorJpaController cins = new InstructorJpaController();
            s.setIdinstructor(codigo);
            s.setNombres(name);
            s.setApellidos(lastaname);
            s.setCorreo(correo);
            s.setTelefono(tel);
            s.setCentroIdcentro(centro);
            s.setCoordinadorIdcoordinador(coor);
            cins.edit(s);
            String msjurl = "editado_instructor";
            response.sendRedirect("Views/Gestion_instructor.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error";
            response.sendRedirect("Views/Gestion_instructor.jsp?rta=" + msjurl);
        }
    }

    protected void EliminarInstructor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idinst"));
            InstructorJpaController cs = new InstructorJpaController();
            cs.destroy(codigo);
            String msjurl = "eliminado_instructor";
            response.sendRedirect("Views/Gestion_instructor.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Referenciado";
            response.sendRedirect("Views/Gestion_instructor.jsp?rta=" + msjurl);

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
