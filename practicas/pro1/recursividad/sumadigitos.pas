{ Comentario inicial }


PROGRAM sumadordigitos (input,output);
VAR
	n: Integer;
	i:integer;
	contador:integer VALUE 0;

(************** FUNCIONES *********************************)
	
FUNCTION sumadigitos(n,i:Integer VAR contador:integer):Integer;
	
BEGIN
	IF  not eoln THEN
		Read(i);
	contador:=sumadigitos(i+1);
	
	sumadigitos:=contador;
END;

(************* PROGRAMA PRINCIPAL ********************)

BEGIN

	Write('Escribe un número entero positivo: ');
	Readln(n);
	Writeln('La suma de sus digitos es: ',sumadigitos(n,i,contador));
	
END.	
