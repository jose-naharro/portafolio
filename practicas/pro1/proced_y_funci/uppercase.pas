PROGRAM pasaramayusculas (input,output);
(* Este programa pide una frase por teclado y la devuelve en mayúsculas.
   Autor: Jose
   Version: 1.0
*)
VAR
	c:char;
	
(******** FUNCION para pasar de minisculas a mayusculas ************)

FUNCTION uppercase(c:char):char;
VAR
	caracter:char;
BEGIN

	IF ((c>='a') AND (c<='z')) THEN
		caracter:=chr(ord(c)-ord('a')+ord('A'))
	ELSE
		caracter:=c;
		
	uppercase:=caracter;
END;

(************** PROGRAMA PRINCIPAL *************)

BEGIN
	Writeln('Escribe una frase y cuando quieras pasarla a mayúsculas pulsa enter');
	WHILE NOT eoln DO
		BEGIN
			Read(c);
			Write(uppercase(c));
	END; {while}
	Writeln;
END.			
