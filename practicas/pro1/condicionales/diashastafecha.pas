(* El siguiente programa pide un dia y mes y saca los dias 
 * trancurridos desde el 1 de Enero de un año. 
 * Autor: Jose
 * Ver.:1.0
 * 
 *)
 
PROGRAM diashastafecha (input, output);

VAR
	dia:1..31;
	mes:1..12;
	dias_totales:Integer;

BEGIN

	Write('Escriba dia y mes de este año separados por un espacio (dd mm): ');
	Readln(dia,mes);
	IF mes=1 THEN
		dias_totales:=dia
		ELSE IF mes=2 THEN
				dias_totales:=31+dia
			  ELSE IF mes=3 THEN
					  dias_totales:=31+29+dia 
				   ELSE IF mes=4 THEN
				           dias_totales:=31+29+31+dia
			            ELSE IF mes=5 THEN
					            dias_totales:=31+29+31+30+dia
					         ELSE IF mes=6 THEN
				                     dias_totales:=31+29+31+30+31+dia
			                      ELSE IF mes=7 THEN
					                      dias_totales:=31+29+31+30+31+30+dia 
					                   ELSE IF mes=8 THEN
				                               dias_totales:=31+29+31+30+31+30+31+dia
			                                ELSE IF mes=9 THEN
					                                dias_totales:=31+29+31+30+31+30+31+31+dia
				                                 ELSE IF mes=10 THEN
				                                         dias_totales:=31+29+31+30+31+30+31+31+30+dia
			                                          ELSE IF mes=11 THEN
					                                          dias_totales:=1+29+31+30+31+30+31+31+30+31+dia
					                                       ELSE IF mes=12 THEN
				                                                   dias_totales:=1+29+31+30+31+30+31+31+30+31+30+dia;
				                                               
	Writeln('La fecha introducida corresponde al dia ',dias_totales:0,' de este año.');
	
 END.					                        
