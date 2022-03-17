PROGRAM buscarenarray (input,output);

(* Este programa pide que metas un array y luego le des un entero, si está, 
 * te devuelve las posiciones y si no está, también te lo dice.
   
   Autor: Jose
   Version: 1.0
*)

		
TYPE
	
	ListaDeEnteros= ARRAY [1..1000] OF INTEGER;
	TipoLista= ListaDeEnteros;
	TipoElementoLista= Integer;
	
VAR
	numeros:TipoLista;
	NumElementos:Integer;
	opcion:char;
	
	
(******************   SECCION DE SUBPROGRAMAS ************************
 *********************************************************************)

PROCEDURE MostrarMenu;
(********* Este procedimiento saca el menu con las opciones disponibles **********)

BEGIN
	Writeln;
	Writeln('  *********** MENU **********');
	Writeln;
	Writeln('   1.- Introducir un array.');
	Writeln('   2.- Sacar el array ordenado.');
	Writeln('   3.- Ver el valor máximo.');
	Writeln('   4.- Ver el valor míximo.');
	Writeln('   5.- Ver la media de los valores.');
	Writeln('   6.- Salir.');
	Writeln;
	Writeln('  ***************************');
	Writeln;


END; { Fin de MostrarMenu }



PROCEDURE pedir (VAR Elementos:TipoLista;VAR NumElementos:Integer);  
(**************** Este procedimiento pide el array *************************)
	
BEGIN
	Write('Introduzca los elementos del array separados por espacios, <enter> para terminar: ');
	
	NumElementos:=0;

	WHILE NOT eoln DO
	BEGIN 	
		NumElementos:=NumElementos+1;
		Read(Elementos[NumElementos]);	
	END;
	Readln;	
	IF NumElementos=0 THEN
		Writeln('No hay ningún elemento en el array.');

END; {Fin pedir}


PROCEDURE  ordenar (VAR Elemento:TipoLista; n:Integer);
(*************  Este procedimiento ORDENA un array *************************)

VAR
	Menor:TipoElementoLista;
	PosMenor,i,j: Integer;
	
BEGIN
	
	FOR i:=1 TO n-1 DO
		BEGIN (* encontrar el menor elemento de la sublista **)
			PosMenor:=i;
			Menor:=Elemento[PosMenor];
			FOR j:=i+1 TO n DO
				IF Elemento[j]<Menor THEN (* encontrado el menor elemento *)
					BEGIN
						PosMenor:=j;
						Menor:=Elemento[j];
					END; {IF} { del FOR no hace falta...}
			Elemento[PosMenor]:=Elemento[i];
			Elemento[i]:=Menor;
	END;{FOR i}

END;{Fin Ordenar}					
			

PROCEDURE sacar (VAR Elementos:TipoLista;VAR NumElementos:Integer);
(************* Este subprograma saca la lista de elementos introducidos (ordenada) ****)

VAR
	i:Integer;

BEGIN

	IF NumElementos=0 THEN
		Writeln('No hay ningún elemento en el array.')
	ELSE
		BEGIN	
			Write('Estos son los elementos,ordenados de menor a mayor, que has introducido: ');
	
			FOR i:=1 TO NumElementos DO
				Write(Elementos[i]:0,' ');
	
	END; { Fin del IF }
		Writeln;	


END; { Fin sacar}	


PROCEDURE sacarmaximo (VAR Elementos:TipoLista;VAR NumElementos:Integer);
(************* Este subprograma saca el valor máximo *****************************)


BEGIN

	IF NumElementos=0 THEN
		Writeln('No hay ningún elemento en el array.')
	ELSE	
	Writeln('El valor máximo es el ',Elementos[NumElementos]:0,'.' );


END; { Fin sacarmaximo}

PROCEDURE sacarminimo (VAR Elementos:TipoLista;VAR NumElementos:Integer);
(************* Este subprograma saca el valor mínimo *****************************)


BEGIN

	IF NumElementos=0 THEN
		Writeln('No hay ningún elemento en el array.')
	ELSE	
	Writeln('El valor mínimo es el ',Elementos[1]:0,'.' );


END; { Fin sacarmaximo}	


PROCEDURE sacarmedia (VAR Elementos:TipoLista;VAR NumElementos:Integer);
(************* Este subprograma saca el valor mínimo *****************************)

FUNCTION media (VAR Elementos:TipoLista;VAR NumElementos:Integer):Real;
(************* Este subprograma saca la media de elementos introducidos ****)

VAR
	i:Integer;
	sumatorio:Integer VALUE 0;
	mediatotal:Real;
BEGIN

	FOR i:=1 TO NumElementos DO
		sumatorio:=Elementos[i]+sumatorio;
	
	mediatotal:=sumatorio/NumElementos;
	
	media:=mediatotal; (* asignacion del valor a la función *)	

END; { Fin funcion media }

(*** Inicio del Procedimiento sacarmedia **)

BEGIN

	IF NumElementos=0 THEN
		Writeln('No hay ningún elemento en el array.')
	ELSE	
	Writeln('La media de los elementos introducidos es ',media(numeros,NumElementos):0:2,'.');


END; { Fin sacarmedia }	




(******************     PROGRAMA PRINCIPAL    ************************)

BEGIN
	
	REPEAT
	  BEGIN	
		Writeln;
		MostrarMenu;
		Write('Elija una opción de este menú: ');
		Readln(opcion);
		CASE opcion OF
		
			'1': pedir(numeros,NumElementos);
			
			'2': BEGIN
					ordenar(numeros,NumElementos);
					sacar(numeros,NumElementos);
				 END;
			
			'3': BEGIN
					ordenar(numeros,NumElementos);
					sacarmaximo(numeros,NumElementos);
				 END;	
			
			'4': BEGIN
					ordenar(numeros,NumElementos);
					sacarminimo(numeros,NumElementos);
				 END;
			
			'5': sacarmedia(numeros,NumElementos);
			
			'6': halt; 
		
		OTHERWISE 
			Writeln('Opción no válida...');
		
		END; {case}
	
	END; {repeat}	
	
	UNTIL opcion='6';	

END.
