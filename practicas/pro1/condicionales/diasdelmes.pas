(* El siguiente programa calcula los dias que tiene un mes introducido 
 * por el usuario, si es febrero tambien preguntará el año para saber si 
 * es bisiesto.  
 * 
 * Autor: Jose
 * Ver.:1.0
 * 
 *) 
 
PROGRAM diasdelmes (input, output);

{TYPE
	tMes=(Enero,Febrero,Marzo,Abril,Mayo,Junio,Julio,Agosto,Septiembre,Octubre,Noviembre,Diciembre);}
VAR

	mes:string(10);
	anio:integer;
	
BEGIN

	
	Write('Introduzca el mes del que quiera calcular los días: ');
	Readln(mes);
	
	IF (mes='Enero') or (mes='Marzo') or (mes='Mayo') or (mes='Julio') or (mes='Agosto') or (mes='Octubre') or (mes='Diciembre') or
	   (mes='enero') or (mes='marzo') or (mes='mayo') or (mes='julio') or (mes='agosto') or (mes='octubre') or (mes='diciembre') THEN 
		Writeln('El mes de ',mes,' tiene 31 días.');
	IF (mes='Abril') or (mes='Junio') or (mes='Septiembre') or (mes='Noviembre') or
	   (mes='abril') or (mes='junio') or (mes='septiembre') or (mes='noviembre') THEN
	   	Writeln('El mes de ',mes,' tiene 30 días.');
	IF (mes='Febrero') or (mes='febrero') THEN
		BEGIN
			Write('Introduzca el año: ');
			Readln(anio);
			IF ((anio MOD 4 = 0) AND (anio MOD 100 <> 0)) OR (anio MOD 400 = 0) THEN
			   Writeln('El mes de ',mes,' tiene 29 días.')
			ELSE   
				Writeln('El mes de ',mes,' tiene 28 días.');
				
		END
		
END.
