(* El siguiente programa dice si dos números enteros que ha introducido el usuario 
 * son divisibles, el primero por el segundo
 * 
 * Autor: Jose
 * Ver.:1.0
 * 
 *)
 
PROGRAM divisibles (input, output);

VAR
	n1,n2,n:Integer;

BEGIN

	Write('Introduzca dos números enteros, separados por un espacio, para ver si el 1º es divisible por el 2º: ');
	Readln(n1,n2);
	
	IF (n1 MOD n2=0) THEN
		BEGIN
		n:=n1 DIV n2;
		Writeln(n1:0,' es divisible entre ',n2:0,' y su resultado es ',n:0);
		END
	ELSE
		Writeln(n1:0,' no es divisible entre ',n2:0);
		
END.
