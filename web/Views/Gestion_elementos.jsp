
<%@page import="Logic.Usuarios"%>
<%@page import="Logic.Elementos"%>
<%@page import="Controllers.ElementosJpaController"%>
<%@page import="Logic.Clima"%>
<%@page import="Controllers.ClimaJpaController"%>
<%@page import="Logic.Area"%>
<%@page import="Controllers.AreaJpaController"%>
<%@page import="Logic.Instructor"%>
<%@page import="Controllers.InstructorJpaController"%>
<%@page import="Logic.Coordinador"%>
<%@page import="Controllers.CoordinadorJpaController"%>
<%@page import="Logic.Centro"%>
<%@page import="Controllers.CentroJpaController"%>
<%@page import="Logic.Sexo"%>
<%@page import="Controllers.SexoJpaController"%>
<%@page import="Logic.Regional"%>
<%@page import="Controllers.RegionalJpaController"%>
<%@page import="Logic.Red"%>
<%@page import="java.util.List"%>
<%@page import="Controllers.RedJpaController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="wide wow-animation" lang="es">
    <head>
        <%
            response.setHeader("Cache-Control", "no-Cache,no-store,must-revalidate");
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setHeader("Expires", "0"); // ProxiesS
            // Obtiene la sesión actual sin crear una nueva si no existe
            HttpSession cecion = request.getSession(false);
            Usuarios usu = (Usuarios) cecion.getAttribute("usuario");
            if (cecion == null || usu == null || usu.getRol() == 3) {
                response.sendRedirect("../index.jsp");
                return; // Detiene la ejecución del código restante
            }
        %>
        <title>Gestión elementos</title>
        <meta name="format-detection" content="telephone=no">
        <meta name="viewport" content="width=device-width height=device-height initial-scale=1.0 maximum-scale=1.0 user-scalable=0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta charset="utf-8">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="icon" href="../images/Logo-de-SENA-png-verde-300x300-1.png" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Work+Sans:300,400,500,700,800%7CPoppins:300,400,700">
        <link rel="stylesheet" href="../css/fonts.css">
        <link rel="stylesheet" href="../css/bootstrap.css"/>
        <link rel="stylesheet" href="../css/style.css" id="main-styles-link">
    </head>
    <body>
        <div class="preloader">
            <div class="preloader-logo"><img src="../images/Logo-de-SENA-png-verde-300x300-1.png" alt="" width="151" height="44" srcset="../images/Logo-de-SENA-png-verde-300x300-1.png 2x"/>
            </div>
            <div class="preloader-body">
                <div id="loadingProgressG">
                    <div class="loadingProgressG" id="loadingProgressG_1"></div>
                </div>
            </div>
        </div>
        <div class="page">
            <!-- Page Header-->
            <header class="section novi-background page-header">
                <!-- RD Navbar-->
                <div class="rd-navbar-wrap">
                    <nav class="rd-navbar rd-navbar-corporate" data-layout="rd-navbar-fixed" data-sm-layout="rd-navbar-fixed" data-md-layout="rd-navbar-fixed" data-md-device-layout="rd-navbar-fixed" data-lg-layout="rd-navbar-static" data-lg-device-layout="rd-navbar-static" data-lg-stick-up="true" data-lg-stick-up-offset="118px" data-xl-layout="rd-navbar-static" data-xl-device-layout="rd-navbar-static" data-xl-stick-up="true" data-xl-stick-up-offset="118px" data-xxl-layout="rd-navbar-static" data-xxl-device-layout="rd-navbar-static" data-xxl-stick-up-offset="118px" data-xxl-stick-up="true">
                        <div class="rd-navbar-aside-outer">
                            <div class="rd-navbar-aside">
                                <!-- RD Navbar Panel-->
                                <div class="rd-navbar-panel">
                                    <!-- RD Navbar Toggle-->
                                    <button class="rd-navbar-toggle" data-rd-navbar-toggle="#rd-navbar-nav-wrap-1"><span></span></button>
                                    <!-- RD Navbar Brand--><a class="rd-navbar-brand" href="../Views/Menu.jsp"><img src="../images/Logo-de-SENA-png-verde-300x300-1.png" alt="" width="151" height="44" srcset="../images/Logo-de-SENA-png-verde-300x300-1.png 2x"/></a>
                                    <a href="Index.jsp"><p class="fs-4 fw-bolder text-center" style="color: green">DotaSoft</p></a>
                                </div>
                                <div class="rd-navbar-collapse">
                                    <button class="rd-navbar-collapse-toggle rd-navbar-fixed-element-1" data-rd-navbar-toggle="#rd-navbar-collapse-content-1"><span></span></button>
                                    <div class="rd-navbar-collapse-content" id="rd-navbar-collapse-content-1">
                                        <article class="unit align-items-center">
                                            <div class="unit-left"><span class="icon novi-icon icon-md icon-modern mdi mdi-phone"></span></div>
                                            <div class="unit-body">
                                                <ul class="list-0">
                                                    <li><a class="link-default">(+605) 280-40-15</a></li>
                                                </ul>
                                            </div>
                                        </article>
                                        <article class="unit align-items-center">
                                            <div class="unit-left"><span class="icon novi-icon icon-md icon-modern mdi mdi-map-marker"></span></div>
                                            <div class="unit-body"><a class="link-default"> Troncal de Occidente km 5 via Sincelejo - Sampues<br>Calle 25B No. 31 - 260, Sincelejo</a></div>
                                        </article>
                                        <a class="fs-4 text-black">Ropa de trabajo y dotación SENA Sucre</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="rd-navbar-main-outer">
                            <div class="rd-navbar-main">
                                <div class="rd-navbar-nav-wrap" id="rd-navbar-nav-wrap-1">
                                    <!-- RD Navbar Nav-->
                                    <ul class="rd-navbar-nav">
                                        <li class="rd-nav-item active"><a class="rd-nav-link" href="Menu.jsp" style="color: green">Inicio</a>
                                        </li>
                                        <li class="nav-item dropdown rd-nav-item "><a class="fs-6 nav-link dropdown-toggle rd-nav-link" href="#" role="button" data-bs-toggle="dropdown" style="color: green">Registros</a>
                                            <ul class="dropdown-menu">
                                                <li><button type="button" class="fs-5 dropdown-item" data-bs-toggle="modal" data-bs-target="#myModal">Sexo</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item" data-bs-toggle="modal" data-bs-target="#myModal2">Clima</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item" data-bs-toggle="modal" data-bs-target="#myModal3">Red</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item" data-bs-toggle="modal" data-bs-target="#myModal4">Area</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item" data-bs-toggle="modal" data-bs-target="#myModal5">Regional</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item" data-bs-toggle="modal" data-bs-target="#myModal6">Centro</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item" data-bs-toggle="modal" data-bs-target="#myModal7">Elementos</button></li>
                                            </ul>
                                        </li>
                                        <li class="nav-item dropdown rd-nav-item "><a class="fs-6 nav-link dropdown-toggle rd-nav-link" href="#" role="button" data-bs-toggle="dropdown" style="color: green">Gestión</a>
                                            <ul class="dropdown-menu">                
                                                <li><a class="fs-5 dropdown-item" href="Gestion_caracterizar.jsp" target="_blank">Caracterización</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><a class="fs-5 dropdown-item" href="Gestion_coordinador.jsp" target="_blank">Coordinadores</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><a class="fs-5 dropdown-item" href="Gestion_clima_sexo.jsp" target="_blank">Climas y sexos</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><a class="fs-5 dropdown-item" href="Gestion_dotacion.jsp" target="_blank">Dotaciones</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><a class="fs-5 dropdown-item" href="Gestion_elementos.jsp" target="_blank">Elementos</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><a class="fs-5 dropdown-item" href="Gestion_instructor.jsp" target="_blank">Instructores</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><a class="fs-5 dropdown-item" href="Gestion_area_red.jsp" target="_blank">Redes y areas</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><a class="fs-5 dropdown-item" href="Gestion_regional_centro.jsp" target="_blank">Regionales y centros</a></li>
                                            </ul>
                                        </li>
                                        <li class="nav-item dropdown rd-nav-item "><a class="fs-6 nav-link dropdown-toggle rd-nav-link" href="#" role="button" data-bs-toggle="dropdown"style="color: green">Reportes</a>
                                            <ul class="dropdown-menu">
                                                <li><a class="fs-5 dropdown-item text-center" href="Consolidado_Instructores.jsp" target="_blank">Consolidado Instructores</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item text-center" data-bs-toggle="modal" data-bs-target="#myModal9">X Área</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item text-center" data-bs-toggle="modal" data-bs-target="#myModal12">X Clima</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item text-center" data-bs-toggle="modal" data-bs-target="#myModal8">X Instructor</button></li>
                                            </ul>
                                        </li>
                                        <li class="nav-item dropdown rd-nav-item">
                                            <input class="form-control me-2" type="search" id="search" placeholder="Busqueda de elementos" oninput="this.value = this.value.toUpperCase()" aria-label="Search">
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="mt-3 mx-2 dropdown position-absolute top-0 end-0">
                                <button class="btn btn-success dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Usuario
                                </button>
                                <ul class="dropdown-menu">
                                    <li>
                                        <div class="row">
                                            <div class="col-6">
                                                <a class="dropdown-item" href="#"><img
                                                        src="../images/user_1177568.png" alt=""></a>
                                            </div>
                                            <div class="col-6">
                                                <a class="dropdown-item" href="Gestion_usuarios.jsp"><img
                                                        src="../images/gear_14549456.png" alt="" style="vertical-align: middle; float: right;"></a>
                                            </div>
                                        </div>
                                    </li>
                                    <li><a class="dropdown-item" href=""><%= usu.getNombreCompleto()%></a></li>
                                    <li><form action="Gestion_elementos.jsp"><input class="dropdown-item" name="link" type="submit" value="Salir">
                                            <%
                                                if (request.getParameter("link") != null) {  //Cierra sesion al eliminar los atributos de la seseion
                                                    cecion.removeAttribute("usuario");
                                                }
                                                if (cecion.getAttribute("usuario") == null) {//valida si la sesion no tiene atributos y redirecciona al login
                                                    response.sendRedirect("../index.jsp");
                                                }
                                            %>
                                        </form>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>
            </header>
            <div class="row">
                <div class="col-lg-7 col-md-12 col-sm-12">
                    <div class="mt-5 table-responsive table-wrapper-scroll-y my-custom-scrollbar" style="overflow-y: scroll; max-height: 600px;">
                        <div class="container-fluid">
                            <table class="table table-striped-columns" id="tab">
                                <p class="mb-3 fs-3 fw-bolder text-center" style="color: green">Elementos registrados</p>
                                <thead>
                                    <tr>
                                        <th scope="col" class="fw-bolder fs-5" style="text-align: center;">Id elemento</th>
                                        <th scope="col" class="fw-bolder fs-5" style="text-align: center;">Nombre</th>
                                        <th scope="col" class="fw-bolder fs-5" style="text-align: center;">Cantidades</th>
                                        <th scope="col" class="fw-bolder fs-5" style="text-align: center;">Configurar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ElementosJpaController cred = new ElementosJpaController();
                                        List<Elementos> lista = cred.findElementosEntities();
                                        for (Elementos c : lista) {
                                    %>
                                    <tr>
                                        <td style="text-align: center;"><%= c.getIdelemento()%></td>
                                        <td style="text-align: center;"><%= c.getNombre()%></td>
                                        <td style="text-align: center;"><%= c.getCantidades()%></td>
                                        <td style="text-align: center;"><button type="submit" onclick="cargarDatosModal2('<%= c.getIdelemento()%>', '<%= c.getNombre()%>')"><img src="../images/process_11907491.png" alt="alt"/></button></td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 col-md-12 col-sm-12">
                    <div class="mt-5 container">
                        <img src="../images/ropa.jpg" alt="alt" width="100%" height="100%"/>
                    </div>
                </div>
            </div>
            <jsp:include page="../Componentes/footer.jsp"></jsp:include>
            </div>
        <jsp:include page="../Componentes/modales.jsp"></jsp:include>
            <!-- Global Mailform Output-->
            <div class="snackbars" id="form-output-global"></div>
            <!-- Javascript-->
            <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
            <script src="../js/core.min.js"></script>
            <script src="../js/script.js"></script>
            <script src="../js/alertas.js"></script>
            <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
            <script>AOS.init();</script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script>
                                            //Elementos
                                            const filtroInput = document.getElementById("search");
                                            const filas = document.getElementById("tab").querySelectorAll("table tbody tr");
                                            filtroInput.addEventListener("input", function () {
                                                const filtro = filtroInput.value.trim().toLowerCase();
                                                filas.forEach(function (fila) {
                                                    const textoFila = fila.textContent.toLowerCase();
                                                    if (textoFila.includes(filtro)) {
                                                        fila.style.display = "";
                                                    } else {
                                                        fila.style.display = "none";
                                                    }
                                                });
                                            });
            </script>
            <div class="modal fade" tabindex="-1" id="myModal7">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Elementos</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="<%=request.getContextPath()%>/Elementos_Servlet" name="" method="post">
                            <div class="mb-3">
                                <input type="number" required max="99999999" name="idelemen"  class="form-control" id="idelemen" placeholder="Id elemento">
                            </div>
                            <div class="mb-3">
                                <input type="text" required maxlength="100" name="nombre_elemen" oninput="this.value = this.value.toUpperCase()" class="form-control"id="nombre_elemen" placeholder="Nombre">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">cerrar</button>
                                <input type="submit"  name="action" value="Guardar" class="btn btn-primary">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" tabindex="-1" id="myModal10">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Elementos</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="<%=request.getContextPath()%>/Elementos_Servlet" method="post">
                            <div class="mb-3">
                                <input type="number" readonly  name="idelem"min="1" id="idelem" required max="99999999" class="form-control" placeholder="Id elementos">
                            </div>
                            <div class="mb-3">
                                <input type="text" name="nom_elemen" id="nom_elemen" oninput="this.value = this.value.toUpperCase()" required maxlength="100" class="form-control" placeholder="Nombre">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">cerrar</button>
                                <input type="submit" name="action" value="Editar" class="btn btn-primary">
                                <input type="submit" name="action" value="Eliminar" class="btn btn-danger">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            // Función para capturar y enviar los datos al modal
            function cargarDatosModal2(idelem, nombre) {
                // Asigna los valores a los campos del formulario dentro del modal
                document.getElementById("idelem").value = idelem;
                document.getElementById("nom_elemen").value = nombre;
                // Abre el modal
                var myModal = new bootstrap.Modal(document.getElementById('myModal10'));
                myModal.show();
            }
        </script>
        <%
            String rta = request.getParameter("rta");
            if (rta != null) {
                switch (rta) {
                    case "Guardado_elemento":
        %>
        <script>
            mostrarExito("Guardado éxitoso");
        </script>
        <%
                break;
            case "Actualizado_elemento":
        %>
        <script>
            mostrarExito("Actualizado éxitoso");
        </script> <%
                break;
            case "eliminado_elemento":
        %>
        <script>
            mostrarExito("Eliminado éxitoso");
        </script> <%
                break;
            case "Encontrado":
        %>
        <script>
            mostrarError("Oops...elemento existente!");
        </script> <%
                break;
            case "Error":
        %>
        <script>
            mostrarError("Oops...ocurrió un error!");
        </script> <%
                break;
            case "Referenciado":
        %>
        <script>
            mostrarError("Oops...refenciado en base de datos!");
        </script> <%
                        break;
                    default:
                        break;
                }
            }%>
    </body>
</html>