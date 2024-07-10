/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controllers.AreaJpaController;
import Controllers.CaracterizarInstructorJpaController;
import Controllers.ClimaJpaController;
import Controllers.DescuentoJpaController;
import Controllers.ElementosJpaController;
import Controllers.InstructorJpaController;
import Controllers.SexoJpaController;
import Logic.Area;
import Logic.CaracterizarInstructor;
import Logic.Clima;
import Logic.Descuento;
import Logic.Elementos;
import Logic.Instructor;
import Logic.Sexo;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isaac
 */
@WebServlet(name = "Caracterizar_Servlet", urlPatterns = {"/Caracterizar_Servlet"})
public class Caracterizar_Servlet extends HttpServlet {

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
        if (button != null) {
            switch (button) {
                case "Guardar":
                    guardarcaracterizar(request, response);
                    break;
                case "Editar":
                    editarcaracterizar(request, response);
                    break;
                case "Eliminar":
                    eliminarcaracterizar(request, response);
                    break;
                default:
                    break;
            }
        }
    }

    protected void guardarcaracterizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            CaracterizarInstructorJpaController cdot = new CaracterizarInstructorJpaController();
            int codigo_inst = Integer.parseInt(request.getParameter("instruc"));
            List<CaracterizarInstructor> bus = cdot.findCaracterizarInstructorEntities();
            int existente = 0;
            for (CaracterizarInstructor bu : bus) {
                if (bu.getInstructorIdinstructor().getIdinstructor() == codigo_inst) {
                    existente = 1;
                    break;
                }
            }
            InstructorJpaController cins = new InstructorJpaController();
            Instructor obj_i = cins.findInstructor(codigo_inst);
            int codigo_sex = Integer.parseInt(request.getParameter("Sex"));
            SexoJpaController csex = new SexoJpaController();
            Sexo obj_s = csex.findSexo(codigo_sex);
            int codigo_clim = Integer.parseInt(request.getParameter("Clim"));
            ClimaJpaController cclim = new ClimaJpaController();
            Clima obj_c = cclim.findClima(codigo_clim);
            int codigo_area = Integer.parseInt(request.getParameter("areass"));
            AreaJpaController carea = new AreaJpaController();
            Area obj_a = carea.findArea(codigo_area);
            String descripcion = request.getParameter("descrip");
            LocalDate fechactual = LocalDate.now();
            int year = fechactual.getYear();
            CaracterizarInstructor s = new CaracterizarInstructor();
            s.setAreaIdarea(obj_a);
            s.setClimaIdclima(obj_c);
            s.setInstructorIdinstructor(obj_i);
            s.setSexoIdsexo(obj_s);
            s.setAno(year);
            s.setDescripcion(descripcion);
            if (existente == 1) {
                String msjurl = "Existente";
                response.sendRedirect("Views/Gestion_caracterizar.jsp?rta=" + msjurl);
            } else {
                cdot.create(s);
                String msjurl = "Guardado";
                response.sendRedirect("Views/Gestion_caracterizar.jsp?rta=" + msjurl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error!";
            response.sendRedirect("Views/Gestion_caracterizar.jsp?rta=" + msjurl);
        }
    }

    protected void editarcaracterizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id_caracterizar = Integer.parseInt(request.getParameter("idcaracterizar"));
            int codigo_inst = Integer.parseInt(request.getParameter("instructor"));
            InstructorJpaController cins = new InstructorJpaController();
            Instructor obj_i = cins.findInstructor(codigo_inst);
            int codigo_sex = Integer.parseInt(request.getParameter("Se"));
            SexoJpaController csex = new SexoJpaController();
            Sexo obj_s = csex.findSexo(codigo_sex);
            int codigo_clim = Integer.parseInt(request.getParameter("Cl"));
            ClimaJpaController cclim = new ClimaJpaController();
            Clima obj_c = cclim.findClima(codigo_clim);
            int codigo_area = Integer.parseInt(request.getParameter("areass2"));
            AreaJpaController carea = new AreaJpaController();
            Area obj_a = carea.findArea(codigo_area);
            String descripcion = request.getParameter("descripc");
            int year = Integer.parseInt(request.getParameter("ano2"));
            CaracterizarInstructor s = new CaracterizarInstructor();
            CaracterizarInstructorJpaController cdot = new CaracterizarInstructorJpaController();
            s.setIdCaracterizarInstructor(id_caracterizar);
            s.setAreaIdarea(obj_a);
            s.setClimaIdclima(obj_c);
            s.setInstructorIdinstructor(obj_i);
            s.setSexoIdsexo(obj_s);
            s.setAno(year);
            s.setDescripcion(descripcion);
            cdot.edit(s);
            String msjurl = "Actualizado";
            response.sendRedirect("Views/Gestion_caracterizar.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error!";
            response.sendRedirect("Views/Gestion_caracterizar.jsp?rta=" + msjurl);
        }
    }

    protected void eliminarcaracterizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("idcaracterizar"));
            CaracterizarInstructorJpaController cs = new CaracterizarInstructorJpaController();
            CaracterizarInstructor buscar = cs.findCaracterizarInstructor(codigo);
            DescuentoJpaController cd = new DescuentoJpaController();
            ElementosJpaController elementosController = new ElementosJpaController();
            List<Descuento> lista = cd.findDescuentoEntities();
            for (Descuento elem : lista) {
                if (elem.getInstructoridInstructor().getIdinstructor() == buscar.getInstructorIdinstructor().getIdinstructor()) {
                    Elementos elemento = elementosController.findElementos(elem.getElementosIdelemento().getIdelemento());
                    elemento.setCantidades(elemento.getCantidades() - elem.getCantidad());
                    elementosController.edit(elemento);
                    cd.destroy(elem.getIddescuento());
                }
            }
            cs.destroy(codigo);
            String msjurl = "Eliminado";
            response.sendRedirect("Views/Gestion_caracterizar.jsp?rta=" + msjurl);
        } catch (Exception e) {
            e.printStackTrace();
            String msjurl = "Error!";
            response.sendRedirect("Views/Gestion_caracterizar.jsp?rta=" + msjurl);
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
