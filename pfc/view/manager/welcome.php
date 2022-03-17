<?php require "view/layouts/header.php";?>
<?php require "view/manager/nav.php"?>

<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5 text-center">
               <h4>Página principal de</h4>
               <h2>Administración</h2>
               <div class="row">
                  <div class="col-sm-6 text-center">
                     <div class="card bg-dark d-flex justify-content-center text-center">
                        <div class="card-body d-flex justify-content-center">
                           <i class="fa-solid fa-users fa-9x"></i>
                        </div>
                        <div class="card-body">
                           <a href="<?php echo urlsite ?>?page=1&section=listaUsuarios" class="btn btn-primary">Usuarios</a>
                        </div>
                     </div>
                  </div>
                  <div class="col-sm-6 text-center">
                     <div class="card bg-dark d-flex justify-content-center text-center">
                        <div class="card-body d-flex justify-content-center">
                           <i class="fa-solid fa-plane fa-9x"></i>
                        </div>
                        <div class="card-body">
                           <a href="<?php echo urlsite ?>?page=1&section=listaAeronaves" class="btn btn-primary">Aeronaves</a>
                        </div>
                     </div>
                  </div>
               </div>
         </div>
      </div>
   </div>
</section>

<?php require "view/layouts/footer.php";?>