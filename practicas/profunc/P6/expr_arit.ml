
open Diccionario;;
open Libreria;;

type expresion_aritmetica =
     Constante of float
   | Variable of string
   | Suma of expresion_aritmetica * expresion_aritmetica
   | Resta of expresion_aritmetica * expresion_aritmetica
   | Multiplicacion of expresion_aritmetica * expresion_aritmetica
   | Division of expresion_aritmetica * expresion_aritmetica
   | Opuesto of expresion_aritmetica
   | Funcion of string * expresion_aritmetica;;

let rec evaluar_expresion dicc = function
	Constante c -> c
	|Variable v -> consultar_valor dicc v
	|Suma (a1,a2) -> (evaluar_expresion dicc a1) +. (evaluar_expresion dicc a2)
	|Resta (a1,a2) -> (evaluar_expresion dicc a1) -. (evaluar_expresion dicc a2)
	|Multiplicacion (a1,a2) -> (evaluar_expresion dicc a1) *. (evaluar_expresion dicc a2)
	|Division (a1,a2) -> (evaluar_expresion dicc a1) /. (evaluar_expresion dicc a2)
	|Opuesto a -> -. (evaluar_expresion dicc a)
	|Funcion (f,a) -> (funcion f) (evaluar_expresion dicc a);;

