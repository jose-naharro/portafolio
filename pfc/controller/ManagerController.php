<?php
session_start();
if(!isset($_SESSION['login']))
    header("location:".urlsite);

require "model/Usuarios.php";
require "model/Aeronaves.php";

class ManagerController{
    
    public static function index(){
        require "view/manager/welcome.php";
    }

    public static function detallesUsuario(){
        $usuarios = new Usuarios();
        $usuario = $usuarios->listar(" user_id =".$_SESSION['userID']);
        require "view/manager/detallesUsuario.php";
    }

    public static function editarDetalles(){
        $datos = [];
        $datos[0]  = $address =   $_REQUEST['u_address_e'];
        $datos[1]  = $city    =   $_REQUEST['u_city_e'];
        $datos[2]  = $phone   =   $_REQUEST['u_phone_e']; 
        $datos[3]  = $id      =   $_REQUEST['u_id_e'];

        $usuario = new Usuarios();
 
        //No se puede comprobar que el email esté libre ya que es su identificador
        if($usuario->actualizarDetalles($datos)){  
            header('location:'.urlsite."?page=1&section=detallesUsuario&msg=Detalles actualizados correctamente.");
        } else {
            header('location:'.urlsite."?page=1&section=detallesUsuario&msg=No se han podido actualizar los detalles.");
        }
    }

    public static function changePassword(){
        //hay que hacer un logout
        if(!isset($_SESSION['login']))
            header('location:'.urlsite);
        unset($_SESSION['login']);
        session_destroy();
        require "view/common/forgottenPage.php";
    }

    /**
     * SECCION DE USUARIOS
    */

    public static function listaUsuarios(){
        $usuarios = new Usuarios();
        $listaUsuarios = $usuarios->listar(" user_id<>99 ");
        require "view/manager/listaUsuarios.php";
    }

    public static function  ver(){
        if(isset($_REQUEST['id']))
            $_SESSION['user_id']   =   $_REQUEST['id'];
        $usuarios = new Usuarios();
        $usuario = $usuarios->listar(" user_id =".$_SESSION['user_id']);
        require "view/manager/verUsuario.php";
    }
    
    public static function formInsertar(){
        require "view/manager/insertarUsuario.php";
    }

    public static function insertar(){
        
        $token = bin2hex(random_bytes(32));

        $now = date('Y-m-d H:i:s');
        $timestamp = strtotime($now) + 60*30;
        $expires = date('Y-m-d H:i:s', $timestamp);
        
        $datos = [];
        $datos[0]  = $name      =   $_REQUEST['u_name'];
        $datos[1]  = $surname1  =   $_REQUEST['u_surname1'];
        $datos[2]  = $surname2  =   $_REQUEST['u_surname2']; 
        $datos[3]  = $dni       =   $_REQUEST['u_dni'];
        $datos[4]  = $birth     =   date('Y-m-d',strtotime($_REQUEST['u_birth']));
        $datos[5]  = $email     =   $_REQUEST['u_email'];
        $datos[6]  = $password  =   md5(bin2hex(openssl_random_pseudo_bytes(8)));//pseudoaleatoria
        $datos[7]  = $phone     =   $_REQUEST['u_phone'];
        $datos[8]  = $city      =   $_REQUEST['u_city'];
        $datos[9]  = $address   =   $_REQUEST['u_address'];
        $datos[10] = $hours     =   $_REQUEST['u_hours'];
        $datos[11] = $rol       =   $_REQUEST['u_rol'];//ojo, aqui lo va a poner
        $datos[12] = $active    =   intval($_REQUEST['u_active']);
        $datos[13] = $fst_login =   0;//ya que al insertar uno nuevo aun no se habrá podido loguear.
        $datos[14] = $token;
        $datos[15] = $expires;

        $usuario = new Usuarios();
        $emailLibre = $usuario->checkEmailLibre($datos[5]);
 
        //Se comprueba que el email esté libre
        if($emailLibre){
            $usuario->insertar($datos);
            $usuario->mailActivacion($datos);
            header('location:'.urlsite."?page=1&section=listaUsuarios&msg=Usuario '". $name . " " . $surname1 ."' insertado correctamente. Se le ha enviado un email para su activación.");
        } else {
            header('location:'.urlsite."?page=1&section=formInsertar&msg=No se ha podido insertar el usuario.");
        }
    }

    public static function formEditar(){
        if(isset($_REQUEST['id']))
            $_SESSION['user_id']   =   $_REQUEST['id'];
        $usuarios = new Usuarios();
        $usuario = $usuarios->listar(" user_id =".$_SESSION['user_id']);
        require "view/manager/editarUsuario.php";
    }

    public static function editar(){
        $datos = [];
        $datos[0]  = $name      =   $_REQUEST['u_name_e'];
        $datos[1]  = $surname1  =   $_REQUEST['u_surname1_e'];
        $datos[2]  = $surname2  =   $_REQUEST['u_surname2_e']; 
        $datos[3]  = $dni       =   $_REQUEST['u_dni_e'];
        $datos[4]  = $birth     =   date('Y-m-d',strtotime($_REQUEST['u_birth_e']));
        $datos[5]  = $email     =   $_REQUEST['u_email_e'];
        $datos[6]  = $password  =   md5('1234');//$_REQUEST['u_password'];
        $datos[7]  = $phone     =   $_REQUEST['u_phone_e'];
        $datos[8]  = $city      =   $_REQUEST['u_city_e'];
        $datos[9]  = $address   =   $_REQUEST['u_address_e'];
        $datos[10] = $hours     =   $_REQUEST['u_hours_e'];
        $datos[11] = $rol       =   $_REQUEST['u_rol_e'];//ojo, aqui lo va a poner
        $datos[12] = $active    =   intval($_REQUEST['u_active_e']);
        $datos[13] = $id        =   $_REQUEST['u_id_e'];

        $usuario = new Usuarios();
 
        //No se puede comprobar que el email esté libre ya que es su identificador
        if($usuario->actualizar($datos)){  
            header('location:'.urlsite."?page=1&section=ver&id=".$id."&msg=Usuario '". $name . " " . $surname1 ."' actualizado correctamente.");
        } else {
            header('location:'.urlsite."?page=1&section=formEditar&id=".$id."&msg=No se ha podido actualizar el usuario.");
        }
    }

    public static function eliminar(){
        $id = $_GET['id'];

        $usuario = new Usuarios();
        
        $eliminado = $usuario->eliminar($id);

        if ($eliminado) {
            header('location:'.urlsite."?page=1&section=listaUsuarios&msg=Usuario eliminado correctamente.");
        } else {
            header('location:'.urlsite."?page=1&section=listaUsuarios&msg=El usuario no ha sido eliminado.");
        } 
    }

    /**
    * SECCION DE AERONAVES
    */
    public static function listaAeronaves(){
        $aeronaves = new Aeronaves();
        $listaAeronaves = $aeronaves->listar(1);//le paso el 1 para listar todas
        require "view/manager/listaAeronaves.php";
    }

    public static function  verAeronave(){
        if(isset($_REQUEST['id']))
            $_SESSION['plane_id']   =   $_REQUEST['id'];
        
        $aeronaves = new Aeronaves();
        $aeronave = $aeronaves->listar(" plane_id =".$_SESSION['plane_id']);
        require "view/manager/verAeronave.php";
    }

    public static function formInsertarAeronave(){
        $usuarios = new Usuarios();
        $listaMiembros = $usuarios->listar(" user_rol = 5 ");//Propietarios solo miembros
        require "view/manager/insertarAeronave.php";
    }

    public static function insertarAeronave(){
        $datos = [];
        $datos[0]  = $brand  = $_REQUEST['p_brand'];
        $datos[1]  = $model  = $_REQUEST['p_model'];
        $datos[2]  = $reg    = mb_strtoupper($_REQUEST['p_reg']);
        $datos[3]  = $motors = $_REQUEST['p_motors'];
        $datos[4]  = $hours  = $_REQUEST['p_hours'];
        $datos[5]  = $year   = $_REQUEST['p_year'];
        $datos[6]  = $cat    = $_REQUEST['p_cat'];
        $datos[7]  = $zfw    = $_REQUEST['p_zfw'];
        $datos[8]  = $mtow   = $_REQUEST['p_mtow'];
        $datos[9]  = $rent   = intval($_REQUEST['p_rent']);
        $datos[10] = $owner  = $_REQUEST['p_owner'];

        $plane = new Aeronaves();
        $matriculaLibre = $plane->checkMatriculaLibre($reg);
 
        //Se comprueba que la matricula esté libre
        if($matriculaLibre){
            $plane->insertar($datos);
            header('location:'.urlsite."?page=1&section=listaAeronaves&msg=Aeronave '". $reg . " => " . $brand ." " . $model . "' insertada correctamente.");
        } else {
            header('location:'.urlsite."?page=1&section=formInsertarAeronave&msg=No se ha podido insertar la aeronave. Ya está registrada.");
        }
    }

    public static function formEditarAeronave(){
        if(isset($_REQUEST['id']))
            $_SESSION['plane_id'] = $_REQUEST['id'];
        $aeronaves = new Aeronaves();
        $aeronave = $aeronaves->listar(" plane_id =".$_SESSION['plane_id']);
        $usuarios = new Usuarios();
        $listaMiembros = $usuarios->listar(" user_rol = 5 ");
        require "view/manager/editarAeronave.php";
    }

    public static function editarAeronave(){
        $datos = [];
        $datos[0]  = $brand  =   $_REQUEST['p_brand_e'];
        $datos[1]  = $model  =   $_REQUEST['p_model_e'];
        $datos[2]  = $reg    =   mb_strtoupper($_REQUEST['p_reg_e']);
        $datos[3]  = $motors =   $_REQUEST['p_motors_e'];
        $datos[4]  = $hours  =   $_REQUEST['p_hours_e'];
        $datos[5]  = $year   =   $_REQUEST['p_year_e'];
        $datos[6]  = $cat    =   $_REQUEST['p_cat_e'];
        $datos[7]  = $zfw    =   $_REQUEST['p_zfw_e'];
        $datos[8]  = $mtow   =   $_REQUEST['p_mtow_e'];
        $datos[9]  = $rent   =   intval($_REQUEST['p_rent_e']);
        $datos[10] = $owner  =   $_REQUEST['p_owner_e'];
        $datos[11] = $id     =   $_REQUEST['p_id_e'];
    
        $aeronave = new Aeronaves();
 
        if($aeronave->actualizar($datos)){  
            header('location:'.urlsite."?page=1&section=verAeronave&id=".$id."&msg=Aeronave '". $reg . " => " . $brand . " ". $model . "' actualizada correctamente.");
        } else {
            header('location:'.urlsite."?page=1&section=formEditarAeronave&id=".$id."&msg=No se ha podido actualizar la aeronave.");
        }
    }

    public static function eliminarAeronave(){
        $id = $_GET['id'];

        $aeronave = new Aeronaves();
        
        $eliminado = $aeronave->eliminar($id);

        if ($eliminado) {
            header('location:'.urlsite."?page=1&section=listaAeronaves&msg=Aeronave eliminada correctamente.");
        } else {
            header('location:'.urlsite."?page=1&section=listaAeronaves&msg=La aeronave no ha sido eliminada.");
        } 
    }

}