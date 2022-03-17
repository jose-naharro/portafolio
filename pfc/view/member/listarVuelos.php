<?php require "view/layouts/header.php"; ?>
<?php require "view/member/nav.php"?>

<div class="container mt-3">
    <div class="row d-flex justify-content-center ">
        <div class="col-md-8">
            <div class="card bg-dark">
                <h2>Vuelos</h2>   
                <div class="d-flex justify-content-between align-items-center">
                    <div class="d-flex flex-row">
                        <a href="<?php echo urlsite?>?page=5&section=formInsertarVuelo" class="btn btn-primary new"><i class="fa fa-plus"></i> Añadir</a>
                    </div>
                    <div class="mt-3 inputs">
                        <input type="text" class="form-control"  id="searchInputVuelo" onkeyup="filtroTablas('flightlistTable','searchInputVuelo') "placeholder="Filtrar">
                    </div>
                </div>
                
                <div class="alert alert-success alert-dismissible fade show" style=<?php echo (isset($_GET['msg'])) ? "display:block;" : "" ?>>
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    <strong>Info!</strong> <?php echo (isset($_GET['msg'])) ? $_GET['msg'] :"" ?>
                </div>
            </div>
        </div>
    </div> 
    <table class="table table-dark table-striped" id="flightlistTable">
        <thead>
        <tr class="tableHeader">
            <th class="order">#</th>
            <th class="order">Fecha</th>
            <th class="order">Aeronave</th>
            <th class="order">Piloto</th>
            <th class="order">Salida</th>
            <th class="order">Llegada</th>
            <th class="order">Finalizado</th>
            <th class="order"></th>
        </tr>
        </thead>
        <tbody>
            <?php if (empty($listaVuelos)) {?>
                <div class="alert alert-success alert-dismissible fade show" style="display:block;">
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    <strong>Info!</strong> No dispone de ningún vuelo
                </div>
            <?php }else{
                foreach ($listaVuelos as $index=>$vuelo):?>
                    <tr>
                        <th scope="row"><?php echo $index+1;//$vuelo->flight_id?></th>
                        <td><?php $orig = $vuelo->flight_day; $date = date_create_from_format('Y-m-d', $orig); echo date_format($date, 'd-m-Y');?></td>
                        <td><?php echo $vuelo->plane_reg." => ". $vuelo->plane_brand ." ". $vuelo->plane_model?></td>
                        <td><?php echo $vuelo->pic ?></td>
                        <td><?php echo $vuelo->flight_dep?></td>
                        <td><?php echo $vuelo->flight_arr?></td>
                        <td><?php echo ($vuelo->flight_finish) ? 'Si' : 'No'?></td>
                        <td>
                            <a href="<?php echo urlsite?>?page=5&section=verVuelo&id=<?php echo $vuelo->flight_id?>" class="btn btn-success">Ver</a>
                            <a href="<?php echo urlsite?>?page=5&section=formEditarVuelo&id=<?php echo $vuelo->flight_id?>" class="btn btn-primary">Editar</a>
                            <a href="<?php echo urlsite ?>?page=5&section=eliminarVuelo&id=<?php echo $vuelo->flight_id?>" onclick="return confirm('¿Confirma el borrado de este vuelo, Si o No?')" class="btn btn-danger">Borrar</a>
                        </td>
                    </tr>
                <?php endforeach; 
            }?>
        </tbody>
    </table>
</div>

<?php require "view/layouts/footer.php"; ?>