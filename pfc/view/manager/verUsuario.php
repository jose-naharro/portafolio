<?php require "view/layouts/header.php"; ?>
<?php require "view/manager/nav.php"?>

<div class="container-fluid py-5 mx-auto container-dark">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-6 col-lg-7 col-md-8 col-10 text-center">
            <h3>Detalles del usuario:</h3>
            <p class="blue-text">
                <?php 
                    echo $usuario[0]->user_name." ". $usuario[0]->user_surname1 ." ". $usuario[0]->user_surname2;
                 ?>
            </p>
            <div class="alert alert-success alert-dismissible fade show" style=<?php echo (isset($_GET['msg'])) ? "display:block;" : "" ?>>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                <strong>Info!</strong> <?php echo (isset($_GET['msg'])) ? $_GET['msg'] :"" ?>
            </div>
            <div class="card text-white bg-dark">
                <form class="form-card" onsubmit="event.preventDefault()">
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Nombre</label> 
                            <input type="text" id="u_name" name="u_name" value="<?php echo $usuario[0]->user_name ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Primer Apellido</label> 
                            <input type="text" id="u_surname1" name="u_surname1" value="<?php echo $usuario[0]->user_surname1 ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Segundo Apellido</label> 
                            <input type="text" id="u_surname2" name="u_surname2" value="<?php echo $usuario[0]->user_surname2 ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Dirección</label> 
                            <input type="text" id="u_address" name="u_address" value="<?php echo $usuario[0]->user_address ?>" disabled>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Ciudad</label> 
                            <input type="text" id="u_city" name="u_city" value="<?php echo $usuario[0]->user_city ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">E-mail</label> 
                            <input type="text" id="u_email" name="u_email" value="<?php echo $usuario[0]->user_email ?>" disabled>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Teléfono</label> 
                            <input type="text" id="u_phone" name="u_phone" value="<?php echo $usuario[0]->user_phone ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">F. Nacimiento</label> 
                            <input type="text" id="u_birth" name="u_birth" value="<?php $orig = $usuario[0]->user_birth; $date = date_create_from_format('Y-m-d', $orig); echo date_format($date, 'd-m-Y');?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">DNI</label> 
                            <input type="text" id="u_dni" name="u_dni" value="<?php echo $usuario[0]->user_dni ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Rol</label> 
                            <input type="text" id="u_rol" name="u_rol" value="<?php echo $usuario[0]->user_rol_name ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Horas de vuelo</label> 
                            <input type="text" id="u_hours" name="label" value="<?php echo $usuario[0]->user_hours ?>" step="0.1" disabled>
                        </div>
                        <div class="form-group col-sm-6" id="checkDiv">
                            <input type="checkbox" class="checkActive" id="u_active" name="u_active" value="<?php echo $usuario[0]->user_active ?>"<?php  if ($usuario[0]->user_active) : echo 'checked'; endif?> disabled>
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="u_active">Activo</label> 
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group col-sm-6">
                            <a href="<?php echo urlsite?>?page=1&section=listaUsuarios" class="btn btn-primary">Volver</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<?php require "view/layouts/footer.php"; ?>