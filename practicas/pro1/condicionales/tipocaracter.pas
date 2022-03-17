(* El siguiente programa pide un caracter y dice si es un caracter 
 * especial, una vocal, una consonante o un digito.  
 * 
 * Autor: Jose
 * Ver.:1.0
 * 
 *) 
 
 PROGRAM tipocaracter (input, output);
 
 VAR
	caracter:char;
 
 BEGIN
 
	Write('Introduzca un caracter ASCII: ');
	Readln(caracter);
	
	CASE caracter OF 
		chr(32)..'/',':'..'@','['..'`','{'..'~': Writeln(caracter,' es un caracter especial.');
	
		'0'..'9': Writeln(caracter,' es un dígito.');
	
	    'a','A','e','E','i','I','o','O','u','U': Writeln(caracter,' es una vocal.')
	OTHERWISE 
		Writeln(caracter,' es una consonante.');
	END;	
		
END.		
