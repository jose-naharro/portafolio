(* Problema 15 de Sentencias Repetitivas.
 * El siguiente programa Da a elegir de entre un menú y muestra lo elegido
 * tambien se puede detener la ejecucion del programa.
 * 
 * Autor: Jose
 * Version: 1
 *)

PROGRAM sacamenusiempre (input,output);

VAR

	opcion:char;
	
BEGIN

	REPEAT 
		BEGIN
			Writeln;
			Writeln('  *********** MENU **********');
			Writeln;
			Writeln('   R.- Dibujar un rectángulo.');
			Writeln('   C.- Dibujar un cuadrado.');
			Writeln('   P.- Dibujar un pentágono.');
			Writeln('   S.- Salir del Programa');
			Writeln;
			Writeln('  ***************************');
			Writeln;
			Write('  Elija una opción del menú (R, C, P o S): ');
			Readln(opcion);
			Writeln;
			CASE opcion OF
				'R','r':Writeln('  "Ahora dibujaría un rectángulo."');
				'C','c':Writeln('  "Ahora dibujaría un cuadrado."');
				'P','p':Writeln('  "Ahora dibujaría un pentágono."');
				'S','s':halt;
			OTHERWISE
				Writeln('  No ha escrito una opción valida...');Writeln;
			END; {case}	
		END; {repeat}	
	UNTIL (opcion='s') OR (opcion='S');
	
END.	
				
