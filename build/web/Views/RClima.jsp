<%@page import="Logic.Usuarios"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
    response.setHeader("Cache-Control", "no-Cache,no-store,must-revalidate");
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // ProxiesS
    // Obtiene la sesi�n actual sin crear una nueva si no existe
    HttpSession cecion = request.getSession(false);
    Usuarios usu = (Usuarios) cecion.getAttribute("usuario");
    if (cecion == null || usu == null) {
        response.sendRedirect("../index.jsp");
        return; // Detiene la ejecuci�n del c�digo restante
    }
    int Rclima = Integer.parseInt(request.getParameter("Rclima")); // Recuperar el par�metro enviado desde el primer JSP
    // Configuraci�n de la conexi�n a la base de datos
    Connection coneccion;
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    coneccion = DriverManager.getConnection("jdbc:mysql://localhost/dotaciones_sena", "root", "5108");

    // Par�metros a pasar al informe Jasper
    Map<String, Object> parametros = new HashMap<>();
    parametros.put("num", Rclima); // Pasar el par�metro num al informe Jasper

    // Ruta al archivo Jasper
    File reportFile = new File(application.getRealPath("Reports/XClima.jasper"));

    // Generar el informe Jasper con los par�metros
    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parametros, coneccion);

    // Configurar la respuesta para enviar el PDF generado
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream outputStream = response.getOutputStream();
    outputStream.write(bytes, 0, bytes.length);
    outputStream.flush();
    outputStream.close();
%>
