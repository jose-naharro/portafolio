(* 
   Jose Carlos Naharro Gordillo
   jose.naharro 
*)

(****************************   Ejercicio 1 *******************************)

(* Redefinicion en recursividad terminal de la funcion fromto dada *)

let fromto_t m n =
   let rec aux a x = 
      if x < m then a
      else aux (x::a) (x-1)
   in aux [] n ;;
 
(******************************   Ejercicio 2 *****************************)   
   
(*
La causa de que sea tan lenta es que recorre una lista ordenada ascendentemente
por lo que comprueba toda la lista desde el principio, a listas más grandes
mas tiempo tarda en recorrerlas.
*)

   
let rec lmax_ok = function
        h::[] -> h
      | h::t -> let x = lmax_ok t in
                if h > x then h
                else x
      |_ -> raise(Invalid_argument "lmax");;

let lmax_t = function
     [] -> raise(Invalid_argument "lmax")
   | h::t -> let rec aux x = function
                    [] -> x
                  | h1::t1 -> if (h1 > x) then aux h1 t1
                              else aux x t1
             in aux h t;;
             

             
(*******************************   Ejercicio 3 *****************************)                

let rec sufixes l = match l with
     [] -> [[]]
   | _::t -> l::sufixes t;;

(* 
sufixes aplicado a una lista devuelve una lista de listas, cada elemento de esa 
lista es, el primero la propia lista, el segundo es la lista sin el primer 
elemento, y así sucesivamente hasta llegar a la vacia. El numero de elemento de 
la lista es igual al numero de elementos de a la que aplicamos inicialmente
sufixes  mas la vacia.
*)

let rec prefixes = function
     [] -> []
   | h::t -> [h]::List.map(function l -> h::l)(prefixes t);;

(*
prefixes aplicado a una lista da una lista de listas. El primer elemento de esa
lista es la lista formada por la cabeza de la lista a la que aplicamos sufixes
la primera vez, la siguiente es la anterior mas el siguiente elemento de la 
"original" y asi sucesivamente hasta acabar co el ultimo elemenmto que es la 
propia lista.
*)


let segments l = List.concat(List.map prefixes(sufixes l));;


(* 
segments aplicado a una lista da una lista de listas. Los elementos de esa 
lista son, primero prefixes aplizada a la lista "orginal"y luego a prefixes de l
le vuleve a aplizar prefixed y asi sucesivamente hasta acabar con el ultimo 
elemento de la lista que es la lista que contiene solo el ultimo elemento de la
lista "original" 
*)


let is_sufix l1 l2 = List.mem l1 (sufixes l2);;

let is_prefix l1 l2 = List.mem l1 (prefixes l2);;

let is_segment l1 l2 = List.mem l1 (segments l2);;

(* Redefiniciones terminales *)

let rec is_sufix_t l1 l2 = match l1,l2 with
        [],[] -> true
      | _ ,[] -> false
      | _ -> l1=l2 || is_sufix_t l1 (List.tl l2);;


let is_prefix_t l1 l2 = match l1,l2 with
        [],_ -> false
      | _,[] -> false
      | h1::t1,h2::t2 -> let rec aux a b = match a,b with
                                   [],[] -> true
                                 | [],_  -> true
                                 | _,[]  -> false
                                 | h1::t1,h2::t2 -> if h1 = h2 then aux t1 t2 
                                                    else false
                         in aux l1 l2;;


let rec is_segment_t l1 l2 = 
        if (l1 = [] || l2 = []) then false
        else is_prefix_t l1 l2 || is_segment_t l1 (List.tl l2);;

        
        
(******************************   Ejercicio 4 *****************************)
        
let rec sublists = function
     [] -> [[]]
   | h::t -> let sl = sublists t in
             sl @ (List.map (function l -> h::l) sl);;



let rec is_sublist l1 l2 = match l1,l2 with
     [],_ -> true
   | h1::t1,h2::t2 -> if h1=h2 then is_sublist t1 t2
                      else is_sublist l1 t2
   | _ -> false;;


















