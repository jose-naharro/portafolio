PROGRAM Cuadrado (input,output);

(**********************************************
   Este programa calcula el area de un cuadrado
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  lado:Real;
  superficie:Real;

BEGIN

  Write('Introduzca el lado de su cuadrado en metros: ');
  Readln(lado);
  superficie:=lado*lado;
  Writeln('La superficie de su cuadrado es de: ',superficie:0:2,' metros cuadrados.');
  
END.
