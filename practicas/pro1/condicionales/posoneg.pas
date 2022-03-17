(* El siguiente programa dice si el número que ha introducido el usuari 
 * es positivo o negativo
 * 
 * Autor: Jose
 * Ver.:1.0
 * 
 *)
 
PROGRAM posoneg (input, output);

VAR
	n:Integer;

BEGIN

	Write('Introduzca un número entero (positivo o negativo): ');
	Readln(n);
	
	IF n>0 THEN
		Writeln('El número ',n:0,' es positivo.');
	IF n=0 THEN
		Writeln('El número ',n:0,' es el elemento neutro!');
	IF n<0 THEN
		Writeln('El número ',n:0,' es negativo.');
		
END.		
