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

let length l = 
      let rec aux sum = function
           [] -> sum 
         | _::t -> aux(sum + 1) t
      in aux 0 l ;;
      
let nth l pos =
   if (pos<0) then raise (Invalid_argument "nth")
   else
      let rec aux = function
           ([],_) -> raise(Failure "nth")
         | (h::_,0) -> h
         | (h::t,n) -> aux (t,(n-1))
      in aux (l,pos);;   

    
let rev l =
   let rec aux acc = function
        [] -> acc
      | h::t -> aux (h::acc) t
   in aux [] l;;


let rec rev_append l1 l2 = match (l1,l2) with
     ([],l) -> l
   | h::t,l -> rev_append t (h::l);;

let append l1 l2 = 
   rev_append (rev l1) l2;;

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
     []   -> k
   | h::t -> f h (fold_right f t k);;
 
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

let filter p l =
   let rec aux p l1 l2 = match l1 with
      [] -> rev l2
      | h::t -> if p h
            then aux p t (h::l2)
            else aux p t l2
   in aux p l [];;   
   
   
let find_all = filter;;


let partition p l =
    let rec aux (l1,l2) = function
        [] -> (rev l1, rev l2)
      | h::t -> if p h then aux (h::l1,l2) t
                else aux (l1,h::l2) t
   in aux ([],[]) l ;;   
                                  
                  
let rec split = function
     [] -> ([],[])
   | h::t -> ((fst h)::((fst (split t))),((snd h)::(snd (split t))));; 

let rec combine l1 l2 = match (l1,l2) with
     ([],[]) -> []
   | (h1::t1,h2::t2) -> if ((length l1) <> (length l2)) then raise (Invalid_argument "combine")
                        else (h1,h2)::(combine t1 t2)
   | (_,_) -> raise (Invalid_argument "combine");;


let remove n l = 
     let rec aux l1 = function
           []   -> rev l1
         | h::t -> if n=h then rev_append l1 t
                   else aux (h::l1) t
     in aux [] l;;


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

let divide l = 
         let rec aux l1 l2 = function
             []-> (rev l1, rev l2)
           | h1::h2::t -> aux (h1::l1) (h2::l2) t
           | h::t -> aux (h::l1) (l2) t
        in aux [] [] l;;

    
   
