(* Problema de bucles Teoria primer parcial. 
* Calcula la suma de numeros que va introduciendo el usuario
* cuando introduce 0 se saca la suma de todos los que introdujo.
* 
* Autor: Jose
* Version:1.0
*)

PROGRAM sumas (input,output);

VAR
	n:Real;
	suma: Real VALUE 0;
	
BEGIN

	Writeln('Se van a sumar todos los números reales que introduzca...');
	Writeln('Cuando quiera saber la suma introduzca un 0.');
	Write('Introducza un número: ');
	Readln(n);
	
	WHILE n <> 0 DO
		BEGIN
		 suma:=suma+n;
		 Write('Introducza otro número: ');
		 Readln(n);
	END; {while}
	
	Writeln('La suma de todos los números introducidos hasta ahora es: ',suma:0:2);	
	
END.	 
