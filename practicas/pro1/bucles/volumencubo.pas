(* Problema de examen. Calcula el volumen de un cubo con While introduciendo
* su arista. Saca el valor del volumen y cuantos cubos se han calculado
* 
* Autor: Jose
* Version:1.0
*)

PROGRAM VolumenCubo (input,output);

VAR
	ari,V:Real;
	suma:Integer;
	
BEGIN
	Write('Introduzca el valor de la arista de un cubo: ');
	Read(ari);

		WHILE ari > 0 DO
			BEGIN
				V:=ari*ari*ari;
				Writeln('El volumen del cubo de arista ',ari:0:2,' es ',V:0:2);
				suma:=suma+1;
				Write('Introduzca otro valor para arista: ');
				Read(ari);
		END; {while}
			
		Writeln('Ha calculado el volumen de ',suma:0,' cubo(s).');

END.
