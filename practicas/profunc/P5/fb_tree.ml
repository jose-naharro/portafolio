
type 'a fb_tree =
 Leaf of 'a
 | Node of 'a * 'a fb_tree * 'a fb_tree;;

let rec string_of_tree f = function
 Leaf a ->
 "(" ^ (f a) ^ ")"
 | Node (a, t1, t2) ->
 "(" ^ (f a) ^ " " ^ (string_of_tree f t1)
 ^ " " ^ (string_of_tree f t2) ^ ")";;

exception Branches;;

let single x = Leaf x ;;

let comp x (a1,a2)= Node (x,a1,a2) ;;

let root = function
	Leaf x -> x
	|Node (x,_,_) -> x ;;

let branches = function
	Leaf _ -> raise Branches
	|Node (_,a1,a2) -> (a1,a2) ;;


