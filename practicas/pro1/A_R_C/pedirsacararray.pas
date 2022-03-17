PROGRAM pedirsacarrarray (input,output);
(*  *)

TYPE
	tVector=ARRAY [1..10] OF INTEGER;
	
VAR
	numeros:tVector;

PROCEDURE pedir(VAR numeros:tVector);
VAR
	i:integer;
BEGIN
	Write('Deme un array de 10 enteros: ');
	FOR i:=1 TO 10 DO
	Readln(numeros[i]);
END;


PROCEDURE sacar(VAR n:tVector);
VAR
	i:Integer;
BEGIN
	Write('Su array es: ');
	FOR i:=1 TO 10 DO
	Write(n[i]:4);
	Writeln;
END;


BEGIN
pedir(numeros);
sacar(numeros);

END.	
