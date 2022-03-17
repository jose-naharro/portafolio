<?php require "view/layouts/header.php"; ?>
<?php require "view/manager/nav.php"?>

<div class="container-fluid py-5 mx-auto container-dark">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-6 col-lg-7 col-md-8 col-10 text-center">
            <h3>Formulario de insercion de usuarios</h3>
            <p class="blue-text">
                Introduzca todos los datos
            </p>
            <div class="card text-white bg-dark">
                <form action="<?php echo urlsite ?>?page=1&section=insertar" enctype="multipart/form-data" method="POST" class="form-card">
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Nombre</label> 
                            <input type="text" id="u_name" name="u_name" value="" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Primer Apellido</label> 
                            <input type="text" id="u_surname1" name="u_surname1" value="" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Segundo Apellido</label> 
                            <input type="text" id="u_surname2" name="u_surname2" value="" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Dirección</label> 
                            <input type="text" id="u_address" name="u_address" value="" required>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Ciudad</label> 
                            <input type="text" id="u_city" name="u_city" value="" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">E-mail</label> 
                            <input type="text" id="u_email" name="u_email" value="" required>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Teléfono</label> 
                            <input type="text" id="u_phone" name="u_phone" value="" required pattern="[0-9]{9}">
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">F. Nacimiento</label> 
                            <input type="date" id="u_birth" name="u_birth" value="" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">DNI</label> 
                            <input type="text" id="u_dni" name="u_dni" value="" required pattern="[0-9]{7,8}[TRWAGMYFPDXBNJZSQVHLCKE]">
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white" for="u_rol">Rol</label>    
                            <select class="form-select form-select-lg" id="u_rol" name="u_rol"  required>
                                <option style="display:none">Elegir...</option>
                                <option value="1">Administrador</option>
                                <option value="2">Jefe de Instrucción</option>
                                <option value="3">Instructor</option>
                                <option value="4">Alumno Piloto</option>
                                <option value="5">Socio Piloto</option>
                            </select>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Horas de vuelo</label> 
                            <input type="number" id="u_hours" name="u_hours" value="0" step="0.1" min="0" required>
                        </div>
                        <div class="form-group col-sm-6" id="checkDiv">
                            <input type="hidden" class="checkActive" id="u_active" name="u_active" name="check[0]" value="0">
                            <input type="checkbox" class="checkActive" id="u_active" name="u_active" name="check[0]" value="1">
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="u_active">Activo</label> 
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group col-sm-6">
                            <button type="submit" class="btn-block btn-primary" value="Insertar" name="btnInsertar">
                            Insertar</button>
                            <button type="button" class="btn-block btn-secondary" onclick="history.go(-1)">Cancelar
                            </button>
                        </div>
                    </div>
                </form>
                <p class="text-danger"><?php echo (isset($_GET['msg'])) ? $_GET['msg'] :"" ?></p>
            </div>
        </div>
    </div>
</div>

<?php require "view/layouts/footer.php"; ?>