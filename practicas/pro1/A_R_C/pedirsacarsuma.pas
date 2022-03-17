PROGRAM pedirsacarsuma (input,output);
(*  *)

TYPE
	tVector=ARRAY [1..7] OF REAL;
	
VAR
	numeros:tVector;

PROCEDURE pedir(VAR n:tVector);
VAR
	i:Integer;
BEGIN
	Write('Introduzca numeros reales: ');
	FOR i:=1 TO 7 DO
	Readln(n[i]);
END;

FUNCTION suma( n:tVector):REAL;

VAR
	i:Integer;
	acumulador:Real VALUE 0.0;
BEGIN

	FOR i:=1 TO 7 DO
		acumulador:=n[i]+acumulador;
	
	suma:=acumulador;	
END;


PROCEDURE sacar(n:tVector);

BEGIN
	Writeln('La suma de los numeros que has introducido es: ',suma(n):0:2);
END;


BEGIN
pedir(numeros);
sacar(numeros);

END.
