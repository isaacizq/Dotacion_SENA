<%@page import="Logic.Usuarios"%>
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
    Connection coneccion;
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    coneccion = DriverManager.getConnection("jdbc:mysql://localhost/dotaciones_sena", "root", "5108");
    File reportFile = new File(application.getRealPath("Reports/Elementos.jasper"));
    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), null, coneccion);
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream ouputStream = response.getOutputStream();
    ouputStream.write(bytes, 0, bytes.length);
    ouputStream.flush();
    ouputStream.close();
%>   