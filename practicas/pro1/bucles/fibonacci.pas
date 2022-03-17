(* FIBONACCI. Este ejercicio calcula la serie de Fibonacci hasta el 
 * término que introduzca el usuario.
 * Autor: Jose
 *)
 
PROGRAM fibonacci (input,output);
 
VAR
	i:Integer VALUE 4;
	n:Integer;
	a,b,c:Integer;
BEGIN

	Write('Escriba de que orden quiere la serie de Fibonacci: ');
	Readln(n);
	WHILE n<=0 DO
		BEGIN
			Writeln('Tiene que ser un término mayor que 0...');
			Write('Ingrese de nuevo el término: ');
			Readln(n);
	END; {while}
	
	a:=1;
	b:=1;
	c:=0;
	IF n=1 THEN
		Write(a:0)
	ELSE IF n=2 THEN
			Write(a:0,' ',b:0)
	ELSE IF n=3 THEN
			BEGIN
				c:=a+b;
				Write(a:0,' ',b:0,' ',c:0);
			END
	ELSE IF n>3 THEN
			BEGIN
				c:=a+b;
				Write(a:0,' ',b:0,' ',c:0);
				FOR i:=4 TO n DO
					BEGIN
						a:=b;
						b:=c;
						c:=a+b;
						Write(' ',c:0);
				END; {for}
	END; {if}
Writeln;
END.						
