PROGRAM ContratoSueldo (input,output);

(**********************************************
   Este programa muestra los apellidos, tipo de contrato y sueldo
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  nombre,apellido1,apellido2,contrato:String(30);
  sueldomes,sueldoanual:Real;

BEGIN

  Write('Nombre: ');Readln(nombre);
  Write('1er Apellido: ');Readln(apellido1);
  Write('2º Apellido: ');Readln(apellido2);
  Write('Tipo de contrato (Indefinido,Eventual o Becario): ');Readln(contrato);
  Write('Sueldo mensual: ');Readln(sueldomes);
  
  sueldoanual:=12*sueldomes;
  
  Writeln('Sr/Sra.',apellido1,' ',apellido2,', está usted contratado/a como ',contrato,' por lo que le corresponde un sueldo anual de ',sueldoanual:0:2,' €');
  
END.
