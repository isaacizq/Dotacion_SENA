<%-- 
    Document   : Index
    Created on : 14/03/2024, 02:33:47 PM
    Author     : isaac
--%>
<%@page import="Controllers.AreaJpaController"%>
<%@page import="Logic.Area"%>
<%@page import="Logic.Instructor"%>
<%@page import="Controllers.InstructorJpaController"%>
<%@page import="Logic.Clima"%>
<%@page import="Controllers.ClimaJpaController"%>
<%@page import="Logic.Usuarios"%>
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
            response.setHeader("Expires", "0"); // Proxies
            // Obtiene la sesión actual sin crear una nueva si no existe
            HttpSession cecion = request.getSession(false);
            Usuarios usu = (Usuarios) cecion.getAttribute("usuario");
            if (cecion == null || usu == null || usu.getRol() == 3) {
                response.sendRedirect("../index.jsp");
                return; // Detiene la ejecución del código restante
            }
        %>
        <title>Dotasoft</title>
        <meta name="format-detection" content="telephone=no">
        <meta name="viewport" content="width=device-width height=device-height initial-scale=1.0 maximum-scale=1.0 user-scalable=0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta charset="utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="icon" href="../images/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Work+Sans:300,400,500,700,800%7CPoppins:300,400,700">
        <link rel="stylesheet" href="../css/fonts.css">
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
                                    <!-- RD Navbar Brand--><a class="rd-navbar-brand" href="../Views/Menu.jsp"><img src="../images/Logo-de-SENA-png-verde-300x300-1.png" alt="" width="151" height="44" srcset="images/Logo-de-SENA-png-verde-300x300-1.png 2x"/></a>
                                    <p class="fs-4 fw-bolder text-center" style="color: green">DotaSoft</p>
                                </div>
                                <div class="rd-navbar-collapse">
                                    <button class="rd-navbar-collapse-toggle rd-navbar-fixed-element-1" data-rd-navbar-toggle="#rd-navbar-collapse-content-1"><span></span></button>
                                    <div class="rd-navbar-collapse-content" id="rd-navbar-collapse-content-1">
                                        <article class="unit align-items-center">
                                            <div class="unit-left"><span class="icon novi-icon icon-md icon-modern mdi mdi-phone"></span></div>
                                            <div class="unit-body">
                                                <ul class="list-0">
                                                    <li><a class="link-default" href="#">(+605) 280-40-15</a></li>
                                                </ul>
                                            </div>
                                        </article>
                                        <article class="unit align-items-center">
                                            <div class="unit-left"><span class="icon novi-icon icon-md icon-modern mdi mdi-map-marker"></span></div>
                                            <div class="unit-body"><a class="link-default" href="#"> Troncal de Occidente km 5 via Sincelejo - Sampues<br>Calle 25B No. 31 - 260, Sincelejo</a></div>
                                        </article>
                                        <a class="fs-4 text-black" href="#">Ropa de trabajo y dotación SENA Sucre</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="rd-navbar-main-outer">
                            <div class="rd-navbar-main">
                                <div class="rd-navbar-nav-wrap" id="rd-navbar-nav-wrap-1">
                                    <!-- RD Navbar Nav-->
                                    <ul class="rd-navbar-nav">
                                        <li class="rd-nav-item active"><a class="fs-6 rd-nav-link" href="../Views/Menu.jsp" style="color: green">Inicio</a>
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
                                        <li class="nav-item dropdown rd-nav-item "><a class="fs-6 nav-link dropdown-toggle rd-nav-link" href="#" role="button" data-bs-toggle="dropdown" style="color: green">Reportes</a>
                                            <ul class="dropdown-menu">
                                                <li><button class="fs-5 dropdown-item text-center" onclick="window.open('Consolidado_Instructores.jsp','_blank')">Consolidado Instructores</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item text-center" data-bs-toggle="modal" data-bs-target="#myModal9">X Área</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item text-center" data-bs-toggle="modal" data-bs-target="#myModal12">X Clima</button></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><button type="button" class="fs-5 dropdown-item text-center" data-bs-toggle="modal" data-bs-target="#myModal8">X Instructor</button></li>
                                            </ul>
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
                                    <li><form action="#"><input class="dropdown-item" name="link" type="submit" value="Salir">
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
            <section class="section swiper-container swiper-slider swiper-slider-minimal" data-loop="true" data-slide-effect="fade" data-autoplay="4759" data-simulate-touch="true">
                <div class="swiper-wrapper">
                    <div class="swiper-slide swiper-slide_video" data-slide-bg="../images/cargado.jpg">
                        <div class="container">
                            <div class="jumbotron-classic-content">
                                <div class="wow-outer">
                                    <div class="title-docor-text font-weight-bold title-decorated text-uppercase wow slideInLeft text-white">Cargado datos</div>
                                </div>
                                <h1 class="text-uppercase text-white font-weight-bold wow-outer"><span class="wow slideInDown" data-wow-delay=".2s">Registrar</span></h1>
                                <p class="text-white wow-outer"><span class="wow slideInDown" data-wow-delay=".40s">Podra registrar sexo, clima, instructores, coordinadores, centros, regional, elementos, red, area, atravez de diversos formularios llenara los campos para el guardado de cada uno de los mismos.</span></p>

                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide" data-slide-bg="../images/ajustar.jpg">
                        <div class="container">
                            <div class="jumbotron-classic-content">
                                <div class="wow-outer">
                                    <div class="title-docor-text font-weight-bold title-decorated text-uppercase wow slideInLeft text-white">Gestión de datos</div>
                                </div>
                                <h1 class="text-uppercase text-white font-weight-bold wow-outer"><span class="wow slideInDown" data-wow-delay=".2s">Configurar</span></h1>
                                <p class="text-white wow-outer"><span class="wow slideInDown" data-wow-delay=".40s">En este campo del software podra ajustar cada uno de lo registro que haga, como por lo menos hacer busqueda, actualizado y eliminado de cada .</span></p>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide" data-slide-bg="../images/reporte.jpg">
                        <div class="container">
                            <div class="jumbotron-classic-content">
                                <div class="wow-outer">
                                    <div class="title-docor-text font-weight-bold title-decorated text-uppercase wow slideInLeft text-white">visualización</div>
                                </div>
                                <h1 class="text-uppercase text-white font-weight-bold wow-outer"><span class="wow slideInDown" data-wow-delay=".2s">Reportes</span></h1>
                                <p class="text-white wow-outer"><span class="wow slideInDown" data-wow-delay=".45s">Se mostrara los reportes acerca de la dotacion que le corresponda a los instructor por area, haciendo filtrado por regional, centro , red y demás.</span></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-pagination-outer container">
                    <div class="swiper-pagination swiper-pagination-modern swiper-pagination-marked" data-index-bullet="true"></div>
                </div>
            </section>
            <section class="section novi-background section-lg bg-gray-100">
                <div class="container">
                    <div class="row row-30">
                        <div class="col-sm-6 col-lg-4 wow-outer">
                            <!-- Box Minimal-->
                            <article class="box-minimal">
                                <div class="box-chloe__icon novi-icon linearicons-user wow fadeIn"></div>
                                <div class="box-minimal-main wow-outer">
                                    <h4 class="box-minimal-title wow slideInDown">Descripción</h4>
                                    <p class="wow fadeInUpSmall">Nuestro software de asignación de ropa de trabajo está diseñado específicamente para automatizar y optimizar el proceso de distribución de indumentaria a los instructores del SENA.</p>
                                </div>
                            </article>
                        </div>
                        <div class="col-sm-6 col-lg-4 wow-outer">
                            <!-- Box Minimal-->
                            <article class="box-minimal">
                                <div class="box-chloe__icon novi-icon linearicons-bubble-text wow fadeIn" data-wow-delay=".1s"></div>
                                <div class="box-minimal-main wow-outer">
                                    <h4 class="box-minimal-title wow slideInDown" data-wow-delay=".1s">Interfaz Amigable</h4>
                                    <p class="wow fadeInUpSmall" data-wow-delay=".1s">Diseñado con una interfaz intuitiva y fácil de usar, el software es accesible para todos los usuarios, independientemente de su nivel de habilidad técnica.</p>
                                </div>
                            </article>
                        </div>
                        <div class="col-sm-6 col-lg-4 wow-outer">
                            <!-- Box Minimal-->
                            <article class="box-minimal">
                                <div class="box-chloe__icon novi-icon linearicons-star wow fadeIn" data-wow-delay=".2s"></div>
                                <div class="box-minimal-main wow-outer">
                                    <h4 class="box-minimal-title wow slideInDown" data-wow-delay=".2s">Reportes y Análisis</h4>
                                    <p class="wow fadeInUpSmall" data-wow-delay=".2s">Genera reportes detallados que permiten a los administradores del SENA analizar las asignaciones realizadas, identificar posibles mejoras y garantizar la equidad y adecuación en la distribución de la ropa de trabajo.</p>
                                </div>
                            </article>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Services-->
            <footer class="section novi-background footer-advanced bg-gray-700">
                <div class="footer-advanced-main">
                    <div class="container">
                        <div class="row row-50">
                            <div class="col-lg-6">
                                <h5 class="font-weight-bold text-uppercase text-white">Acerca de nosotros</h5>
                                <p class="footer-advanced-text">Nuestro software es la solución líder en la automatización de la asignación de ropa de trabajo para los instructores del SENA. Diseñado específicamente para optimizar y simplificar el proceso, nuestro sistema ofrece una gestión integral y eficiente de la dotación de vestimenta laboral. Con nuestra herramienta, aseguramos que cada instructor reciba la ropa adecuada según sus necesidades y requisitos específicos, mejorando así la organización y el cumplimiento de normativas en el entorno educativo.</p>
                            </div>
                            <div class="col-sm-7 col-md-5 col-lg-6">
                                <h5 class="font-weight-bold text-uppercase text-white">Normativa</h5>
                                <!-- Post Inline-->
                                <article class="post-inline">
                                    <p class="post-inline-title"><a href="#">Código Sustantivo del Trabajo (CST):</a></p>
                                    <ul class="post-inline-meta">
                                        <li>Artículo 57</li>
                                    </ul>
                                </article>
                                <!-- Post Inline-->
                                <article class="post-inline">
                                    <p class="post-inline-title"><a href="#">Resolución 0343 de 2009 del SENA</a></p>
                                </article>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footer-advanced-aside">
                    <div class="container">
                        <div class="footer-advanced-layout">
                            <div>
                                <ul class="list-nav"> 
                                    <li><a href="../Views/Menu.jsp">Inicio</a></li>
                                    <li><a href="#">Registrar</a></li>
                                    <li><a href="#">Gestionar</a></li>
                                    <li><a href="#">Reportes</a></li>
                                </ul>
                            </div>
                            <div>
                                <ul class="foter-social-links list-inline list-inline-md">
                                    <li><a class="icon novi-icon icon-sm link-default mdi mdi-facebook" href="#"></a></li>
                                    <li><a class="icon novi-icon icon-sm link-default mdi mdi-twitter" href="#"></a></li>
                                    <li><a class="icon novi-icon icon-sm link-default mdi mdi-instagram" href="#"></a></li>
                                    <li><a class="icon novi-icon icon-sm link-default mdi mdi-google" href="#"></a></li>
                                    <li><a class="icon novi-icon icon-sm link-default mdi mdi-linkedin" href="#"></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <hr/>
                </div>
                <div class="footer-advanced-aside">
                    <div class="container">
                        <div class="footer-advanced-layout"><a class="brand" href="../Views/Menu.jsp"><img src="../images/Logo-de-SENA-png-verde-300x300-1.png" alt="" width="115" height="34" srcset="../images/Logo-de-SENA-png-verde-300x300-1.png 2x"/></a>
                            <!-- Rights-->
                            <p class="rights"><span>&copy;&nbsp;</span><span class="copyright-year"></span>. All Rights Reserved. Design by <a href="#">Isaac Izquierdo B</a></p>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
        <!-- Global Mailform Output-->
        <div class="snackbars" id="form-output-global"></div>
        <!-- Javascript-->
        <script src="../js/core.min.js"></script>
        <script src="../js/script.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <jsp:include page="../Componentes/modales.jsp"></jsp:include>
        <%
            String rta = request.getParameter("rta");
            if (rta != null) {
                switch (rta) {
                    case "Guardado_sexo":
        %>
        <script>
            Swal.fire({
                title: "Buen trabajo!",
                text: "Datos guardados in BD Sexo!",
                icon: "success"
            });
        </script>
        <%
                break;
            case "Guardado_clima":
        %>
        <script>
            Swal.fire({
                title: "Buen trabajo!",
                text: "Datos guardados in BD Clima!",
                icon: "success"
            });
        </script>
        <%
                break;
            case "Guardado_red":
        %>
        <script>
            Swal.fire({
                title: "Buen trabajo!",
                text: "Datos guardados in BD Red!",
                icon: "success"
            });
        </script>
        <%
                break;
            case "Guardado_area":
        %>
        <script>
            Swal.fire({
                title: "Buen trabajo!",
                text: "Datos guardados in BD Area!",
                icon: "success"
            });
        </script>
        <%
                break;
            case "Existentes":
        %>
        <script>
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Datos existentes!"
            });
        </script>
        <%
                break;
            case "Guardado_regional":
        %>
        <script>
            Swal.fire({
                title: "Buen trabajo!",
                text: "Datos guardados in BD Regional!",
                icon: "success"
            });
        </script>
        <%
                break;
            case "Guardado_centro":
        %>
        <script>
            Swal.fire({
                title: "Buen trabajo!",
                text: "Datos guardados in BD Centro!",
                icon: "success"
            });
        </script>
        <%
                break;
            case "Guardado_coordinador":
        %>
        <script>
            Swal.fire({
                title: "Buen trabajo!",
                text: "Datos guardados in BD Coordinador!",
                icon: "success"
            });
        </script>
        <%
                break;
            case "Guardado_instructor":
        %>
        <script>
            Swal.fire({
                title: "Buen trabajo!",
                text: "Datos guardados in BD Instructor!",
                icon: "success"
            });
        </script>
        <%
                        break;
                    default:
                        break;
                }
            }
        %>
    </body>
</html>
