<%@page import="java.util.List"%>
<%@page import="Logic.*"%>
<%@page import="Controllers.*"%>
<div class="modal fade" tabindex="-1" id="myModal14">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Dotación</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Dotacion_Servlet" method="post">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Id dotación</label>
                        <select name="elemento" id="elemento" required class="form-select">
                            <option value="" disabled selected>---Seleccione el elemento---</option>
                            <%
                                ElementosJpaController ce = new ElementosJpaController();
                                List listaelem = ce.findElementosEntities();
                                for (int i = 0; i < listaelem.size(); i++) {
                                    Elementos obj_cr = (Elementos) listaelem.get(i);
                                    out.print("<option value='" + obj_cr.getIdelemento() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Sexo</label>
                        <select name="Sexos" id="Sexos" required class="form-select">
                            <option value="" disabled selected>---Selecciona el sexo---</option>
                            <%
                                SexoJpaController csexo = new SexoJpaController();
                                List listasexo = csexo.findSexoEntities();
                                for (int i = 0; i < listasexo.size(); i++) {
                                    Sexo obj_cr = (Sexo) listasexo.get(i);
                                    out.print("<option value='" + obj_cr.getIdsexo() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Clima</label>
                        <select name="Climas" id="Climas" required class="form-select">
                            <option value="" disabled selected>---Selecciona el clima---</option>
                            <%
                                ClimaJpaController cclima = new ClimaJpaController();
                                List listacclima = cclima.findClimaEntities();
                                for (int i = 0; i < listacclima.size(); i++) {
                                    Clima obj_cr = (Clima) listacclima.get(i);
                                    out.print("<option value='" + obj_cr.getIdclima() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Área</label>
                        <select name="areas" id="areas" required class="form-select">
                            <option value="" disabled selected>---Selecciona el área---</option>
                            <%
                                AreaJpaController careas = new AreaJpaController();
                                List listareas = careas.findAreaEntities();
                                for (int i = 0; i < listareas.size(); i++) {
                                    Area obj_cr = (Area) listareas.get(i);
                                    out.print("<option value='" + obj_cr.getIdarea() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Cantidades</label>
                        <input type="number"  name="cant" id="cant" min="1" required max="999" class="form-control" placeholder="Cantidad">
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
<div class="modal fade" tabindex="-1" id="myModal13">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Dotación</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Dotacion_Servlet" method="post">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Id dotación</label>
                        <input type="number"  name="iddotacion" id="iddotacion" readonly max="99999999" class="form-control" placeholder="Id dotación">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Elemento</label>
                        <select name="elementos" id="elementos" required class="form-select">
                            <option value="" disabled selected>---Seleccione el elemento---</option>
                            <%
                                for (int i = 0; i < listaelem.size(); i++) {
                                    Elementos obj_cr = (Elementos) listaelem.get(i);
                                    out.print("<option value='" + obj_cr.getIdelemento() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Sexo</label>
                        <select name="Sexo" id="Sexo" required class="form-select">
                            <option value="" disabled selected>---Selecciona el sexo---</option>
                            <%
                                for (int i = 0; i < listasexo.size(); i++) {
                                    Sexo obj_cr = (Sexo) listasexo.get(i);
                                    out.print("<option value='" + obj_cr.getIdsexo() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Clima</label>
                        <select name="Clima" id="Clima" required class="form-select">
                            <option value="" disabled selected>---Selecciona el clima---</option>
                            <%
                                for (int i = 0; i < listacclima.size(); i++) {
                                    Clima obj_cr = (Clima) listacclima.get(i);
                                    out.print("<option value='" + obj_cr.getIdclima() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Red</label>
                        <select name="redes" id="redes" required class="form-select" onchange="filtrar_areas()">
                            <option value="" disabled selected>---Selecciona el red---</option>
                            <%
                                RedJpaController rc = new RedJpaController();
                                List<Red> listared = rc.findRedEntities();
                                for (int i = 0; i < listared.size(); i++) {
                                    Red obj_cr = (Red) listared.get(i);
                                    out.print("<option value='" + obj_cr.getIdred() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Area</label>
                        <select name="areass2" id="areass2" required class="form-select">
                            <option value="" disabled selected>---Selecciona el área---</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Cantidades</label>
                        <input type="number"  name="canti" id="canti" min="1" required max="999" class="form-control" placeholder="Cantidad">
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
    function cargarDatosModal2(iddotacion, elemento, sexo, clima, red, area, cantidad) {
        // Asigna los valores a los campos del formulario dentro del modal
        document.getElementById("iddotacion").value = iddotacion;
        document.getElementById("elementos").value = elemento;
        document.getElementById("Sexo").value = sexo;
        document.getElementById("Clima").value = clima;
        document.getElementById("redes").value = red;
        document.getElementById("canti").value = cantidad;
        // Abre el modal
        var myModal = new bootstrap.Modal(document.getElementById('myModal13'));
        myModal.show();
        filtrar_areas();
        document.getElementById("areass2").value = area;

    }

    function filtrarCentros2() {
        var regionalSeleccionada = document.getElementById("region").value;
        var centrosSelect = document.getElementById("center");
        centrosSelect.innerHTML = "<option value='' disabled selected>---Selecciona su centro---</option>";
    <%
        CentroJpaController ccc = new CentroJpaController();
        List listacctrr = ccc.findCentroEntities();
        for (int i = 0; i < listacctrr.size(); i++) {
            Centro obj_c = (Centro) listacctrr.get(i);
    %>
        if ("<%= obj_c.getRegionalIdregional().getIdregional()%>" === regionalSeleccionada) {
            var option = document.createElement("option");
            option.value = "<%= obj_c.getIdcentro()%>";
            option.text = "<%= obj_c.getNombre()%>";
            centrosSelect.add(option);
        }
    <% }%>
    }
    function filtrar_areas() {
        var redselect = document.getElementById("redes").value;
        var areaSelect = document.getElementById("areass2");
        areaSelect.innerHTML = "<option value='' disabled selected>---Selecciona su área ---</option>";
    <%
        AreaJpaController areac = new AreaJpaController();
        List listarea = areac.findAreaEntities();
        for (int i = 0; i < listarea.size(); i++) {
            Area obj_area = (Area) listarea.get(i);
    %>
        if ("<%= obj_area.getRedIdred().getIdred()%>" === redselect) {
            var option = document.createElement("option");
            option.value = "<%= obj_area.getIdarea()%>";
            option.text = "<%= obj_area.getNombre().replace("\r", "").replace("\n", "")%>";
            areaSelect.add(option);
        }
    <% }%>
    }
</script>