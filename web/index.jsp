<%-- 
    Document   : index
    Created on : 4/05/2024, 10:11:14 AM
    Author     : isaac
--%>
<%@page import="Logic.Usuarios"%>
<%@page import="Controllers.InstructorJpaController"%>
<%@page import="Controllers.CoordinadorJpaController"%>
<%@page import="Controllers.UsuariosJpaController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setHeader("Expires", "0"); // Proxies
        %>
        <title>Login SENA-Sucre</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style2.css">
        <link rel="icon" href="images/Logo-de-SENA-png-verde-300x300-1.png" type="image/x-icon">
    </head>
    <body class="img js-fullheight" style="background-image: url(images/bg.jpg);">
        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">Ropa de trabajo</h2>
                        <h2 class="heading-section">SENA - Sucre</h2>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-4">
                        <div class="login-wrap p-0">
                            <form action="index.jsp" class="signin-form">
                                <div class="form-group">
                                    <input type="number" name="user" class="form-control" placeholder="Cédula" required>
                                </div>
                                <div class="form-group">
                                    <input id="password-field" type="password" name="password" class="form-control" placeholder="Contraseña" required>
                                    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </div>
                                <div class="form-group">
                                    <button type="submit" name="button" class="form-control btn btn-primary submit px-3">Iniciar sesión</button>
                                </div>
                                <div class="form-group d-md-flex">
                                    <div class="mx-5">
                                        <a href="#" style="color:#fff" data-toggle="modal" data-target="#">Olvidé Id usuario / Contraseña?</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="../js/core.min.js"></script>
        <script src="../js/script.js"></script>
        <script src="../js/alertas.js"></script>
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
        <script>AOS.init();</script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- Modal para cambio de contraseña -->
        <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cambio de contraseña</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="<%=request.getContextPath()%>/Usuario_Servlet" method="post" class="signin-form">
                            <div class="mb-3">
                                <label class="fs-6 dropdown">Confirme su Cédula</label>
                                <input type="text" required name="Id" class="form-control bg-secondary" id="Id" placeholder="Id usuario">
                            </div>
                            <div class="mb-3 form-group">
                                <label class="fs-6 dropdown">Nueva contraseña</label>
                                <div class="form-group">
                                    <input id="password-field2" type="password" name="clave" class="form-control bg-secondary" placeholder="Contraseña" required>
                                    <span toggle="#clave-field" class="fa fa-fw fa-eye field-icon toggle-clave"></span>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" name="action" value="Confirmar" class="btn btn-primary">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
                document.addEventListener("DOMContentLoaded", function () {
                    const togglePassword = document.querySelector('#toggle-password');
                    const passwordField = document.querySelector('#password-field2');

                    togglePassword.addEventListener('click', function () {
                        // Alternar el atributo "type" entre "password" y "text"
                        const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
                        passwordField.setAttribute('type', type);

                        // Alternar el icono entre el ojo y el ojo tachado
                        this.classList.toggle('fa-eye');
                        this.classList.toggle('fa-eye-slash');
                    });
                });
        </script>
        <%
            String rta = request.getParameter("rta");
            if (rta != null) {
                switch (rta) {
                    case "Change_password":
        %>
        <script>
            Swal.fire({
                title: "Buen trabajo!",
                text: "Contraseña actualizada éxitosamente!",
                icon: "success"
            });
        </script>
        <%
                break;
            case "Error":
        %>
        <script>
            Swal.fire({
                title: "Oops...",
                text: "Cédula o nombre de usuarios incorrectos!",
                icon: "error"
            });
        </script>
        <%
                        break;
                }
            }
            HttpSession sesion = request.getSession(false);
            UsuariosJpaController cu = new UsuariosJpaController();
            String boton = request.getParameter("button");
            if (boton != null) {
                int user = Integer.parseInt(request.getParameter("user"));
                Usuarios buscar = cu.findUsuarios(user);
                if (buscar != null) {
                    sesion.setAttribute("usuario", buscar);
                    if (buscar.getEstado() == 1) {
                        Usuarios obj = new Usuarios();
                        String clave = request.getParameter("password");
                        if (obj.DencryptarClave(buscar.getClave(), clave)) {
                            String user2 = request.getParameter("user");
                            if (user2.equals(clave)) {%>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var myModal = new bootstrap.Modal(document.getElementById('myModal'));
                myModal.show();
            });
        </script>
        <%} else {
                if (buscar.getRol() == 3) {
                    response.sendRedirect("Views/Menu_r_humanos.jsp");
                } else {
                    response.sendRedirect("Views/Menu.jsp");
                }
            }
        } else {
        %>
        <script>
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Clave incorrecta'
            });
        </script>
        <%
            }
        } else {
        %>
        <script>
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Usuario inactivo'
            });
        </script>
        <%
            }
        } else {
        %>
        <script>
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Id Usuario no existente'
            });
        </script>
        <%
                }

            }

        %>
    </body>
</html>
