PROGRAM pedirsacarnombres (input,output);
(*  *)

TYPE
	tVector=ARRAY [1..7] OF String(20);
	
VAR
	animal:tVector;

PROCEDURE pedir(VAR bicho:tVector);
VAR
	i:integer;
BEGIN
	Write('Digame el nombre de 7 animales: ');
	FOR i:=1 TO 7 DO
	Readln(bicho[i]);
END;


PROCEDURE sacar(VAR bicho:tVector);
VAR
	i:Integer;
BEGIN
	Write('Los animales escritos han sido...');
	FOR i:=1 TO 6 DO
	Write(bicho[i],', ');
	Writeln(bicho[7],'.');
END;


BEGIN
pedir(animal);
sacar(animal);

END.
