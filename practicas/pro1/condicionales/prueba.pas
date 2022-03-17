program dias_semana (input,output); (* muestra los dias de la semana con tipos enumerados *)
type
dia_semana = (lunes,martes,miercoles,jueves,viernes,sabado,domingo);
var
dias :dia_semana;
begin
Write('fdsafasdfa: ' );
Readln(dias);
case dias of
lunes :writeln('lunes ');
martes :writeln('martes ');
miercoles :writeln('miercoles');
jueves :writeln('jueves ');
viernes :writeln('viernes ');
sabado :writeln('sabado ');
domingo :writeln('domingo ')
end;
end.
