<?php require "view/layouts/header.php"; ?>
<?php require "view/layouts/head.php"; ?>

<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="alert alert-danger" style=<?php echo (isset($_GET['msg'])) ? "display:block;" : "" ?>>
                    <strong>Atención!</strong> <?php echo (isset($_GET['msg'])) ? $_GET['msg'] :"" ?>
                </div> 
            </div>
        </div>
    </div>
</section>

<?php require "view/layouts/footer.php"; ?>
