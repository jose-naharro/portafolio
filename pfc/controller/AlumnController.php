<?php
 session_start();
 if(!isset($_SESSION['login']))
    header("location:".urlsite);

require_once 'model/Vuelos.php';
require_once 'model/Usuarios.php';
require_once 'model/Aeronaves.php';

class AlumnController{
    
    public static function index(){
        require "view/alumn/welcome.php";
    }

    public static function detallesUsuario(){
        $usuarios = new Usuarios();
        $usuario = $usuarios->listar(" user_id =".$_SESSION['userID']);
        require "view/alumn/detallesUsuario.php";
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
            header('location:'.urlsite."?page=4&section=detallesUsuario&msg=Detalles actualizados correctamente.");
        } else {
            header('location:'.urlsite."?page=4&section=detallesUsuario&msg=No se han podido actualizar los detalles.");
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
     * SECCION DE VUELOS
    */

    public static function listarVuelos(){
        $vuelos = new Vuelos();
        $listaVuelos = $vuelos->listar(" flight_pic =".$_SESSION['userID']);
        require 'view/alumn/listarVuelos.php';
    }

    public static function  verVuelo(){
        if(isset($_REQUEST['id']))
            $_SESSION['flight_id'] = $_REQUEST['id'];
        $vuelos = new Vuelos();
        $vuelo = $vuelos->listar(" flight_id =".$_SESSION['flight_id']);
        require "view/alumn/verVuelo.php";
    }
}