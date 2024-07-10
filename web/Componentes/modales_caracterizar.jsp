<%@page import="Controllers.RedJpaController"%>
<%@page import="Controllers.AreaJpaController"%>
<%@page import="Logic.Area"%>
<%@page import="Logic.Dotacion"%>
<%@page import="Controllers.DotacionJpaController"%>
<%@page import="Logic.Coordinador"%>
<%@page import="Controllers.CoordinadorJpaController"%>
<%@page import="Controllers.CentroJpaController"%>
<%@page import="Logic.Centro"%>
<%@page import="Logic.Instructor"%>
<%@page import="Controllers.InstructorJpaController"%>
<%@page import="Logic.Sexo"%>
<%@page import="Controllers.SexoJpaController"%>
<%@page import="Logic.Clima"%>
<%@page import="Controllers.ClimaJpaController"%>
<%@page import="Logic.Red"%>
<%@page import="Logic.Regional"%>
<%@page import="java.util.List"%>
<%@page import="Controllers.RegionalJpaController"%>
<div class="mt-8 modal fade" tabindex="-1" id="myModal14">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Caracterización</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Caracterizar_Servlet" method="post" onsubmit="return enviarFormulario()">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Regional</label>
                        <select name="regiona" id="regiona" required class="form-select" onchange="filtrarCentro3()">
                            <option value="" disabled selected>---Selecciona  regional---</option>
                            <%
                                RegionalJpaController cre = new RegionalJpaController();
                                List listaccregionall = cre.findRegionalEntities();
                                for (int i = 0; i < listaccregionall.size(); i++) {
                                    Regional obj_cr = (Regional) listaccregionall.get(i);
                                    out.print("<option value='" + obj_cr.getIdregional() + "'>");
                                    out.print((obj_cr.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Centro</label>
                        <select name="centros1" id="centros1" required class="form-select" onchange="filtrarCoordinador3()">
                            <option value="" disabled selected>---Selecciona  centro---</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Coordinador</label>
                        <select name="coor2" id="coor2" required class="form-select" onchange="filtrarinstructor()">
                            <option value="" disabled selected>---Selecciona coordinador---</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Instructor</label>
                        <select name="instruc" id="instruc" required class="form-select">
                            <option value="" disabled selected>---Seleccione el instructor---</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Red</label>
                        <select name="r" required id="r" class="form-select" onchange="filtrar_areas()">
                            <option value="" disabled selected>---Selecciona su red ---</option>
                            <%
                                RedJpaController rc = new RedJpaController();
                                List<Red> listared = rc.findRedEntities();
                                for (int i = 0; i < listared.size(); i++) {
                                    Red obj_redes = (Red) listared.get(i);
                                    out.print("<option value='" + obj_redes.getIdred() + "'>");
                                    out.print((obj_redes.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Área</label>
                        <select name="areass" id="areass" required class="form-select" onchange="llenartxtarea()">
                            <option value="" disabled selected>---Selecciona el área---</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Clima</label>
                        <select name="Clim" id="Clim" required class="form-select"onchange="llenartxtarea()">
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
                        <label class="fs-6 dropdown">Sexo</label>
                        <select name="Sex" id="Sex" required class="form-select" onchange="llenartxtarea()">                                  
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
                        <label class="fs-6 dropdown">Dotación</label>
                        <textarea name="descrip" id="descrip" required readonly maxlength="500" class="form-control" placeholder="Descripción"></textarea> 
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">cerrar</button>
                        <input type="submit" name="action" value="Guardar" class="btn btn-primary">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>

        function filtrarinstructor() {
            var coorSeleccionado = document.getElementById("coor2").value;
            var InstructorSelect = document.getElementById("instruc");
            InstructorSelect.innerHTML = "<option value='' disabled selected>---Selecciona su Instructor---</option>";
        <%
            InstructorJpaController controller = new InstructorJpaController();
            List listac = controller.findInstructorEntities();
            for (int i = 0; i < listac.size(); i++) {
                Instructor obj_cto = (Instructor) listac.get(i);
        %>
            if ("<%= obj_cto.getCoordinadorIdcoordinador().getIdcoordinador()%>" === coorSeleccionado) {
                var option = document.createElement("option");
                option.value = "<%= obj_cto.getIdinstructor()%>";
                option.text = "<%= obj_cto.getNombres() + " " + obj_cto.getApellidos()%>\n";
                InstructorSelect.add(option);
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
        function filtrarCentro3() {
            var regionalSeleccionada = document.getElementById("regiona").value;
            var centrosSelect = document.getElementById("centros1");
            centrosSelect.innerHTML = "<option value='' disabled selected>---Selecciona su centro---</option>";
        <%
            CentroJpaController cc = new CentroJpaController();
            List lista3 = cc.findCentroEntities();
            for (int i = 0; i < lista3.size(); i++) {
                Centro obj_c = (Centro) lista3.get(i);
        %>
            if ("<%= obj_c.getRegionalIdregional().getIdregional()%>" === regionalSeleccionada) {
                var option = document.createElement("option");
                option.value = "<%= obj_c.getIdcentro()%>";
                option.text = "<%= obj_c.getNombre()%>";
                centrosSelect.add(option);
            }
        <% } %>
        }
        function filtrarCoordinador3() {
            var centroSeleccionado = document.getElementById("centros1").value;
            var CoordinadorsSelect = document.getElementById("coor2");
            CoordinadorsSelect.innerHTML = "<option value='' disabled selected>---Selecciona su coordinador---</option>";
        <%
            CoordinadorJpaController ccro = new CoordinadorJpaController();
            List listacct2 = ccro.findCoordinadorEntities();
            for (int i = 0; i < listacct2.size(); i++) {
                Coordinador obj_cto = (Coordinador) listacct2.get(i);
        %>
            if ("<%= obj_cto.getCentroIdcentro().getIdcentro()%>" === centroSeleccionado) {
                var option = document.createElement("option");
                option.value = "<%= obj_cto.getIdcoordinador()%>";
                option.text = "<%= obj_cto.getNombres() + " " + obj_cto.getApellidos()%>";
                CoordinadorsSelect.add(option);
            }
        <% }%>
        }
        function llenartxtarea() {
            var areaSeleccionada = document.getElementById("areass").value;
            var sexoSeleccionada = document.getElementById("Sex").value;
            var climaSeleccionada = document.getElementById("Clim").value;
            var txtarea = document.getElementById("descrip");
            txtarea.innerHTML = "";
            var dotacionArray = [];
        <%  DotacionJpaController cdo = new DotacionJpaController();
            List listadot = cdo.findDotacionEntities();
            for (int i = 0; i < listadot.size(); i++) {
                Dotacion obj_c = (Dotacion) listadot.get(i);
        %>
            if ("<%= obj_c.getAreaIdarea().getIdarea()%>" === areaSeleccionada && "<%= obj_c.getClimaIdclima().getIdclima()%>" === climaSeleccionada && "<%= obj_c.getSexoIdsexo().getIdsexo()%>" === sexoSeleccionada) {
                txtarea.append("(<%=obj_c.getCantidad()%>-<%=obj_c.getElementosIdelemento()%>);\n");

                var dotacionItem = {
                    idelemento: <%=obj_c.getElementosIdelemento().getIdelemento()%>,
                    cantidad: <%=obj_c.getCantidad()%>
                };
                dotacionArray.push(dotacionItem); // Agregar el objeto al array
            }
        <% } %>
            return dotacionArray;
        }
        function enviarFormulario() {
            // Obtener el array de dotación
            var dotacionArray = llenartxtarea();
            var instructor = document.getElementById("instruc").value;
            // Llamar a la función para guardar datos en la base de datos
            guardarDatosEnBaseDeDatos(dotacionArray, instructor);
            // Retornar true para enviar el formulario
            return true;
        }
        function filtrar_areas() {
            var redselect = document.getElementById("r").value;
            var areaSelect = document.getElementById("areass");
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
</div>
<div class="mt-8 modal fade" tabindex="-1" id="myModal13">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Caracterizar</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Caracterizar_Servlet" method="post" id="caracterizarForm">
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Id Caracterización</label>
                        <input type="number" name="idcaracterizar" id="idcaracterizar" readonly max="99999999" class="form-control" placeholder="Id caracterizar">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Instructor</label>
                        <select name="instructor" id="instructor" required class="form-select">
                            <option value="" disabled selected>---Seleccione el instructor---</option>
                            <%
                                InstructorJpaController cont = new InstructorJpaController();
                                List listins = cont.findInstructorEntities();
                                for (int i = 0; i < listins.size(); i++) {
                                    Instructor obj_cr = (Instructor) listins.get(i);
                                    out.print("<option value='" + obj_cr.getIdinstructor() + "'>");
                                    out.print((obj_cr.getNombres() + " " + obj_cr.getApellidos()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Red</label>
                        <select name="redd" required id="redd" class="form-select" onchange="filtrar_areas2()">
                            <option value="" disabled selected>---Selecciona su red ---</option>
                            <%
                                List listared2 = rc.findRedEntities();
                                for (int i = 0; i < listared2.size(); i++) {
                                    Red obj_redes = (Red) listared2.get(i);
                                    out.print("<option value='" + obj_redes.getIdred() + "'>");
                                    out.print((obj_redes.getNombre()));
                                    out.print("</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Área</label>
                        <select name="areass2" id="areass2" required class="form-select" onchange="llenardot()">
                            <option value="" disabled selected>---Selecciona el área---</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Clima</label>
                        <select name="Cl" id="Cl" required class="form-select" onchange="llenardot()">
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
                        <label class="fs-6 dropdown">Sexo</label>
                        <select name="Se" id="Se" required class="form-select" onchange="llenardot()">
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
                        <label class="fs-6 dropdown">Año</label>
                        <input type="number" name="ano2" id="ano2"  readonly required max="99999" class="form-control" placeholder="Año">
                    </div>
                    <div class="mb-3">
                        <label class="fs-6 dropdown">Dotación</label>
                        <textarea name="descripc" id="descripc" required readonly maxlength="500" class="form-control" placeholder="Descripción"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">cerrar</button>
                        <input type="submit" name="action"  id="btnEditar" value="Editar"  class="btn btn-primary">
                        <input type="submit" name="action"  id="btnEliminar" value="Eliminar" class="btn btn-danger">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    // Función para capturar y enviar los datos al modal

    function cargarDatosModal(Idcaracterizar, Instructor, red, area, clima, sexo, ano) {
        // Asigna los valores a los campos del formulario dentro del modal
        document.getElementById("idcaracterizar").value = Idcaracterizar;
        document.getElementById("instructor").value = Instructor;
        document.getElementById("redd").value = red;
        document.getElementById("Cl").value = clima;
        document.getElementById("Se").value = sexo;
        document.getElementById("ano2").value = ano;
        // Abre el modal
        var myModal2 = new bootstrap.Modal(document.getElementById('myModal13'));
        myModal2.show();
        filtrar_areas2();
        document.getElementById("areass2").value = area;
        llenardot();
    }
    function filtrar_areas2() {
        var redselect = document.getElementById("redd").value;
        var areaSelect = document.getElementById("areass2");
        areaSelect.innerHTML = "<option value='' disabled selected>---Selecciona su área ---</option>";
    <%
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
    function filtrar_areas3() {
        var redselect = document.getElementById("r2").value;
        var areaSelect = document.getElementById("Rarea");
        areaSelect.innerHTML = "<option value='' disabled selected>---Selecciona su área ---</option>";
    <%
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
    <% } %>
    }
    function llenardot() {
        var area = document.getElementById("areass2").value;
        var sexo = document.getElementById("Se").value;
        var clima = document.getElementById("Cl").value;
        var txt2 = document.getElementById("descripc");
        txt2.innerHTML = "";
        var dotacionArray = [];
    <%
        DotacionJpaController controlador = new DotacionJpaController();
        List listadotacion = controlador.findDotacionEntities();
        for (int i = 0; i < listadotacion.size(); i++) {
            Dotacion obj = (Dotacion) listadotacion.get(i);
    %>
        if ("<%= obj.getAreaIdarea().getIdarea()%>" === area && "<%= obj.getClimaIdclima().getIdclima()%>" === clima && "<%= obj.getSexoIdsexo().getIdsexo()%>" === sexo) {
            txt2.append("(<%=obj.getCantidad()%>-<%=obj.getElementosIdelemento()%>);\n");
            var dotacionItem = {
                idelemento:<%=obj.getElementosIdelemento().getIdelemento()%>,
                cantidad: <%=obj.getCantidad()%>
            };
            dotacionArray.push(dotacionItem); // Agregar el objeto al array
        }
    <% }%>
        return dotacionArray;
    }
    document.addEventListener("DOMContentLoaded", function () {
        var btnEditar = document.getElementById("btnEditar");
        var btnEliminar = document.getElementById("btnEliminar");
        btnEditar.addEventListener('click', function () {
            enviarFormulario2();

        });
        btnEliminar.addEventListener('click', function () {
            // Lógica adicional para el botón de eliminar si es necesario
        });
    });
    function enviarFormulario2() {
        // Obtener el array de dotación
        var dotacionArray = llenardot();
        var instructor = document.getElementById("instructor").value;
        guardarDatosEnBaseDeDatos(dotacionArray, instructor);
        // Después de guardar los datos, enviar el formulario

    }
    function guardarDatosEnBaseDeDatos(dotacionArray, instructor) {
        $.ajax({
            type: "POST",
            url: "cantidades.jsp", // URL de tu script JSP para procesar y guardar los datos en la base de datos
            data: {
                dotacionArray: JSON.stringify(dotacionArray),
                instructor: instructor
            },
            success: function (response) {
                // Manejar la respuesta del servidor si es necesario
                console.log(response);
            },
            error: function (xhr, status, error) {
                // Manejar errores si los hay
                console.error(xhr.responseText);
            }
        });
    }
</script>