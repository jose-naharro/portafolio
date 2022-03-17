<?php require "view/layouts/header.php"; ?>
<?php require "view/layouts/head.php"; ?>

<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col-6">
                <div class="alert alert-success" style=<?php echo (isset($_GET['msg'])) ? "display:block;" : "" ?>>
                    <strong>Éxito!</strong> <?php echo (isset($_GET['msg'])) ? $_GET['msg'] :"" ?>
                </div>
                <p>Será redirigido a la página principal en <span id="countdown">10</span> segundos.</p> 
            </div>
        </div>
    </div>
</section>

<?php require "view/layouts/footer.php"; ?>
