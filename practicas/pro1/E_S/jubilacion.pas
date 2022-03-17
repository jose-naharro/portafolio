PROGRAM jubilacion (input,output);

(**********************************************
   Este programa calcula los años que quedan para jubilarse
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  nombre,apellido1,apellido2:String(30);
  edad,aniosjubilacion:Integer;

BEGIN

  Write('Nombre: ');Readln(nombre);
  Write('1er Apellido: ');Readln(apellido1);
  Write('2º Apellido: ');Readln(apellido2);
  Write('Edad: ');Readln(edad);
  
  aniosjubilacion:=67-edad;
  
  Writeln('Sr/Sra.',apellido1,', le faltan aún ',aniosjubilacion:0,' años para jubilarse.');
  
END.
