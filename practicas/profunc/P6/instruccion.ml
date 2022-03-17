
open Diccionario;;
open Expr_arit;;
open Libreria;;

type instruccion =
     Asignacion of string * expresion_aritmetica
   | Calculo of expresion_aritmetica
   | Comando of string;;

exception Fin_de_ejecucion;;

exception Comando_no_implementado of string;;

let ejecutar_instruccion dicc = function
	Asignacion(s,e) -> let
				v = evaluar_expresion dicc e
			   in
				print_endline(s ^ " = " ^ (string_of_float v));
				asignar_valor dicc s v
  
	|Calculo e -> print_endline(string_of_float (evaluar_expresion dicc e));
			dicc
	|Comando "var" -> mostrar_diccionario dicc;
			dicc
	|Comando "fun" -> mostrar_libreria ();
			dicc
	|Comando "fin" -> raise Fin_de_ejecucion 
	|Comando s -> raise(Comando_no_implementado s);;
