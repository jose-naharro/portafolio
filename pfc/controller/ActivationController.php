<?php
session_start();

require "model/Activation.php";
require "model/Usuarios.php";

class ActivationController{

    public static function index(){
        if(isset($_SESSION['login']))
            header('location:'.urlsite."?page=login");
        require "view/common/userActivationPage.php";
    }

    public static function errorActivation(){
        if(isset($_SESSION['login']))
            header('location:'.urlsite."?page=login");
        require "view/common/errorPage.php";
    }
    
    public static function activation(){
        // cogemos el token (no se si hay que desencriptarlo, habria que verlo en insertar de Usuarios)
        $token = $_POST['token'];
        // cogemos los dos password que ha metido
        $pass1 = MD5($_POST['activationpass1']);
        // buscamos el usuario al que corresponde por token //require 'model/Usuarios.php';
        $usuario = new Usuarios();
        $datosUsuario = $usuario->listarSimple("SELECT * FROM fbl_users WHERE user_token= '".$token."'");
        // si existe el usuario
        if($datosUsuario[0]){
            // creamos una un Activation model y llamamos a activar($datos, $passwordnuevo)
            $activacion = new Activation();
            $activacionHecha = $activacion->activar($datosUsuario[0], $pass1);
            // si activar es true  
            if ($activacionHecha) 
                header('location:'.urlsite."?page=login&msgActivate=La activacion ha sido correcta, puede autenticarse.");
            else
                header('location:'.urlsite."?page=activation&section=errorActivation&msg=Algo ha fallado, no se ha podido activar la cuenta.");
        } else {
            header('location:'.urlsite."?page=activation&section=errorActivation&msg=Algo ha fallado, no se ha podido activar la cuenta.");

        }
    }

    public static function forgotten(){
        if(isset($_SESSION['login']))
            header('location:'.urlsite."?page=login");
        require "view/common/forgottenPage.php";
    }

    public static function success(){
        if(isset($_SESSION['login']))
            header('location:'.urlsite."?page=login");
        require "view/common/successPage.php";
    }

    public static function mandarRecordatorio(){
        $email = $_POST['emailRecover'];
        // buscamos el usuario al que corresponde el email
        $usuario = new Usuarios();
        $datosUsuario = $usuario->listarSimple("SELECT * FROM fbl_users WHERE user_email= '".$email."'");
        // si existe el usuario
        if($datosUsuario[0]){
            // creamos un token y un expiration
            $token = bin2hex(random_bytes(32));
            $now = date('Y-m-d H:i:s');
            $timestamp = strtotime($now) + 60*30;
            $expires = date('Y-m-d H:i:s', $timestamp);

            $datosUsuario[0]->user_token = $token;
            $datosUsuario[0]->user_expires = $expires;

            $usuario->__construct();
            $usuarioActualizado = $usuario->actualizarForggoten($token, $expires, $email);
            $usuario->__construct();
            $mailEnviado = $usuario->mailRecuperacion($token, $email);

            // si activar es true  
            if ($usuarioActualizado && $mailEnviado) 
                header('location:'.urlsite."?page=forgotPassword&section=success&msg=Se a enviado un email para recuperar su contraseña a ". $email);
            else
                header('location:'.urlsite."?page=forgotPassword&msg=Algo ha fallado, el email introducido no está registrado.");
        } else {
            header('location:'.urlsite."?page=forgotPassword&msg=Algo ha fallado, el email introducido no está registrado.");

        }
    }
}
