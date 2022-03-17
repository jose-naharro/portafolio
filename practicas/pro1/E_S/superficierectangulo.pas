PROGRAM SuperficieRectangulo (input,output);

(**********************************************
   Este programa calcula superficie de un rectángulo introduciendo su base y altura
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  base,altura:Real;
  superficie:Real;

BEGIN

  Write('Introduzca la base del rectangulo: ');Readln(base);
  Write('Introduzca la altura del rectangulo: ');Readln(altura);
  superficie:=base*altura;
  Writeln('La superficie del rectángulo de base ',base:0:2,' y de altura ',altura:0:2,' es ',superficie:0:2);
  
END.
