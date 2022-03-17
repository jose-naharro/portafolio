open Diccionario;;
open Expr_arit;;
open Libreria;;
open Instruccion;;
open Parser;;
open Lexing;;

open Scanner;;
open Parsing;;


let lazo_interactivo ()= 
	print_endline "Calculadora de punto flotante con variables...";
	let rec aux dicc =
		print_string ">>";
		
		let
		       buffer = from_string(read_line())
                in

			try
				let
					new_dicc = ejecutar_instruccion dicc (s token buffer)
				in
					aux new_dicc
			with 
				Variable_no_asignada s -> print_endline("Variable " ^ s ^ " no asignada" );
					aux dicc
				|Funcion_no_implementada s -> print_endline("Funcion " ^ s ^ " no implementada");
                                        aux dicc
				|Comando_no_implementado s -> print_endline("Comando " ^ s ^ " no implementado" );
					aux dicc
				|Error_lexico -> print_endline "Error lexico";
					aux dicc
				|Parse_error -> print_endline "Error sintactico";
					aux dicc
				|Fin_de_ejecucion -> print_endline " adios "
		
	in aux diccionario_vacio;;

lazo_interactivo();;
