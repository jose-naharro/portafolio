<?php
session_start();

require "model/Login.php";

class LoginController{

    public static function index(){
        if(isset($_SESSION['login']))
            header('location:'.urlsite."?page=login");
        require "view/common/loginForm.php";
    }
    
    public static function login(){
        $modelo = new Login();
        $email = htmlentities(addslashes(trim($_POST['txtemail'])));
        $password = htmlentities(addslashes(md5(trim($_POST['txtpassword']))));

        $resultado = $modelo->login($email, $password);

        if($resultado[0]){
            $_SESSION['login'] = $email;
            $register = $resultado[1]->fetch(PDO::FETCH_ASSOC);
            $rol = $register['user_rol'];
            $nombre = $register['user_name']." ".$register['user_surname1']." ".$register['user_surname2'];
            $id = $register['user_id'];
            $_SESSION['userName'] = $nombre;
            $_SESSION['rol'] = $rol;
            $_SESSION['userID'] = $id;
            header("location:".urlsite."?page=".$rol);
        }else{
            header('location:'.urlsite."?msg=E-mail o contraseña incorrectos.");
        }
    }

    public static function logout(){
        if(!isset($_SESSION['login']))
            header('location:'.urlsite);
        unset($_SESSION['login']);
        session_destroy();
        header('location:'.urlsite);
    }
}
