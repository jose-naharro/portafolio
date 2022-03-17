(* Este programa pide un numero entero y saca por pantalla 
 * por un lado la suma de los terminos pares y por otro 
 * la suma de los impares hasta dicho número.
 * 
 * Autor: Jose
 * Ver.:1.0
 *)

PROGRAM sumadorparesimpares (input,output);

VAR	
	n,i:integer;
	acumuladorimpar,acumuladorpar:integer;
	
BEGIN

	Write('Escriba un número entero y pulse enter: ');
	Readln(n);
	
	IF n>0 THEN
	
	BEGIN
	
		(* Cálculo de la suma de los impares *)
	
		acumuladorimpar:=0;
		i:=1;
		WHILE i<=n DO
			BEGIN
			acumuladorimpar:=acumuladorimpar+i;
			i:=i+2;
		END;
	
		(* Cálculo de la suma de los pares *)
	
		acumuladorpar:=0;
		i:=2;
		WHILE i<=n DO
			BEGIN
			acumuladorpar:=acumuladorpar+i;
			i:=i+2;
		END;
	
		(* Sacamos los resultados por pantalla *)
	
		Writeln('La suma de los términos pares hasta ',n:0,' es ',acumuladorpar:0);
		Writeln('La suma de los términos impares hasta ',n:0,' es ',acumuladorimpar:0);
	END
	ELSE
	Writeln('No ha introducido un número entero positivo.');
	
END.	
