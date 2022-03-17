PROGRAM exponenterecur (input,output);
(* Este programa calcula una potencia de manera recursiva.
   
   Autor: Jose
   Version: 1.0
*)

VAR
	a,b: Integer;
	
(********** DECLARACIÓ DE FUNCIONES **********************)

FUNCTION poten(a,b:INTEGER):INTEGER;
(* Calculo de la potencia de un número de manera recursiva. *)

BEGIN
	IF b>0 THEN 
		poten:=a*poten(a,b-1)
	ELSE		
		poten:=1;
END; {fin de la funcion poten}

(***************  PROGRAMA PRINCIPAL  **********************)

BEGIN

	Write('Deme la base de la potencia: ');
	Readln(a);
	Write('Deme el exponente de la potencia: ');
	Readln(b);
	Writeln('La ponecia de ',a:0,' elevado a ',b:0,' es ',poten(a,b):0);
END.	
