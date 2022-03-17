<?php
require_once  "Conexion.php";

use PHPMailer\PHPMailer\PHPMailer; 
use PHPMailer\PHPMailer\Exception; 

class Usuarios extends Conexion{
    
    private $listadoUsuarios;
    
    public function __construct(){
        parent::__construct();
    }

    public function listar($condicion){
        try {
            $consulta = "SELECT * FROM fbl_users INNER JOIN fbl_users_rol ON fbl_users.user_rol = fbl_users_rol.user_rol_id WHERE ". $condicion . " ORDER BY fbl_users.user_id";
            $resultado = $this->connect()->prepare($consulta);
            $resultado->execute();
            $this->disconnect();
            while($row = $resultado->fetch(PDO::FETCH_OBJ)){
                $this->listadoUsuarios[] = $row;
            }
            return $this->listadoUsuarios;
        } catch (PDOException $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function listarSimple($statment){
        try {
            $consulta = $statment;
            $resultado = $this->connect()->prepare($consulta);
            $resultado->execute();
            $this->disconnect();
            while($row = $resultado->fetch(PDO::FETCH_OBJ)){
                $this->listadoUsuarios[] = $row;
            }
            return $this->listadoUsuarios;
        } catch (PDOException $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function insertar($datos){
        try {
            $consulta = "INSERT INTO fbl_users (user_name, user_surname1, user_surname2, user_dni, user_birth, user_email, user_password, user_phone, user_city, user_address, user_hours, user_rol, user_active,user_fst_login, user_token, user_expires) VALUES (:uname, :usurname1, :usurname2, :udni, :ubirth, :uemail, :upassword, :uphone, :ucity, :uaddress, :uhours, :urol, :uactive, :ufstlogin, :utoken, :uexpires)";
            $resultado = $this->connect()->prepare($consulta);

            $resultado->execute(array(":uname"=>$datos[0], ":usurname1"=>$datos[1], ":usurname2"=>$datos[2], ":udni"=>$datos[3], ":ubirth"=>$datos[4], ":uemail"=>$datos[5], ":upassword"=>$datos[6], ":uphone"=>$datos[7], ":ucity"=>$datos[8], ":uaddress"=>$datos[9], ":uhours"=>$datos[10], ":urol"=>$datos[11], ":uactive"=>$datos[12], ":ufstlogin"=>$datos[13], ":utoken"=>$datos[14], ":uexpires"=>$datos[15]
            ));
            $resultado->closeCursor();
            $this->disconnect();
            if($resultado){
                echo "El usuario ha sido registrado correctamente.<br>";
                return true;
            } else {
                echo "El usuario no ha sido registrado.<br>";
                return false;
            }
        } catch (PDOException $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function actualizar($datos){
        try {
            $consulta = "UPDATE fbl_users SET user_name=:uname, user_surname1=:usurname1, user_surname2=:usurname2, user_dni=:udni, user_birth=:ubirth, user_email=:uemail, user_password=:upassword, user_phone=:uphone, user_city=:ucity, user_address=:uaddress, user_hours=:uhours, user_rol=:urol, user_active=:uactive WHERE user_id = :uid";
            $resultado = $this->connect()->prepare($consulta);

            $resultado->execute(array(":uname"=>$datos[0], ":usurname1"=>$datos[1], ":usurname2"=>$datos[2], ":udni"=>$datos[3], ":ubirth"=>$datos[4], ":uemail"=>$datos[5], ":upassword"=>$datos[6], ":uphone"=>$datos[7], ":ucity"=>$datos[8], ":uaddress"=>$datos[9], ":uhours"=>$datos[10], ":urol"=>$datos[11], ":uactive"=>$datos[12], ":uid"=>$datos[13]
            ));
            $resultado->closeCursor();
            $this->disconnect();
            if($resultado){
                echo "El usuario ha sido actualizado correctamente.<br>";
                return true;
            } else {
                echo "El usuario no ha sido actualizado.<br>";
                return false;
            }
        } catch (PDOException $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function actualizarHoras($horasVuelo, $pic){
        try {
            $consulta = "UPDATE fbl_users SET user_hours=:uhours WHERE user_id = :uid";
            $resultado = $this->connect()->prepare($consulta);

            $resultado->execute(array(":uhours"=>$horasVuelo, ":uid"=>$pic));
            $resultado->closeCursor();
            $this->disconnect();
            if($resultado){
                echo "Horas actualizadas correctamente.<br>";
                return true;
            } else {
                echo "Horas no actualizadas .<br>";
                return false;
            }
        } catch (PDOException $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function actualizarForggoten($token, $expires, $email){
        try {
            $consulta = "UPDATE fbl_users SET user_token=:utoken, user_expires=:uexpires WHERE user_email = :uemail";
            $resultado = $this->connect()->prepare($consulta);

            $resultado->execute(array(":utoken"=>$token, ":uexpires"=>$expires, ":uemail"=>$email));
            $resultado->closeCursor();
            $this->disconnect();
            if($resultado){
                echo "Token actualizado correctamente.<br>";
                return true;
            } else {
                echo "Token no actualizado .<br>";
                return false;
            }
        } catch (PDOException $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function actualizarDetalles($datos){
        try {
            $consulta = "UPDATE fbl_users SET user_address=:uaddress, user_city=:ucity, user_phone=:uphone WHERE user_id = :uid";
            $resultado = $this->connect()->prepare($consulta);

            $resultado->execute(array(":uaddress"=>$datos[0], ":ucity"=>$datos[1], ":uphone"=>$datos[2], ":uid"=>$datos[3]));
            $resultado->closeCursor();
            $this->disconnect();
            if($resultado){
                echo "Detalles actualizados correctamente.<br>";
                return true;
            } else {
                echo "Detalles no actualizados .<br>";
                return false;
            }
        } catch (PDOException $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function eliminar($usuario){
        try {
            $consulta = "DELETE FROM fbl_users WHERE user_id = :u_id";
            $resultado = $this->connect()->prepare($consulta);
            $resultado->execute(array(":u_id"=>$usuario));
            $resultado->closeCursor();
            if($resultado)
                return true;
            else
                return false;
        } catch (PDOException $e) {
            die ("Error: " . $e->getMessage());
        } finally {//esto se ejecuta siempre
            $this->disconnect();
        }
    }

    public function checkEmailLibre($email){
        try {
            $consulta = "SELECT * FROM fbl_users WHERE user_email = '". $email . "'";
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
        } catch (PDOException $e) {
            die("Error: " . $e->getMessage());
        }
    }

    public function mailActivacion($datos){
        
        require "C:\php\includes\PHPMailer\src\Exception.php";
        require "C:\php\includes\PHPMailer\src\PHPMailer.php";
        require "C:\php\includes\PHPMailer\src\SMTP.php";
          
        $email = $datos[5];    
        $token = $datos[14];

        $mail = new PHPMailer(TRUE); 
 
        $mail->isSMTP();
        $mail->Host = MAIL_HOSTNAME;
        $mail->SMTPAuth = true;
        $mail->Username = MAIL_USERNAME;
        $mail->Password = MAIL_PASSWORD;
        $mail->SMTPSecure = 'tls';
        $mail->Port = 587;
        
        $mail->setFrom('usuarios@flightembl.com', 'Flight\'em Book and Log'); 
        $mail->addReplyTo('usuarios@flightembl.com', 'Flight\'em Book and Log'); 
        
        $mail->addAddress($email); 

        $mail->isHTML(true);
        $mail->CharSet = 'UTF-8';
        $mail->Subject = 'Activación de su cuenta de Flight\'em.'; 
        
        $link = urlsite."?page=activation&token=".$token;
        $email_template = urlsite.'view/common/activationMail.html';
        $message = file_get_contents($email_template);
        $message = str_replace('%link%', $link, $message);
        $message = str_replace('%urlsite%', urlsite, $message);
     
        $mail->MsgHTML($message);

        if(!$mail->send()) { 
            return false;
            echo 'El email no ha podido ser enviado. Error: '.$mail->ErrorInfo; 
        } else { 
            return true;
            echo 'El email ha sido enviado.'; 
        } 
    }

    public function mailRecuperacion($token, $email){
        
        require "C:\php\includes\PHPMailer\src\Exception.php";
        require "C:\php\includes\PHPMailer\src\PHPMailer.php";
        require "C:\php\includes\PHPMailer\src\SMTP.php";

        $mail = new PHPMailer(TRUE); 
 
        $mail->isSMTP();
        $mail->Host = MAIL_HOSTNAME;
        $mail->SMTPAuth = true;
        $mail->Username = MAIL_USERNAME;
        $mail->Password = MAIL_PASSWORD;
        $mail->SMTPSecure = 'tls';
        $mail->Port = 587;
        
        $mail->setFrom('usuarios@flightembl.com', 'Flight\'em Book and Log'); 
        $mail->addReplyTo('usuarios@flightembl.com', 'Flight\'em Book and Log'); 
        
        $mail->addAddress($email); 

        $mail->isHTML(true);
        $mail->CharSet = 'UTF-8';
        $mail->Subject = 'Cambio de contraseña de Flight\'em.'; 
        
        $link = urlsite."?page=activation&token=".$token;
        $email_template = urlsite.'view/common/newPasswordMail.html';
        $message = file_get_contents($email_template);
        $message = str_replace('%link%', $link, $message);
        $message = str_replace('%urlsite%', urlsite, $message);
     
        $mail->MsgHTML($message);

        if(!$mail->send()) { 
            return false;
            echo 'El email no ha podido ser enviado. Error: '.$mail->ErrorInfo; 
        } else { 
            return true;
            echo 'El email ha sido enviado.'; 
        } 
    }
}