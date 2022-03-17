open Fb_tree;;

let is_single t = 
	try let _ = branches t in false 
	with Branches -> true;;

let l_branch t = let l,r = branches t in l;;

let r_branch t = let l,r = branches t in r;;

let rec size t = if is_single t then 1 
			else size (l_branch t) + size (r_branch t) + 1;;

let rec height t = if is_single t then 1 
			else max (height (l_branch t)) (height (r_branch t)) +1 ;;

let rec preorder t = if is_single t then [root t]
			else root t::preorder (l_branch t) @ preorder(r_branch t);; 

let rec postorder t = if is_single t then [root t]
			else postorder (l_branch t) @ postorder (r_branch t) @ [root t];;

let rec inorder t = if is_single t then [root t]
			else inorder (l_branch t) @ (root t) :: inorder (r_branch t);;

let rec leafs t = if is_single t then [root t]
		else leafs (l_branch t) @ leafs (r_branch t);;

let rec mirror t = if is_single t then single (root t)
		else comp (root t) (mirror (r_branch t) , mirror (l_branch t));;

let rec treemap f t = if is_single t then single (f (root t))
		else comp (f (root t)) (treemap f (l_branch t), treemap f (r_branch t));;

let rec is_perfect t = if is_single t then true 
		else size (l_branch t) = size (r_branch t);;
		
let rec is_complet t = if is_single t then true
        else 
            (height(l_branch t) - height(r_branch t) = 0
            ||height(l_branch t) - height(r_branch t) = 1)
            && is_complet(l_branch t)
            && is_complet(r_branch t);;
