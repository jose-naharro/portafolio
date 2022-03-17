
type diccionario = 
	(string * float) list;;

exception Variable_no_asignada of string;;

let diccionario_vacio = 
	[];;

let consultar_valor diccionario a = 
	try List.assoc a diccionario with Not_found -> raise (Variable_no_asignada a);;

let rec remove x l =		
		match l with
		[] -> []
		|(a,b)::t -> if a = x then t else (a,b)::remove x t;;

let asignar_valor diccionario a b = 
	(a,b)::(remove a diccionario);;

let rec mostrar_diccionario = function
	[] -> ()
	|(a,b)::t -> print_endline(a^" = "^(string_of_float b)); mostrar_diccionario t;;
	

