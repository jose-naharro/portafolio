<?php require "view/layouts/header.php"; ?>
<?php require "view/manager/nav.php"?>

<div class="container mt-3">
    <div class="row d-flex justify-content-center ">
        <div class="col-md-8">
            <div class="card bg-dark">
                <h2>Usuarios</h2>   
                <div class="d-flex justify-content-between align-items-center">
                    <div class="d-flex flex-row">
                        <a href="<?php echo urlsite?>?page=1&section=formInsertar" class="btn btn-primary new"><i class="fa fa-plus"></i> Añadir</a>
                    </div>
                    <div class="mt-3 inputs">
                        <input type="text" class="form-control"  id="searchInput" onkeyup="filtroTablas('userlistTable','searchInput') "placeholder="Filtrar">
                    </div>
                </div>
                <div class="alert alert-success alert-dismissible fade show" style=<?php echo (isset($_GET['msg'])) ? "display:block;" : "" ?>>
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    <strong>Info!</strong> <?php echo (isset($_GET['msg'])) ? $_GET['msg'] :"" ?>
                </div>
            </div>
        </div>
    </div> 
    <table class="table table-dark table-striped" id="userlistTable">
        <thead>
        <tr class="tableHeader">
            <th class="order">#</th>
            <th class="order">Nombre</th>
            <th class="order">Apellido 1</th>
            <th class="order">Apellido 2</th>
            <th class="order">E-mail</th>
            <th class="order">Telefono</th>
            <th class="order">Rol</th>
            <th class="order"></th>
        </tr>
        </thead>
        <tbody>
            <?php if (empty($listaUsuarios)) {?>
                <div class="alert alert-success alert-dismissible fade show" style="display:block;">
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    <strong>Info!</strong> No dispone de ningún usuario
                </div>
            <?php }else{
                foreach ($listaUsuarios as $index=>$usuario):?>
                    <tr>
                        <th scope="row"><?php echo $index+1;//$usuario->user_id?></th>
                        <td><?php echo $usuario->user_name?></td>
                        <td><?php echo $usuario->user_surname1?></td>
                        <td><?php echo $usuario->user_surname2?></td>
                        <td><?php echo $usuario->user_email?></td>
                        <td><?php echo $usuario->user_phone?></td>
                        <td><?php echo $usuario->user_rol_name?></td>
                        <td>
                            <a href="<?php echo urlsite?>?page=1&section=ver&id=<?php echo $usuario->user_id?>" class="btn btn-success">Ver</a>
                            <a href="<?php echo urlsite?>?page=1&section=formEditar&id=<?php echo $usuario->user_id?>" class="btn btn-primary">Editar</a>
                            <a href="<?php echo urlsite ?>?page=1&section=eliminar&id=<?php echo $usuario->user_id?>" onclick="return confirm('¿Confirma el borrado de este usuario, Si o No?')" class="btn btn-danger">Borrar</a>
                        </td>
                    </tr>
                <?php endforeach; 
            } ?> 
        </tbody>
    </table>
</div>

<?php require "view/layouts/footer.php"; ?>