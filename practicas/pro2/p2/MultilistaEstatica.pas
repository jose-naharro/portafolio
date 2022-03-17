UNIT MultilistaEstatica;

(*********************************************************************
 * TITULO: Practicas de PROGRAMACION II
 * SUBTITULO: Practica 1
 * AUTOR: Jos� Carlos Naharro Gordillo LOGIN: jose.naharro
 * GRUPO: 3.3
**********************************************************************)


INTERFACE

USES ListaLocal;

CONST
	NULOM = 0;
	MAX =4;
	VOTONULO = 'N';
	VOTOBLANCO = 'B';
TYPE
	tNombreColegio = String;
	tNumVotantes = Integer;
	tDatoM = RECORD
				nombreColegio: tNombreColegio;
				numVotantes: tNumVotantes;
				finalizado: BOOLEAN;
				recuentoVotos: tListaLocal;
			END;	
	tPosM=NULOM..MAX;
	tMultilista = RECORD
						DatoM: ARRAY [1..MAX] OF tDatoM;
						finM:tPosM; {Marca la �ltima posici�n del array que est� ocupada}
					END;	
			
PROCEDURE MultilistaVacia(VAR M:tMultilista);
FUNCTION posInser(d:tDatoM; M:tMultilista):tPosM;
FUNCTION InsertarDatoM(d:tDatoM; VAR M : tMultilista): boolean;
PROCEDURE ActualizarDatoM(p:tPosM; VAR M:tMultilista; d:tDatoM);
PROCEDURE EliminarPosicionM(p:tPosM; VAR M : tMultilista); 
PROCEDURE borrarMultilista(VAR M:tMultilista);
FUNCTION BuscarDatoM(d:tNombreColegio; M:tMultilista):tPosM;
FUNCTION esMultilistaVacia(M:tMultilista):boolean; 
FUNCTION ObtenerDatoM( p:tPosM; M : tMultilista):tDatoM; 
FUNCTION PrimeraM(M:tMultilista):tPosM; 
FUNCTION UltimaM(M:tMultilista):tPosM; 
FUNCTION AnteriorM(p:tPosM; M:tMultilista):tPosM; 
FUNCTION SiguienteM(p:tPosM; M:tMultilista):tPosM;


IMPLEMENTATION

PROCEDURE MultilistaVacia(VAR M:tMultilista);
(*************************************************************************************
 * Objetivo: Crea una lista vac�a.
 * Salida: Una lista vac�a.
 * Poscondici�n: La lista queda inicializada y vac�a.
 *************************************************************************************)
BEGIN
	M.finM := NULOM;
END;

// ------------------------------------------------------------------------------------

FUNCTION posInser(d:tDatoM; M:tMultilista):tPosM;
(*************************************************************************************
 * Objetivo: Funci�n auxiliar que da la posici�n donde queremos insertar un dato.
 *	
 * Entrada:
 * 		Dato: Contenido del elemento a insertar.
 * 		Lista: Lista donde vamos a insertar.
 * Salida:
 * 		Posici�n: Posici�n de inserci�n.
 * 	
 * Precondici�n:
 * 			Posici�n es una posici�n v�lida de la lista o es una posici�n nula.
 *************************************************************************************)
VAR
	p:tPosM;

BEGIN
	p:=PrimeraM(M);

	WHILE (d.nombreColegio>=M.DatoM[p].nombreColegio) DO
		p:=p+1;

	posInser:=p;

END;		



// ------------------------------------------------------------------------------------

FUNCTION InsertarDatoM(d:tDatoM; VAR M : tMultilista): boolean;
(*************************************************************************************
 * Objetivo: Inserta un elemento en la lista de forma ordenada en funci�n del campo 
 *			 nombrePartido.
 * Entrada:
 * 		Dato: Contenido del elemento a insertar.
 * 		Lista: Lista donde vamos a insertar.
 * Salida:
 * 		Lista: Lista con el elemento Dato insertado
 * 	
 * Precondici�n:
 * 			Posici�n es una posici�n v�lida de la lista o es una posici�n nula.
 *************************************************************************************)
VAR
	p,q:tPosM;
	
BEGIN
	
	IF (M.finM = MAX) THEN
		InsertarDatoM:=FALSE  //La lista est� llena y devuelve false
	ELSE BEGIN
		InsertarDatoM:=TRUE;
		
		IF esMultilistaVacia(M) OR (d.nombreColegio >= M.DatoM[M.finM].nombreColegio) THEN
			p:=M.finM+1
		
		ELSE BEGIN
			
			p:=posInser(d,M);
			
			FOR q:=M.finM DOWNTO p DO
				M.DatoM[q+1]:=M.DatoM[q];
		END;
		
		M.DatoM[p]:=d;
		M.finM:=M.finM+1;
	END;		
END;
	
// ------------------------------------------------------------------------------------	
	
PROCEDURE ActualizarDatoM(p:tPosM; VAR M:tMultilista; d:tDatoM);	
(*************************************************************************************
 * Objetivo: Actualiza la informaci�n asociada al elemento situado en la posici�n 
 *           indicada.
 * Entrada:
 * 		Lista: Lista a modificar.
 * 		Posici�n: Posici�n del elemento que queremos modificar.
 * 		Dato: Nuevo contenido a asignar al elemento en Posici�n.
 * Salida:
 * 		Lista: Lista con el contenido del elemento modifcado.
 * Precondici�n:
 * 		Posici�n es una posici�n v�lida de la lista.	
 *************************************************************************************)	
BEGIN
	M.DatoM[p].numVotantes := d.numVotantes;
END;	
	
// ------------------------------------------------------------------------------------		

PROCEDURE EliminarPosicionM(p:tPosM; VAR M : tMultilista); 
(*************************************************************************************
 * Objetivo: Elimina de la lista el elemento de la posici�n dada.
 * Entrada: 
 * 		Posici�n: Posici�n del elemento a eliminar.
 * 		Lista: Lista a modifcar.
 * Salida:
 * 		Lista: Lista sin el elemento correspondiente a Posici�n.
 * Precondici�n:
 *		La posici�n es una posici�n v�lida de la lista.
 * Poscondici�n:
		La posici�n deja de ser v�lida.
 *************************************************************************************)

VAR
	i:tPosM;
	
BEGIN
	
	M.finM := M.finM-1;
	
	FOR i := p TO M.finM DO
		M.DatoM[i] := M.DatoM[i+1];
END;

// ------------------------------------------------------------------------------------

PROCEDURE borrarMultilista(VAR M:tMultilista);
(*************************************************************************************
 * Objetivo: Elimina todos los elementos de la lista
 * Entrada:
 * 		Lista: Lista a borrar
 * Salida: Lista vac�a
 *************************************************************************************)
BEGIN
	
	M.finM := NULOM;
	
END;	

// ------------------------------------------------------------------------------------

FUNCTION BuscarDatoM(d:tNombreColegio; M:tMultilista):tPosM;	
(*************************************************************************************
 * Objetivo: Devuelve la posici�n del primer elemento con el nombre de partido dado 
 * 			 en la lista (o NULO si el elemento no existe ).
 * Entrada:
 * 		Dato: Contenido del elemento buscado.
 * 		Lista: Lista donde realizar la b�squeda.
 * Salida:
 * 		Posici�n: Posici�n del elemento encontrado o nulo si no se encuentra.
 *************************************************************************************)	
VAR
	p: tPosM;
	
 BEGIN
	
	IF M.finM=0 THEN
		BuscarDatoM:=NULOM
	
	ELSE BEGIN	
	
		p:=1;
	
		WHILE (p <= M.finM) AND (M.DatoM[p].nombreColegio < d) DO
			p:=p+1;
	
		IF {(p <= L.fin)} M.DatoM[p].nombreColegio=d THEN
			BuscarDatoM := p
		ELSE
			BuscarDatoM := NULOM;
			
	END;
			
END;		
	
// ------------------------------------------------------------------------------------	
	
FUNCTION esMultilistaVacia(M:tMultilista):boolean; 
(*************************************************************************************
 * Objetivo: Determina si la lista est� vac�a.
 * Entrada:
 * 		Lista: Lista a comprobar.
 * Salida:
 * 		Verdadero si la lista est� vac�a, falso en caso contrario.
 *************************************************************************************)		
BEGIN
	esMultilistaVacia := (M.finM = NULOM);
END;		
	
// ------------------------------------------------------------------------------------		
	
FUNCTION ObtenerDatoM( p:tPosM; M : tMultilista):tDatoM; 	
(*************************************************************************************
 * Objetivo: Obtiene los datos asociados al elemento que ocupa la posici�n indicada 
 *           de la lista.
 * Entrada:
 * 		Posici�n: Posici�n del elemento buscado.
 * 		Lista: Lista donde realizar la b�squeda.
 * Salida:
 * 		Dato: Contenido del elemento que est� en Posici�n.
 * Precondici�n:
 * 		La posici�n dada tiene que ser v�lida.
 *************************************************************************************)
BEGIN
	ObtenerDatoM := M.DatoM[p];
END;

// ------------------------------------------------------------------------------------	
	
FUNCTION PrimeraM(M:tMultilista):tPosM; 
(*************************************************************************************
 * Objetivo: Devuelve la posici�n del primer elemento de la lista.
 * Entrada:
 * 		Lista: Lista a manipular.
 * Salida:
 * 		Posici�n: Posici�n del primer elemento.
 * Precondici�n: 
 * 		La lista no est� vac�a.
 *************************************************************************************)	
BEGIN
	PrimeraM := 1;
END;

// ------------------------------------------------------------------------------------	

FUNCTION UltimaM(M:tMultilista):tPosM;	
(*************************************************************************************
 * Objetivo: Devuelve la posici�n del �ltimo elemento de la lista. 
 * Entrada:
 * 		Lista: Lista a manipular.
 * Salida:
 * 		Posici�n: Posici�n del �ltimo elemento.
 * Precondici�n: 
 * 		La lista no est� vac�a.
 *************************************************************************************)	
	
BEGIN

	UltimaM := M.finM;
	
END;

// ------------------------------------------------------------------------------------

FUNCTION AnteriorM(p:tPosM; M:tMultilista):tPosM; 
(*************************************************************************************
 * Objetivo: Devuelve la posici�n del elemento anterior al actual.
 * Entrada:
 * 		Posici�n: Posici�n del elemento actual.
 * 		Lista: Lista a manipular.
 * Salida:
 * 		Posici�n del elemento anterior o nulo si es el primero.
 * Precondici�n: 
 * 		Posici�n es una posici�n v�lida de la lista.
 *************************************************************************************)
	
BEGIN
	
	IF p = 1 THEN
		AnteriorM := NULOM
	ELSE
		AnteriorM := p-1;
		
END;		
		
// ------------------------------------------------------------------------------------	

FUNCTION SiguienteM(p:tPosM; M:tMultilista):tPosM;
(*************************************************************************************
 * Objetivo: Devuelve la posici�n en la lista del siguiente elemento a la posici�n 
 *           indicada (o NULO si la posici�n no tiene siguiente).
 * Entrada:
 * 		Posici�n: Posici�n del elemento actual.
 * 		Lista: Lista a manipular.
 * Salida:
 * 		Posici�n: Posici�n del elemento siguiente o nulo si es el �ltimo.
 * Precondici�n: 
 *		Posici�n es una posici�n v�lida de la lista.
 *************************************************************************************)	
BEGIN
	
	IF p < M.finM THEN    {tambi�n puede ser p < ultima(L)}
		SiguienteM := p+1
	ELSE
		SiguienteM := NULOM;

END;		

// ------------------------------------------------------------------------------------
	
END.	{fin de implementation}
