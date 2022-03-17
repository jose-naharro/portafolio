(* El siguiente programa muestra un menú por pantalla y saca la opción elegida.  
 * 
 * Autor: Jose
 * Ver.:1.0
 * 
 *)
 
PROGRAM sacamenuedades (input,output);
VAR
	opcion:char;

BEGIN

	Writeln('B.- Bebé');
	Writeln('A.- Adolescente');
	Writeln('M.- Mujer');
	Writeln('H.- Hombre');
	Write('Elija una opción del menú (B, A, M o H): ');
	Readln(opcion);
	CASE opcion OF
		'B','b':Writeln('BEBÉ');
		'A','a':Writeln('ADOLESCENTE');
		'M','m':Writeln('MUJER');
		'H','h':Writeln('HOMBRE');
	OTHERWISE
		Writeln('No ha escrito una opción valida...');
	END;		
END.		
