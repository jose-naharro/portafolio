<?php require "view/layouts/header.php"; ?>
<?php require "view/manager/nav.php"?>

<div class="container-fluid py-5 mx-auto container-dark">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-6 col-lg-7 col-md-8 col-10 text-center">
            <h3>Formulario de insercion de aeronaves</h3>
            <p class="blue-text">
                Introduzca todos los datos
            </p>
            <div class="card text-white bg-dark">
                <form action="<?php echo urlsite ?>?page=1&section=insertarAeronave" enctype="multipart/form-data" method="POST" class="form-card">
                <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Matrícula</label> 
                            <input type="text" id="p_reg" name="p_reg" value="" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Marca</label> 
                            <input type="text" id="p_brand" name="p_brand" value="" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Modelo</label> 
                            <input type="text" id="p_model" name="p_model" value="" min="1900" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Año</label> 
                            <input type="number" id="p_year" name="p_year" value="" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white" for="p_cat">Categoría</label>    
                            <select class="form-select form-select-lg" id="p_cat" name="p_cat"  required>
                                <option style="display:none">Elegir...</option>
                                <option value="1">Drone</option>
                                <option value="2">ULM</option>
                                <option value="3">Ligera</option>
                                <option value="4">Cat A</option>
                                <option value="5">Cat B</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Horas</label> 
                            <input type="number" id="p_hours" name="p_hours" value="" min="0" step="0.1" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">ZFW</label> 
                            <input type="number" id="p_zfw" name="p_zfw" value="" min="0" step="0.1" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">MTOW</label> 
                            <input type="number" id="p_mtow" name="p_mtow" value="" min="0" step="0.1" required>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Motores</label> 
                            <input type="number" id="p_motors" name="p_motors" value="" min="0" required>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Propietario</label> 
                            <select class="form-select form-select-lg" id="p_owner" name="p_owner"  required>
                                <option style="display:none">Propietario...</option>
                                <?php foreach ($listaMiembros as $propietario):?>
                                    <option value="<?php echo $propietario->user_id?>"><?php echo $propietario->user_name." ".$propietario->user_surname1." ".$propietario->user_surname2?></option>
                                <?php endforeach; ?>
                            </select> 
                        </div>
                        <div class="form-group col-sm-6" id="checkDiv">
                            <input type="hidden" class="checkActive" id="p_rent" name="p_rent" name="check[0]" value="0" />
                            <input type="checkbox" class="checkActive" id="p_rent" name="p_rent" name="check[0]" value="1" />
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="p_rent">Alquilable</label> 
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