<%@page import="Logic.*"%>
<%@page import="java.util.List"%>
<%@page import="Controllers.*"%>
<div class="modal fade" tabindex="-1" id="myModal11">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Coordinador</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Coordinador_Servlet" method="post">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Cédula</label>
                        <input type="number"  name="idcor" required max="9999999999" min="1" class="form-control" id="idcor" placeholder="Id coordinador">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Nombres</label>
                        <input type="text" name="nombre_cor" required maxlength="45" oninput="this.value = this.value.toUpperCase()"  class="form-control"id="nombre_cor" placeholder="Nombres">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Apellidos</label>
                        <input type="text" name="lastname" required maxlength="45" oninput="this.value = this.value.toUpperCase()"class="form-control"id="nombre" placeholder="Apellidos">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Correo</label>
                        <input type="text" name="correo_cor" required maxlength="45" oninput="this.value = this.value.toUpperCase()"class="form-control"id="correo_cor" placeholder="Correo">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Regional</label>
                        <select name="reg" id="reg" required class="form-select" onchange="filtrarCentrot()">
                            <option value="" disabled selected>---Selecciona su regional---</option>
                            <% RegionalJpaController cre = new RegionalJpaController();
                                List listaccre = cre.findRegionalEntities();
                                for (int i = 0; i < listaccre.size(); i++) {
                                    Regional obj_cr = (Regional) listaccre.get(i);
                                    out.print("<option value='" + obj_cr.getIdregional() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Centro</label>
                        <select name="cent" id="cent" required class="form-select">
                            <option value="" disabled selected>---Selecciona su centro---</option>
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
<script>
    function filtrarCentrot() {
        var regionalSeleccionada = document.getElementById("reg").value;
        var centrosSelect = document.getElementById("cent");
        centrosSelect.innerHTML = "<option value='' disabled selected>---Selecciona su centro---</option>";
    <%
        CentroJpaController cc = new CentroJpaController();
        List listacctro = cc.findCentroEntities();
        for (int i = 0; i < listacctro.size(); i++) {
            Centro obj_c = (Centro) listacctro.get(i);
    %>
        if ("<%= obj_c.getRegionalIdregional().getIdregional()%>" === regionalSeleccionada) {
            var option = document.createElement("option");
            option.value = "<%= obj_c.getIdcentro()%>";
            option.text = "<%= obj_c.getNombre()%>";
            centrosSelect.add(option);
        }
    <% }%>
    }
</script>
<div class="modal fade" tabindex="-1" id="myModal10">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Coordinador</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Coordinador_Servlet" method="post">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Cédula</label>
                        <input type="number"  name="idcoor" readonly id="idcoor" required max="9999999999" class="form-control" placeholder="Id Instructor">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Nombres</label>
                        <input type="text" name="nombre_coor" id="nombre_coor" required maxlength="45"oninput="this.value = this.value.toUpperCase()" class="form-control" placeholder="Nombres">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Apellidos</label>
                        <input type="text" name="lastname_" id="lastname_" required maxlength="45" oninput="this.value = this.value.toUpperCase()" class="form-control" placeholder="Apellidos">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Correo</label>
                        <input type="text" name="correo_coor" id="correo_coor" required maxlength="45" oninput="this.value = this.value.toUpperCase()" class="form-control" placeholder="Correo">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Regional</label>
                        <select name="region" id="region" required class="form-select" onclick="filtrarCentros2()">
                            <option value="" disabled selected>---Selecciona su regional---</option>
                            <%
                                List listaccregi = cre.findRegionalEntities();
                                for (int i = 0; i < listaccregi.size(); i++) {
                                    Regional obj_cr = (Regional) listaccregi.get(i);
                                    out.print("<option value='" + obj_cr.getIdregional() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Centro</label>
                        <select name="center" id="center" required class="form-select">
                            <option value="" disabled selected>---Selecciona su centro---</option>
                            <%
                                CentroJpaController cct = new CentroJpaController();
                                List listacc = cct.findCentroEntities();
                                for (int i = 0; i < listacc.size(); i++) {
                                    Centro obj_cr = (Centro) listacc.get(i);
                                    out.print("<option value='" + obj_cr.getIdcentro() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
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
    function cargarDatosModal2(idcoord, nombres, apellidos, correo, regionalId, centroId) {
        // Asigna los valores a los campos del formulario dentro del modal
        document.getElementById("idcoor").value = idcoord;
        document.getElementById("nombre_coor").value = nombres;
        document.getElementById("lastname_").value = apellidos;
        document.getElementById("correo_coor").value = correo;
        document.getElementById("region").value = regionalId;
        document.getElementById("center").value = centroId;
        // Abre el modal
        var myModal = new bootstrap.Modal(document.getElementById('myModal10'));
        myModal.show();
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
</script>