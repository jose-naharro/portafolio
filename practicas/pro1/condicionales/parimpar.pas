(* El siguiente programa dice si el número entero que ha introducido el usuario 
 * es par o impar
 * 
 * Autor: Jose
 * Ver.:1.0
 * 
 *)
 
PROGRAM parimpar (input, output);

VAR
	n:Integer;

BEGIN

	Write('Introduzca un número entero (positivo o negativo) para ver su paridad: ');
	Readln(n);
	
	IF (n MOD 2=0) THEN
		Writeln('El número ',n:0,' es par.')
	ELSE
		Writeln('El número ',n:0,' es impar.');
		
END.		
