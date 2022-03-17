UNIT ListaDinamica;

(*********************************************************************
 * TITULO: Practicas de PROGRAMACION II
 * SUBTITULO: Practica 1
 * AUTOR: José Carlos Naharro Gordillo LOGIN: jose.naharro
 * GRUPO: 3.3
**********************************************************************)


INTERFACE

CONST
	NULO = NIL;
	VOTONULO = 'N';
	VOTOBLANCO = 'B';
TYPE
	tNombrePartido = String;
	tNumVotos = Integer;
	tDato = RECORD
				nombrePartido: tNombrePartido;
				numVotos: tNumVotos;
			END;	
	
	tPosL=^tNodo;
	
	tNodo= RECORD
				Dato:tDato;
				sig:tPosL;
			END;	
	
	tListaOrdenada = tPosL;
			
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
 * Objetivo: Crea una lista vacía.
 * Salida: Una lista vacía.
 * Poscondición: La lista queda inicializada y vacía.
 *************************************************************************************)
BEGIN
	
	L := NULO;
END;

// ------------------------------------------------------------------------------------

FUNCTION posInser(d:tDato;L:tListaOrdenada):tPosL;
(*************************************************************************************
 * Objetivo: Función auxiliar que da la posición donde queremos insertar un dato.
 *	
 * Entrada:
 * 		Dato: Contenido del elemento a insertar.
 * 		Lista: Lista donde vamos a insertar.
 * Salida:
 * 		Posición: Posición de inserción.
 * 	
 * Precondición:
 * 			Posición es una posición válida de la lista o es una posición nula.
 *************************************************************************************)
VAR
	p:tPosL;

BEGIN
	p:=primera(L);

	WHILE (p^.sig<>NULO) AND (d.nombrePartido >= p^.sig^.Dato.nombrePartido)  DO
		p:=p^.sig;

	posInser:=p;

END;		



// ------------------------------------------------------------------------------------

PROCEDURE crearNodo(d:tDato; VAR nuevo:tPosL);
(*************************************************************************************
 * Objetivo: Crea un nuevo nodo.
 * Entrada:
 * 		Dato: Contenido del elemento a insertar.
 * 		nuevo: posicion donde vamos a insertar.
 * Salida: Un nuevo nodo.
 *************************************************************************************)
BEGIN
	
	nuevo := NULO;
	new(nuevo);
	IF (nuevo <> NULO) THEN
		BEGIN
			nuevo^.Dato := d;
			nuevo^.sig := NULO;
	END;		
END;

// ------------------------------------------------------------------------------------

FUNCTION insertarDato(d:tDato; VAR L : tListaOrdenada):BOOLEAN;	
(*************************************************************************************
 * Objetivo: Inserta un elemento en la lista de forma ordenada en función del campo 
 *			 nombrePartido.
 * Entrada:
 * 		Dato: Contenido del elemento a insertar.
 * 		Lista: Lista donde vamos a insertar.
 * Salida:
 * 		Lista: Lista con el elemento Dato insertado
 * 	
 * Precondición:
 * 			Posición es una posición válida de la lista o es una posición nula.
 *************************************************************************************)
VAR
	ant,nuevo:tPosL;
	
BEGIN
	
	crearNodo(d,nuevo);
	
	IF (nuevo = NULO) THEN
		
		insertarDato:=FALSE
	
	ELSE 
		BEGIN
		
			insertarDato:=TRUE;
		
			IF esListaVacia(L) THEN
				L:=nuevo
		
			ELSE 
				BEGIN
			
					IF d.nombrePartido < L^.Dato.nombrePartido THEN 
						BEGIN
							nuevo^.sig := L;
							L := nuevo;
						END 
					ELSE 
						BEGIN
							ant := posInser(d,L);
							nuevo^.sig := ant^.sig;
							ant^.sig := nuevo;
					END;
			END;
		END;			
END;

// ------------------------------------------------------------------------------------	
	
PROCEDURE actualizarDato(VAR L:tListaOrdenada; p:tPosL; v:tNumVotos);		
(*************************************************************************************
 * Objetivo: Actualiza la información asociada al elemento situado en la posición 
 *           indicada.
 * Entrada:
 * 		Lista: Lista a modificar.
 * 		Posición: Posición del elemento que queremos modificar.
 * 		Dato: Nuevo contenido a asignar al elemento en Posición.
 * Salida:
 * 		Lista: Lista con el contenido del elemento modifcado.
 * Precondición:
 * 		Posición es una posición válida de la lista.	
 *************************************************************************************)	
BEGIN
	p^.Dato.numVotos := v;
END;	
	
// ------------------------------------------------------------------------------------		

PROCEDURE eliminarPosicion(p:tPosL; VAR L : tListaOrdenada);
(*************************************************************************************
 * Objetivo: Elimina de la lista el elemento de la posición dada.
 * Entrada: 
 * 		Posición: Posición del elemento a eliminar.
 * 		Lista: Lista a modifcar.
 * Salida:
 * 		Lista: Lista sin el elemento correspondiente a Posición.
 * Precondición:
 *		La posición es una posición válida de la lista.
 * Poscondición:
		La posición deja de ser válida.
 *************************************************************************************)

VAR
	q:tPosL;
	
BEGIN

	IF p=L THEN
		L := L^.sig
	ELSE
		IF p^.sig=NULO THEN
			BEGIN
				q:=L;
				WHILE q^.sig<>P DO
					q:=q^.sig;
				q^.sig:=NULO;
			END
		ELSE
			BEGIN
				q:=p^.sig;
				p^.Dato:=q^.Dato;
				p^.sig:=q^.sig;
				p:=q;
		END;	

	dispose(p);
	p:=NULO;
	
END;

// ------------------------------------------------------------------------------------

PROCEDURE borrarLista(VAR L:tListaOrdenada);
(*************************************************************************************
 * Objetivo: Elimina todos los elementos de la lista
 * Entrada:
 * 		Lista: Lista a borrar
 * Salida: Lista vacía
 *************************************************************************************)
BEGIN
	
	WHILE L <> NULO DO
		eliminarPosicion(primera(L),L);
	
END;	

// ------------------------------------------------------------------------------------

FUNCTION buscarDato(d:tDato; L:tListaOrdenada):tPosL;	
(*************************************************************************************
 * Objetivo: Devuelve la posición del primer elemento con el nombre de partido dado 
 * 			 en la lista (o NULO si el elemento no existe ).
 * Entrada:
 * 		Dato: Contenido del elemento buscado.
 * 		Lista: Lista donde realizar la búsqueda.
 * Salida:
 * 		Posición: Posición del elemento encontrado o nulo si no se encuentra.
 *************************************************************************************)	
VAR
	p: tPosL;
	

BEGIN
	p:=L;
	
	IF esListaVacia(L) THEN
		buscarDato:=NULO
	ELSE
		BEGIN
			WHILE (p<>NULO) AND (p^.dato.nombrePartido<>d.nombrePartido) DO
				p:=p^.sig;
			buscarDato:=p;
	END;
END;		
		
	
// ------------------------------------------------------------------------------------	
	
FUNCTION esListaVacia(L:tListaOrdenada):boolean;	
(*************************************************************************************
 * Objetivo: Determina si la lista está vacía.
 * Entrada:
 * 		Lista: Lista a comprobar.
 * Salida:
 * 		Verdadero si la lista está vacía, falso en caso contrario.
 *************************************************************************************)		
BEGIN
	esListaVacia := L= NULO;
END;		
	
// ------------------------------------------------------------------------------------		
	
FUNCTION obtenerDato( p:tPosL; L : tListaOrdenada):tDato;	
(*************************************************************************************
 * Objetivo: Obtiene los datos asociados al elemento que ocupa la posición indicada 
 *           de la lista.
 * Entrada:
 * 		Posición: Posición del elemento buscado.
 * 		Lista: Lista donde realizar la búsqueda.
 * Salida:
 * 		Dato: Contenido del elemento que está en Posición.
 * Precondición:
 * 		La posición dada tiene que ser válida.
 *************************************************************************************)
BEGIN
	obtenerDato := p^.Dato;
END;

// ------------------------------------------------------------------------------------	
	
FUNCTION primera(L:tListaOrdenada):tPosL;	
(*************************************************************************************
 * Objetivo: Devuelve la posición del primer elemento de la lista.
 * Entrada:
 * 		Lista: Lista a manipular.
 * Salida:
 * 		Posición: Posición del primer elemento.
 * Precondición: 
 * 		La lista no está vacía.
 *************************************************************************************)	
BEGIN
	primera := L;
END;

// ------------------------------------------------------------------------------------	

FUNCTION ultima(L:tListaOrdenada):tPosL;	
(*************************************************************************************
 * Objetivo: Devuelve la posición del último elemento de la lista. 
 * Entrada:
 * 		Lista: Lista a manipular.
 * Salida:
 * 		Posición: Posición del último elemento.
 * Precondición: 
 * 		La lista no está vacía.
 *************************************************************************************)	
	
BEGIN

	WHILE (L^.sig <> NULO) DO
		L := L^.sig;
	
	ultima := L;
	
END;

// ------------------------------------------------------------------------------------

FUNCTION anterior(p:tPosL; L:tListaOrdenada):tPosL;
(*************************************************************************************
 * Objetivo: Devuelve la posición del elemento anterior al actual.
 * Entrada:
 * 		Posición: Posición del elemento actual.
 * 		Lista: Lista a manipular.
 * Salida:
 * 		Posición del elemento anterior o nulo si es el primero.
 * Precondición: 
 * 		Posición es una posición válida de la lista.
 *************************************************************************************)
VAR
	q:tPosL;

	
BEGIN
	
	IF (p = L) THEN
		anterior := NULO
	ELSE
		BEGIN
			q:=L;
			
			WHILE (q^.sig<>p) DO
				q:=q^.sig;
			
			anterior := q;
	END;
		
END;		
		
// ------------------------------------------------------------------------------------	

FUNCTION siguiente(p:tPosL; L:tListaOrdenada):tPosL;
(*************************************************************************************
 * Objetivo: Devuelve la posición en la lista del siguiente elemento a la posición 
 *           indicada (o NULO si la posición no tiene siguiente).
 * Entrada:
 * 		Posición: Posición del elemento actual.
 * 		Lista: Lista a manipular.
 * Salida:
 * 		Posición: Posición del elemento siguiente o nulo si es el último.
 * Precondición: 
 *		Posición es una posición válida de la lista.
 *************************************************************************************)	
BEGIN
	
	siguiente := p^.sig;

END;		

// ------------------------------------------------------------------------------------
	
END.	{fin de implementation}
