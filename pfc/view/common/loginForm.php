<?php require "view/layouts/header.php"; ?>
<?php require "view/layouts/head.php"; ?>

<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <?php 
                    if(isset($_GET['msgActivate'])){ ?>
                        <div class="alert alert-success" id="alertSuccess"><?php echo $_GET['msgActivate']; ?></div>
                <?php } else echo "";?>  
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                        <p class="text-white-50 mb-5">Introduzca su nombre de usuario y contraseña</p>
                        <form action="<?php echo urlsite?>?page=loginAuth" method="POST">
                            <div class="form-outline form-white mb-4">
                                <input type="email" id="txtemail" name="txtemail" class="form-control form-control-lg" placeholder="usuario@dominio.com" required>
                                <label class="form-label" for="txtemail">Email</label>
                            </div>
                            <div class="form-outline form-white mb-4">
                                <div class="input-group">
                                    <input type="password" id="txtpassword" name="txtpassword" class="form-control form-control-lg" placeholder="contraseña" required>
                                    <span class="input-group-text" id="showhidepass" onclick="passShowHide();">
                                        <i class="fas fa-eye" id="show"></i>
                                        <i class="fas fa-eye-slash d-none" id="hide"></i>
                                    </span>
                                </div>
                                <label class="form-label" for="txtpassword">Password</label>
                            </div>
                            <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="<?php echo urlsite?>?page=forgotPassword">Recordar contraseña</a></p>
                            <?php 
                                if(isset($_GET['msg'])){ ?>
                                    <div class="alert alert-danger" id="alertDanger"><?php echo $_GET['msg']; ?></div>
                            <?php } else echo "";?>  
                            <button class="btn btn-outline-light btn-lg px-5" type="submit" name="btnlogin">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<?php require "view/layouts/footer.php"; ?>
