<?php

require "Conexion.php";

class Login extends Conexion {
    
    public function __construct(){
        parent::__construct();
    }
    
    public function login($email, $password){
        try {
            $consulta = "SELECT * FROM fbl_users WHERE user_email = :email AND user_password= :password";
            $resultado = $this->connect()->prepare($consulta);
            $resultado->bindValue(":email", $email);
            $resultado->bindValue(":password", $password);
            $resultado->execute();
            $this->disconnect();
            if ($resultado->rowCount()>0) {
                return array(true, $resultado);
            } else {
                return array(false, $resultado);
            }
    
        } catch (Exception $e) {
            die("Error: " . $e->getMessage());
        }
    }

}