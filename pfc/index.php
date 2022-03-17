<?php
require "config.php";

$page = "index";

if(isset($_GET['page']))
    $page = $_GET['page'];
switch($page){
    case 'login': 
        require "controller/LoginController.php";
        LoginController::index();
        break;
    case 'loginAuth': 
        require "controller/LoginController.php";
        LoginController::login();
        break;
    case 'logout': 
        require "controller/LoginController.php";
        LoginController::logout(); 
        break;
    case 'activation': 
        require "controller/ActivationController.php";
        if(isset($_GET['section'])){
            $metodo = $_GET['section'];
            if(method_exists('ActivationController', $metodo)){
                ActivationController::{$metodo}();
            }
        } else{
            ActivationController::index();
        }
        break;
    case 'forgotPassword': 
        require "controller/ActivationController.php";
        if(isset($_GET['section'])){
            $metodo = $_GET['section'];
            if(method_exists('ActivationController', $metodo)){
                ActivationController::{$metodo}();
            }
        } else{
            ActivationController::forgotten();
        }
        break;
    case '1': 
        require "controller/ManagerController.php";
        if ($_SESSION['rol'] == 1){
            if(isset($_GET['section'])){
                $metodo = $_GET['section'];
                if(method_exists('ManagerController', $metodo)){
                    ManagerController::{$metodo}();
                }
            } else{
                ManagerController::index();
            }
        } else {
            header("location:".urlsite."?page=".$_SESSION['rol']);
        }
        break;
    case '2': 
        require "controller/ChiefController.php";
        if ($_SESSION['rol'] == 2){
            if(isset($_GET['section'])){
                $metodo = $_GET['section'];
                if(method_exists('ChiefController', $metodo)){
                    ChiefController::{$metodo}();
                }
            } else{
                ChiefController::index();
            }
        } else {
            header("location:".urlsite."?page=".$_SESSION['rol']);
        }
        break;
    case '3':
        require "controller/InstructorController.php";
        if ($_SESSION['rol'] == 3){
            if(isset($_GET['section'])){
                $metodo = $_GET['section'];
                if(method_exists('InstructorController', $metodo)){
                    InstructorController::{$metodo}();
                }
            } else{
                InstructorController::index();
            }
        } else {
            header("location:".urlsite."?page=".$_SESSION['rol']);
        }
        break;
    case '4':
        require "controller/AlumnController.php";
        if ($_SESSION['rol'] == 4){
            if(isset($_GET['section'])){
                $metodo = $_GET['section'];
                if(method_exists('AlumnController', $metodo)){
                    AlumnController::{$metodo}();
                }
            } else{
                AlumnController::index();
            }
        } else {
            header("location:".urlsite."?page=".$_SESSION['rol']);
        }
        break;
    case '5':
        require "controller/MemberController.php";
        if ($_SESSION['rol'] == 5){
            if(isset($_GET['section'])){
                $metodo = $_GET['section'];
                if(method_exists('MemberController', $metodo)){
                    MemberController::{$metodo}();
                }
            } else{
                MemberController::index();
            }
        } else {
            header("location:".urlsite."?page=".$_SESSION['rol']);
        }
        break;           
    default : 
        require "controller/LoginController.php";
        LoginController::index();
    break;  
}