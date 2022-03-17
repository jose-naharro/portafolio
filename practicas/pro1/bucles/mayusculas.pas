PROGRAM EJERC104 (input,output); 

VAR i, mayus:INTEGER;
	cadena:string(255);

BEGIN
     Write('Escribe una frase: ');
     Readln(cadena);

     FOR i:=1 TO LENGTH(cadena) DO
     BEGIN
          IF cadena[i] = maxstring(cadena[i]) THEN
             mayus:=mayus + 1;
          IF cadena[i]=' ' THEN
             mayus:=mayus - 1;

     END;

     WRITELN ('El numero de mayúsculas es: ', mayus);

END.

