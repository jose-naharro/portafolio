(* El siguiente programa muestra un menú por pantalla y saca la opción elegida.  
 * 
 * Autor: Jose
 * Ver.:1.0
 * 
 *)
PROGRAM sacamenu (input,output);
VAR
	opcion:char;

BEGIN

	Writeln('A.- Altas');
	Writeln('B.- Bajas');
	Writeln('C.- Consultas');
	Writeln('L.- Listado');
	Write('Elija una opción del menú (A, B, C o L): ');
	Readln(opcion);
	CASE opcion OF
		'A','a':Writeln('ALTAS');
		'B','b':Writeln('BAJAS');
		'C','c':Writeln('CONSULTAS');
		'L','l':Writeln('LISTADO');
	OTHERWISE
		Writeln('No ha escrito una opción valida...');
	END;		
END.		
	
