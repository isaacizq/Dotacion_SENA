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
                            <span toggle="#password-field2" class="fa fa-fw fa-eye field-icon toggle-password2"></span>
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
