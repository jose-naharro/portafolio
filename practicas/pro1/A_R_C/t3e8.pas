program t3e8(Input, Output);

var cont, temp, index: integer;

begin

cont := 0;

{Contador, nº de veces que la temperatura es inferior a 0 grados.}

for index := 1 to 7 do

begin

write('Dime temperatura(',index,'): ');

readln(temp);

{ Leer temperatura desde teclado}

if temp < 0 then cont := cont + 1

{ Si es menor que cero, incrementar el contador}

end;

write('La temperatura fue ',cont);

{ Escribir resultado por pantalla.}

if cont = 1 then write(' vez ') else write(' veces ');

writeln('inferior a cero.')

end.
