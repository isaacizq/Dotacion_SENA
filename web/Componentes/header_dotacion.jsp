<%@page import="Logic.Usuarios"%>
<%
    response.setHeader("Cache-Control", "no-Cache,no-store,must-revalidate");
    // Obtiene la sesión actual sin crear una nueva si no existe
    HttpSession cecion = request.getSession(false);
    Usuarios usu = (Usuarios) cecion.getAttribute("usuario");
    if (cecion == null || usu == null || usu.getRol() == 3) {
        response.sendRedirect("Login.jsp");
        return; // Detiene la ejecución del código restante
    }
%>
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
                        <!-- RD Navbar Brand--><a class="rd-navbar-brand" href="Index.jsp"><img src="images/Logo-de-SENA-png-verde-300x300-1.png" alt="" width="151" height="44" srcset="images/Logo-de-SENA-png-verde-300x300-1.png 2x"/></a>
                        <p class="fs-4 fw-bolder text-center" style="color: green">DotaSoft</p>
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
                            <li class="rd-nav-item active"><a class="fs-6 rd-nav-link" href="Index.jsp"style="color: green">Inicio</a>
                            </li>
                            <li class="nav-item dropdown rd-nav-item "><a class="fs-6 nav-link dropdown-toggle rd-nav-link" href="#" role="button" data-bs-toggle="dropdown"style="color: green">Registros</a>
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
                                    <li><button type="button" class="fs-5 dropdown-item text-center" data-bs-toggle="modal" data-bs-target="#myModal10">X Clima</button></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><button type="button" class="fs-5 dropdown-item text-center" data-bs-toggle="modal" data-bs-target="#myModal8">X Instructor</button></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown rd-nav-item ">
                                <input class="form-control me-2" type="search" id="search" placeholder="Search" aria-label="Search">
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="mt-3 mx-2 dropdown position-absolute top-0 end-0">
                    <button class="btn btn-success dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Usuario
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#"><img
                                    src="images/user_1177568.png" alt=""></a>
                        </li>
                        <li><a class="dropdown-item" href=""><%= usu.getNombreCompleto()%></a></li>
                        <li><form action="Gestion_usuarios.jsp"><input class="dropdown-item" name="link" type="submit" value="Salir">
                                <%
                                    if (request.getParameter("link") != null) {  //Cierra sesion al eliminar los atributos de la seseion
                                        cecion.removeAttribute("usuario");
                                    }
                                    if (cecion.getAttribute("usuario") == null) {//valida si la sesion no tiene atributos y redirecciona al login
                                        response.sendRedirect("Login.jsp");
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