PROGRAM principal;

(*********************************************************************
 * TITULO: Practicas de PROGRAMACION II
 * SUBTITULO: Practica 2
 * AUTOR: José Carlos Naharro Gordillo LOGIN: jose.naharro
 * GRUPO: 3.3
**********************************************************************)



USES Multilista, ListaLocal, ColaPeticiones, sysutils;	{Uso del TAD}
	

	
(*************************************************************************************	
*************************** SECCIÓN DE SUBPROGRAMAS **********************************
**************************************************************************************)


// ------------------------------------------------------------------------------------

PROCEDURE abrirColegio(param1:STRING; param2: STRING; VAR M:tMultilista);
(***************************************************************************************
Objetivo: Abrir el colegio electoral para poder recibir votos y se añade VOTOBLANCO y VOTONULO como partidos.
Entrada:
 	param1: Nombre del colegio
 	param2: Numero de votantes posibles
 	Multilista: Multilista donde vamos a insertar el colegio.
Salida:
	Multilista: Multilista con el colegio insertado y VOTOBLANCO y VOTONULO insertados como partidos. 
Precondición:
  	Las posiciones son posiciones válidas de la multilista o nulas.
Poscondición: 
	-
*****************************************************************************************)	
VAR
	censo:Integer;
	dM: tDatoM;
	dL: tDatoL;

BEGIN
	
		IF BuscarDatoM(param1,M)<>NULOM THEN	//Buscamos el colegio, si existe..
			Writeln('	++++ Error de apertura: El colegio ',param1,' ya esta dado de alta.') // Notificamos que ya existe un colegio con ese nombre
		ELSE
			BEGIN
			dM.nombreColegio:=param1;
			censo:=strtoint(param2); // El dato de electorado que leemos como STRING lo pasamos a INTEGER
			dM.numVotantes:=censo;
			dM.finalizado:=FALSE; //queda el colegio Abierto
			
			listaVacia(dM.recuentoVotos); // Inicializamos la lista
			
			dL.nombrePartido:=VOTOBLANCO; //Insertamos VOTONULO y VOTOBLANCO
			dL.numVotos:=0;
			IF InsertarDatoL(dL,dM.recuentoVotos)=TRUE THEN	// Insertamos VOTONULO en la lista si hay memoria			
			ELSE
				Writeln('	++++ Error de apertura: No hay memoria para añadir VOTOBLANCO.');
			
			dL.nombrePartido:=VOTONULO;
			dL.numVotos:=0;
			IF InsertarDatoL(dL,dM.recuentoVotos)=TRUE THEN	// Insertamos VOTONULO en la lista si hay memoria			
			ELSE
				Writeln('	++++ Error de apertura: No hay memoria para añadir VOTONULO.');
			
			
			
			IF InsertarDatoM(dM,M)=TRUE THEN	// Insertamos todo en la multilista si hay memoria			
				Writeln('	**** Apertura: Colegio ',dM.nombreColegio,' -> num. votantes ',dM.numVotantes)// Notificamos apertura de colegio
			ELSE
				Writeln('	++++ Error de apertura: No hay memoria para añadir colegios.');
		END;
		
END;	
	
// ------------------------------------------------------------------------------------

PROCEDURE finalizarColegio(param1:STRING; VAR M:tMultilista);
(***************************************************************************************
Objetivo: Finaliza la votación en un colegio dado.
Entrada:
	Param1: es el primer parametro opcional (nombre de colegio)
	M: Multilista donde insertaremos el colegio.
Salida:
	M: Multilista con el colegio cerrado.
Precondición:
	Las posiciones son posiciones válidas de la multilista o nulas.
*****************************************************************************************)	
VAR
	dM: tDatoM;
	p: tPosM;

BEGIN
		p:=BuscarDatoM(param1,M);

		IF p<>NULOM THEN // Si existe el colegio que ha pasado (param1)...
			BEGIN
				dM:=ObtenerDatoM(p,M);
				IF dM.finalizado=FALSE THEN  // Y esta abierto...
					BEGIN
						dM.finalizado:=TRUE;
						ActualizarDatoM(p,M,dM);
						Writeln('	++++ Finalizacion: Colegio ',dM.nombreColegio,'.');
					END
				ELSE
					Writeln('	++++ Error de Finalizacion: el colegio ',dM.nombreColegio,' ya estaba cerrado.');
			END		
		ELSE
			Writeln('	++++ Error de Finalizacion: el colegio ',dM.nombreColegio,' no existe.');

END;

// ------------------------------------------------------------------------------------



PROCEDURE Votar(param1:STRING; param2:STRING; VAR M:tMultilista);
(***************************************************************************************
Objetivo: Sumar votos al partido elegido, si no existe, es creado.
Entrada:
	Dato1: nombre del colegio
	Dato2: nombre del partido al que quiero votar.
	M: Multilista
Salida:
	M: Multilista con el número de votos incrementados (suma 1 al total) en el partido votado.
	   Si no existia el partido, es creado.
Precondición:
	Posición es una posición válida de la lista o es una posición nula.
	El colegio tiene que estar abierto.
Poscondición:
	Se han sumado los votos al partido que queríamos.
*****************************************************************************************)
VAR
	v,vPar: Integer;
	dM: tDatoM;
	dL: tDatoL;
	pM: tPosM;
	pL: tPosL;

BEGIN

		pM:=BuscarDatoM(param1,M);
		
		
		IF pM<>NULOM THEN	// Si existe el colegio...(La lista esta inicializada porque he metido B y N)	
			BEGIN
				dM:=ObtenerDatoM(pM,M);
				pL:=BuscarDatoL(param2,dM.recuentoVotos);
				IF dM.finalizado=FALSE THEN  // Y esta abierto...
					IF pL<>NULOL THEN // Si existe el partido...
						BEGIN  
							vPar:=ObtenerDatoL(pL,dM.recuentoVotos).numVotos;
							v:=vPar+1; //Sumamos voto a los que ya tenia el partido
							ActualizarDatoL(dM.recuentoVotos,pL,v);
							ActualizarDatoM(pM,M,dM); // Actualizo la Multilista
							Writeln('	**** Voto: Al partido ',param2,' (colegio ',dM.nombreColegio,') nuevo numero de votos ',v); // Notificamos la operacion
						END
					ELSE  // Si no existe lo tenemos que crear y actualizar voto a 1
						BEGIN
							dL.nombrePartido:=param2;
							dL.numVotos:=1;
							IF InsertarDatoL(dL,dM.recuentoVotos)=TRUE THEN //Si hay memoria en la lista de partidos, Insertamos el partido
								BEGIN
									//pL:=BuscarDatoL(param2,dM.recuentoVotos); // Tengo que buscar de nuevo ya que es un partido nuevo a insertar
									ActualizarDatoM(pM,M,dM); // Actualizo la Multilista
									Writeln('	**** Voto: Al partido ',param2,' (colegio ',dM.nombreColegio,') nuevo numero de votos ',dL.numVotos); // Notificamos la operacion
								END
							ELSE
								Writeln('	++++ Error de voto: No hay memoria para añadir partidos.');
						END
				ELSE		
					Writeln('	++++ Error de Voto : Colegio ',dM.nombreColegio,' cerrado, no se puede votar.') //Con el colegio cerrado no se puede votar
			END
		ELSE
			Writeln('	++++ Error de Voto: No existe el colegio ',param1); // Notificamos que no existe un colegio con ese nombre
				
END;

// ------------------------------------------------------------------------------------

PROCEDURE ilegalizarPartido(param1:STRING; VAR M:tMultilista);
(***************************************************************************************
Objetivo: Elimina la posición que ocupa en la lista de todos los colegios el partido dado.
		  No se puede ilegalizar ni VOTOBLANCO ni VOTONULO.
Entrada:
	param1: Contenido del elemento a eliminar, nombre.Partido.
	M: Multilista donde vamos a eliminar.
Salida:
	M: Multilista sin la posición que ocupaba el elemento Dato eliminado en todos los
	   colegios.
Precondición:
	Posición es una posición válida de la multilista o es una posición nula.
	El colegio puede estar abierto o cerrado.
Poscondición:
	La posición deja de ser válida.
	Los votos del partido ilegalizado pasan a sumarse a VOTONULO.
*****************************************************************************************)
VAR
	pParam1:tPosL;
	pVotoNulo:tPosL;
	aux:INTEGER;
	pM:tPosM;
	dM:tDatoM;
	v:tNumVotos;

BEGIN

	aux:=0;
	
	
	IF (param1=VOTOBLANCO) OR (param1=VOTONULO) THEN // Si es votonulo o votoblanco...
		Writeln('	++++ Error de Ilegalizacion: No se pueden ilegalizar ni VOTONULO ni VOTOBLANCO. ') // Notificamos que no se pueden ilegalizar esos partidos	
	ELSE 
		BEGIN
			IF NOT esMultilistaVacia(M) THEN   // Vemos si hay colegios que podamos ilegalizar
				BEGIN
					pM:=PrimeraM(M);
					WHILE (pM<>NULOM) DO
						BEGIN
							dM:=ObtenerDatoM(pM,M); // "Cargo" los datos de esa posicion de la multilista
							pParam1:= BuscarDatoL(param1,dM.recuentoVotos); // Posicion que ocupa param1
							IF pParam1<>NULOL THEN
								BEGIN
									// Ahora hay que sumar los votos del partido ilegalizado a VOTONULO
									pVotoNulo:= BuscarDatoL(VOTONULO,dM.recuentoVotos); // Posicion que ocupa VOTONULO
									v:=ObtenerDatoL(pParam1,dM.recuentoVotos).numVotos+ObtenerDatoL(pVotoNulo,dM.recuentoVotos).numVotos;	// Sumamos los votos del partido ilegalizado con los de VOTONULO
									ActualizarDatoL(dM.recuentoVotos,pVotoNulo,v);	// Actualizamos VOTONULO con el nuevo valor
									EliminarPosicionL(pParam1,dM.recuentoVotos);
									ActualizarDatoM(BuscarDatoM(dM.nombreColegio,M),M,dM); // Actualizo la Multilista
									aux:=aux+1;
							END;	
						
							pM:=SiguienteM(pM,M);
					END;
			
					IF aux>0 THEN
						Writeln('	**** Ilegalizacion: El voto al partido ',param1,' ha sido anulado.')	// Notificamos la operacion
					ELSE
						Writeln('	++++ Error de Ilegalizacion: No existe el partido ',param1) // Notificamos que no existe un partido con ese nombre

				END
			ELSE
				Writeln('	++++ No hay partidos para poder ilegalizar.')
		END;	

END;


// ------------------------------------------------------------------------------------

PROCEDURE estadisticaLocal(param1:STRING; M:tMultilista);
(***************************************************************************************
Objetivo:  Listar los partidos que haya introducidos y el número de votos de cada uno. Además,
		   para cada uno se muestra su porcentaje sobre voto válido y al final de la lista también
		   se muestra la participación. Si no hay partidos se muestra una advertencia.
		   Si existe irregularidad por superar los votos al censo, muestra advertencia.
Entrada:
	param1: Total de personas con derecho a voto.
	M: Multilista
Salida:
	Lista alfabéticamente ordenada con el número de votos para cada partido político su porcentaje
	sobre voto válido y dato de participación.
Precondición:
	Posición es una posición válida de la listalocal y multilista o es una posición nula.

*****************************************************************************************)

VAR
	participacion:REAL;
	v,censo,votosNulos:Integer;
	nPart:tNombrePartido;
	votosTotales,auxVotos,votoValido:Integer;
	pL:tPosL;
	pM: tPosM;
	dM:tDatoM;

	
BEGIN

// ojo, compruebo que no haya divisiones por cero
	
		votosTotales:=0;
		pM:=BuscarDatoM(param1,M);
		
		IF pM<>NULOM THEN // Veo si existe el colegio, si existe puedo sacar las estadisticas
			BEGIN
				dM:=ObtenerDatoM(BuscarDatoM(param1,M),M);
				censo:=dM.numVotantes;
				IF dM.finalizado=TRUE THEN
					Writeln('	Votacion finalizada: SI')
				ELSE
					Writeln('	Votacion finalizada: NO');
				
				pL:=PrimeraL(dM.recuentoVotos);
				WHILE (pL<>NULOL) DO // Calculo los votos emitidos a los partidos (votosTotales)
					BEGIN
						auxVotos:=ObtenerDatoL(pL,dM.recuentoVotos).numVotos;
						votosTotales:=votosTotales+auxVotos;
						pL:=SiguienteL(pL,dM.recuentoVotos);
				END;
				
				votosNulos:=ObtenerDatoL(BuscarDatoL(VOTONULO,dM.recuentoVotos),dM.recuentoVotos).numVotos;
				
				IF censo>=votosTotales THEN // Si el numVotantes es mayor o igual que los votos que ha habido totales no hay irregularidad.
					BEGIN
						participacion:=(votosTotales*100/censo); // Segun comentaste el censo no va a ser 0 nunca así que no lo compruebo
						votoValido:=votosTotales-votosNulos; // votosTotales-VOTONULO
					
						IF votoValido>0 THEN     // compruebo que no hay divisiones por cero
							BEGIN
								pL:=PrimeraL(dM.recuentoVotos);
								WHILE (pL<>NULOL) DO  //Doy los datos para cada partido
									BEGIN
										v:=ObtenerDatoL(pL,dM.recuentoVotos).numVotos;
										nPart:= ObtenerDatoL(pL,dM.recuentoVotos).nombrePartido;
										IF pL=BuscarDatoL(VOTONULO,dM.recuentoVotos) THEN
											Writeln('	Partido: ',nPart,' Votos: ',v) // Si es el VOTONULO no doy porcentaje sobre votoValido
										ELSE
											Writeln('	Partido: ',nPart,' Votos: ',v,' Porcentaje: (',(v*100/votoValido):0:2,' %).'); // Si NO es el VOTONULO doy porcentaje sobre votoValido
							
										pL:=SiguienteL(pL,dM.recuentoVotos);
								END;

								Writeln('	Participacion: ',votosTotales,' de ',censo,' (',participacion:0:2,' %).');
							END	
						ELSE	// Por el contrario, si el voto valido es 0...
							BEGIN
								pL:=PrimeraL(dM.recuentoVotos);
								WHILE (pL<>NULOL) DO  // Doy los datos para cada partido
									BEGIN
										v:=ObtenerDatoL(pL,dM.recuentoVotos).numVotos;
										nPart:= ObtenerDatoL(pL,dM.recuentoVotos).nombrePartido;
										Writeln('	Partido: ',nPart,' Votos: ',v); // No doy porcentaje porque el voto valido es 0 es decir, solo quedaría VOTOBLANCO=0 y VOTONULO lo que sea y de este ultimo no hay que dar %.
										pL:=SiguienteL(pL,dM.recuentoVotos);
								END;

								Writeln('	Participacion: ',votosTotales,' de ',censo,' (',participacion:0:2,' %).');
								
								Writeln('	++++ El voto valido de este colegio es 0.');
								
							END;	
					END
				ELSE	// Si por el contrario, el numVotantes es menor que los votos totales emitidos, hay irregularidad.
					BEGIN
						participacion:=(votosTotales*100/censo); // El censo no va a ser 0 nunca así que no lo compruebo
						votoValido:=votosTotales-votosNulos; // votosTotales-VOTONULO
					
						IF votoValido>0 THEN     // compruebo que no hay divisiones por cero
							BEGIN
								pL:=PrimeraL(dM.recuentoVotos);
								WHILE (pL<>NULOL) DO  //Doy los datos para cada partido
									BEGIN
										v:=ObtenerDatoL(pL,dM.recuentoVotos).numVotos;
										nPart:= ObtenerDatoL(pL,dM.recuentoVotos).nombrePartido;
										IF pL=BuscarDatoL(VOTONULO,dM.recuentoVotos) THEN
											Writeln('	Partido: ',nPart,' Votos: ',v) // Si es el VOTONULO no doy porcentaje sobre votoValido
										ELSE
											Writeln('	Partido: ',nPart,' Votos: ',v,' Porcentaje: (',(v*100/votoValido):0:2,' %).'); // Si NO es el VOTONULO doy porcentaje sobre votoValido
							
										pL:=SiguienteL(pL,dM.recuentoVotos);
								END;

								Writeln('	Participacion: ',votosTotales,' de ',censo,' (',participacion:0:2,' %).');
							END	
						ELSE  // Por el contrario, si el voto valido es 0...
							BEGIN
								pL:=PrimeraL(dM.recuentoVotos);
								WHILE (pL<>NULOL) DO  //Doy los datos para cada partido
									BEGIN
										v:=ObtenerDatoL(pL,dM.recuentoVotos).numVotos;
										nPart:= ObtenerDatoL(pL,dM.recuentoVotos).nombrePartido;
										Writeln('	Partido: ',nPart,' Votos: ',v); // No doy porcentaje porque el voto valido es 0 es decir, solo quedaría VOTOBLANCO=0 y VOTONULO lo que sea y de este ultimo no hay que dar %.
										pL:=SiguienteL(pL,dM.recuentoVotos);
								END;

								Writeln('	Participacion: ',votosTotales,' de ',censo,' (',participacion:0:2,' %).');
								
								Writeln('	++++ El voto valido de este colegio es 0.');
								
							END;
								
						Writeln('	++++ Irregularidad detectada.'); // Este es el caso en que existe la irregularidad, ha votado más gente que de la prevista.
					END;
						
			END
		ELSE
			Writeln('	++++ Error de estadisticas locales: No existe el colegio ',param1);

			
END;


// ------------------------------------------------------------------------------------


PROCEDURE anularColegio(param1:STRING; VAR M:tMultilista);
(***************************************************************************************
Objetivo: Anula la votación de un colegio.
Entrada:
	Dato: Nombre del Colegio.
	M: Multilista
Salida:
	M: Multilista sin la posición que ocupaba ese colegio electoral.
Precondición:
	La posición del colegio es una posición válida de la Multilista.
Poscondición:
	La posición del colegio deja de ser válida.
*****************************************************************************************)	
VAR
	pM:tPosM;

BEGIN	

		pM:=BuscarDatoM(param1,M);

		IF pM<>NULOM THEN
			BEGIN
				Writeln('	**** Anulacion: colegio ',param1);
				EliminarPosicionM(pM,M);  // Damos de baja el partido
			END
		ELSE
			Writeln('	++++ Error de Anulacion: No existe el colegio ',param1); // Notificamos que no existe un colegio con ese nombre

		
END;

// ------------------------------------------------------------------------------------

PROCEDURE estadisticaGlobal(M:tMultilista);
(**************************************************************************************
Objetivo: Listar los partidos que haya introducidos y el número de votos de cada uno. Además,
		  para cada uno se muestra su porcentaje sobre voto válido y al final de la lista también
		  se muestra la participación. Si no hay partidos se muestra una advertencia.
		  Muestra también el porcentaje de participación global.
Entrada:
	M: Multilista
Salida:
	Lista alfabética con el número de votos para cada partido político su porcentaje
	sobre voto válido y dato de participación.
	Dato global de participación sobre posibles votantes totales.
	Detecta irregularidad si hay mas votods que censo.
Precondición:
	Posición es una posición válida de la listalocal y multilista o es una posición nula.

****************************************************************************************)

VAR
	finalizados,auxVotos,votosTotales,CensoTotal,numColegios:INTEGER;
	dM:tDatoM;
	pM:tPosM;
	pL:tPosL;
	

BEGIN

	finalizados:=0;
	auxVotos:=0;
	votosTotales:=0;
	CensoTotal:=0;
	numColegios:=0;
	
	IF NOT esMultilistaVacia(M) THEN
		BEGIN
			pM:=PrimeraM(M);
			WHILE pM<>NULOM DO	// Obtenemos el colegio, las estadisticas y numero de colegios
				BEGIN
					dM:=ObtenerDatoM(pM,M);
					Writeln('	## Colegio ',dM.nombreColegio,' ##');
					estadisticaLocal(dM.nombreColegio,M);
					numColegios:=numColegios+1;
					
					IF dM.finalizado=TRUE THEN	//Contador de colegios finalizados
						finalizados:=finalizados+1
					ELSE
						finalizados:=finalizados;
					
					censoTotal:=censoTotal+dM.numVotantes;	// Censo total previsto
					pL:=PrimeraL(dM.recuentoVotos);
					WHILE pL<>NULOL DO	// Recuento de votos totales por colegio
						BEGIN
						auxVotos:=ObtenerDatoL(pL,dM.recuentoVotos).numVotos;
						votosTotales:=votosTotales+auxVotos;
						pL:=SiguienteL(pL,dM.recuentoVotos);
					END;
					Writeln;
					pM:=SiguienteM(pM,M);
			END;
			IF votosTotales<=censoTotal THEN // Detecta irregularidad si la participación total ha sido mayor que el censo total previsto
				BEGIN
					Writeln('	## Resumen ##');
					Writeln('	Colegios finalizados: ',finalizados,' de ',numColegios,' (',(finalizados/numColegios)*100:0:2,' %)');
					Writeln('	Participacion Glogal: ',votosTotales,' de ',censoTotal,' (',(votosTotales/censoTotal)*100:0:2,' %)');		
				END
			ELSE
				BEGIN
					Writeln('	## Resumen ##');
					Writeln('	Colegios finalizados: ',finalizados,' de ',numColegios,' (',(finalizados/numColegios)*100:0:2,' %)');
					Writeln('	Participacion Glogal: ',votosTotales,' de ',censoTotal,' (',(votosTotales/censoTotal)*100:0:2,' %)');		
					Writeln('	++++ Irregularidad detectada.')
				END;
		END
	ELSE
		Writeln('	++++ No hay colegios registrados.');

END;	


// ------------------------------------------------------------------------------------

PROCEDURE borrarMultilista(VAR M:tMultilista);
(*************************************************************************************
 * Objetivo: Elimina todos los elementos de la multilista.
 * Entrada:
 * 		M: Multilista a borrar
 * Salida: Multilista vacía
 * Precondición: La posición de la multilista es válida o nula.
 *************************************************************************************)
VAR
	pM: tPosM;

BEGIN
	pM:=PrimeraM(M);
	WHILE esMultilistaVacia(M)=FALSE DO
		BEGIN
			EliminarPosicionM(pM,M);
			pM:=SiguienteM(pM,M);
	END;
END;	


// ------------------------------------------------------------------------------------

PROCEDURE cabeceraOperacion(operacion:CHAR; param1:STRING; param2:STRING);	
(**************************************************************************************
Objetivo: Sacar por pantalla el tipo de operación y los parámetros necesarios según ese 
		  tipo de operación.
Entrada:
	operacion: CHAR correspondiente al tipo de operación.
	param1: num. nombreColegio o nombrePartido
	param2: num. Votantes
Salida:
	Cabecera según lo descrito en la práctica.
Precondición:
	Ha de ser una operación válida.

****************************************************************************************)


VAR
	censo:INTEGER;
	
BEGIN	
	
	CASE operacion OF
		
		'c','C': BEGIN
					censo:=strtoint(param2); // El dato de electorado que leemos como STRING lo pasamos a INTEGER
					Writeln;
					Writeln('	***************************************************************');
					Writeln('	Nueva operacion ',operacion,': Colegio ',param1,' -> Num. Votantes ',censo);
					Writeln('	***************************************************************');
				 END;
				 
		'v','V': BEGIN
					Writeln;
					Writeln('	***************************************************************');
					Writeln('	Nueva operacion ',operacion,': Colegio ',param1,' -> Partido ',param2);
					Writeln('	***************************************************************');
				 END;
															
		'i','I': BEGIN
					Writeln;
					Writeln('	**********************************************');
					Writeln('	Nueva operacion ',operacion,': Partido ',param1);
					Writeln('	**********************************************');
				 END;
				 
		'a','A',
		'f','F',
		'l','L': BEGIN
					Writeln;
					Writeln('	**********************************************');
					Writeln('	Nueva operacion ',operacion,': Colegio ',param1);
					Writeln('	**********************************************');
				 END;
		
		'g','G': BEGIN
					Writeln;
					Writeln('	**********************************************');
					Writeln('	Nueva operacion G');
					Writeln('	**********************************************');
				 END;				

	END;	

	
END;


// ------------------------------------------------------------------------------------


PROCEDURE procesarCola(VAR C: tColaPet; VAR M:tMultilista);	
(**************************************************************************************
Objetivo: Procesar las peticiones que hay añadidas en la Cola.
Entrada:
	C: Cola donde se va a insertar.
	M: Multilista
Salida:
	C: Cola vacía.
	M: Multilista modificada o no, según la operación.
Poscondición:
	La Cola queda vacía.

****************************************************************************************)


VAR
	operacion:tPeticion;
	param1,param2:STRING;


BEGIN	
	
	IF NOT EsColaVacia(C) THEN
		WHILE EsColaVacia(C)=FALSE DO		
			BEGIN
				operacion:=FrenteCola(C).peticion;
				param1:=FrenteCola(C).parametro1;
				param2:=FrenteCola(C).parametro2;
			
				CASE operacion OF
				
					'c','C': BEGIN
								cabeceraOperacion(operacion,param1,param2);
								abrirColegio(param1,param2,M);		// Apertura de un Colegio
							 END;		
					
					'v','V': BEGIN 
								cabeceraOperacion(operacion,param1,param2);
								Votar(param1,param2,M);			// Votar en un determinado colegio a un determinado partido		
							 END;
							
					'i','I': BEGIN
								cabeceraOperacion(operacion,param1,param2);
								ilegalizarPartido(param1,M);	    // Ilegalizacion de partido
							 END;	

					'a','A': BEGIN
								cabeceraOperacion(operacion,param1,param2);
								anularColegio(param1,M);			// Anula la votacion de todo el colegio
							 END;	
					
					'l','L': BEGIN
								cabeceraOperacion(operacion,param1,param2);
								estadisticaLocal(param1,M);		// Muestra las estadísticas (L)ocales de un colegio
							 END;	
				
					'g','G': BEGIN
								cabeceraOperacion(operacion,param1,param2);
								estadisticaGlobal(M);				// Muestra las estadísticas (G)lobales de un colegio
							 END;
				
					'f','F': BEGIN
								cabeceraOperacion(operacion,param1,param2);
								finalizarColegio(param1,M);		// (F)inaliza la votación de un colegio
							 END;	
								
				OTHERWISE
					Writeln('	++++ No ha escrito una opcion valida...')
				END;	
				EliminarDatoCola(C);
		END
	ELSE
		Writeln('	++++ La Cola de peticiones no tiene elementos.');
	
END;

// ------------------------------------------------------------------------------------


PROCEDURE agregarAcola(operacion:CHAR; param1:STRING; param2:STRING; VAR C:tColaPet);	
(**************************************************************************************
Objetivo: Añadir las peticiones del fichero de texto a una Cola.
Entrada:
	Operacion: Tipo de operación que se quiere procesar posteriormente.
	Param1: es el primer parametro opcional (nombre de colegio/nombre de partido)
	Param2: es el segundo parametro opcional (no. de votantes/nombre de partido)
	C: Cola donde se va a insertar.
Salida:
	C: Cola con los elementos insertados.
Precondición:
	La cola esta inicializada.
****************************************************************************************)

VAR
	d:tDatoC;
		
BEGIN	
	
	d.peticion:=operacion;
	d.parametro1:=param1;
	d.parametro2:=param2;
	
	IF InsertarDatoCola(d,C)=TRUE THEN				

	ELSE
		Writeln('	++++ Cola de peticiones llena.');

END;


// ------------------------------------------------------------------------------------


PROCEDURE leerOperaciones(nombreFichero:string);
(************************************************************************************* 
 * NOMBRE:   leerOperaciones
 * OBJETIVO: leer de un fichero de entrada las operaciones a realizar y mostrarlas por
 *           pantalla a modo de ejemplo. El formato del fichero debe ser de 1 operacion
 *           por linea con el siguiente formato a su vez:
 *                      C PARAM1 PARAM2
 *           donde: 
 *			- C es el codigo de operacion
 *	       	- PARAM1 es el primer parametro opcional (nombre de colegio/nombre de partido)
 *	       	- PARAM2 es el segundo parametro opcional (no. de votantes/nombre de partido) 
 * ENTRADAS: 
 *	nombreFichero: nombre del fichero de entrada
 * SALIDAS:  --
 * PRECD:    --
 * POSTCD:   --
 *************************************************************************************)

var
   fichero: Text;
// ------------------------------------------------------------------------------------
   linea             : STRING;
   operacion         : CHAR;
   param1            : STRING;
   param2            : STRING;
   C: tColaPet;
   M: tMultilista;


begin

	ColaVacia(C); // Por precondicion hay que inicializar la Cola
	MultilistaVacia(M); // Inicializamos tambien la Multilista por precondicion.
   
   { ABRIMOS FICHERO }
   {$i-} { Desactiva la comprobacion de errores de entrada/salida}
   assign(fichero, nombreFichero);
   reset(fichero);
   {$i+} { Activa la comprobacion de errores de entrada/salida}
   if (IOResult<>0) then begin
   		writeln('	**** lectura. Error al acceder al fichero ',nombreFichero);
   		halt(1);
   end;

 
   { LEEMOS FICHERO LINEA A LINEA }
   While (not EOF(fichero))  do
   begin
   
      ReadLn(fichero, linea);
      operacion := linea[1];
      param1 := trim(copy(linea,3,12));  { trim elimina los espacios al comienzo y final de un string }
                                         { copy(s, i, j) copia j caracteres del string s }
                                         { a partir de la posicion i }
      param2 := trim(copy(linea,14,12)); 


      { Ahora procesamos los elementos leidos }
      
      //writeln('	[',operacion, '] --> [', param1,';',param2,']');
	 
	  agregarAcola(operacion,param1,param2,C);	// Leemos y metemos en una Cola las peticiones. Importante decir que NO EJECUTAREMOS INMEDIATAMENTE las peticiones
												// hasta que se lean todas las peticiones del fichero correspondiente.
													  
   end;

   { CERRAMOS FICHERO }
   Close(fichero);
	
	procesarCola(C,M); // Aqui ejecutaremos las instrucciones almacenadas en la cola hasta vaciarla. 
	
	borrarMultilista(M); // Vaciamos la Multilista segun nos dice el enunciado de la practica. 
	Writeln;
	
	Writeln('	Es multilista vacia? ',esMultilistaVacia(M));// Comprobamos que efectivamente está vacia
	Writeln; 

end;


(*************************************************************************************	
******************************   PROGRAMA PRINCIPAL **********************************
**************************************************************************************)


BEGIN

	leerOperaciones('peticiones2.txt');
	
END.	



