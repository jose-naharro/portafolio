PROGRAM ProductoEscalar (input,output);

(**********************************************
   Este programa calcula el producto escalar de dos vectores en el espacio euclídeo
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  v1,v2,v3,u1,u2,u3,productoescalar:Integer;

BEGIN

  Write('Coordenadas cartesianas del primer vector (separadas por espacios): ');Readln(v1,v2,v3);
  Write('Coordenadas cartesianas del segundo vector (separadas por espacios): ');Readln(u1,u2,u3);

  productoescalar:=v1*u1+v2*u2+v3*u3;
  
  Writeln('Producto escalar: ',productoescalar:0);
  
END.
