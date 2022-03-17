PROGRAM saludo(input,output);
 (*********************************************************************************
  Este programa pide su nombre y edad y los muestra por pantalla en forma de saludo
  Autor: Jose Carlos
  Version: 1.0
************************************************************************************)

VAR
  
  nombre:string(10);
  edad:integer;

BEGIN
  
  Write('Introduzca su nombre por teclado: ');
  Readln(nombre);
  Write('Introduzca su edad por teclado: ');
  Readln(edad);
  Write('Buenos dias ',(nombre),',tiene usted ',(edad),'años');
  Writeln;

END.
