(* Este programa calcula el factorial de un numero entero y lo saca por pantalla.
 * 
 * Pero con bucle WHILE
 * 
 * Autor: Jose
 * Ver.:1.0
 *)

PROGRAM factorial2 (input,output);

VAR 
	n,fact:Integer;
	acumulador:Integer;
BEGIN
	
	Write('Escriba el número entero positivo del que quiere calcular su factorial: ');
	Readln(n);
	
	IF n>=0 THEN
	
		BEGIN
		
			fact:=1;       (* inicializamos las variables *)
			acumulador:=n;
			
			WHILE acumulador>0 DO
		      BEGIN
				fact:=fact*acumulador;
				acumulador:=acumulador-1;
			  END;
			
			Writeln('El factorial de ',n:0,' es, ',fact:0);  	
		END
	ELSE
			Writeln('No ha introducido un número entero positivo.');
END.		
