(* FIBONACCI II. Este ejercicio calcula la serie de Fibonacci hasta el 
 * término que introduzca el usuario. PERO solo saca ese término.
 * Autor: Jose
 *)
 
PROGRAM solounofibonacci (input,output);
 
VAR
	i:Integer VALUE 4;
	n:Integer;
	a,b,c:Integer;
	opcion:Char;
BEGIN
  REPEAT
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
			Write(b:0)
	ELSE IF n=3 THEN
			BEGIN
				c:=a+b;
				Write(c:0);
			END
	ELSE IF n>3 THEN
			BEGIN
				c:=a+b;
				FOR i:=4 TO n DO
					BEGIN
						a:=b;
						b:=c;
						c:=a+b;
				END; {for}
				Write(c:0);
	END; {if}
	REPEAT
	BEGIN
	Writeln;
	Write('Quiere saber el valor de otro término? (S/N): ');
	Readln(opcion);
	CASE opcion OF
		's','S': Writeln;
		'n','N': halt;
  	OTHERWISE
		Writeln('No ha escrito una opción correcta...');
	END; {case}	
	END; {prepeat}
	UNTIL ((opcion='n') OR (opcion='N') OR (opcion='s') OR (opcion='S'));
  END; {repeat}
  UNTIL ((opcion='n') OR (opcion='N'));	
END.						
