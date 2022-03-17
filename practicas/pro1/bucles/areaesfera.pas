(* Problema de examen. Calcula el área de una esfera con While introduciendo
* su radio. Si este es menor o igual que 0 lo alerta y pide un radio nuevo
* 
* Autor: Jose
* Version:1.0
*)

PROGRAM AreaEsfera (input,output);

CONST
	pi=3.14;
VAR
	A,r:Real;
		
BEGIN
	
	Write('Introduzca el radio de la esfera: ');
	Read(r);

		WHILE r <= 0 DO
			BEGIN
				Writeln('ERROR: El radio debe ser mayor que 0.');
				Write('Introduzca de nuevo el radio de la esfera: ');
				Read(r);
		END; {while}
			
		A:=4*pi*r*r;
		Writeln('El area de la esfera de radio ',r:0:2,' es ',A:0:2);
		
END.
