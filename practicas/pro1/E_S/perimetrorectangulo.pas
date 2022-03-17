PROGRAM PerimetroRectangulo (input,output);

(**********************************************
   Este programa calcula el preímetro de un rectángulo introduciendo base y altura
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  base,altura:Real;
  perimetro:Real;

BEGIN

  Write('Introduzca la base del rectángulo: ');Readln(base);
  Write('Introduzca la altura del rectángulo: ');Readln(altura);
  perimetro:=2*base+2*altura;
  Writeln('El perímetro del rectángulo de base ',base:0:2,' y de altura ',altura:0:2,' es ',perimetro:0:2);
  
END.
