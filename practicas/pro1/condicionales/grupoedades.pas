(* El siguiente programa pide nombre, apellidos y por otro lado edad y 
 * dice a que grupo de edad pertenecemos.  
 * 
 * Autor: Jose
 * Ver.:1.0
 * 
 *) 		
PROGRAM grupoedades (input,output);

VAR
	nombre:string(50);
	edad:Integer;
	grupo:string(50);
BEGIN

		Write('Nombre y apellidos: ');
		Readln(nombre);
		Write('Edad: ');
		Readln(edad);
		WHILE (edad<=0) DO
			BEGIN
				Writeln('Ha introducido una edad incorrecta...');
				Write('Introduzca su edad de nuevo: ');
				Readln(edad);
			END; {while}	
		
		IF ((edad>0) AND (edad<32)) THEN
			grupo:='JOVEN (<32)';
		IF (edad>=32)AND(edad<75) THEN
			grupo:='ADULTA (entre 32 y 74)';
		IF edad>=75 THEN		
			grupo:='ANCIANA (>=75)';
		  	
		Writeln(nombre,' es una persona ',grupo);

END.		     
