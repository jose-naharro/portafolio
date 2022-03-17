PROGRAM AreaTriangulo (input,output);

(**********************************************
   Este programa calcula superficie de un triangulo introduciendo base y altura
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  base,altura:Real;
  superficie:Real;

BEGIN

  Write('Introduzca la base del triangulo: ');Readln(base);
  Write('Introduzca la altura del triangulo: ');Readln(altura);
  superficie:=0.5*base*altura;
  Writeln('La superficie del triangulo de base ',base:0:2,' y de altura ',altura:0:2,' es ',superficie:0:2);
  
END.
