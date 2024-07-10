<%@page import="Logic.Instructor"%>
<%@page import="Controllers.InstructorJpaController"%>
<%@page import="Logic.Area"%>
<%@page import="Controllers.AreaJpaController"%>
<%@page import="Logic.Clima"%>
<%@page import="Controllers.ClimaJpaController"%>
<%@page import="Logic.Coordinador"%>
<%@page import="Controllers.CoordinadorJpaController"%>
<%@page import="Logic.Regional"%>
<%@page import="Controllers.RegionalJpaController"%>
<%@page import="Logic.Red"%>
<%@page import="java.util.List"%>
<%@page import="Controllers.RedJpaController"%>
<div class="modal fade" tabindex="-1" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Sexo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Sexo_Servlet" method="post">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Id sexo</label>
                        <input type="number" required max="99999999" name="idsexo" min="1" class="form-control" id="idsexo" placeholder="Id sexo">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Nombre</label>
                        <input type="text" required maxlength="45" name="nombresexo" class="form-control" id="nombre" placeholder="Nombre">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">cerrar</button>
                        <input type="submit" name="action" value="Guardar" class="btn btn-primary">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" tabindex="-1" id="myModal2">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Clima</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Clima_Servlet" method="post">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Id clima</label>
                        <input type="number" required max="99999999" name="idclima" min="1" class="form-control" id="idclima" placeholder="Id clima">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Nombre</label>
                        <input type="text" required maxlength="45" name="nombre_clima"  class="form-control"id="nombre_clima" placeholder="Nombre">
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
<div class="modal fade" tabindex="-1" id="myModal3">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Red</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Red_Servlet" method="post">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Id red</label>
                        <input type="number"  name="idred" required max="99999999" min="1"  class="form-control" id="idred" placeholder="Id Red">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Nombre</label>
                        <input type="text" name="nombre_red" required maxlength="45"  class="form-control"id="nombre_red" placeholder="Nombre">
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
<div class="modal fade" tabindex="-1" id="myModal4">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Area</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Area_Servlet" method="post">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Id área</label>
                        <input type="number" required  name="idarea" max="9999999" min="1" class="form-control" placeholder="Id Area">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Nombre</label>
                        <input type="text" required maxlength="45" name="nombre_area" placeholder="Nombre"  class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Red</label>
                        <select name="red" required id="red" class="form-select">
                            <option value="" disabled selected>---Selecciona su Red correspondiente---</option>
                            <% RedJpaController rc = new RedJpaController();
                                List listar = rc.findRedEntities();
                                for (int i = 0; i < listar.size(); i++) {
                                    Red obj_red = (Red) listar.get(i);
                                    out.print("<option value='" + obj_red.getIdred() + "'>");
                                    out.print((obj_red.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
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
<div class="modal fade" tabindex="-1" id="myModal5">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Regional</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Regional_Servlet" method="post">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Id regional</label>
                        <input type="number" required max="99999999" name="idregional" min="1"  class="form-control" id="idregional" placeholder="Id regional">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Nombre</label>
                        <input type="text" required maxlength="45" name="nombre_regional"  class="form-control"id="nombre_regional" placeholder="Nombre">
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
<div class="modal fade" tabindex="-1" id="myModal6">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Centro</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Centro_Servlet" method="post">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Id centro</label>
                        <input type="number" required max="9999999" name="idcentro" min="1" class="form-control" placeholder="Id centro">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Nombre</label>
                        <input type="text" required maxlength="100" name="nombre_centro" class="form-control" placeholder="Nombre">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Regional</label>
                        <select name="regional" required id="regional" class="form-select">
                            <option value="" disabled selected>---Selecciona su Regional correspondiente---</option>
                            <% RegionalJpaController rgc = new RegionalJpaController();
                                List listarg = rgc.findRegionalEntities();
                                for (int i = 0; i < listarg.size(); i++) {
                                    Regional obj_rg = (Regional) listarg.get(i);
                                    out.print("<option value='" + obj_rg.getIdregional() + "'>");
                                    out.print((obj_rg.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
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
                        <label class="fs-6 dropdown">Id elemento</label>
                        <input type="number" required max="99999999" name="idelemen" min="1"  class="form-control" id="idelemen" placeholder="Id elemento">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Nombre</label>
                        <input type="text" required maxlength="45" name="nombre_elemen"  class="form-control"id="nombre_elemen" placeholder="Nombre">
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
<div class="modal fade" tabindex="-1" id="myModal8">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Instructores</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="RInstructores.jsp" name="" method="post" target="_blank">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Coordinador</label>
                        <select name="coordinador" id="coordinador" required class="form-select" onchange="filtrarinstructor2()">
                            <option value="" disabled selected>---Selecciona coordinador---</option>
                            <% CoordinadorJpaController fc = new CoordinadorJpaController();
                                List l = fc.findCoordinadorEntities();
                                for (int i = 0; i < l.size(); i++) {
                                    Coordinador obj = (Coordinador) l.get(i);
                                    out.print("<option value='" + obj.getIdcoordinador() + "'>");
                                    out.print((obj.getNombres() + " " + obj.getApellidos()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Instructor</label>
                        <select name="Rins" id="Rins" required class="form-select">
                            <option value="" disabled selected>---Selecciona el Instructor(a)---</option>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">cerrar</button>
                        <input type="submit"  name="action" value="Generar" class="btn btn-primary">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" tabindex="-1" id="myModal9">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Área</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="RAreas.jsp" name="" method="post" target="_blank">
                    <div class="mb-3">
                        <div class="mb-3">
                            <label class="fs-6 dropdown">Red</label>
                            <select name="r2" required id="r2" class="form-select" onchange="filtrar_areas3()">
                                <option value="" disabled selected>---Selecciona su red ---</option>
                                <%
                                    List listared = rc.findRedEntities();
                                    for (int i = 0; i < listared.size(); i++) {
                                        Red obj_redes = (Red) listared.get(i);
                                        out.print("<option value='" + obj_redes.getIdred() + "'>");
                                        out.print((obj_redes.getNombre()));
                                        out.print("</option>");
                                    }
                                %>
                            </select>
                        </div>
                        <label class="fs-6 dropdown">Área</label>
                        <select name="Rarea" id="Rarea" required class="form-select">
                            <option value="" disabled selected>---Selecciona el área---</option>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">cerrar</button>
                        <input type="submit"  name="action" value="Generar" class="btn btn-primary">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" tabindex="-1" id="myModal12">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Clima</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="RClima.jsp" name="" method="post" target="_blank">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Clima</label>
                        <select name="Rclima" required id="Rclima" class="form-select">
                            <option value="" disabled selected>---Selecciona el clima---</option>
                            <% ClimaJpaController climac = new ClimaJpaController();
                                List l3 = climac.findClimaEntities();
                                for (int i = 0; i < l3.size(); i++) {
                                    Clima obj = (Clima) l3.get(i);
                                    out.print("<option value='" + obj.getIdclima() + "'>");
                                    out.print((obj.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">cerrar</button>
                        <input type="submit"  name="action" value="Generar" class="btn btn-primary">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function filtrar_areas3() {
        var redselect = document.getElementById("r2").value;
        var areaSelect = document.getElementById("Rarea");
        areaSelect.innerHTML = "<option value='' disabled selected>---Selecciona su área ---</option>";
    <%
        AreaJpaController areac = new AreaJpaController();
        List listarea2 = areac.findAreaEntities();
        for (int i = 0; i < listarea2.size(); i++) {
            Area obj_area = (Area) listarea2.get(i);
    %>
        if ("<%= obj_area.getRedIdred().getIdred()%>" === redselect) {
            var option = document.createElement("option");
            option.value = "<%= obj_area.getIdarea()%>";
            option.text = "<%= obj_area.getNombre().replace("\r", "").replace("\n", "")%>";
            areaSelect.add(option);
        }
    <% }%>
    }
    function filtrarinstructor2() {
        var coorSeleccionado = document.getElementById("coordinador").value;
        var InstructorSelect = document.getElementById("Rins");
        InstructorSelect.innerHTML = "<option value='' disabled selected>---Selecciona su Instructor---</option>";
    <%
        InstructorJpaController controller2 = new InstructorJpaController();
        List listac2 = controller2.findInstructorEntities();
        for (int i = 0; i < listac2.size(); i++) {
            Instructor obj_cto = (Instructor) listac2.get(i);
    %>
        if ("<%= obj_cto.getCoordinadorIdcoordinador().getIdcoordinador()%>" === coorSeleccionado) {
            var option = document.createElement("option");
            option.value = "<%= obj_cto.getIdinstructor()%>";
            option.text = "<%= obj_cto.getNombres() + " " + obj_cto.getApellidos()%>\n";
            InstructorSelect.add(option);
        }
    <% }%>
    }
</script>