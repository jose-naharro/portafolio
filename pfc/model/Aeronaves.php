<?php
//require "Conexion.php";

class Aeronaves extends Conexion{
    
    private $listadoAeronaves;
    
    public function __construct(){
        parent::__construct();
    }

    public function listar($condicion){
        try {
            $consulta =
                "SELECT 
                    *,  
                    CONCAT(owner.user_name, ' ', owner.user_surname1, ' ', owner.user_surname2) AS owner 
                FROM 
                    fbl_planes
                INNER JOIN 
                    fbl_users owner ON (owner.user_id = fbl_planes.plane_owner) 
                INNER JOIN 
                    fbl_planes_cat ON fbl_planes.plane_cat = fbl_planes_cat.plane_cat_id
                WHERE
                    ". $condicion . " 
                ORDER BY 
                    fbl_planes.plane_id";

            $resultado = $this->connect()->prepare($consulta);
            $resultado->execute();
            $this->disconnect();
            while($row = $resultado->fetch(PDO::FETCH_OBJ)){
                $this->listadoAeronaves[] = $row;
            }
            return $this->listadoAeronaves;
        } catch (Exception $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function insertar($datos){
        try {
            $consulta = "INSERT INTO fbl_planes (plane_brand, plane_model, plane_reg, plane_motors, plane_hours, plane_year, plane_cat, plane_zfw, plane_mtow, plane_rent, plane_owner) VALUES (:pbrand, :pmodel, :preg, :pmotors, :phours, :pyear, :pcat, :pzfw, :pmtow, :prent, :powner)";
            $resultado = $this->connect()->prepare($consulta);

            $resultado->execute(array(":pbrand"=>$datos[0], ":pmodel"=>$datos[1], ":preg"=>$datos[2], ":pmotors"=>$datos[3], ":phours"=>$datos[4], ":pyear"=>$datos[5], ":pcat"=>$datos[6], ":pzfw"=>$datos[7], ":pmtow"=>$datos[8], ":prent"=>$datos[9], ":powner"=>$datos[10]
            ));
            $resultado->closeCursor();
            $this->disconnect();
            if($resultado){
                echo "La aeronave ha sido registrada correctamente.<br>";
                return true;
            } else {
                echo "La aeronave no ha sido registrada.<br>";
                return false;
            }
        } catch (Exception $e) {
            die("Error: " . $e->getMessage());
        }
    }
    public function actualizar($datos){
        try {
            $consulta = "UPDATE fbl_planes SET plane_brand=:pbrand, plane_model=:pmodel, plane_reg=:preg, plane_motors=:pmotors, plane_hours=:phours, plane_year=:pyear, plane_cat=:pcat, plane_zfw=:pzfw, plane_mtow=:pmtow, plane_rent=:prent, plane_owner=:powner WHERE plane_id = :pid";
            
            $resultado = $this->connect()->prepare($consulta);

            $resultado->execute(array(":pbrand"=>$datos[0], ":pmodel"=>$datos[1], ":preg"=>$datos[2], ":pmotors"=>$datos[3], ":phours"=>$datos[4], ":pyear"=>$datos[5], ":pcat"=>$datos[6], ":pzfw"=>$datos[7], ":pmtow"=>$datos[8], ":prent"=>$datos[9], ":powner"=>$datos[10], ":pid"=>$datos[11]
            ));
            $resultado->closeCursor();
            $this->disconnect();
            if($resultado){
                echo "La aeronave ha sido actualizada correctamente.<br>";
                return true;
            } else {
                echo "La aeronave no ha sido actualizada.<br>";
                return false;
            }
        } catch (Exception $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function actualizarHoras($horasVuelo, $pid){
        try {
            $consulta = "UPDATE fbl_planes SET plane_hours=:phours WHERE plane_id = :pid";
            
            $resultado = $this->connect()->prepare($consulta);

            $resultado->execute(array(":phours"=>$horasVuelo, ":pid"=>$pid));
            $resultado->closeCursor();
            $this->disconnect();
            if($resultado){
                echo "Las horas de la aeronave han sido actualizadas correctamente.<br>";
                return true;
            } else {
                echo "La horas de la aeronave no han sido actualizadas.<br>";
                return false;
            }
        } catch (Exception $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function eliminar($aeronave){
        try {
            $consulta = "DELETE FROM fbl_planes WHERE plane_id = :p_id";
            $resultado = $this->connect()->prepare($consulta);
            $resultado->execute(array(":p_id"=>$aeronave));
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

    public function checkMatriculaLibre($reg){
        try {
            $consulta = "SELECT * FROM fbl_planes WHERE plane_reg = '". $reg . "'";
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