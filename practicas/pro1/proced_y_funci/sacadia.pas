PROGRAM sacadia (input,output);
(***********************************************************************
  Este programa tiene un procedimiento que saca el nombre del dia de la
  semana segun la inicial introducida.
  Autor: Jose Carlos
  Version: 1.0
************************************************************************)
TYPE
	tDia=(L,M,X,J,V,S,D);
VAR
	dia:tDIA;

{ ****************      PROCEDIMIENTO           *********************** }

PROCEDURE nombredia(dia:tDia);

BEGIN

	
	CASE dia OF
		L: Writeln('Lunes');
		M: Writeln('Martes');
		X: Writeln('Miércoles');
		J: Writeln('Jueves');
		V: Writeln('Viernes');
		S: Writeln('Sábado');
		D: Writeln('Domingo');
	OTHERWISE
		Writeln('No ha introducido una inicial válida...');
	END; {case}	
END;

{ ******************* PROGRAMA PRINCIPAL ******************************}

BEGIN
    Write('Escriba la inicial de un dia de la semana (L,M,X,J,V,S,D): ');
	Readln(dia);
	
	nombredia;
	
END.	
