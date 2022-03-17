
PROGRAM ConvertirSegundos (input,output);

{     Este programa pasa de segundos introducidos por teclado a hh:mm:ss
      Autor: José Carlos Naharro Gordillo
      Versión: 1.0
}


VAR
   tiempo: integer;
   horas: integer;     
   minutos: integer; 
   segundos: integer;

BEGIN
   
   Write('Introduzca un tiempo en segundos:');Read(tiempo);
   
   horas:= tiempo DIV 3600;
   minutos:=(tiempo MOD 3600) DIV 60;
   segundos:= (tiempo MOD 3600) MOD 60;

   Writeln(tiempo:0,' segundos son:', horas:2,' hh :',minutos:2,' mm : ',segundos:2,' ss'); { Ahora mostramos el resultado por pantalla }

END.
