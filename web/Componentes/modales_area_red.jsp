<%@page import="Controllers.RedJpaController"%>
<%@page import="Logic.Red"%>
<%@page import="java.util.List"%>
<div class="modal fade" tabindex="-1" id="myModal10">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Area</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Area_Servlet" method="post">
                    <div class="mb-3">
                        <input type="number" readonly  name="idareas" id="idareas" required max="99999999" class="form-control" placeholder="Id Área">
                    </div>
                    <div class="mb-3">
                        <input type="text" name="nombre_areas" id="nombre_areas" oninput="this.value = this.value.toUpperCase()" required maxlength="1000" class="form-control" placeholder="Nombre">
                    </div>
                    <div class="mb-3">
                        <select name="Redes" id="Redes" required class="form-select">
                            <option value="" disabled selected>---Selecciona su red---</option>
                            <%
                                RedJpaController cred = new RedJpaController();
                                List listaccregi = cred.findRedEntities();
                                for (int i = 0; i < listaccregi.size(); i++) {
                                    Red obj_cr = (Red) listaccregi.get(i);
                                    out.print("<option value='" + obj_cr.getIdred() + "'>");
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
<div class="modal fade" tabindex="-1" id="myModal11">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Red</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Red_Servlet" method="post">
                    <div class="mb-3">
                        <input type="number" readonly name="idredes" id="idredes" required max="99999999" class="form-control" placeholder="Id redes">
                    </div>
                    <div class="mb-3">
                        <input type="text" name="nombre_redes" id="nombre_redes" oninput="this.value = this.value.toUpperCase()" required maxlength="100" class="form-control" placeholder="Nombre">
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
    function cargarDatosModal2(idarea, nombres, red) {
        // Asigna los valores a los campos del formulario dentro del modal
        document.getElementById("idareas").value = idarea;
        document.getElementById("nombre_areas").value = nombres;
        document.getElementById("Redes").value = red;
        // Abre el modal
        var myModal = new bootstrap.Modal(document.getElementById('myModal10'));
        myModal.show();
    }
    // Función para capturar y enviar los datos al modal
    function cargarDatosModal(idred, nombre) {
        // Asigna los valores a los campos del formulario dentro del modal
        document.getElementById("idredes").value = idred;
        document.getElementById("nombre_redes").value = nombre;
        // Abre el modal
        var myModal = new bootstrap.Modal(document.getElementById('myModal11'));
        myModal.show();
    }
</script>