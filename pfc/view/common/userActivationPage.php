<?php require "view/layouts/header.php"; ?>
<?php require "view/layouts/head.php"; ?>

<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <div class="mb-md-5 mt-md-4 pb-5">
                            <h2 class="fw-bold mb-2 text-uppercase">Activación de Usuario</h2>
                            <p class="text-white-50 mb-5">Introduzca una contraseña para activar su cuenta.</p>
                            <form action="<?php echo urlsite?>?page=activation&section=activation" enctype="multipart/form-data" method="POST" onkeyup="checkPassword(); return false;">
                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="activationpass1" name="activationpass1" class="form-control form-control-lg" required>
                                    <label class="form-label" for="activationpass1">Nueva contraseña</label>
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="activationpass2" name="activationpass2" class="form-control form-control-lg" required>
                                    <label class="form-label" for="activationpass2">Confirme la contraseña</label>
                                </div>
                                <div id="error" class="alert alert-danger ocultar" role="alert">
                                    Las contraseñas no coinciden.
                                </div>
                                <div id="ok" class="alert alert-success ocultar" role="alert">
                                    Las contraseñas coinciden.</br> Puede realizar la activación.
                                </div>
                                <p class="text-danger small mb-5 pb-lg-2"><?php echo (isset($_GET['msg'])) ? $_GET['msg'] : "" ?></p>
                                <button class="btn btn-outline-light btn-lg px-5" type="submit" name="btnEnviarActivation" id="btnEnviarActivation" disabled>Enviar</button>
                                <input type="hidden" id="token" name="token" value="<?php echo (isset($_GET['token'])) ? $_GET['token'] : "" ?>">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<?php require "view/layouts/footer.php"; ?>
