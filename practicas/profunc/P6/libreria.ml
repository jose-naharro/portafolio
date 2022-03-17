
exception Funcion_no_implementada of string;;

let funcion = function 
	"abs" -> abs_float
	|"sqrt" -> sqrt
	|"exp" -> exp
	|"log" -> log
	|"round" -> (function x -> floor(x +. 0.5))
	|s -> raise(Funcion_no_implementada s);;

let descripcion = function 
	"abs" -> "funcion que devuelve el valor absoluto"
	|"sqrt" -> "funcion que calcula la raiz cuadrada"
	|"exp" -> "funcion que calcula potencias del numero e"
	|"log" -> "funcion que calcula logaritmos"
	|"round" -> "funcion que redondea un numero" 
	|s -> raise(Funcion_no_implementada s);;


let mostrar_libreria () = 
	print_endline ("abs : " ^ descripcion "abs");
	print_endline ("sqrt : " ^ descripcion "sqrt");
	print_endline ("exp : " ^ descripcion "exp");
	print_endline ("log : " ^ descripcion "log");
	print_endline ("round : " ^ descripcion "round");

