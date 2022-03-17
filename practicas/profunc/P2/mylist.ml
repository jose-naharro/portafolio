(* 
   Jose Carlos Naharro Gordillo
   jose.naharro 
*)

let hd = function
        h::_ -> h
      | []   -> raise (Failure "hd");;

let tl = function
        _::t -> t
      | []   -> raise (Failure "tl");;

let rec length = function
        _::t -> 1 + length t
      | []   -> 0 ;;
      
let nth l pos =
   if (pos<0) then raise (Invalid_argument "nth")
   else
      let rec aux = function
           ([],_) -> raise(Failure "nth")
         | (h::_,0) -> h
         | (h::t,n) -> aux (t,(n-1))
      in aux (l,pos);;   

let rec append l1 l2 = match  (l1,l2) with
        ([],l) -> l
      | (h::t,l) -> h::append t l;;


let rev l =
   let rec aux acc = function
        [] -> acc
      | h::t -> aux (h::acc) t
   in aux [] l;;


let rev_append l1 l2 = match (l1,l2) with
   (l1,l2) -> append (rev l1) (l2);;


let rec concat = function
     [] -> []
   | h::t -> append h (concat t);;

let rec flatten = function
     [] -> []
   | h::t -> append h (flatten t);;

let rec map f = function
     [] -> []
   | h::t -> f h :: map f t ;;

let rec map2 f l1 l2 = match (l1,l2) with
     ([],[]) -> []
   | (h1::t1,h2::t2) -> f h1 h2 ::map2 f t1 t2
   | (_,_) -> raise (Invalid_argument "map2");;

let rec fold_left f k l = match l with
     [] -> k
   | h::t -> fold_left f (f k h) t;;

let rec fold_right f l k = match l with
     [] -> k
   | h::t -> fold_right f t (f k h);;

let rec find p = function
     [] -> raise (Not_found)
   | h::t ->  if p h  then h
              else find p t;;

let rec for_all p = function
     [] -> true
   | h::t -> p h && for_all p t;;

let rec exists p = function
     [] -> false
   | h::t -> p h || exists p t;;

let rec mem e = function
     [] -> false
   | h::t -> if e = h then true
             else mem e t;;

let filter p =
   let rec find l = function
        [] -> l
      | h::t -> if p h then h::(find l t)
                else find l t 
   in find [];;

let find_all p =
   let rec find l = function
        [] -> l
      | h::t -> if p h then h::(find l t)
                else find l t 
   in find [];;

let rec partition p = function
     [] -> [],[]
   | h::t -> let t1,t2 = partition p t in 
             if p h then h::t1,t2
             else t1,h::t2;;
                  
                  
let rec split = function
     [] -> ([],[])
   | h::t -> ((fst h)::((fst (split t))),((snd h)::(snd (split t))));; 

let rec combine l1 l2 = match (l1,l2) with
     ([],[]) -> []
   | (h1::t1,h2::t2) -> if ((length l1) <> (length l2)) then raise (Invalid_argument "combine")
                        else (h1,h2)::(combine t1 t2)
   | (_,_) -> raise (Invalid_argument "combine");;


let rec remove e = function
     [] -> []
   | h::t -> if e = h then t
             else h::(remove e t);;
             
let rec remove_all a = function
     [] -> []
   | b::l when a == b -> remove_all a l
   | b::l -> b::(remove_all a l) ;;

let rec ldif l1 l2 = match (l1,l2) with
     [],[] -> []
   | _ ,[] -> l1
   | [] ,_ -> []
   | l,h2::t2 -> ldif (remove_all h2 l) t2;;       

let lprod l l' = 
   concat (map (fun e -> map (fun e' -> (e,e')) l') l);;


let rec divide = function
     h1::h2::t -> let t1,t2 = divide t in (h1::t1,h2::t2)
   | l -> (l,[]);;


