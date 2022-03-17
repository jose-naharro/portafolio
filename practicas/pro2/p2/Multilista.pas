UNIT Multilista;

(*********************************************************************
 * TITULO: Practicas de PROGRAMACION II
 * SUBTITULO: Practica 2
 * AUTOR: José Carlos Naharro Gordillo LOGIN: jose.naharro
 * GRUPO: 3.3
**********************************************************************)

INTERFACE

USES ListaLocal;

CONST
	NULOM = NIL;

TYPE
	tNombreColegio = String;
	tNumVotantes = Integer;
	tDatoM = RECORD
				nombreColegio: tNombreColegio;
				numVotantes: tNumVotantes;
				finalizado: BOOLEAN;
				recuentoVotos: tListaLocal;
			END;	
	
	tPosM=^tNodoM;
	
	tNodoM= RECORD
				DatoM:tDatoM;
				sigM:tPosM;
			END;	
	
	tMultilista = tPosM;


PROCEDURE MultilistaVacia(VAR M:tMultilista);
FUNCTION InsertarDatoM(d:tDatoM; VAR M : tMultilista): boolean;
PROCEDURE ActualizarDatoM( p:tPosM; VAR M:tMultilista; d:tDatoM);
PROCEDURE EliminarPosicionM(p:tPosM; VAR M : tMultilista); 
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
 * Objetivo: Crea una Multilista vacía.
 * Salida: Una Multilista vacía.
 * Poscondición: La Multilista queda inicializada y vacía.
 *************************************************************************************)
BEGIN
	
	//new(M);    {borrarMultilista(M);}
	M := NULOM;
END;

// ------------------------------------------------------------------------------------

FUNCTION posInser(d:tDatoM; M:tMultilista):tPosM;
(*************************************************************************************
 * Objetivo: Función auxiliar que da la posición donde queremos insertar un dato.
 *	
 * Entrada:
 * 		Dato: Contenido del elemento a insertar.
 * 		Multilista: Multilista donde vamos a insertar.
 * Salida:
 * 		Posición: Posición de inserción.
 * 	
 * Precondición:
 * 			Posición es una posición válida de la Multilista o es una posición nula.
 *************************************************************************************)
VAR
	p:tPosM;

BEGIN
	p:=PrimeraM(M);

	WHILE (p^.sigM<>NULOM) AND (d.nombreColegio >= p^.sigM^.DatoM.nombreColegio)  DO
		p:=p^.sigM;

	posInser:=p;

END;		



// ------------------------------------------------------------------------------------

PROCEDURE crearNodo(d:tDatoM; VAR nuevo:tPosM);
(*************************************************************************************
 * Objetivo: Crea un nuevo nodo.
 * Entrada:
 * 		Dato: Contenido del elemento a insertar.
 * 		nuevo: posicion donde vamos a insertar.
 * Salida: Un nuevo nodo.
 *************************************************************************************)
BEGIN
	
	nuevo := NULOM;
	new(nuevo);
	IF (nuevo <> NULOM) THEN
		BEGIN
			nuevo^.DatoM := d;
			nuevo^.sigM := NULOM;
	END;		
END;

// ------------------------------------------------------------------------------------

FUNCTION InsertarDatoM(d:tDatoM; VAR M : tMultilista):BOOLEAN;	
(*************************************************************************************
 * Objetivo: Inserta un elemento en la multilista de forma ordenada en función del campo 
 *			 nombreColegio.
 * Entrada:
 * 		Dato: Contenido del elemento a insertar.
 * 		MultiLista: MultiLista donde vamos a insertar.
 * Salida:
 * 		Multilista: Multilista con el elemento Dato insertado
 * 	
 * Precondición:
 * 			Posición es una posición válida de la multilista o es una posición nula.
 *************************************************************************************)
VAR
	ant,nuevo:tPosM;
	
BEGIN
	
	crearNodo(d,nuevo);
	
	IF (nuevo = NULOM) THEN
		
		InsertarDatoM:=FALSE
	
	ELSE 
		BEGIN
		
			InsertarDatoM:=TRUE;
		
			IF esMultilistaVacia(M) THEN
				M:=nuevo
		
			ELSE 
				BEGIN
			
					IF d.nombreColegio < M^.DatoM.nombreColegio THEN 
						BEGIN
							nuevo^.sigM := M;
							M := nuevo;
						END 
					ELSE 
						BEGIN
							ant := posInser(d,M);
							nuevo^.sigM := ant^.sigM;
							ant^.sigM := nuevo;
					END;
			END;
		END;			
END;

// ------------------------------------------------------------------------------------	
	
PROCEDURE ActualizarDatoM( p:tPosM; VAR M:tMultilista; d:tDatoM);		
(*************************************************************************************
 * Objetivo: Actualiza la información asociada al elemento situado en la posición 
 *           indicada salvo que sea el nombre del colegio
 * Entrada:
 * 		Multilista: Multilista a modificar.
 * 		Posición: Posición del elemento que queremos modificar.
 * 		Dato: Nuevo contenido a asignar al elemento en Posición.
 * Salida:
 * 		Multilista: Multilista con el contenido del elemento modifcado.
 * Precondición:
 * 		Posición es una posición válida de la multilista.	
 *************************************************************************************)	
BEGIN

	p^.DatoM := d;
	
END;	
	
// ------------------------------------------------------------------------------------		

PROCEDURE EliminarPosicionM(p:tPosM; VAR M : tMultilista);
(*************************************************************************************
 * Objetivo: Elimina de la multilista el elemento de la posición dada.
 * Entrada: 
 * 		Posición: Posición del elemento a eliminar.
 * 		Multilista: Multilista a modifcar.
 * Salida:
 * 		Multilista: Multilista sin el elemento correspondiente a Posición.
 * Precondición:
 *		La posición es una posición válida de la Multilista.
 * Poscondición:
		La posición deja de ser válida.
 *************************************************************************************)

VAR
	q:tPosM;
	
BEGIN

	IF p=M THEN
		M := M^.sigM
	ELSE
		IF p^.sigM=NULOM THEN
			BEGIN
				q:=M;
				WHILE q^.sigM<>p DO
					q:=q^.sigM;
				q^.sigM:=NULOM;
			END
		ELSE
			BEGIN
				q:=p^.sigM;
				p^.DatoM:=q^.DatoM;
				p^.sigM:=q^.sigM;
				p:=q;
		END;	

	dispose(p);
	p:=NULOM;
	
END;

// ------------------------------------------------------------------------------------

PROCEDURE borrarMultilista(VAR M:tMultilista);
(*************************************************************************************
 * Objetivo: Elimina todos los elementos de la multilista
 * Entrada:
 * 		Multilista: Multilista a borrar
 * Salida: Multilista vacía
 *************************************************************************************)
BEGIN
	
	WHILE M <> NULOM DO
		EliminarPosicionM(PrimeraM(M),M);
	
END;	

// ------------------------------------------------------------------------------------

FUNCTION BuscarDatoM(d:tNombreColegio; M:tMultilista):tPosM;	
(*************************************************************************************
 * Objetivo: Devuelve la posición del primer elemento con el nombre de partido dado 
 * 			 en la Multilista (o NULOM si el elemento no existe ).
 * Entrada:
 * 		Dato: Contenido del elemento buscado.
 * 		Multilista: Multilista donde realizar la búsqueda.
 * Salida:
 * 		Posición: Posición del elemento encontrado o NULOM si no se encuentra.
 *************************************************************************************)	
VAR
	p: tPosM;
	

BEGIN
	p:=M;
	
	IF esMultilistaVacia(M) THEN
		BuscarDatoM:=NULOM
	ELSE
		BEGIN
			WHILE (p<>NULOM) AND (p^.DatoM.nombreColegio<>d) DO
				p:=p^.sigM;
			BuscarDatoM:=p;
	END;
END;				


{VAR
	p: tPosM;
	

BEGIN



	p:=M;
	Hallado:=FALSE;
	
	WHILE (p^.sigM <> NULOM) AND (NOT Hallado) DO
		BEGIN
			IF d=ObtenerDatoM(p,M).nombreColegio THEN
				Hallado:= TRUE
			ELSE
				p:=SiguienteM(p,M);
		END;
	
	BuscarDatoM:=p;			
	
END;		

VAR
	p: tPosM;
	
 BEGIN
	
	p:=M;
	
	WHILE (p^.sigM <> NULOM) AND (p^.DatoM.nombreColegio < d) DO
		p:=p^.sigM;
	
	BuscarDatoM := p;
			
END;		
}	
// ------------------------------------------------------------------------------------	
	
FUNCTION esMultilistaVacia(M:tMultilista):boolean;	
(*************************************************************************************
 * Objetivo: Determina si la Multilista está vacía.
 * Entrada:
 * 		Multilista: Lista a comprobar.
 * Salida:
 * 		Verdadero si la Multilista está vacía, falso en caso contrario.
 *************************************************************************************)		
BEGIN
	esMultilistaVacia := M= NULOM;
END;		
	
// ------------------------------------------------------------------------------------		
	
FUNCTION ObtenerDatoM( p:tPosM; M : tMultilista):tDatoM;	
(*************************************************************************************
 * Objetivo: Obtiene los datos asociados al elemento que ocupa la posición indicada 
 *           de la Multilista.
 * Entrada:
 * 		Posición: Posición del elemento buscado.
 * 		Multilista: Multilista donde realizar la búsqueda.
 * Salida:
 * 		Dato: Contenido del elemento que está en Posición.
 * Precondición:
 * 		La posición dada tiene que ser válida.
 *************************************************************************************)
BEGIN
	ObtenerDatoM := p^.DatoM;
END;

// ------------------------------------------------------------------------------------	
	
FUNCTION PrimeraM(M:tMultilista):tPosM;	
(*************************************************************************************
 * Objetivo: Devuelve la posición del primer elemento de la Multilista.
 * Entrada:
 * 		Multilista: Multilista a manipular.
 * Salida:
 * 		Posición: Posición del primer elemento.
 * Precondición: 
 * 		La Multilista no está vacía.
 *************************************************************************************)	
BEGIN
	PrimeraM := M;
END;

// ------------------------------------------------------------------------------------	

FUNCTION UltimaM(M:tMultilista):tPosM;	
(*************************************************************************************
 * Objetivo: Devuelve la posición del último elemento de la lista. 
 * Entrada:
 * 		Multilista: Multilista a manipular.
 * Salida:
 * 		Posición: Posición del último elemento.
 * Precondición: 
 * 		La Multilista no está vacía.
 *************************************************************************************)	
	
BEGIN

	WHILE (M^.sigM <> NULOM) DO
		M := M^.sigM;
	
	UltimaM := M;
	
END;

// ------------------------------------------------------------------------------------

FUNCTION AnteriorM(p:tPosM; M:tMultilista):tPosM;
(*************************************************************************************
 * Objetivo: Devuelve la posición del elemento anterior al actual.
 * Entrada:
 * 		Posición: Posición del elemento actual.
 * 		Multilista: Multilista a manipular.
 * Salida:
 * 		Posición del elemento anterior o NULOM si es el primero.
 * Precondición: 
 * 		Posición es una posición válida de la Multilista.
 *************************************************************************************)
VAR
	q:tPosM;

	
BEGIN
	
	IF (p = M) THEN
		AnteriorM := NULOM
	ELSE
		BEGIN
			q:=M;
			
			WHILE (q^.sigM<>p) DO
				q:=q^.sigM;
			
			AnteriorM := q;
	END;
		
END;		
		
// ------------------------------------------------------------------------------------	

FUNCTION SiguienteM(p:tPosM; M:tMultilista):tPosM;
(*************************************************************************************
 * Objetivo: Devuelve la posición en la Multilista del siguiente elemento a la posición 
 *           indicada (o NULOM si la posición no tiene siguiente).
 * Entrada:
 * 		Posición: Posición del elemento actual.
 * 		Multilista: Multilista a manipular.
 * Salida:
 * 		Posición: Posición del elemento siguiente o NULOM si es el último.
 * Precondición: 
 *		Posición es una posición válida de la Multilista.
 *************************************************************************************)	
BEGIN
	
	SiguienteM := p^.sigM;

END;		

// ------------------------------------------------------------------------------------
	
END.	{fin de implementation}
