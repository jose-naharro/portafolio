<?php require "view/layouts/header.php"; ?>
<?php require "view/member/nav.php"?>

<div class="container-fluid py-5 mx-auto container-dark">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-6 col-lg-7 col-md-8 col-10 text-center">
            <h3>Formulario de inserción de Vuelos</h3>
            <p class="blue-text">
                Introduzca los datos del vuelo
            </p>
            <div class="card text-white bg-dark">
                <form action="<?php echo urlsite ?>?page=5&section=insertarVuelo" enctype="multipart/form-data" method="POST" class="form-card">
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Fecha</label> 
                            <input type="date" id="f_day" name="f_day" value="" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Hora Salida</label> 
                            <input type="time" id="f_hdep" name="f_hdep" value="" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Hora LLegada</label> 
                            <input type="time" id="f_harr" name="f_harr" value="" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Aeronave</label> 
                            <select class="form-select form-select-lg" id="f_reg" name="f_reg"  required>
                                <option style="display:none">Aeronave...</option>
                                <?php foreach ($listaAeronaves as $aeronave):?>
                                    <option value="<?php echo $aeronave->plane_id?>"><?php echo $aeronave->plane_reg." => ".$aeronave->plane_brand." ".$aeronave->plane_model?></option>
                                <?php endforeach; ?>
                            </select> 
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Salida</label> 
                            <input type="text" id="f_dep" name="f_dep" value="" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">LLegada</label> 
                            <input type="text" id="f_arr" name="f_arr" value="" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Piloto</label> 
                            <input type="text" id="f_pic_d" name="f_pic_d" value="<?php echo  $_SESSION['userName'] ?>" disabled>
                            <input type="number" id="f_pic" name="f_pic" value="<?php echo $_SESSION['userID'] ?>" hidden>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Instructor</label> 
                            <input type="text" id="f_inst_d" name="f_inst_d" value="<?php echo  "No es un vuelo de instrucción" ?>" disabled> 
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Aterrizajes día</label> 
                            <input type="number" id="f_land_d" name="f_land_d" value="" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Aterrizajes Noche</label> 
                            <input type="number" id="f_land_n" name="f_land_n" value="" disabled>
                        </div>
                        <div class="form-group col-sm-3" id="checkDiv">
                            <input type="hidden" class="checkActive" id="f_train" name="f_train" name="check[0]" value="0">
                            <input type="checkbox" class="checkActive" id="f_train" name="f_train" name="check[0]" value="1" disabled>
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="f_train">Instrucción</label> 
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-12 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Ruta</label> 
                            <input type="text" id="f_route" name="f_route" value="" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-9 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Notas</label> 
                            <input type="text" id="f_notes" name="f_notes" value="" required>
                        </div>
                        <div class="form-group col-sm-3" id="checkDiv">
                            <input type="hidden" class="checkActive" id="f_finish" name="f_finish" name="check[0]" value="0" disabled>
                            <input type="checkbox" class="checkActive" id="f_finish" name="f_finish" name="check[0]" value="1" disabled>
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="f_finish">Finalizado</label> 
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