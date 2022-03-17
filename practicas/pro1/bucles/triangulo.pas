(* Este programa saca el triangulo de floyd hasta un numero que introduce
 * el usuario.
 * 
 * Autor: Jose
 * Version: 1.0
 *)

PROGRAM floyd(input,output);

VAR
	n:integer;
	i:integer;
	fila:integer;
	numero:integer;

BEGIN
 
	Write('Hasta que número entero tiene que llegar el triangulo de Floyd? ');
	Read(numero);
    i:=1;
    fila:=1;
    n:=1;
	WHILE n<=numero DO
		BEGIN
			WHILE ((n<=numero)AND(i<=fila)) DO
				BEGIN
					Write(n:3,'  ');
                    n:=n+1;
                    i:=i+1;
             END; {while2}
             i:=1;
             fila:=fila+1;
             writeln;
    END;{while 1}
END.
