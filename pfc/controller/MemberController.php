<?php
 session_start();
 if(!isset($_SESSION['login']))
    header("location:".urlsite);

require_once 'model/Vuelos.php';
require_once 'model/Usuarios.php';
require_once 'model/Aeronaves.php';

class MemberController{
    
    public static function index(){
        require "view/member/welcome.php";
    }

    public static function detallesUsuario(){
        $usuarios = new Usuarios();
        $usuario = $usuarios->listar(" user_id =".$_SESSION['userID']);
        require "view/member/detallesUsuario.php";
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
            header('location:'.urlsite."?page=5&section=detallesUsuario&msg=Detalles actualizados correctamente.");
        } else {
            header('location:'.urlsite."?page=5&section=detallesUsuario&msg=No se han podido actualizar los detalles.");
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
        require 'view/member/listarVuelos.php';
    }

    public static function  verVuelo(){
        if(isset($_REQUEST['id']))
            $_SESSION['flight_id'] = $_REQUEST['id'];
        $vuelos = new Vuelos();
        $vuelo = $vuelos->listar(" flight_id =".$_SESSION['flight_id']);
        require "view/member/verVuelo.php";
    }

    public static function formInsertarVuelo(){
        $aeronaves = new Aeronaves();
        $listaAeronaves = $aeronaves->listar(" plane_rent = 1 OR plane_owner = ". $_SESSION['userID']);
        require "view/member/insertarVuelo.php";
    }

    public static function insertarVuelo(){
        $datos = [];
        $datos[0]  = $day   = $_REQUEST['f_day'];
        $datos[1]  = $hdep  = $_REQUEST['f_hdep'];
        $datos[2]  = $reg   = $_REQUEST['f_reg'];
        $datos[3]  = $dep   = mb_strtoupper($_REQUEST['f_dep']);
        $datos[4]  = $arr   = mb_strtoupper($_REQUEST['f_arr']);
        $datos[5]  = $pic   = $_REQUEST['f_pic'];
        $datos[6]  = $inst  = 99;//$_REQUEST['f_inst'];
        $datos[7]  = $train = 0;//intval($_REQUEST['f_train']);
        $datos[8]  = $route = $_REQUEST['f_route'];
        $datos[9]  = $notes = $_REQUEST['f_notes'];

        $date = date_create_from_format('Y-m-d', $day);
        $diaFormateado = date_format($date, 'd-m-Y');

        $vuelo = new Vuelos();

        $condicion = " fbl_flights.flight_pic  = ". $pic ." AND fbl_flights.flight_day  = ". $day;
        
        $slotLibre = $vuelo->checkSlotLibre($condicion);
        
        if($slotLibre){//Se comprueba que la matricula esté libre
            if($vuelo->insertar($datos)){
                header('location:'.urlsite."?page=5&section=listarVuelos&msg=Vuelo '". $diaFormateado . " => " . $dep ." - " . $arr . "' insertado correctamente.");
            } else {
                header('location:'.urlsite."?page=5&section=formInsertarVuelo&msg=No se ha podido insertar el vuelo. Slot ocupado.");
            }
        } else {
            header('location:'.urlsite."?page=5&section=formInsertarVuelo&msg=No se ha podido insertar el vuelo. Slot ocupado.");
        }
    }

    public static function formEditarVuelo(){
        if(isset($_REQUEST['id']))
            $_SESSION['flight_id'] = $_REQUEST['id'];
        
        $vuelos = new Vuelos();
        $vuelo = $vuelos->listar(" flight_id =".$_SESSION['flight_id']);
        $aeronaves = new Aeronaves();
        $listaAeronaves = $aeronaves->listar(" plane_rent = 1 OR plane_owner = ". $_SESSION['userID']);
        require "view/member/editarVuelo.php";
    }

    public static function editarVuelo(){
        $datos = [];
        $datos[0]  = $day   = $_REQUEST['f_day_e'];
        $datos[1]  = $hdep  = $_REQUEST['f_hdep_e'];
        $datos[2]  = $reg   = $_REQUEST['f_reg_e'];
        $datos[3]  = $dep   = mb_strtoupper($_REQUEST['f_dep_e']);
        $datos[4]  = $arr   = mb_strtoupper($_REQUEST['f_arr_e']);
        $datos[5]  = $pic   = $_SESSION['userID'];
        $datos[6]  = $inst  = 99;
        $datos[7]  = $train = 0;//Porque nunca son vuelos de instrucción
        $datos[8]  = $route = $_REQUEST['f_route_e'];
        $datos[9]  = $notes = $_REQUEST['f_notes_e'];
        $datos[10] = $landd  = $_REQUEST['f_land_d_e'];
        $datos[11] = $landn  = $_REQUEST['f_land_n_e'];
        $datos[12] = $finish = intval($_REQUEST['f_finish_e']);
        $datos[13] = $harr   = $_REQUEST['f_harr_e'];
        $datos[14] = $id     = $_REQUEST['f_id_e'];

        $date = date_create_from_format('Y-m-d', $day);
        $diaFormateado = date_format($date, 'd-m-Y');
        
        $vuelo = new Vuelos();
        $aeronave = new Aeronaves();
        $usuarioPic = new Usuarios();

        $listaDatosPIC = $usuarioPic->listar(' user_id ='. $pic);
        $listaDatosPlane = $aeronave->listar(" plane_id = ". $reg);
        
        $condicion = " fbl_flights.flight_pic  = ". $pic ." AND fbl_flights.flight_day  = ". $day;
        
        $slotLibre = $vuelo->checkSlotLibre($condicion);

        $tiempo1 = new DateTime($harr);
        $tiempo2 = new DateTime($hdep);
        $tiempoVuelo = $tiempo1->diff($tiempo2);
        $timeFlight = explode(':', $tiempoVuelo->format('%h:%i'));
        $decTime = ($timeFlight[0]) + ($timeFlight[1]/60);
        $horasAntPic = $listaDatosPIC[0]->user_hours;
        $horasAntPlane = $listaDatosPlane[0]->plane_hours;
        $horasVueloPic = $decTime + $horasAntPic;
        $horasVueloPlane = $decTime + $horasAntPlane;

        //Se comprueba que el slot esté libre
        if($slotLibre){
            if($vuelo->actualizar($datos)){  
                $aeronave->__construct();
                $aeronave->actualizarHoras($horasVueloPlane, $reg);
                $usuarioPic->__construct();
                $usuarioPic->actualizarHoras($horasVueloPic, $pic);
                header('location:'.urlsite."?page=5&section=verVuelo&id=".$id."&msg=Vuelo '". $diaFormateado . " => " . $dep ." - " . $arr . "' actualizado correctamente.");
            } else {
                header('location:'.urlsite."?page=5&section=formEditarVuelo&msg=No se ha podido actualizar el vuelo. Slot ocupado.");
            }
        } else {
            header('location:'.urlsite."?page=5&section=formEditarVuelo&msg=No se ha podido actualizar el vuelo. Slot ocupado.");
        }
    }

    public static function eliminarVuelo(){
        $id = $_GET['id'];

        $vuelo = new Vuelos();
        
        $eliminado = $vuelo->eliminar($id);

        if ($eliminado) {
            header('location:'.urlsite."?page=5&section=listarVuelos&msg=Vuelo eliminado correctamente.");
        } else {
            header('location:'.urlsite."?page=5&section=listarVuelos&msg=El vuelo no ha sido eliminado.");
        } 
    }

    /**
    * SECCION DE AERONAVES
    */
    public static function listaAeronaves(){
        $aeronaves = new Aeronaves();
        $listaAeronaves = $aeronaves->listar(" plane_owner = ".$_SESSION['userID']);
        require "view/member/listaAeronaves.php";
    }

    public static function  verAeronave(){
        if(isset($_REQUEST['id']))
            $_SESSION['plane_id']   =   $_REQUEST['id'];
        
        $aeronaves = new Aeronaves();
        $aeronave = $aeronaves->listar(" plane_id =".$_SESSION['plane_id']);
        require "view/member/verAeronave.php";
    }

    public static function formEditarAeronave(){
        if(isset($_REQUEST['id']))
            $_SESSION['plane_id'] = $_REQUEST['id'];
        $aeronaves = new Aeronaves();
        $aeronave = $aeronaves->listar(" plane_id =".$_SESSION['plane_id']);
        $usuarios = new Usuarios();
        $listaMiembros = $usuarios->listar(" user_id = ".$_SESSION['userID']);
        require "view/member/editarAeronave.php";
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
            header('location:'.urlsite."?page=5&section=verAeronave&id=".$id."&msg=Aeronave '". $reg . " => " . $brand . " ". $model . "' actualizada correctamente.");
        } else {
            header('location:'.urlsite."?page=5&section=formEditarAeronave&id=".$id."&msg=No se ha podido actualizar la aeronave.");
        }
    }
}