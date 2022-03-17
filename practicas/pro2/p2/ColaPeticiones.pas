UNIT ColaPeticiones;

(*********************************************************************
 * TITULO: Practicas de PROGRAMACION II
 * SUBTITULO: Practica 2
 * AUTOR: José Carlos Naharro Gordillo LOGIN: jose.naharro
 * GRUPO: 3.3
**********************************************************************)


INTERFACE

CONST
	NULOC = 0;
	MAXCOLA = 150;

TYPE
	tPeticion = Char;
	
	tDatoC = RECORD
				peticion: tPeticion;
				parametro1: String;
				parametro2: String;
			END;	
	
	tPosC = NULOC..MAXCOLA;
	
	tColaPet = RECORD
					datos: ARRAY [1..MAXCOLA] OF tDatoC;
					ini,fin:tPosC; 
			   END;	

	
PROCEDURE ColaVacia(VAR C:tColaPet);
FUNCTION EsColaVacia( C:tColaPet):BOOLEAN;
FUNCTION InsertarDatoCola(d:tDatoC ; VAR C:tColaPet):BOOLEAN;
FUNCTION FrenteCola(C:tColaPet):tDatoC;
PROCEDURE EliminarDatoCola(VAR C:tColaPet);


IMPLEMENTATION


FUNCTION sumaUno(k:tPosC):tPosC;
(*************************************************************************************
 * Objetivo: Sumar uno a una posicion dada.
 * Entrada: Posicion a la que se va a incrementar.
 * Salida: Posicion incrementada en uno.
 * Precondicion: La posicion ha de ser una posicion valida.
 *************************************************************************************)

BEGIN
	
	sumaUno:=(k MOD MAXCOLA)+1;
	
END;	

// ------------------------------------------------------------------------------------

FUNCTION hayMemoria(C:tColaPet):BOOLEAN;
(*************************************************************************************
 * Objetivo: Determina si hay posiciones libres en Cola.
 * Entrada: Cola a evaluar.
 * Salida: Verdadero o falso dependiendo de si hay o no posicilnes libres.
 * Precondición: La cola esta inicializada.
 *************************************************************************************)

BEGIN

	hayMemoria:=sumaUno(sumaUno(C.fin))<>C.ini;

END;	

// ------------------------------------------------------------------------------------


PROCEDURE ColaVacia(VAR C:tColaPet);
(*************************************************************************************
 * Objetivo: Crea una cola vacía.
 * Salida: Una cola vacía.
 * Poscondición: La cola queda inicializada y vacía.
 *************************************************************************************)
BEGIN
	
	C.ini := 1;
	C.fin := MAXCOLA;

END;

// ------------------------------------------------------------------------------------

FUNCTION EsColaVacia( C:tColaPet):BOOLEAN;
(*************************************************************************************
 * Objetivo: Determina si la cola está vacía.
 * Entrada:
 * 		Cola: Cola a comprobar.
 * Salida:
 * 		Verdadero si la cola está vacía, falso en caso contrario.
 * Precondición: La cola esta inicializada.
 *************************************************************************************)	

BEGIN
	
	EsColaVacia := sumaUno(C.fin) = C.ini;

END;

// ------------------------------------------------------------------------------------

FUNCTION InsertarDatoCola(d:tDatoC ; VAR C:tColaPet):BOOLEAN;
(*************************************************************************************
 * Objetivo: Inserta un elemento en la cola quedando al final.
 * Entrada:
 * 		Dato: Contenido del elemento a insertar.
 * 		Cola: Cola donde vamos a insertar.
 * Salida:
 * 		Cola: Lista con el elemento Dato insertado y verdadero o falso según se haya 
 * 			  podido insertar o no.
 * Precondición: La cola esta inicializada.
 *************************************************************************************)
			   
BEGIN
	
	IF hayMemoria(C) THEN
		BEGIN
			C.fin := sumaUno(C.fin);
			C.datos[C.fin]:=d;
			InsertarDatoCola:=TRUE;
		END
	ELSE
		InsertarDatoCola:=FALSE;

END;

// ------------------------------------------------------------------------------------

FUNCTION FrenteCola(C:tColaPet):tDatoC;

(*************************************************************************************
 * Objetivo: Recupera el contenido del primer elemento de la cola.
 * Entrada:
 * 		Cola: Cola donde obtener el dato.
 * Salida:
 * 		Dato: Contenido del primer elemento de la cola.
 * Precondición:
 * 		La Cola dada no está vacia.
 * 		La cola esta inicializada.
 *************************************************************************************)

BEGIN
	
	FrenteCola:=C.datos[C.ini];

END;	

// ------------------------------------------------------------------------------------

PROCEDURE EliminarDatoCola(VAR C:tColaPet);
(*************************************************************************************
 * Objetivo: Elimina el primer elemento de la cola.
 * Entrada: 
 * 		Cola: Cola a modifcar.
 * Salida:
 * 		Cola: Cola sin el primer elemento.
 * Precondición:
 *		La Cola dada no está vacia.
 * 		La cola esta inicializada.
 *************************************************************************************)

BEGIN
	
	C.ini:=sumaUno(C.ini);
	
END;	

// ------------------------------------------------------------------------------------
	
END.	{fin de implementation}
