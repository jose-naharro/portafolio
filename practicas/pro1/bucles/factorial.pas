(* Este programa calcula el factorial de un numero entero y lo saca por pantalla.
 * 
 * Autor: Jose
 * Ver.:1.0
 *)

PROGRAM factorial (input,output);

VAR 
	n,fact:Integer;
	
BEGIN
	
	Write('Escriba el número entero positivo del que quiere calcular su factorial: ');
	Readln(n);
	
	IF n>=0 THEN
	
		BEGIN
		
			fact:=1; (* inicializamos la variable *)
	
			FOR n:=1 TO n DO
		
				fact:=fact*n;
		
			Writeln('El factorial de ',n:0,' es, ',fact:0);
		END
	ELSE
			Writeln('No ha introducido un número entero positivo.');
END.		
