<?php

require_once  "Conexion.php";

class Activation extends Conexion {
    
    public function __construct(){
        parent::__construct();
    }
    
    public function activar($datos, $password){
        try {
            $id = $datos->user_id;
            $expirationDate = $datos->user_expires;
            $currentDate = date("Y-m-d H:i:s");

            if ($expirationDate > $currentDate) {
                //eso es que no ha pasado el expiration date
                $consulta = "UPDATE fbl_users SET user_password= :password WHERE user_id = :id";
                $resultado = $this->connect()->prepare($consulta);
                $resultado->bindValue(":password", $password);
                $resultado->bindValue(":id", $id);
                $resultado->execute();
                $this->disconnect();
                if ($resultado->rowCount()>0)
                    return true;
                else
                    return false;
            } else
                return false;
        } catch (Exception $e) {
            die("Error: " . $e->getMessage());
        }
    }

}