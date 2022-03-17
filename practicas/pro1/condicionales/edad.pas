(* El siguiente programa pide la fecha de nacimiento y la fecha actual
 * para calcular y sacar la edad. 
 * Autor: Jose
 * Ver.:1.0
 * 
 *)
 
PROGRAM edad (input, output);

VAR
	da,ma,aa:Integer;
	dn,mn,an:Integer;
	anios:integer;

BEGIN

	Write('Introduzca la fecha de nacimiento (dd mm aa): ');
	Read(dn,mn,an);
	Write('Introduzca la fecha de hoy (dd mm aa): ');
	Read(da,ma,aa);
	
	IF (ma<mn) THEN
		anios:=aa-an-1
	ELSE IF ((ma=mn) AND (da>=dn)) OR (ma>mn) THEN
			anios:=aa-an
		  ELSE				
		    anios:=aa-an-1;
			
	Writeln('Usted tiene ',anios:0,' años.');

END.	
