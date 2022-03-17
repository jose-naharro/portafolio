open List;;

let is_in_board n (x,y) =
   x >= 0 && x < n && y >= 0 && y < n;;

let notmem l e =
   not (mem e l);;

let legal_moves n (x,y) visited =
   let
      all_moves = [(x+1,y);(x-1,y);(x,y+1);(x,y-1)]
   in
      filter (notmem visited) (filter (is_in_board n) all_moves)
   ;; 

exception NoSol


let rec remove e = function
     [] -> []
   | h::t -> if h = e then t else h :: remove e t;;


let flow n lista = 
   if n <= 0 || not (for_all (function (s,m) -> is_in_board n s && is_in_board n m) lista) then
      raise (Invalid_argument "Flow")	
   else

      	let
			 	e1 = fst (hd lista) and
          	e2 = snd (hd lista) 
      	in
         let
         	visited = let ss,mm = split lista in remove e2 (ss @ mm)
        	in
			let
				pend = tl lista
			in
				
				let rec aux solucion p objetivo pendientes visitados movs =
					if movs = [] then 
						raise NoSol
					else
						let 
							mov = hd movs 
						in
						
						if mov = objetivo && pendientes = [] then
							if length (flatten ((objetivo::p)::solucion)) = n*n then 
								rev(map rev ((objetivo::p)::solucion))
							else
								aux solucion p objetivo pendientes visitados (tl movs)
						else
							try
								if mov = objetivo then 
									let i = fst (hd pendientes) in 
									let f = snd (hd pendientes) in
									let nvisitados = remove f (objetivo::visitados)in
									aux ((mov::p)::solucion) [i] f (tl pendientes) nvisitados (legal_moves n i nvisitados)
								else
									aux solucion (mov::p) objetivo pendientes (mov::visitados) (legal_moves n mov visitados)
							with
								NoSol -> aux solucion p objetivo pendientes visitados (tl movs)

				in aux [] [e1] e2 pend visited (legal_moves n e1 visited);;
