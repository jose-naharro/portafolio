(* El siguiente programa pide nombre, apellidos y edad para calculae la 
 * edad de jubilación. Avisa si la edad es superior a 67 avisa de que ya 
 * deberia haberse jubilado y hace cuantos años. 
 * Autor: Jose
 * Ver.:1.0
 * 
 *)
 
PROGRAM aniosjubilacion2 (input, output);

VAR
	edad,anios,sobran:Integer;
	nombre,apellido1,apellido2:string(20);

BEGIN

	Write('Introduzca su nombre: ');
	Readln(nombre);
	Write('Introduzca su primer apellido: ');
	Readln(apellido1);
	Write('Introduzca su segundo apellido: ');
	Readln(apellido2);
	Write('Introduzca su edad: ');
	Readln(edad);
	
	IF edad<67 THEN
		BEGIN
			anios:=67-edad;
			Writeln('Sr./Sra. ',apellido1,' le quedan ',anios:0,' para jubilarse.');
		END
	ELSE IF edad>67 THEN
			BEGIN
				sobran:=edad-67;
				Writeln('Sr./Sra. ',apellido1,' hace ',sobran:0,' año/s que se tendría que haber jubilado.');
			END
		 ELSE
			Writeln('Sr./Sra. ',apellido1,' se ha jubilado este año.');

END.
