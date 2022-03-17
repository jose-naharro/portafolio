(* El siguiente programa calcula el sueldo mensual, se cobran 30€ día si 
 * trabaja 15 días, a partir de ahí cada día extra se cobra un 50 % más 
 * cada día.
 * Se descuenta también IRPF y SS, 17 y 6 % respectivamente  
 * 
 * Autor: Jose
 * Ver.:1.0
 * 
 *)
PROGRAM salario (input,output);
VAR
	dias:1..31;
	sueldo:Real;
	
BEGIN

Write('Introduzca el número de días que ha trabajado este mes: ');
Readln(dias);

IF (15<dias) THEN
	sueldo:=(15*30+(dias-15)*45)*(1-0.17-0.06)
ELSE IF (dias<=15) THEN
		sueldo:=(dias*30)*(1-0.17-0.06);
	 
			
Writeln('Salario mensual: ',sueldo:0:2,' €.');

END.			
