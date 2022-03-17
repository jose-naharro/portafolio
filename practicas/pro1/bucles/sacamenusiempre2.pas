(* Problema 15 de Sentencias Repetitivas.
 * El siguiente programa Da a elegir de entre un menú y muestra lo elegido
 * tambien se puede detener la ejecucion del programa.
 * 
 * Autor: Jose
 * Version: 1
 *)

PROGRAM sacamenusiempre2 (input,output);

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
				'R','r':
				        BEGIN
				        Writeln('  _____________________');
				        Writeln('  |                   |');
				        Writeln('  |                   |');
				        Writeln('  |                   |');
				        Writeln('  |___________________|');
				        END;
				'C','c':
				        BEGIN
				        Writeln('  __________');
				        Writeln('  |        |');
				        Writeln('  |        |');
				        Writeln('  |        |');
				        Writeln('  |________|');
				        END;
				'P','p':
				        BEGIN
				        Writeln('    /\ ');
				        Writeln('   /  \');
				        Writeln('  /    \');
				        Writeln('  \    /');
				        Writeln('   \__/');
				        END;
				'S','s':halt;
			OTHERWISE
				Writeln('  No ha escrito una opción valida...');Writeln;
			END; {case}	
		END; {repeat}	
	UNTIL (opcion='s') OR (opcion='S');
	
	halt;
END.	
