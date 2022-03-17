PROGRAM tablamultiplicar (input,output);
(* Este programa saca por pantalla la tabla de multiplicar de un numero introducido por el usuario
   Autor: Jose
   Version: 1.0
*)


VAR
        numero,resultado: Integer;
        i:0..10;

BEGIN

        Write('¿De qué número quiere hacer la tabla de multiplicar? ');
        Readln (numero);
        Writeln;
        Writeln('TABLA DE MULTIPLICAR DEL ',numero:0);
        Writeln;

        FOR i:=0 to 10 DO
          BEGIN
          resultado:=numero*i;
          Writeln(numero:10,' x ',i:0,' = ',resultado:0);
          END;
END.
