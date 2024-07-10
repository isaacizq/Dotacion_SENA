<%@page import="Logic.Usuarios"%>
<%
    // Obtiene la sesión actual sin crear una nueva si no existe
    HttpSession cecion = request.getSession(false);
    Usuarios usu = (Usuarios) cecion.getAttribute("usuario");
%>
