<?php require "view/layouts/header.php"; ?>
<?php require "view/manager/nav.php"?>

<div class="container-fluid py-5 mx-auto container-dark">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-6 col-lg-7 col-md-8 col-10 text-center">
            <h3>Formulario de edición de usuario</h3>
            <p class="blue-text">
                Introduzca todos los datos
            </p>
            <div class="card text-white bg-dark">
                <form action="<?php echo urlsite ?>?page=1&section=editar" enctype="multipart/form-data" method="POST" class="form-card">
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Nombre</label> 
                            <input type="text" id="u_name_e" name="u_name_e" value="<?php echo $usuario[0]->user_name ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Primer Apellido</label> 
                            <input type="text" id="u_surname1_e" name="u_surname1_e" value="<?php echo $usuario[0]->user_surname1 ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Segundo Apellido</label> 
                            <input type="text" id="u_surname2_e" name="u_surname2_e" value="<?php echo $usuario[0]->user_surname2 ?>" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Dirección</label> 
                            <input type="text" id="u_address_e" name="u_address_e" value="<?php echo $usuario[0]->user_address ?>" required>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Ciudad</label> 
                            <input type="text" id="u_city_e" name="u_city_e" value="<?php echo $usuario[0]->user_city ?>" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">E-mail</label> 
                            <input type="email" id="u_email_e" name="u_email_e" value="<?php echo $usuario[0]->user_email ?>" required>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Teléfono</label> 
                            <input type="text" id="u_phone_e" name="u_phone_e" value="<?php echo $usuario[0]->user_phone ?>" required pattern="[0-9]{9}">
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">F. Nacimiento</label> 
                            <input type="date" id="u_birth_e" name="u_birth_e" value="<?php $fecha = $usuario[0]->user_birth; echo date('Y-m-d', strtotime($fecha))?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">DNI</label> 
                            <input type="text" id="u_dni_e" name="u_dni_e" value="<?php echo $usuario[0]->user_dni ?>" required pattern="[0-9]{7,8}[TRWAGMYFPDXBNJZSQVHLCKE]">
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white" for="u_rol">Rol</label>    
                            <select class="form-select form-select-lg" id="u_rol_e" name="u_rol_e"  required>
                                <option style="display:none">Elegir...</option>
                                <option value="1"<?php if($usuario[0]->user_rol == '1') echo" selected";?>>Manager</option>
                                <option value="2"<?php if($usuario[0]->user_rol == '2') echo" selected";?>>Jefe de Instructores</option>
                                <option value="3"<?php if($usuario[0]->user_rol == '3') echo" selected";?>>Instructor</option>
                                <option value="4"<?php if($usuario[0]->user_rol == '4') echo" selected";?>>Alumno</option>
                                <option value="5"<?php if($usuario[0]->user_rol == '5') echo" selected";?>>Socio</option>
                            </select>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Horas de vuelo</label> 
                            <input type="number" id="u_hours_e" name="u_hours_e" value="<?php echo $usuario[0]->user_hours ?>" min="0" step="0.1" required>
                        </div>
                        <div class="form-group col-sm-6" id="checkDiv">
                            <input type="hidden" class="checkActive" id="u_active_e_h" name="u_active_e" value="0" />
                            <input type="checkbox" class="checkActive" id="u_active_e" name="u_active_e" value="1"<?php  if ($usuario[0]->user_active) : echo 'checked'; endif?>>
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="u_active_e">Activo</label> 
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group col-sm-6">
                            <input type="hidden" name="u_id_e" value="<?php echo $usuario[0]->user_id ?>">
                            <button type="submit" class="btn-block btn-primary" value="Actualizar" name="btnActualizar">
                            Actualizar</button>
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