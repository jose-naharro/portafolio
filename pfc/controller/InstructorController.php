<?php
 session_start();
 if(!isset($_SESSION['login']))
    header("location:".urlsite);

require_once 'model/Vuelos.php';
require_once 'model/Usuarios.php';
require_once 'model/Aeronaves.php';

class InstructorController{
    
    public static function index(){
        require "view/instructor/welcome.php";
    }

    public static function detallesUsuario(){
        $usuarios = new Usuarios();
        $usuario = $usuarios->listar(" user_id =".$_SESSION['userID']);
        require "view/instructor/detallesUsuario.php";
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
            header('location:'.urlsite."?page=3&section=detallesUsuario&msg=Detalles actualizados correctamente.");
        } else {
            header('location:'.urlsite."?page=3&section=detallesUsuario&msg=No se han podido actualizar los detalles.");
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

    // Lista los vuelos para este usuario
    public static function listarVuelos(){
        $vuelos = new Vuelos();
        $listaVuelos = $vuelos->listar(" flight_inst =".$_SESSION['userID']);
        require "view/instructor/listarVuelos.php";
    }

    // Ve un vuelo de los listados
    public static function  verVuelo(){
        if(isset($_REQUEST['id']))
            $_SESSION['flight_id'] = $_REQUEST['id'];
        $vuelos = new Vuelos();
        $vuelo = $vuelos->listar(" flight_id =".$_SESSION['flight_id']);
        require "view/instructor/verVuelo.php";
    }

    // Se muestra el formulario de edición del vuelo
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
        require "view/instructor/editarVuelo.php";
    }

    // Guarda los datos de un vuelo si hay un slot libre
    // Añade las horas de vuelo a los diarios de aeronave y pilotos
    public static function editarVuelo(){
        $datos = [];
        $datos[0]  = $day    = $_REQUEST['f_day_e'];
        $datos[1]  = $hdep   = $_REQUEST['f_hdep_e'];
        $datos[2]  = $reg    = $_REQUEST['f_reg_e'];
        $datos[3]  = $dep    = mb_strtoupper($_REQUEST['f_dep_e']);
        $datos[4]  = $arr    = mb_strtoupper($_REQUEST['f_arr_e']);
        $datos[5]  = $pic    = $_REQUEST['f_pic_e'];
        $datos[6]  = $inst   = $_REQUEST['f_inst_e'];
        $datos[7]  = $train  = 1; // Siempre es de instrucción para los instructores
        $datos[8]  = $route  = $_REQUEST['f_route_e'];
        $datos[9]  = $notes  = $_REQUEST['f_notes_e'];    
        $datos[10] = $landd  = $_REQUEST['f_land_d_e'];
        $datos[11] = $landn  = $_REQUEST['f_land_n_e'];
        $datos[12] = $finish = 1;
        $datos[13] = $harr   = $_REQUEST['f_harr_e'];
        $datos[14] = $id     = $_REQUEST['f_id_e'];

        $date = date_create_from_format('Y-m-d', $day);
        $diaFormateado = date_format($date, 'd-m-Y');
        
        $vuelo = new Vuelos();
        $aeronave = new Aeronaves();
        $usuarioPic = new Usuarios();
        $usuarioInst = new Usuarios();
        
        $listaDatosPIC = $usuarioPic->listar(' user_id ='. $pic);
        $listaDatosINST = $usuarioInst->listar(" user_id = ". $inst);
        $listaDatosPlane = $aeronave->listar(" plane_id = ". $reg);
        
        $condicion = " (fbl_flights.flight_pic  = ". $pic ."  OR fbl_flights.flight_inst = ". $inst.") AND fbl_flights.flight_day  = ". $day;
        $slotLibre = $vuelo->checkSlotLibre($condicion);

        $tiempo1 = new DateTime($harr);
        $tiempo2 = new DateTime($hdep);
        $tiempoVuelo = $tiempo1->diff($tiempo2);
        $timeFlight = explode(':', $tiempoVuelo->format('%h:%i'));
        $decTime = ($timeFlight[0]) + ($timeFlight[1]/60);
        $horasAntPic = $listaDatosPIC[0]->user_hours;
        $horasAntInst = $listaDatosINST[0]->user_hours;
        $horasAntPlane = $listaDatosPlane[0]->plane_hours;
        $horasVueloPic = $decTime + $horasAntPic;
        $horasVueloInst = $decTime + $horasAntInst;
        $horasVueloPlane = $decTime + $horasAntPlane;
        
        //Se comprueba que el slot esté libre
        //Se introducen los datos y se actualizan las horas de piltos y aeronaves
        if($slotLibre){
            if($vuelo->actualizar($datos)){
                $aeronave->__construct();
                $aeronave->actualizarHoras($horasVueloPlane, $reg);
                $usuarioPic->__construct();
                $usuarioPic->actualizarHoras($horasVueloPic, $pic);
                $usuarioInst->__construct();
                $usuarioInst->actualizarHoras($horasVueloInst, $pic);
                header('location:'.urlsite."?page=3&section=verVuelo&id=".$id."&msg=Vuelo '". $diaFormateado . " => " . $dep ." - " . $arr . "' actualizado correctamente.");
            } else {
                header('location:'.urlsite."?page=3&section=formEditarVuelo&msg=No se ha podido actualizar el vuelo. Slot ocupado.");
            }
        } else {
            header('location:'.urlsite."?page=3&section=formEditarVuelo&msg=No se ha podido actualizar el vuelo. Slot ocupado.");
        }
    }
}