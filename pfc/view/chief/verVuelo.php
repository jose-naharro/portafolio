<?php require "view/layouts/header.php"; ?>
<?php require "view/chief/nav.php"?>

<div class="container-fluid py-5 mx-auto container-dark">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-6 col-lg-7 col-md-8 col-10 text-center">
            <h3>Detalles del vuelo:</h3>
            <p class="blue-text">
                <?php 
                    $orig = $vuelo[0]->flight_day; $date = date_create_from_format('Y-m-d', $orig);
                    echo date_format($date, 'd-m-Y')." => From: ". $vuelo[0]->flight_dep ." - To: ". $vuelo[0]->flight_arr;
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
                            <label class="form-control-label px-3 text-white">Fecha</label> 
                            <input type="date" id="f_date" name="f_date" value="<?php $fecha = $vuelo[0]->flight_day; echo date('Y-m-d', strtotime($fecha))?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Hora Salida</label> 
                            <input type="time" id="f_hdep" name="f_hdep" value="<?php echo $vuelo[0]->flight_hdep ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Hora LLegada</label> 
                            <input type="time" id="f_harr" name="f_harr" value="<?php echo $vuelo[0]->flight_harr ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Matrícula</label> 
                            <input type="text" id="f_reg" name="f_reg" value="<?php echo $vuelo[0]->plane_reg ." => ".$vuelo[0]->plane_brand." ".$vuelo[0]->plane_model ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Salida</label> 
                            <input type="text" id="f_dep" name="f_dep" value="<?php echo $vuelo[0]->flight_dep ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">LLegada</label> 
                            <input type="text" id="f_arr" name="f_arr" value="<?php echo $vuelo[0]->flight_arr ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Piloto</label> 
                            <input type="text" id="f_pic" name="f_pic" value="<?php echo $vuelo[0]->pic ?>" disabled>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Instructor</label> 
                            <input type="text" id="f_inst" name="f_inst" value="<?php echo $vuelo[0]->inst ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Aterrizajes día</label> 
                            <input type="number" id="f_land_d" name="f_land_d" value="<?php echo $vuelo[0]->flight_land_d ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Aterrizajes Noche</label> 
                            <input type="number" id="f_land_n" name="f_land_n" value="<?php echo $vuelo[0]->flight_land_n ?>" disabled>
                        </div>
                        <div class="form-group col-sm-3" id="checkDiv">
                            <input type="checkbox" class="checkActive" id="f_train" name="f_train" value="<?php echo $vuelo[0]->flight_isTrain ?>"<?php  if ($vuelo[0]->flight_isTrain) : echo 'checked'; endif?> disabled>
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="f_train">Instrucción</label> 
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-12 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Ruta</label> 
                            <input type="text" id="f_route" name="f_route" value="<?php echo $vuelo[0]->flight_route ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-9 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Notas</label> 
                            <input type="text" id="f_notes" name="f_notes" value="<?php echo $vuelo[0]->flight_notes ?>" disabled>
                        </div>
                        <div class="form-group col-sm-3" id="checkDiv">
                            <input type="checkbox" class="checkActive" id="f_finish" name="f_finish" value="<?php echo $vuelo[0]->flight_finish ?>"<?php  if ($vuelo[0]->flight_finish) : echo 'checked'; endif?> disabled>
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="f_finish">Finalizado</label> 
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group col-sm-6">
                            <a href="<?php echo urlsite?>?page=2&section=listarVuelos" class="btn btn-primary">Volver</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<?php require "view/layouts/footer.php"; ?>