PROGRAM SueldoMensual (input,output);

(**********************************************
   Este programa muestra los nombre, edad, número de hijos y el sueldo mensual (se introduce sueldo anual)
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  nombre:String(30);
  sueldomes,sueldoanual:Real;
  edad,hijos:Integer;

BEGIN

  Write('Introduzca su nombre: ');Readln(nombre);
  Write('Introduzca su edad: ');Readln(edad);
  Write('Introduzca su número de hijos: ');Readln(hijos);
  Write('Introduzca su sueldo anual (en euros): ');Readln(sueldoanual);
  
  sueldomes:=sueldoanual/12;
  
  Writeln('Nombre: ',nombre);
  Writeln('Edad: ',edad:0);
  Writeln('Número de hijos: ',hijos:0);
  Writeln('Sueldo mensual: ',sueldomes:0:2,' €');
  
END.
