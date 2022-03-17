UNIT ListaLocal;

(*********************************************************************
 * TITULO: Practicas de PROGRAMACION II
 * SUBTITULO: Practica 2
 * AUTOR: José Carlos Naharro Gordillo LOGIN: jose.naharro
 * GRUPO: 3.3
**********************************************************************)


INTERFACE

CONST
	NULOL = NIL;
	VOTONULO = 'N';
	VOTOBLANCO = 'B';
TYPE
	tNombrePartido = String;
	tNumVotos = Integer;
	tDatoL = RECORD
				nombrePartido: tNombrePartido;
				numVotos: tNumVotos;
			END;	
	
	tPosL=^tNodoL;
	
	tNodoL= RECORD
				DatoL:tDatoL;
				sigL:tPosL;
			END;	
	
	tListaLocal = tPosL;
			
PROCEDURE listaVacia(VAR L:tListaLocal);
FUNCTION InsertarDatoL(d:tDatoL; VAR L : tListaLocal): boolean;
PROCEDURE ActualizarDatoL(VAR L:tListaLocal; p:tPosL; d:tnumVotos);
PROCEDURE EliminarPosicionL(p:tPosL; VAR L : tListaLocal); 
FUNCTION BuscarDatoL(d:tnombrePartido; L:tListaLocal):tPosL;
FUNCTION esListaVacia(L:tListaLocal):boolean; 
FUNCTION ObtenerDatoL( p:tPosL; L : tListaLocal):tDatoL; 
FUNCTION PrimeraL(L:tListaLocal):tPosL; 
FUNCTION UltimaL(L:tListaLocal):tPosL; 
FUNCTION AnteriorL(p:tPosL; L:tListaLocal):tPosL; 
FUNCTION SiguienteL(p:tPosL; L:tListaLocal):tPosL;


IMPLEMENTATION

PROCEDURE listaVacia(VAR L:tListaLocal);
(*************************************************************************************
 * Objetivo: Crea una lista vacía.
 * Salida: Una lista vacía.
 * Poscondición: La lista queda inicializada y vacía.
 *************************************************************************************)
BEGIN
	
	//new(L);    {borrarLista(L);}
	L := NULOL;
END;

// ------------------------------------------------------------------------------------

FUNCTION posInser(d:tDatoL;L:tListaLocal):tPosL;
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
	p:=PrimeraL(L);

	WHILE (p^.sigL<>NULOL) AND (d.nombrePartido >= p^.sigL^.DatoL.nombrePartido)  DO
		p:=p^.sigL;

	posInser:=p;

END;		



// ------------------------------------------------------------------------------------

PROCEDURE crearNodo(d:tDatoL; VAR nuevo:tPosL);
(*************************************************************************************
 * Objetivo: Crea un nuevo nodo.
 * Entrada:
 * 		Dato: Contenido del elemento a insertar.
 * 		nuevo: posicion donde vamos a insertar.
 * Salida: Un nuevo nodo.
 *************************************************************************************)
BEGIN
	
	nuevo := NULOL;
	new(nuevo);
	IF (nuevo <> NULOL) THEN
		BEGIN
			nuevo^.DatoL := d;
			nuevo^.sigL := NULOL;
	END;		
END;

// ------------------------------------------------------------------------------------

FUNCTION InsertarDatoL(d:tDatoL; VAR L : tListaLocal):BOOLEAN;	
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
	
	IF (nuevo = NULOL) THEN
		
		InsertarDatoL:=FALSE
	
	ELSE 
		BEGIN
		
			InsertarDatoL:=TRUE;
		
			IF esListaVacia(L) THEN
				L:=nuevo
		
			ELSE 
				BEGIN
			
					IF d.nombrePartido < L^.DatoL.nombrePartido THEN 
						BEGIN
							nuevo^.sigL := L;
							L := nuevo;
						END 
					ELSE 
						BEGIN
							ant := posInser(d,L);
							nuevo^.sigL := ant^.sigL;
							ant^.sigL := nuevo;
					END;
			END;
		END;			
END;

// ------------------------------------------------------------------------------------	
	
PROCEDURE ActualizarDatoL(VAR L:tListaLocal; p:tPosL; d:tnumVotos);		
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
	//p^.DatoL.numVotos := d.numVotos;
	p^.DatoL.numVotos := d;
END;	
	
// ------------------------------------------------------------------------------------		

PROCEDURE EliminarPosicionL(p:tPosL; VAR L : tListaLocal);
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
		L := L^.sigL
	ELSE
		IF p^.sigL=NULOL THEN
			BEGIN
				q:=L;
				WHILE q^.sigL<>P DO
					q:=q^.sigL;
				q^.sigL:=NULOL;
			END
		ELSE
			BEGIN
				q:=p^.sigL;
				p^.DatoL:=q^.DatoL;
				p^.sigL:=q^.sigL;
				p:=q;
		END;	

	dispose(p);
	p:=NULOL;
	
END;

// ------------------------------------------------------------------------------------

PROCEDURE borrarLista(VAR L:tListaLocal);
(*************************************************************************************
 * Objetivo: Elimina todos los elementos de la lista
 * Entrada:
 * 		Lista: Lista a borrar
 * Salida: Lista vacía
 *************************************************************************************)
BEGIN
	
	WHILE L <> NULOL DO
		EliminarPosicionL(PrimeraL(L),L);
	
END;	

// ------------------------------------------------------------------------------------

FUNCTION BuscarDatoL(d:tnombrePartido; L:tListaLocal):tPosL;	
(*************************************************************************************
 * Objetivo: Devuelve la posición del primer elemento con el nombre de partido dado 
 * 			 en la lista (o NULOL si el elemento no existe ).
 * Entrada:
 * 		Dato: Contenido del elemento buscado.
 * 		Lista: Lista donde realizar la búsqueda.
 * Salida:
 * 		Posición: Posición del elemento encontrado o NULOL si no se encuentra.
 *************************************************************************************)	

VAR
	p: tPosL;
	

BEGIN
	p:=L;
	
	IF esListaVacia(L) THEN
		BuscarDatoL:=NULOL
	ELSE
		BEGIN
			WHILE (p<>NULOL) AND (p^.DatoL.nombrePartido<>d) DO	
				p:=p^.sigL;
			BuscarDatoL:=p;
	END;
END;

{VAR
	p: tPosL;
	
 BEGIN
	
	p:=L;
	
	WHILE (L^.sigL <> NULOL) AND (L^.DatoL.nombrePartido < d.nombrePartido) DO
		L:=L^.sigL;
	
	BuscarDatoL := L;
			
END;		
}	
// ------------------------------------------------------------------------------------	
	
FUNCTION esListaVacia(L:tListaLocal):boolean;	
(*************************************************************************************
 * Objetivo: Determina si la lista está vacía.
 * Entrada:
 * 		Lista: Lista a comprobar.
 * Salida:
 * 		Verdadero si la lista está vacía, falso en caso contrario.
 *************************************************************************************)		
BEGIN
	esListaVacia := L= NULOL;
END;		
	
// ------------------------------------------------------------------------------------		
	
FUNCTION ObtenerDatoL( p:tPosL; L : tListaLocal):tDatoL;	
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
	ObtenerDatoL := p^.DatoL;
END;

// ------------------------------------------------------------------------------------	
	
FUNCTION PrimeraL(L:tListaLocal):tPosL;	
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
	PrimeraL := L;
END;

// ------------------------------------------------------------------------------------	

FUNCTION UltimaL(L:tListaLocal):tPosL;	
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

	WHILE (L^.sigL <> NULOL) DO
		L := L^.sigL;
	
	UltimaL := L;
	
END;

// ------------------------------------------------------------------------------------

FUNCTION AnteriorL(p:tPosL; L:tListaLocal):tPosL;
(*************************************************************************************
 * Objetivo: Devuelve la posición del elemento anterior al actual.
 * Entrada:
 * 		Posición: Posición del elemento actual.
 * 		Lista: Lista a manipular.
 * Salida:
 * 		Posición del elemento anterior o NULOL si es el primero.
 * Precondición: 
 * 		Posición es una posición válida de la lista.
 *************************************************************************************)
VAR
	q:tPosL;

	
BEGIN
	
	IF (p = L) THEN
		AnteriorL := NULOL
	ELSE
		BEGIN
			q:=L;
			
			WHILE (q^.sigL<>p) DO
				q:=q^.sigL;
			
			AnteriorL := q;
	END;
		
END;		
		
// ------------------------------------------------------------------------------------	

FUNCTION SiguienteL(p:tPosL; L:tListaLocal):tPosL;
(*************************************************************************************
 * Objetivo: Devuelve la posición en la lista del siguiente elemento a la posición 
 *           indicada (o NULOL si la posición no tiene siguiente).
 * Entrada:
 * 		Posición: Posición del elemento actual.
 * 		Lista: Lista a manipular.
 * Salida:
 * 		Posición: Posición del elemento siguiente o NULOL si es el último.
 * Precondición: 
 *		Posición es una posición válida de la lista.
 *************************************************************************************)	
BEGIN
	
	SiguienteL := p^.sigL;

END;		

// ------------------------------------------------------------------------------------
	
END.	{fin de implementation}
