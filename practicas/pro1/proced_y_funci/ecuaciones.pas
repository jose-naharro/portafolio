PROGRAM ecuaciones (input,output);
(* Este programa calcula las raices reales e imaginarias de una ecuacion 
 * de 2º grado, introduciendo sus coeficientes mediante procedimiento.
   Autor: Jose
   Version: 1.0
*)
VAR
	opcion:char;

(************ PROCEDIMIENTO calculo de ecuacio de 2º grado ****************)
PROCEDURE raices;

VAR
	a,b,c: Real;
	discri:Real;
	x1,x2,PR,PI:Real;

BEGIN
	
	Writeln;
	Write('Introduzca el coeficiente A de una ecuación del tipo Ax²+Bx+C: ');
    Readln(a);
    WHILE a=0 DO
		BEGIN
			Writeln('El coeficiente A no puede ser 0...');
			Write('Introduzca el coeficiente A de una ecuación del tipo Ax²+Bx+C: ');
			Readln(a);
	END; {while}
	
	{ Ahora ya ho hace falta este primer IF, pero antes no estaba este 
	  WHILE ya que el programa se hizo sin saber WHILE, FOR o REPEAT.  }		
        
    IF a<>0 THEN
		BEGIN	
			Write('Introduzca el coeficiente B: ');
			Readln(b);
			Write('Introduzca el coeficiente C: ');
			Readln(c);
            Writeln;
			discri:=(b-4*a*c);
			
			IF discri>=0 THEN
				BEGIN
				  x1:=(-b+sqrt(discri))/(2*a);
				  x2:=(-b-sqrt(discri))/(2*a);
				  Writeln('Las raices reales son...');
				  Writeln;
				  Writeln('x1= ',x1:0:2,' y x2= ',x2:0:2);
				  Writeln;
				END
			ELSE IF discri<0 THEN
					BEGIN
						PR:=-b/(2*a);
						PI:=sqrt(-discri)/(2*a);
						Writeln('Las raices complejas son...');
						Writeln;
						Writeln('Parte real: ',PR:0:2);
						Writeln('Parte imaginaria: ',PI:0:2,' j');
						Writeln;
					END;
			
		END	 
	ELSE
		Writeln('A no puede ser 0');
		
END;	

(**********  PROGRAMA PRINCIPAL *****************)

BEGIN
	raices;
	REPEAT
		BEGIN
			Write('¿Desea calcular otra ecuación? S/N: ');
			Readln(opcion);
			CASE opcion OF
				'S','s':raices;
				'N','n':halt;
			OTHERWISE
				Writeln('No ha introducido una opción correcta...');
			END;		
	END;
	UNTIL ((opcion='n') OR (opcion='N'));
END.
