<%@page import="Logic.Usuarios"%>
<%@page import="Logic.Instructor"%>
<%@page import="Controllers.InstructorJpaController"%>
<%@page import="Logic.Descuento"%>
<%@page import="java.util.List"%>
<%@page import="Controllers.DescuentoJpaController"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="Logic.Elementos"%>
<%@page import="Controllers.ElementosJpaController"%>
<%
    response.setHeader("Cache-Control", "no-Cache,no-store,must-revalidate");
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // ProxiesS
    // Obtiene la sesión actual sin crear una nueva si no existe
    HttpSession cecion = request.getSession(false);
    Usuarios usu = (Usuarios) cecion.getAttribute("usuario");
    if (cecion == null || usu == null) {
        response.sendRedirect("../index.jsp");
        return; // Detiene la ejecución del código restante
    }
    String instructorId = request.getParameter("instructor");
    int instructorIdInt = Integer.parseInt(instructorId);
    String dotacionArrayStr = request.getParameter("dotacionArray");
    JSONArray dotacionArray = new JSONArray(dotacionArrayStr);
    DescuentoJpaController cd = new DescuentoJpaController();
    Descuento des = new Descuento();
    InstructorJpaController n = new InstructorJpaController();
    Instructor obj_i = n.findInstructor(instructorIdInt);
    ElementosJpaController elementosController = new ElementosJpaController();
    List<Descuento> lista = cd.findDescuentoEntities();
    for (Descuento elem : lista) {
        if (elem.getInstructoridInstructor().getIdinstructor() == instructorIdInt) {
            Elementos elemento = elementosController.findElementos(elem.getElementosIdelemento().getIdelemento());
            elemento.setCantidades(elemento.getCantidades() - elem.getCantidad());
            elementosController.edit(elemento);
            cd.destroy(elem.getIddescuento());
        }
    }
    for (int i = 0; i < dotacionArray.length(); i++) {
        JSONObject dotacionItem = dotacionArray.getJSONObject(i);
        int idelemento = dotacionItem.getInt("idelemento");
        int cantidad = dotacionItem.getInt("cantidad");
        // Aquí puedes procesar y guardar los datos en la base de datos
        // Por ejemplo, podrías actualizar la cantidad en la tabla Elementos
        Elementos elemento = elementosController.findElementos(idelemento);
        elemento.setCantidades(elemento.getCantidades() + cantidad);
        elementosController.edit(elemento);
        des.setInstructoridInstructor(obj_i);
        des.setElementosIdelemento(elemento);
        des.setCantidad(cantidad);
        cd.create(des);
    }
    // Envía una respuesta al cliente si es necesario
    out.println("Datos guardados exitosamente");
%>
