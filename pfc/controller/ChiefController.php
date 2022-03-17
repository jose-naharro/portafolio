<?php
 session_start();
 if(!isset($_SESSION['login']))
    header("location:".urlsite);

require_once 'model/Vuelos.php';
require_once 'model/Usuarios.php';
require_once 'model/Aeronaves.php';

class ChiefController{
    
    public static function index(){
        require "view/chief/welcome.php";
    }

    public static function detallesUsuario(){
        $usuarios = new Usuarios();
        $usuario = $usuarios->listar(" user_id =".$_SESSION['userID']);
        require "view/chief/detallesUsuario.php";
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
            header('location:'.urlsite."?page=2&section=detallesUsuario&msg=Detalles actualizados correctamente.");
        } else {
            header('location:'.urlsite."?page=2&section=detallesUsuario&msg=No se han podido actualizar los detalles.");
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
        $listaVuelos = $vuelos->listar(' fbl_flights.flight_inst <> 99 ');
        require "view/chief/listarVuelos.php";
    }

    public static function  verVuelo(){
        if(isset($_REQUEST['id']))
            $_SESSION['flight_id'] = $_REQUEST['id'];
        $vuelos = new Vuelos();
        $vuelo = $vuelos->listar(" flight_id =".$_SESSION['flight_id']);
        require "view/chief/verVuelo.php";
    }

    public static function formInsertarVuelo(){
        $aeronaves = new Aeronaves();
        $listaAeronaves = $aeronaves->listar(" plane_rent = 1 ");
        $usuariosAlumnos = new Usuarios();
        $listaAlumnos = $usuariosAlumnos->listar(" user_rol = 4 ");
        $usuariosInstructores = new Usuarios();
        $listaInstructores = $usuariosInstructores->listar(" (user_rol = 3 AND user_id <> 99) ");
        require "view/chief/insertarVuelo.php";
    }

    public static function insertarVuelo(){
        $datos = [];
        $datos[0]  = $day   = $_REQUEST['f_day'];
        $datos[1]  = $hdep  = $_REQUEST['f_hdep'];
        $datos[2]  = $reg   = $_REQUEST['f_reg'];
        $datos[3]  = $dep   = mb_strtoupper($_REQUEST['f_dep']);
        $datos[4]  = $arr   = mb_strtoupper($_REQUEST['f_arr']);
        $datos[5]  = $pic   = $_REQUEST['f_pic'];
        $datos[6]  = $inst  = $_REQUEST['f_inst'];
        $datos[7]  = $train = 1;//Siempre es de entrenamiento
        $datos[8]  = $route = $_REQUEST['f_route'];
        $datos[9]  = $notes = $_REQUEST['f_notes'];

        $date = date_create_from_format('Y-m-d', $day);
        $diaFormateado = date_format($date, 'd-m-Y');

        $vuelo = new Vuelos();

        $condicion = " (fbl_flights.flight_pic  = ". $pic ."  OR fbl_flights.flight_inst = ". $inst.") AND fbl_flights.flight_day  = ". $day;
        
        $slotLibre = $vuelo->checkSlotLibre($condicion);
        
        if($slotLibre){//Se comprueba que la matricula esté libre
            if($vuelo->insertar($datos)){
                header('location:'.urlsite."?page=2&section=listarVuelos&msg=Vuelo '". $diaFormateado . " => " . $dep ." - " . $arr . "' insertado correctamente.");
            } else {
                header('location:'.urlsite."?page=2&section=formInsertarVuelo&msg=No se ha podido insertar el vuelo. Slot ocupado.");
            }
        } else {
            header('location:'.urlsite."?page=2&section=formInsertarVuelo&msg=No se ha podido insertar el vuelo. Slot ocupado.");
        }
    }

    public static function formEditarVuelo(){
        if(isset($_REQUEST['id']))
            $_SESSION['flight_id'] = $_REQUEST['id'];
        
        $vuelos = new Vuelos();
        $vuelo = $vuelos->listar(" flight_id =".$_SESSION['flight_id']);
        $aeronaves = new Aeronaves();
        $listaAeronaves = $aeronaves->listar(" plane_rent = 1 ");
        $usuariosAlumnos = new Usuarios();
        $listaAlumnos = $usuariosAlumnos->listar(" user_rol = 4 ");
        $usuariosInstructores = new Usuarios();
        $listaInstructores = $usuariosInstructores->listar(" (user_rol = 3 AND user_id <> 99) ");
        require "view/chief/editarVuelo.php";
    }

    public static function editarVuelo(){
        $datos = [];
        $datos[0]  = $day    = $_REQUEST['f_day_e'];
        $datos[1]  = $hdep   = $_REQUEST['f_hdep_e'];
        $datos[2]  = $reg    = $_REQUEST['f_reg_e'];
        $datos[3]  = $dep    = mb_strtoupper($_REQUEST['f_dep_e']);
        $datos[4]  = $arr    = mb_strtoupper($_REQUEST['f_arr_e']);
        $datos[5]  = $pic    = $_REQUEST['f_pic_e'];
        $datos[6]  = $inst   = $_REQUEST['f_inst_e'];
        $datos[7]  = $train  = 1;//intval($_REQUEST['f_train_e']);
        $datos[8]  = $route  = $_REQUEST['f_route_e'];
        $datos[9]  = $notes  = $_REQUEST['f_notes_e'];
        $datos[10] = $landd  = NULL;//$_REQUEST['f_land_d_e'];
        $datos[11] = $landn  = NULL;//$_REQUEST['f_land_n_e'];
        $datos[12] = $finish = 0;//intval($_REQUEST['f_finish_e']);
        $datos[13] = $harr   = NULL;//$_REQUEST['f_harr_e'];
        $datos[14] = $id     = $_REQUEST['f_id_e'];

        $date = date_create_from_format('Y-m-d', $day);
        $diaFormateado = date_format($date, 'd-m-Y');
        
        $vuelo = new Vuelos();
        
        $condicion = " (fbl_flights.flight_pic  = ". $pic ."  OR fbl_flights.flight_inst = ". $inst.") AND fbl_flights.flight_day  = ". $day;
        
        $slotLibre = $vuelo->checkSlotLibre($condicion);

        //Se comprueba que el slot esté libre
        if($slotLibre){
            if($vuelo->actualizar($datos)){  
                header('location:'.urlsite."?page=2&section=verVuelo&id=".$id."&msg=Vuelo '". $diaFormateado . " => " . $dep ." - " . $arr . "' actualizado correctamente.");
            } else {
                header('location:'.urlsite."?page=2&section=formEditarVuelo&msg=No se ha podido actualizar el vuelo. Slot ocupado.");
            }
        } else {
            header('location:'.urlsite."?page=2&section=formEditarVuelo&msg=No se ha podido actualizar el vuelo. Slot ocupado.");
        }
    }

    public static function eliminarVuelo(){
        $id = $_GET['id'];

        $vuelo = new Vuelos();
        
        $eliminado = $vuelo->eliminar($id);

        if ($eliminado) {
            header('location:'.urlsite."?page=2&section=listarVuelos&msg=Vuelo eliminado correctamente.");
        } else {
            header('location:'.urlsite."?page=2&section=listarVuelos&msg=El vuelo no ha sido eliminado.");
        } 
    }
}