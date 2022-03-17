<?php require "view/layouts/header.php"; ?>
<?php require "view/layouts/head.php"; ?>

<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <?php 
                    if(isset($_GET['msg'])){ ?>
                        <div class="alert alert-danger" id="alertDanger"><?php echo $_GET['msg']; ?></div>
                <?php } else echo "";?>  
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <div class="mb-md-5 mt-md-4 pb-5">
                            <h2 class="fw-bold mb-2 text-uppercase">Restablecer contraseña</h2>
                            <p class="text-white-50 mb-5">Introduzca el email con el que está registrado en Flight'em Book and Log.</p>
                            <form action="<?php echo urlsite?>?page=activation&section=mandarRecordatorio" enctype="multipart/form-data" method="POST" onkeyup="checkPassword(); return false;">
                                <div class="form-outline form-white mb-4">
                                    <input type="email" id="emailRecover" name="emailRecover" class="form-control form-control-lg" required>
                                    <label class="form-label" for="emailRecover">E-mail</label>
                                </div>
                                <p class="text-danger small mb-5 pb-lg-2"><?php echo (isset($_GET['msg'])) ? $_GET['msg'] : "" ?></p>
                                <button class="btn btn-outline-light btn-lg px-5" type="submit" name="btnEnviarMail" id="btnEnviarMail">Enviar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<?php require "view/layouts/footer.php"; ?>
