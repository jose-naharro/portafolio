PROGRAM gastomensual (input,output);

(**********************************************
   Este programa muestra el gasto semanal en cañas,tranporte y el total
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  nombre:String(30);
  cerveza,transporte,gastototal:Real;
  edad:Integer;

BEGIN

  Write('Introduzca su nombre: ');Readln(nombre);
  Write('Introduzca su edad: ');Readln(edad);
  Write('Introduzca el total de sus gastos semanales en cañas (en euros): ');Readln(cerveza);
  Write('Introduzca el total de sus gastos semanales en transporte (en euros): ');Readln(transporte);
  
  gastototal:=cerveza+transporte;
  
  Writeln('Nombre: ',nombre);
  Writeln('Edad: ',edad:0);
  Writeln('Gasto semanal en cañas: ',cerveza:0:2,' €');
  Writeln('Gasto semanal en transporte: ',transporte:0:2,' €');
  Writeln('Total gastos semanales: ',gastototal:0:2,' €');
  
  
END.
