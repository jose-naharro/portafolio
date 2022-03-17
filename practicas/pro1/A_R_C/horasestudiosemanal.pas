PROGRAM horasestudiosemanal (input,output);

(* Este programa pide las horas que ha estudiado un alumno una asignatura 
 * durante una semana, muestra la media de las horas, que día ha estudiado 
 * más y que día ha estudiado menos.
   
   Autor: Jose
   Version: 1.0
*)

TYPE
	tHora= ARRAY [1..7] OF INTEGER;
	
VAR

	hora:tHora;
	maximo:Integer;
	
(******************   SECCION DE SUBPROGRAMAS ************************
 *********************************************************************)

PROCEDURE pedirh (VAR h:tHora);  
(* Este procedimiento pide las horas estudiadas cada dia *)

VAR
	j:Integer;
	
BEGIN
	Write('Escribe las horas estudiadas cada dia, de lunes a domingo: ');
	FOR j:=1 TO 7 DO
		Readln(h[j]);

END;

PROCEDURE convertir(VAR media:Real);

{ Este subprograma pasa de segundos introducidos por teclado a hh:mm:ss } 

VAR 

	tiempo:integer;
	horas,minutos,segundos:Integer;

BEGIN

   tiempo:=trunc(media*3600);
   horas:= tiempo DIV 3600;
   minutos:=(tiempo MOD 3600) DIV 60;
   segundos:= (tiempo MOD 3600) MOD 60;

   Writeln(horas:2,' hh :',minutos:2,' mm : ',segundos:2,' ss'); { Ahora mostramos el resultado por pantalla }

END;


PROCEDURE sacarmedia (VAR h:tHora);
(* Este procedimiento saca la media de las horas estudiadas esa semana *)

VAR
	suma:INTEGER VALUE 0;
	media:REAL;
	i:Integer;
	
	
BEGIN
	
		FOR i:=1 TO 7 DO
			suma:=h[i]+suma;
			
		media:=suma/7;
		Write('La media de horas estudiadas es: ');
		convertir(media);
END;

FUNCTION sacarmaximo(h:tHora):Integer;

VAR
	i:integer VALUE 1;
	donde:Integer;
	n:integer;
	busca:Integer;
	
BEGIN
	WHILE ((h[i]<> busca) AND (i>=n)) DO
	i:=i+1;
	
	IF i>n THEN
		donde:=0
	ELSE
		donde:=i;
		
	sacarmaximo:=donde;	
END;

(******************     PROGRAMA PRINCIPAL    ************************)

BEGIN

	pedirh(hora);
	sacarmedia(hora);
	maximo:=sacarmaximo(hora);
	Writeln('El día que más has estudiado han sido ',maximo);


END.
