<?php require "view/layouts/header.php"; ?>
<?php require "view/member/nav.php"?>

<div class="container-fluid py-5 mx-auto container-dark">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-6 col-lg-7 col-md-8 col-10 text-center">
            <h3>Formulario de edición de Vuelos</h3>
            <p class="blue-text">
                Introduzca los datos del vuelo
            </p>
            <div class="card text-white bg-dark">
                <form action="<?php echo urlsite ?>?page=5&section=editarVuelo" enctype="multipart/form-data" method="POST" class="form-card">
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Fecha</label> 
                            <input type="date" id="f_day_e" name="f_day_e" value="<?php $fecha = $vuelo[0]->flight_day; echo date('Y-m-d', strtotime($fecha)) ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Hora Salida</label> 
                            <input type="time" id="f_hdep_e" name="f_hdep_e" value="<?php echo $vuelo[0]->flight_hdep ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Hora LLegada</label> 
                            <input type="time" id="f_harr_e" name="f_harr_e" value="<?php echo $vuelo[0]->flight_harr ?>" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Aeronave</label> 
                            <select class="form-select form-select-lg" id="f_reg_e" name="f_reg_e">
                                <option style="display:none">Aeronave...</option>
                                <?php
                                foreach ($listaAeronaves as $aeronave) {
                                    $selected = ($vuelo[0]->flight_p_reg == $aeronave->plane_id) ? "selected" : "";
                                    echo '<option '.$selected.' value="'.$aeronave->plane_id.'">'.$aeronave->plane_reg.' => '.$aeronave->plane_brand.' '.$aeronave->plane_model.'</option>';}
                                ?>
                            </select>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Salida</label> 
                            <input type="text" id="f_dep_e" name="f_dep_e" value="<?php echo $vuelo[0]->flight_dep ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">LLegada</label> 
                            <input type="text" id="f_arr_e" name="f_arr_e" value="<?php echo $vuelo[0]->flight_arr ?>" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Piloto</label> 
                            <select class="form-select form-select-lg" id="f_pic_ee" name="f_pic_ee"  disabled>
                                <option value="<?php $_SESSION['userID'] ?>"><?php echo $_SESSION['userName'] ?></option>
                            </select>
                            <select class="form-select form-select-lg" id="f_pic_e" name="f_pic_e"  hidden>
                                <option value="<?php $_SESSION['userID'] ?>"><?php echo $_SESSION['userName'] ?></option>
                            </select>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Instructor</label> 
                            <input type="text" id="f_inst_e" name="f_inst_e" value="<?php echo  "No es un vuelo de instrucción" ?>" disabled> 
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Aterrizajes día</label> 
                            <input type="number" id="f_land_d_e" name="f_land_d_e" value="<?php echo $vuelo[0]->flight_land_d ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Aterrizajes Noche</label> 
                            <input type="number" id="f_land_n_e" name="f_land_n_e" value="<?php echo $vuelo[0]->flight_land_n ?>" required>
                        </div>
                        <div class="form-group col-sm-3" id="checkDiv">
                            <input type="checkbox" class="checkActive" id="f_train_e" name="f_train_e" name="f_train_ee" value="<?php echo $vuelo[0]->flight_isTrain ?>"<?php  if ($vuelo[0]->flight_isTrain) : echo " checked"; endif?> disabled>
                            <input type="checkbox" class="checkActive" id="f_train_e" name="f_train_e" name="f_train_e" value="0" hidden>
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="f_train_e">Instrucción</label> 
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-12 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Ruta</label> 
                            <input type="text" id="f_route_e" name="f_route_e" value="<?php echo $vuelo[0]->flight_route ?>" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-9 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Notas</label> 
                            <input type="text" id="f_notes_e" name="f_notes_e" value="<?php echo $vuelo[0]->flight_notes ?>" required>
                        </div>
                        <div class="form-group col-sm-3" id="checkDiv">
                            <input type="hidden" class="checkActive" id="f_finish_e_h" name="f_finish_e" value="0" />
                            <input type="checkbox" class="checkActive" id="f_finish_e" name="f_finish_e" value="1"<?php  if ($vuelo[0]->flight_finish) : echo 'checked'; endif?>>
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="f_finish_e">Finalizado</label> 
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group col-sm-6">
                            <input type="hidden" name="f_id_e" value="<?php echo $vuelo[0]->flight_id ?>">
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