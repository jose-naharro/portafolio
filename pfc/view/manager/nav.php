<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <ul class="navbar-nav">    
            <li class="nav-item">    
                <a class="navbar-brand" href="<?php echo urlsite?>?page=1">
                    <img src="<?php echo urlsite?>/assets/images/logo.png" width="180" height="100" alt="Flight'em">
                </a>
            </li>
        </ul>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<?php echo urlsite?>?page=1">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<?php echo urlsite ?>?page=1&section=listaUsuarios">Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<?php echo urlsite ?>?page=1&section=listaAeronaves">Aeronaves</a>
                </li>
            </ul>
            <div class="linkUsuario">
                <a href="<?php echo urlsite ?>?page=1&section=detallesUsuario">
                    <span class="navbar-text" id="navUserName">
                        <?php echo 'Hola, '.$_SESSION['userName']?>
                    </span>
                </a>
            </div>
            <a href="<?php echo urlsite ?>?page=logout" class="btn btn-outline-light">Salir</a>
        </div>
    </div>
</nav>