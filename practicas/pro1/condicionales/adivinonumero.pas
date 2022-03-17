(* El siguiente programa pide al usuario que piense un numero del 1 al 10, 
 * que diga si es par o impar y el resto al dividirlo por 5. Saca el
 * número que ha pensado el usuario.  
 * 
 * Autor: Jose
 * Ver.:1.0
 * 
 *) 
 
PROGRAM adivinonumero (input, output);
 
VAR
	paridad:1..2;
	resto:0..4;

BEGIN

	Writeln('Piense un número del 1 al 10 ...');
	Write('¿El numero pensado es impar(1) o par(2) ? ');
	Read(paridad);
	Write('¿Cuál es el resto de dividir su número entre 5? ');
	Readln(resto);
	
  
	IF ((resto=0) AND (paridad=1)) THEN
		Writeln('El número pensado es el 5.');
	IF ((resto=0) AND (paridad=2)) THEN
		Writeln('El número pensado es el 10.');
	IF ((resto=1) AND (paridad=2)) THEN
		Writeln('El número pensado es el 6.');
	IF ((resto=1) AND (paridad=1)) THEN
		Writeln('El número pensado es el 1.');
	IF ((resto=2) AND (paridad=1)) THEN
		Writeln('El número pensado es el 7.');
	IF ((resto=2) AND (paridad=2)) THEN
		Writeln('El número pensado es el 2.');
	IF ((resto=3) AND (paridad=2)) THEN
		Writeln('El número pensado es el 8.');
	IF ((resto=3) AND (paridad=1)) THEN
		Writeln('El número pensado es el 3.');
	IF ((resto=4) AND (paridad=1)) THEN
		Writeln('El número pensado es el 9.');
	IF ((resto=4) AND (paridad=2)) THEN
		Writeln('El número pensado es el 4.');
		
END.		
