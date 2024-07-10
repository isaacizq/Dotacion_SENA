<div class="modal fade" tabindex="-1" id="myModal10">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Sexo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Sexo_Servlet" method="post">
                    <div class="mb-3">
                        <input type="number" readonly  name="idsexos" id="idsexos" required max="99999999" class="form-control" placeholder="Id Regional">
                    </div>
                    <div class="mb-3">
                        <input type="text" name="nom_sexo" id="nom_sexo" required maxlength="100" class="form-control" placeholder="Nombre">
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
                <h5 class="modal-title">Clima</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Clima_Servlet" method="post">
                    <div class="mb-3">
                        <input type="number" readonly name="idclimas" id="idclimas" required max="99999999" class="form-control" placeholder="Id Clima">
                    </div>
                    <div class="mb-3">
                        <input type="text" name="nom_clima" id="nom_clima" required maxlength="100" class="form-control" placeholder="Nombre">
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
    function cargarDatosModal2(idsexo, nombres) {
        // Asigna los valores a los campos del formulario dentro del modal
        document.getElementById("idsexos").value = idsexo;
        document.getElementById("nom_sexo").value = nombres;
        // Abre el modal
        var myModal = new bootstrap.Modal(document.getElementById('myModal10'));
        myModal.show();
    }
    // Función para capturar y enviar los datos al modal
    function cargarDatosModal(idclima, nombre) {
        // Asigna los valores a los campos del formulario dentro del modal
        document.getElementById("idclimas").value = idclima;
        document.getElementById("nom_clima").value = nombre;
        // Abre el modal
        var myModal = new bootstrap.Modal(document.getElementById('myModal11'));
        myModal.show();
    }
</script>