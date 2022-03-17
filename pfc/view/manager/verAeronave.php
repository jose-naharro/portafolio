<?php require "view/layouts/header.php"; ?>
<?php require "view/manager/nav.php"?>

<div class="container-fluid py-5 mx-auto container-dark">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-6 col-lg-7 col-md-8 col-10 text-center">
            <h3>Detalles de la:</h3>
            <p class="blue-text">
                <?php 
                    echo $aeronave[0]->plane_reg." => ". $aeronave[0]->plane_brand ." ". $aeronave[0]->plane_model;
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
                            <label class="form-control-label px-3 text-white">Matrícula</label> 
                            <input type="text" id="p_reg" name="p_reg" value="<?php echo $aeronave[0]->plane_reg ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Marca</label> 
                            <input type="text" id="p_brand" name="p_brand" value="<?php echo $aeronave[0]->plane_brand ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Modelo</label> 
                            <input type="text" id="p_model" name="p_model" value="<?php echo $aeronave[0]->plane_model ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Año</label> 
                            <input type="number" id="p_year" name="p_year" value="<?php echo $aeronave[0]->plane_year ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Categoría</label> 
                            <input type="text" id="p_cat" name="p_cat" value="<?php echo $aeronave[0]->plane_cat_name ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Horas</label> 
                            <input type="number" id="p_hours" name="p_hours" value="<?php echo $aeronave[0]->plane_hours ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">ZFW</label> 
                            <input type="number" id="p_zfw" name="p_zfw" value="<?php echo $aeronave[0]->plane_zfw ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">MTOW</label> 
                            <input type="number" id="p_mtow" name="p_mtow" value="<?php echo $aeronave[0]->plane_mtow ?>" disabled>
                        </div>
                        <div class="form-group col-sm-4 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Motores</label> 
                            <input type="number" id="p_motors" name="p_motors" value="<?php echo $aeronave[0]->plane_motors ?>" disabled>
                        </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label class="form-control-label px-3 text-white">Propietario</label> 
                            <input type="test" id="p_owner" name="p_owner" value="<?php echo $aeronave[0]->owner ?>" disabled>
                        </div>
                        <div class="form-group col-sm-6" id="checkDiv">
                            <input type="checkbox" class="checkActive" id="p_rent" name="p_rent" value="<?php echo $aeronave[0]->plane_rent ?>"<?php  if ($aeronave[0]->plane_rent) : echo 'checked'; endif?> disabled>
                            <label class="form-control-label px-3 text-white" id="checkLabel" for="p_rent">Alquilable</label> 
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group col-sm-6">
                            <a href="<?php echo urlsite?>?page=1&section=listaAeronaves" class="btn btn-primary">Volver</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<?php require "view/layouts/footer.php"; ?>