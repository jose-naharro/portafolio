UNIT ListaEstatica;

(*********************************************************************
 * TITULO: Practicas de PROGRAMACION II
 * SUBTITULO: Practica 1
 * AUTOR: José Carlos Naharro Gordillo LOGIN: jose.naharro
 * GRUPO: 3.3
**********************************************************************)


INTERFACE

CONST
	NULO = 0;
	MAX =100;
	VOTONULO = 'N';
	VOTOBLANCO = 'B';
TYPE
	tNombrePartido = String;
	tNumVotos = Integer;
	tDato = RECORD
				nombrePartido: tNombrePartido;
				numVotos: tNumVotos;
			END;	
	tPosL=NULO..MAX;
	tListaOrdenada = RECORD
						Dato: ARRAY [1..MAX] OF tDato;
						fin:tPosL; {Marca la �ltima posici�n del array que est� ocupada}
					END;	
			
PROCEDURE listaVacia(VAR L:tListaOrdenada); 
FUNCTION insertarDato(d:tDato; VAR L : tListaOrdenada): boolean; 
PROCEDURE actualizarDato(VAR L:tListaOrdenada; p:tPosL; v:tNumVotos);
PROCEDURE eliminarPosicion(p:tPosL; VAR L : tListaOrdenada);
FUNCTION buscarDato(d:tDato; L:tListaOrdenada):tPosL;
FUNCTION esListaVacia(L:tListaOrdenada):boolean;
FUNCTION obtenerDato( p:tPosL; L : tListaOrdenada):tDato;
FUNCTION primera(L:tListaOrdenada):tPosL; 
FUNCTION ultima(L:tListaOrdenada):tPosL;
FUNCTION anterior(p:tPosL; L:tListaOrdenada):tPosL;
FUNCTION siguiente(p:tPosL; L:tListaOrdenada):tPosL; 


IMPLEMENTATION

PROCEDURE listaVacia(VAR L:tListaOrdenada);
(*************************************************************************************
 * Objetivo: Crea una lista vac�a.
 * Salida: Una lista vac�a.
 * Poscondici�n: La lista queda inicializada y vac�a.
 *************************************************************************************)
BEGIN
	L.fin := NULO;
END;

// ------------------------------------------------------------------------------------

FUNCTION posInser(d:tDato;L:tListaOrdenada):tPosL;
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
	p:tPosL;

BEGIN
	p:=primera(L);

	WHILE (d.nombrePartido>=L.Dato[p].nombrePartido) DO
		p:=p+1;

	posInser:=p;

END;		



// ------------------------------------------------------------------------------------

FUNCTION insertarDato(d:tDato; VAR L : tListaOrdenada):BOOLEAN;	
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
	p,q:tPosL;
	
BEGIN
	
	IF (L.fin = MAX) THEN
		insertarDato:=FALSE  //La lista est� llena y devuelve false
	ELSE BEGIN
		insertarDato:=TRUE;
		
		IF esListaVacia(L) OR (d.nombrePartido >= L.dato[L.fin].nombrePartido) THEN
			p:=L.fin+1
		
		ELSE BEGIN
			
			p:=posInser(d,L);
			
			FOR q:=L.fin DOWNTO p DO
				L.Dato[q+1]:=L.Dato[q];
		END;
		
		L.Dato[p]:=d;
		L.fin:=L.fin+1;
	END;		
END;
	
// ------------------------------------------------------------------------------------	
	
PROCEDURE actualizarDato(VAR L:tListaOrdenada; p:tPosL; v:tNumVotos);		
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
	L.Dato[p].numVotos := v;
END;	
	
// ------------------------------------------------------------------------------------		

PROCEDURE eliminarPosicion(p:tPosL; VAR L : tListaOrdenada);
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
	i:tPosL;
	
BEGIN
	
	L.fin := L.fin-1;
	
	FOR i := p TO L.fin DO
		L.Dato[i] := L.Dato[i+1];
END;

// ------------------------------------------------------------------------------------

PROCEDURE borrarLista(VAR L:tListaOrdenada);
(*************************************************************************************
 * Objetivo: Elimina todos los elementos de la lista
 * Entrada:
 * 		Lista: Lista a borrar
 * Salida: Lista vac�a
 *************************************************************************************)
BEGIN
	
	L.fin := NULO;
	
END;	

// ------------------------------------------------------------------------------------

FUNCTION buscarDato(d:tDato; L:tListaOrdenada):tPosL;	
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
	p: tPosL;
	
 BEGIN
	
	IF L.fin=0 THEN
		buscarDato:=NULO
	
	ELSE BEGIN	
	
		p:=1;
	
		WHILE (p <= L.fin) AND (L.Dato[p].nombrePartido <> d.nombrePartido) DO
			p:=p+1;
	
		IF {(p <= L.fin)} L.Dato[p].nombrePartido=d.nombrePartido THEN
			buscarDato := p
		ELSE
			buscarDato := NULO;
			
	END;
			
END;		
	
// ------------------------------------------------------------------------------------	
	
FUNCTION esListaVacia(L:tListaOrdenada):boolean;	
(*************************************************************************************
 * Objetivo: Determina si la lista est� vac�a.
 * Entrada:
 * 		Lista: Lista a comprobar.
 * Salida:
 * 		Verdadero si la lista est� vac�a, falso en caso contrario.
 *************************************************************************************)		
BEGIN
	EsListaVacia := (L.fin = NULO);
END;		
	
// ------------------------------------------------------------------------------------		
	
FUNCTION obtenerDato( p:tPosL; L : tListaOrdenada):tDato;	
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
	obtenerDato := L.Dato[p];
END;

// ------------------------------------------------------------------------------------	
	
FUNCTION primera(L:tListaOrdenada):tPosL;	
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
	primera := 1;
END;

// ------------------------------------------------------------------------------------	

FUNCTION ultima(L:tListaOrdenada):tPosL;	
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

	ultima := L.fin;
	
END;

// ------------------------------------------------------------------------------------

FUNCTION anterior(p:tPosL; L:tListaOrdenada):tPosL;
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
		anterior := NULO
	ELSE
		anterior := p-1;
		
END;		
		
// ------------------------------------------------------------------------------------	

FUNCTION siguiente(p:tPosL; L:tListaOrdenada):tPosL;
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
	
	IF p < L.fin THEN    {tambi�n puede ser p < ultima(L)}
		siguiente := p+1
	ELSE
		siguiente := NULO;

END;		

// ------------------------------------------------------------------------------------
	
END.	{fin de implementation}
