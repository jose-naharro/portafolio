(*
   Jose Carlos Naharro Gordillo
   jose.naharro 
*)

();;
(* -: unit = () *)

2 + 5 * 3;;
(* -: int = 17 *)

1.0;;
(* -: float = 1 *)

1.0 * 2;;
(* Esperado-> -: int = 2 
Error: This expression has type float but an expression was expected of type int
Error de tipo
La expresion sería: 1 * 2;; 
*)
1 * 2;;

2 - 2.0;;
(* Esperado: -: float = 0
Error: This expression has type float but an expression was expected of type int
Error de tipo
La expresion sería: 2 - 2;;
*)
2 - 2;;

3.0 + 2.0;;
(* Esperado: Valor float 5.0 
Error: This expression has type float but an expression was expected of type int
La expresion sería: 3 + 2;;
*)
3 + 2;;


5 / 3;;
(* -: int = 1 *)

5 mod 3;;
(* -:int = 2 *)


3.0 *. 2.0 ** 3.0;;
(* -: float = 24.0 *)


3.0 = float_of_int 3;;
(* -: bool = true *)


sqrt 4;;
(* Esperado: -: float = 2 
Error: This expression has type int but an expression was expected of type float
La expresion deberia ser sqrt 4.0;;
*)
sqrt 4.0;;


int_of_float 2.1 + int_of_float (-2.9);;
(* -: int = 0 *)

truncate 2.1 + truncate (-2.9);;
(* -: int = 0 *)

floor 2.1 +. floor (-2.9);;
(*  -: float = -1.0 *)


ceil 2.1 +. ceil -2.9;;
(* -: float 1.0
Error: This expression has type float -> float
       but an expression was expected of type float
La expresion deberia ser ceil 2.1 +. ceil (-2.9);;
*)
ceil 2.1 +. ceil (-2.9);;

'B';;
(* -: char = 'B' *)


int_of_char 'A';;
(* -: int = 65 *)


char_of_int 66;;
(* -: char = 'B' *)


Char.code 'B';;
(* -: int = 66 *)


Char.chr 67;;
(* -: char = 'C' *)

'\067';;
(* -: char = 'C' *)

Char.chr (Char.code 'a' - Char.code 'A' + Char.code 'Ñ');;
(* Esperaba: el valor que corresponde al ascii 241, faltaba el ultimo parentesis *)

Char.uppercase 'ñ';;
(* Esperaba: Ñ 
    -: char ='\209', faltaba la ultima comilla simple *)

Char.lowercase 'O';;
(* -:char =  o *)


"this is a string";;
(* -: string = "this is a string" *)


String.length "longitud";;
(* -: int 8 *)


"1999" + "1";;
(* Esperaba: Error ya que no se pueden sumar cadenas de caracteres
Error: This expression has type string but an expression was expected of type
         int
La expresion deberia ser 1999 + 1;; para dar: int 2000
*)
1999 + 1;;


"1999" ^ "1";;
(* -: string = "19991" *)


int_of_string "1999" + 1;;
(* -: int = 2000 *)


"\064\065";;
(* -: string = "@A" *)

string_of_int 010;;
(* -: string = "10" *)


not true;;
(* -: bool = false *)


true && false;;
(* -: bool = false *)

true || false;;
(* -: bool true *)

true or false;;
(* -: bool = true *)

true and false;;
(* Error: Syntax error. No existe and *)

(1 < 2) = false;;
(* -: bool = false *)


"1" < "2";;
(* -: bool = true *)

2 < 12;;
(* -: bool = true *)

"2" < "12";;
(* -: bool = false *)


"uno" < "dos";;
(* -: bool = false *)


2,5;;
(* -: int*int = (2,5) *)

"hola", "adios";;
(* -: string*string =("hola","adios") *)

0, 0.0;;
(*-: int * float = (0, 0.) *)


fst ('a',0);;
(* -: char = 'a' *)


snd (false, true);;
(* -: bool = false *)


(1,2,3);;
(* -: int*int*int = (1,2,3) *)

(1,2),3;;
(* -: (int * int) * int = ((1, 2), 3) *)


fst ((1,2),3);;
(* -: int*int = (1,2) *)


(),abs;;
(* -: unit * (int -> int) = ((), <fun>) *) 


if 3 = 4 then 0 else 4;;
(* -: int = 4 *)

if 3 = 4 then "0" else "4";;
(* -: string = 4 *)

if 3 = 4 then 0 else "4";;
(* Esperaba -: string = 4 
Error: This expression has type string but an expression was expected of type int
La expresion correcta seria if 3 = 4 then 0 else 4;;
*)
if 3 = 4 then 0 else 4;;

(if 3 < 5 then 8 else 10) + 4;;
(* -: int = 12 *)

let pi = 3.14;;
(* val pi : float = 3.14 *)

sin (pi /. 2.);;
(* Esperaba el valor del seno de pi/2 -: float = 1. *)

let x = 1;;
(* val x: int = 1 *)

let y = 2;;
(* val y: int = 2 *)

x - y;;
(* -: int = -1 *)

let x = y in x - y;;
(* -: int = 0 *)

x - y;;
(* -: int = -1 *)

z;;
(* Esperaba: que diera error
Error: Unbound value z
*)

let z = x + y;;
(* val z: int = 3 *)

z;;
(* -: int = 3 *)

let x = 5;;
(* val x: int = 5 *)

z;;
(* -: int = 3 *)

let y = 5 in x + y;;
(* -: int = 10 *)

x + y;;
(* -: int = 7 *)

let p = 2,5;;
(* val p = int*int (2, 5) *)

snd p, fst p;;
(* -: int*int = (5, 2) *)

p;;
(* -: int*int = (2, 5) *)

let p = 0,1 in snd p, fst p;;
(* val p : int*int = (1, 0) *)

p;;
(* -: int*int = (2, 5) *)

let x,y = p;;
(* val x: int = 2
   val y: int = 5 
*)

let z = x + y;;
(* val z: int = 7 *)

let x,y = p,x;;
(* val x = int*int (2, 5) 
   val y: int = 2
*)

let x = let x,y = 2,3 in x * x + y;;
(* val x: int = 7 *)

x + y;;
(* -: int = 12 *)

z;;
(* -: int = 7 *)

let x = x + y in let y = x * y in x + y + z;;
(* -: int = 34 *)

x + y + z;;
(* -: int = 16 *)

int_of_float;;
(* -:float -> int = <fun> *)

float_of_int;;
(* -: int -> float = <fun> *)

int_of_char;;
(* -: char -> int = <fun> *)

char_of_int;;
(* -: int -> char = <fun> *)

abs;;
(* -: int -> int = <fun> *)

sqrt;;
(* -: float -> float = <fun> *)

truncate;;
(* -: float -> int = <fun> *)

ceil;;
(* -: float -> float = <fun> *)

floor;;
(* -: float -> float = <fun> *)

Char.code;;
(* -: char -> int = <fun> *)

String.length;;
(* -: string -> int = <fun> *)

fst;;
(* -: 'a * 'b -> 'a = <fun> *)

snd;;
(* -: 'a * 'b -> 'b = <fun> *)

function x -> 2 * x;;
(* Esperaba: funcion que dado un x nos devuelve el elemento x multiplicado por 2 
-: int -> int = <fun> *)

(function x -> 2 * x) (2 + 1);;
(* -: int = 6 *)

function (x,y) -> x;;
(* Esperaba: funcion que nos devuelve el primer elemento de una tupla, como la funcion predefinida fst
-: 'a * 'b -> 'a = <fun> *)

let f = function x -> 2 * x;;
(* Esperaba: llamo f a la funcion que dado un x nos devuelve el elemento x multiplicado por 2 
val f : int -> int = <fun> *)

f(2 + 1);;
(* -: int = 6 *)

f 2 + 1;;
(* -: int = 5 *)



