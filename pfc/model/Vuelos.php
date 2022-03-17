<?php

require_once 'model/Conexion.php';

class Vuelos extends Conexion{
    
    private $listadoVuelos;
    
    public function __construct(){
        parent::__construct();
    }

    public function listar($condicion){
        try {

            $consulta =
                "SELECT 
                    *,  
                    CONCAT(pilot1.user_name, ' ', pilot1.user_surname1, ' ', pilot1.user_surname2) AS pic, 
                    CONCAT(pilot2.user_name, ' ', pilot2.user_surname1, ' ', pilot2.user_surname2) AS inst 
                FROM 
                    fbl_flights
                INNER JOIN 
                    fbl_users pilot1 ON (pilot1.user_id = fbl_flights.flight_pic)
                INNER JOIN 
                    fbl_users pilot2 ON (pilot2.user_id = fbl_flights.flight_inst) 
                INNER JOIN 
                    fbl_planes plane ON (plane.plane_id = fbl_flights.flight_p_reg)
                WHERE
                    ". $condicion . " 
                ORDER BY 
                    fbl_flights.flight_day";

            $resultado = $this->connect()->prepare($consulta);
            $resultado->execute();
            $this->disconnect();
            while($row = $resultado->fetch(PDO::FETCH_OBJ)){
                $this->listadoVuelos[] = $row;
            }
            return $this->listadoVuelos;
        } catch (Exception $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function insertar($datos){
        try {
            $consulta = "INSERT INTO fbl_flights (flight_day, flight_hdep, flight_p_reg , flight_dep, flight_arr, flight_pic , flight_inst , flight_isTrain, flight_route, flight_notes) VALUES (:day, :hdep, :p_reg, :dep, :arr, :pic, :inst, :isTrain, :route, :notes)";
            $resultado = $this->connect()->prepare($consulta);

            $resultado->execute(array(":day"=>$datos[0], ":hdep"=>$datos[1], ":p_reg"=>$datos[2], ":dep"=>$datos[3], ":arr"=>$datos[4], ":pic"=>$datos[5], ":inst"=>$datos[6], ":isTrain"=>$datos[7], ":route"=>$datos[8], ":notes"=>$datos[9]
            ));
            $resultado->closeCursor();
            $this->disconnect();
            if($resultado){
                echo "El vuelo ha sido registrado correctamente.<br>";
                return true;
            } else {
                echo "El vuelo no ha sido registrado.<br>";
                return false;
            }
        } catch (Exception $e) {
            die("Error: " . $e->getMessage());
        }
    }
    
    public function actualizar($datos){
        try {
            $consulta = "UPDATE fbl_flights SET flight_day=:day, flight_hdep=:hdep, flight_p_reg=:p_reg, flight_dep=:dep, flight_arr=:arr, flight_pic=:pic, flight_inst=:inst, flight_isTrain=:isTrain, flight_route=:route, flight_notes=:notes, flight_land_d=:land_d, flight_land_n=:land_n, flight_finish=:finish, flight_harr=:harr
            WHERE flight_id =:fid";
            
            $resultado = $this->connect()->prepare($consulta);

            $resultado->execute(array(":day"=>$datos[0], ":hdep"=>$datos[1], ":p_reg"=>$datos[2], ":dep"=>$datos[3], ":arr"=>$datos[4], ":pic"=>$datos[5], ":inst"=>$datos[6], ":isTrain"=>$datos[7], ":route"=>$datos[8], ":notes"=>$datos[9], ":land_d"=>$datos[10], ":land_n"=>$datos[11], ":finish"=>$datos[12], ":harr"=>$datos[13], ":fid"=>$datos[14]
            ));
            $resultado->closeCursor();
            $this->disconnect();
            if($resultado){
                echo "El vuelo ha sido actualizado correctamente.<br>";
                return true;
            } else {
                echo "El vuelo no ha sido actualizado.<br>";
                return false;
            }
        } catch (Exception $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function eliminar($vuelo){
        try {
            $consulta = "DELETE FROM fbl_flights WHERE flight_id = :f_id";
            $resultado = $this->connect()->prepare($consulta);
            $resultado->execute(array(":f_id"=>$vuelo));
            $resultado->closeCursor();
            if($resultado)
                return true;
            else
                return false;
        } catch (Exception $e) {
            die ("Error: " . $e->getMessage());
        } finally {
            $this->disconnect();
        }
    }

    //public function checkSlotLibre($fpic, $finst, $fday, $fhdep){
    public function checkSlotLibre($condicion){    
        try {
            $consulta =
                "SELECT 
                    *,  
                    CONCAT(pilot1.user_name, ' ', pilot1.user_surname1, ' ', pilot1.user_surname2) AS pic, 
                    CONCAT(pilot2.user_name, ' ', pilot2.user_surname1, ' ', pilot2.user_surname2) AS inst 
                FROM 
                    fbl_flights
                INNER JOIN 
                    fbl_users pilot1 ON (pilot1.user_id = fbl_flights.flight_pic)
                INNER JOIN 
                    fbl_users pilot2 ON (pilot2.user_id = fbl_flights.flight_inst) 
                INNER JOIN 
                    fbl_planes plane ON (plane.plane_id = fbl_flights.flight_p_reg)
                WHERE
                    " . $condicion ." 
                ORDER BY 
                    fbl_flights.flight_id";
            
            $sentencia = $this->connect()->prepare($consulta);
            $sentencia->execute(array());
            $resultado= $sentencia->fetchAll(PDO::FETCH_ASSOC);
            $sentencia->closeCursor();

            if ($sentencia->rowCount()>0) {
                return false;
            } else {
                return true;
            }
            $this->disconnect();
        } catch (Exception $e) {
            die("Error: " . $e->getMessage());
        }
    }

}