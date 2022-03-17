<?php require "view/layouts/header.php"; ?>
<?php require "view/member/nav.php"?>

<div class="container-fluid py-5 mx-auto container-dark">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-6 col-lg-7 col-md-8 col-10 text-center">
            <h3>Formulario de edición de aeronave</h3>
            <p class="blue-text">
                Introduzca todos los datos
            </p>
            <div class="card text-white bg-dark">
                <form action="<?php echo urlsite ?>?page=5&section=editarAeronave" enctype="multipart/form-data" method="POST" class="form-card">
                <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Matrícula</label> 
                            <input type="text" id="p_reg_e" name="p_reg_e" value="<?php echo $aeronave[0]->plane_reg ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Marca</label> 
                            <input type="text" id="p_brand_e" name="p_brand_e" value="<?php echo $aeronave[0]->plane_brand ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Modelo</label> 
                            <input type="text" id="p_model_e" name="p_model_e" value="<?php echo $aeronave[0]->plane_model ?>" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Año</label> 
                            <input type="number" id="p_year_e" name="p_year_e" value="<?php echo $aeronave[0]->plane_year ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Categoría</label> 
                            <select class="form-select form-select-lg" id="p_cat_e" name="p_cat_e"  required>
                                <option style="display:none">Elegir...</option>
                                <option value="1"<?php if($aeronave[0]->plane_cat == '1') echo" selected";?>>Drone</option>
                                <option value="2"<?php if($aeronave[0]->plane_cat == '2') echo" selected";?>>ULM</option>
                                <option value="3"<?php if($aeronave[0]->plane_cat == '3') echo" selected";?>>Ligera</option>
                                <option value="4"<?php if($aeronave[0]->plane_cat == '4') echo" selected";?>>Cat A</option>
                                <option value="5"<?php if($aeronave[0]->plane_cat == '5') echo" selected";?>>Cat B</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Horas</label> 
                            <input type="number" id="p_hours_e" name="p_hours_e" value="<?php echo $aeronave[0]->plane_hours ?>" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">ZFW</label> 
                            <input type="number" id="p_zfw_e" name="p_zfw_e" value="<?php echo $aeronave[0]->plane_zfw ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">MTOW</label> 
                            <input type="number" id="p_mtow_e" name="p_mtow_e" value="<?php echo $aeronave[0]->plane_mtow ?>" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Motores</label> 
                            <input type="number" id="p_motors_e" name="p_motors_e" value="<?php echo $aeronave[0]->plane_motors ?>" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Propietario</label> 
                            <select class="form-select form-select-lg" id="p_owner_e" name="p_owner_e">
                                <option style="display:none">Propietario...</option>
                                <?php
                                foreach ($listaMiembros as $propietario) {
                                    $selected = ($aeronave[0]->plane_owner == $propietario->user_id) ? "selected" : "";
                                    echo '<option '.$selected.' value="'.$propietario->user_id.'">'.$propietario->user_name." ".$propietario->user_surname1." ".$propietario->user_surname2.'</option>';}
                                ?>
                            </select>
                        </div>
                        <div class="form-group col-sm-6" id="checkDiv">
                            <input type="hidden" class="checkActive" id="p_rent_e_h" name="p_rent_e" value="0">
                            <input type="checkbox" class="checkActive" id="p_rent_e" name="p_rent_e" value="1"<?php  if ($aeronave[0]->plane_rent) : echo 'checked'; endif?>>
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="p_rent_e">Alquilable</label> 
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group col-sm-6">
                            <input type="hidden" name="p_id_e" value="<?php echo $aeronave[0]->plane_id ?>">
                            <button type="submit" class="btn-block btn-primary" value="Actualizar" name="btnActualizarAeronave">
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