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
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "GetDotacion", urlPatterns = {"/GetDotacion"})
public class GetDotacion extends HttpServlet {

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
        cargarTabla(request, response);

    }

    protected void cargarTabla(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        // Lógica para consultar los datos de las caracterizaciones
        DotacionJpaController CC = new DotacionJpaController();
        List<Dotacion> Dotacion = CC.findDotacionEntities();

        // Crear una lista para almacenar objetos JSON personalizados de estudiantes
        List<JsonObject> DotacionJson = new ArrayList<>();

        // Lógica para consultar los datos de formaciones y sedes
        AreaJpaController areaController = new AreaJpaController();
        SexoJpaController sexoController = new SexoJpaController();
        ClimaJpaController climaControlador = new ClimaJpaController();
        ElementosJpaController elemencontolador = new ElementosJpaController();

        // Iterar sobre la lista de estudiantes y crear objetos JSON personalizados
        for (Dotacion dot : Dotacion) {
            // Crear un nuevo objeto JSON personalizado con los datos del estudiante
            JsonObject estudianteJson = new JsonObject();
            estudianteJson.addProperty("id", dot.getIddotacion());
            estudianteJson.addProperty("cantidad", dot.getCantidad());

            // Para el tipo de sexo
            Sexo sex = sexoController.findSexo(dot.getSexoIdsexo().getIdsexo());
            if (sex != null) {
                estudianteJson.addProperty("idsexo", sex.getIdsexo());
                estudianteJson.addProperty("nombre_sexo", sex.getNombre());
            }
            Area ar = areaController.findArea(dot.getAreaIdarea().getIdarea());
            if (ar != null) {
                estudianteJson.addProperty("idred", ar.getRedIdred().getIdred());
                estudianteJson.addProperty("nombre_red", ar.getRedIdred().getNombre());
            }

            Area art = areaController.findArea(dot.getAreaIdarea().getIdarea());
            if (art != null) {
                estudianteJson.addProperty("idarea", art.getIdarea());
                estudianteJson.addProperty("nombre_area", art.getNombre());
            }

            // Obtener el objeto de clima del estudiante y agregar sus datos al JSON
            Clima clim = climaControlador.findClima(dot.getClimaIdclima().getIdclima());
            if (clim != null) {
                estudianteJson.addProperty("idclima", clim.getIdclima());
                estudianteJson.addProperty("nombre_clima", clim.getNombre());
            }

            Elementos elem = elemencontolador.findElementos(dot.getElementosIdelemento().getIdelemento());
            if (elem != null) {
                estudianteJson.addProperty("idelemento", elem.getIdelemento());
                estudianteJson.addProperty("nombre_elemento", elem.getNombre());
            }

            // Agregar el objeto JSON personalizado a la lista
            DotacionJson.add(estudianteJson);
        }

        // Convertir la lista de objetos JSON personalizados a una cadena JSON
        String json = new Gson().toJson(DotacionJson);

        // Enviar la respuesta JSON al cliente
        response.getWriter().write(json);
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
