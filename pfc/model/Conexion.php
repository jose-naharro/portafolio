<?php
class Conexion{

    protected $conexion_db;

    public function __construct(){
        try {
            $this->conexion_db = new PDO('mysql:host=' . DB_HOST . '; dbname=' . DB_NAME,DB_USER,DB_PASS);
            $this->conexion_db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->conexion_db->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
            $this->conexion_db->exec("SET CHARACTER SET utf8");

            return $this->conexion_db;
        } catch (Exception $e) {
            echo "La linea de error es: " . $e->getLine();;
        }
    }

    public function connect(){
        return $this->conexion_db;
    }

    public function disconnect(){
        $this->conexion_db = null;
    }
}