<?php require "view/layouts/header.php"; ?>
<?php require "view/member/nav.php"?>

<div class="container mt-3">
    <div class="row d-flex justify-content-center ">
        <div class="col-md-8">
            <div class="card bg-dark">
                <h2>Aeronaves</h2>   
                <div class="d-flex justify-content-between align-items-center">
                    <div class="mt-3 inputs">
                        <input type="text" class="form-control"  id="searchInputAero" onkeyup="filtroTablas('planelistTable','searchInputAero') "placeholder="Filtrar">
                    </div>
                </div>
                
                <div class="alert alert-success alert-dismissible fade show" style=<?php echo (isset($_GET['msg'])) ? "display:block;" : "" ?>>
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    <strong>Info!</strong> <?php echo (isset($_GET['msg'])) ? $_GET['msg'] :"" ?>
                </div>
            </div>
        </div>
    </div> 
    <table class="table table-dark table-striped" id="planelistTable">
        <thead>
        <tr class="tableHeader">
            <th class="order">#</th>
            <th class="order">Matrícula</th>
            <th class="order">Marca</th>
            <th class="order">Modelo</th>
            <th class="order">Categoría</th>
            <th class="order">Propietario</th>
            <th class="order">MTOW</th>
            <th class="order"></th>
        </tr>
        </thead>
        <tbody>
            <?php if (empty($listaAeronaves)) {?>
                <div class="alert alert-success alert-dismissible fade show" style="display:block;">
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    <strong>Info!</strong> No dispone de ninguna aeronave
                </div>
            <?php }else{
                foreach ($listaAeronaves as $index=>$aeronave):?>
                    <tr>
                        <th scope="row"><?php echo $index+1//$aeronave->plane_id?></th>
                        <td><?php echo $aeronave->plane_reg?></td>
                        <td><?php echo $aeronave->plane_brand?></td>
                        <td><?php echo $aeronave->plane_model?></td>
                        <td><?php echo $aeronave->plane_cat_name?></td>
                        <td><?php echo $aeronave->owner?></td>
                        <td><?php echo $aeronave->plane_mtow?></td>
                        <td>
                            <a href="<?php echo urlsite?>?page=5&section=verAeronave&id=<?php echo $aeronave->plane_id?>" class="btn btn-success">Ver</a>
                            <a href="<?php echo urlsite?>?page=5&section=formEditarAeronave&id=<?php echo $aeronave->plane_id?>" class="btn btn-primary">Editar</a>
                        </td>
                    </tr>
                <?php endforeach;
            }?>
        </tbody>
    </table>
</div>

<?php require "view/layouts/footer.php"; ?>