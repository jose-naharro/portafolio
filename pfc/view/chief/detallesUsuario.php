<?php require "view/layouts/header.php"; ?>
<?php require "view/chief/nav.php"?>

<div class="container-fluid py-5 mx-auto container-dark">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-6 col-lg-7 col-md-8 col-10 text-center">
            <h3>Detalles de tu cuenta</h3>
            <p class="blue-text">
                Introduzca los datos que desee actualizar
            </p>
            <div class="alert alert-info alert-dismissible fade show" style=<?php echo (isset($_GET['msg'])) ? "display:block;" : "" ?>>
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    <strong>Info!</strong> <?php echo (isset($_GET['msg'])) ? $_GET['msg'] :"" ?>
            </div>
            <div class="card text-white bg-dark">
                <form action="<?php echo urlsite ?>?page=2&section=editarDetalles" enctype="multipart/form-data" method="POST" class="form-card">
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Nombre</label> 
                            <input type="text" id="u_name_e" name="u_name_e" value="<?php echo $usuario[0]->user_name ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Primer Apellido</label> 
                            <input type="text" id="u_surname1_e" name="u_surname1_e" value="<?php echo $usuario[0]->user_surname1 ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Segundo Apellido</label> 
                            <input type="text" id="u_surname2_e_h" name="u_surname2_e" value="<?php echo $usuario[0]->user_surname2 ?>" disabled>
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
                            <input type="text" value="<?php echo $usuario[0]->user_email ?>" disabled>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Teléfono</label> 
                            <input type="text" id="u_phone_e" name="u_phone_e" value="<?php echo $usuario[0]->user_phone ?>" required pattern="[0-9]{9}">
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">F. Nacimiento</label> 
                            <input type="date" id="u_birth_e" name="u_birth_e" value="<?php $fecha = $usuario[0]->user_birth; echo date('Y-m-d', strtotime($fecha))?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">DNI</label> 
                            <input type="text" id="u_dni_e" name="u_dni_e" value="<?php echo $usuario[0]->user_dni ?>" disabled pattern="[0-9]{7,8}[TRWAGMYFPDXBNJZSQVHLCKE]">
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white" for="u_rol">Rol</label>    
                            <input type="text" value="<?php echo $usuario[0]->user_rol_name ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Horas de vuelo</label> 
                            <input type="number" id="u_hours_e" name="u_hours_e" value="<?php echo $usuario[0]->user_hours ?>" min="0" step="0.1" disabled>
                        </div>
                        <div class="form-group col-sm-6" id="cambioPass">
                            <a class="btn btn-success" href="<?php echo urlsite ?>?page=2&section=changePassword" onclick="return confirm('Va a cerrar sesión y abandonar la página para poder cambiar la contraseña de manera segura, se perderán los cambios no guardados ¿desea continuar?')">Cambiar Contraseña</a>
                            <label class="form-control-label px-3 text-white">Cambio seguro de contraseña</label> 
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group col-sm-6">
                            <input type="hidden" name="u_id_e" value="<?php echo $usuario[0]->user_id ?>">
                            <button type="submit" class="btn-block btn-primary" value="Actualizar" name="btnActualizar">
                            Guardar</button>
                            <button type="button" class="btn-block btn-secondary" onclick="location.href='<?php echo urlsite ?>?page=2'">Cancelar
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<?php require "view/layouts/footer.php"; ?>