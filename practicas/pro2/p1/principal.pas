PROGRAM principal;

(*********************************************************************
 * TITULO: Practicas de PROGRAMACION II
 * SUBTITULO: Practica 1
 * AUTOR: José Carlos Naharro Gordillo LOGIN: jose.naharro
 * GRUPO: 3.3
**********************************************************************)



USES ListaDinamica, sysutils;	{Uso del TAD ListaOrdenada Dinamica o Estatica}

//ELIMINAR VARIABLE GLOBAL!!!

	// Ya no hay
	
(*************************************************************************************	
*************************** SECCIÓN DE SUBPROGRAMAS **********************************
**************************************************************************************)

PROCEDURE aperturaColegio (VAR colegioCerrado:BOOLEAN; VAR L:tListaOrdenada);
(***************************************************************************************
Objetivo: Abrir el colegio electoral para poder recibir votos y se añade VOTOBLANCO y VOTONULO como partido.
Entrada:
 	Lista: Lista donde vamos a insertar VOTOBLANCO y VOTONULO.
Salida:
Lista: Lista con VOTOBLANCO y VOTONULO insertados. 
Precondición:
  	Inicialmente colegioCerrado:= TRUE
  	Posición es una posición válida de la lista o es una posición nula.
Poscondición: 
	El colegio queda abierto.
*****************************************************************************************)	
VAR
   d: tDato;
	
BEGIN
	IF colegioCerrado=TRUE THEN // Si el colegio esta cerrado
		BEGIN
			colegioCerrado:=FALSE;   //queda el colegio Abierto	
			Writeln('	**** Comienza la votacion.'); //Notificamos apertura de colegio
			
// comprobar que no están dados de alta B/N 
			d.nombrePartido:=VOTOBLANCO; //Insertamos VOTOBLANCO si no fue creado mientras el colegio estaba cerrado
			d.numVotos:=0;
			IF buscarDato(d,L)=NULO THEN
				IF insertarDato(d,L)=TRUE THEN  // Damos de alta el partido si no devuelve FALSE
				ELSE
					Writeln('	++++ Error de Alta: Lista llena. ');
	
			d.nombrePartido:=VOTONULO;   //Insertamos VOTONULO si no fue creado mientras el colegio estaba cerrado
			d.numVotos:=0;
			IF buscarDato(d,L)=NULO THEN
				IF insertarDato(d,L)=TRUE THEN  // Damos de alta el partido si no devuelve FALSE
				ELSE
					Writeln('	++++ Error de Alta: Lista llena. ');

		END	
	ELSE
		Writeln('	++++ Error de apertura de Colegio: Ya estaba abierto.');
END;

	
// ------------------------------------------------------------------------------------

PROCEDURE agregarPartido (colegioCerrado:BOOLEAN; d:tDato; VAR L:tListaOrdenada);
(**************************************************************************************
* Objetivo: Inserta un elemento en la lista de forma ordenada en función del campo 
* 			nombrePartido.
* Entrada:
* 	Dato: Contenido del elemento a insertar.
*  	Lista: Lista donde vamos a insertar.
* Salida:
* 	Lista: Lista con el elemento Dato insertado.
* Precondición:
*  	Posición es una posición válida de la lista o es una posición nula.
*	El colegio tiene que estar cerrado.
****************************************************************************************)
BEGIN

	IF colegioCerrado=TRUE THEN
		IF buscarDato(d,L)=NULO THEN
			BEGIN
// comprobar que no devuelva FALSE
				IF insertarDato(d,L)=TRUE THEN  // Damos de alta el partido si no devuelve FALSE
					BEGIN
						actualizarDato(L,buscarDato(d,L),0);	// Inicializamos votos a O					
						Writeln('	**** Alta: partido ',d.nombrePartido);
					END	
				ELSE
					Writeln('	++++ Error de Alta: Lista llena. ') // Si devuelve FALSE es porque no hay memoria
			END
		ELSE
			Writeln('	++++ Error de Alta: Ya hay una candidatura llamada ',d.nombrePartido) // Notificamos que ya existe un partido con ese nombre		
	ELSE
		Writeln('	++++ Error de Alta : Colegio abierto.'); // Con el colegio abierto no se pueden dar de alta partidos

END;	


// ------------------------------------------------------------------------------------


PROCEDURE Listar(L:tListaOrdenada);
(***************************************************************************************
Objetivo: Listar los partidos que haya introducidos y el número de votos de cada uno. 
		  Si no hay partidos se muestra una advertencia.
Entrada:
  	Lista: Lista donde tenemos a ese partido.
Salida:
 	Lista: Lista alfabética con el número de votos para cada partido político.
Precondición:
  	Posición es una posición válida de la lista o es una posición nula.
*****************************************************************************************)
VAR
	p:tPosL;

BEGIN
	IF NOT esListaVacia(L) THEN
		BEGIN
			p:=primera(L);
			WHILE (p<>NULO) DO
				BEGIN
					Writeln('	Partido: ',obtenerDato(p,L).nombrePartido,' -> Numero de votos: ',obtenerDato(p,L).numVotos);
					p:=siguiente(p,L);
			END
		END			
	ELSE
		Writeln('	++++ No hay partidos dados de alta.');
		
END;


// ------------------------------------------------------------------------------------

PROCEDURE eliminarPartido(colegioCerrado:BOOLEAN; d:tDato; VAR L:tListaOrdenada);
(***************************************************************************************
Objetivo: Elimina la posición que ocupa en la lista el partido dado.
Entrada:
 	Dato: Contenido del elemento a insertar.
  	Lista: Lista donde vamos a insertar.
Salida:
 	Lista: Lista sin la posición que ocupaba el elemento Dato eliminado.
Precondición:
  	Posición es una posición válida de la lista o es una posición nula.
	El colegio tiene que estar cerrado.
Poscondición:
	La posición deja de ser válida.
*****************************************************************************************)
VAR
	pDato:tPosL;

BEGIN

	
	IF colegioCerrado=TRUE THEN
		BEGIN
			pDato:=buscarDato(d,L);
			IF pDato<>NULO THEN
				BEGIN
					Writeln('	**** Baja: partido ',d.nombrePartido);// Damos de baja el partido
					eliminarPosicion(pDato,L);
				END
			ELSE
				Writeln('	++++ Error de Baja: No existe el partido ',d.nombrePartido); // Notificamos que no existe un partido con ese nombre
		END
	ELSE
		Writeln('	++++ Error de Baja : Colegio abierto.'); // Con el colegio abierto no se pueden dar de baja partidos
					
END;

// ------------------------------------------------------------------------------------

PROCEDURE Votar(colegioCerrado:BOOLEAN; d:tDato; VAR L:tListaOrdenada);
(***************************************************************************************
Objetivo: Sumar votos al partido elegido.
Entrada:
 	Dato: nombre del partido al que quiero votar.
  	Lista: Lista donde tenemos a ese partido.
Salida:
 	Lista: Lista con el número de votos incrementados en el partido votado.
Precondición:
  	Posición es una posición válida de la lista o es una posición nula.
	El colegio tiene que estar cerrado.
Poscondición:
	Se han sumado los votos al partido que queríamos.
*****************************************************************************************)
VAR
	v:Integer;
	pDato:tPosL;

BEGIN
	
	pDato:=buscarDato(d,L);
	
	IF colegioCerrado=FALSE THEN		
		IF pDato<>NULO THEN
			BEGIN
				v:=	obtenerDato(pDato,L).numVotos+1;	//Sumamos voto al partido
				actualizarDato(L,pDato,v);
				Writeln('	**** Voto: Al partido ',d.nombrePartido,' nuevo numero de votos ',v);
			END
		ELSE
			Writeln('	++++ Error de Voto: No existe el partido ',d.nombrePartido) //Notificamos que no existe un partido con ese nombre
	ELSE
		Writeln('	++++ Error de Voto : Colegio cerrado,aun no se puede votar.'); //Con el colegio cerrado no se puede votar
					
END;

// ------------------------------------------------------------------------------------

PROCEDURE ilegalizarPartido(colegioCerrado:BOOLEAN; d:tDato; VAR L:tListaOrdenada);
(*************************************************************************************** 
Objetivo: Elimina la posición que ocupa en la lista el partido dado. Colegio abierto o cerrado.
		  No se puede ilegalizar ni VOTONULO ni VOTOBLANCO.
Entrada:
 	Dato: Contenido del elemento a eliminar, nombre.Partido.
  	Lista: Lista donde vamos a eliminar.
Salida:
 	Lista: Lista sin la posición que ocupaba el elemento Dato eliminado.
Precondición:
  	Posición es una posición válida de la lista o es una posición nula.
Poscondición:
	La posición deja de ser válida.
	Los votos del partido ilegalizado pasan a sumarse a VOTONULO.
*****************************************************************************************)
VAR
	q:tDato;
	v:tNumVotos;
	pDato:tPosL;
	pNulo:tPosL;

BEGIN
// Ilegalizar es valido con colegio abierto y cerrado. Al transferir los votos del partido ilegalizado a Nulo, antes hay que comprobar que ya esta dado de alta
	
	IF (d.nombrePartido=VOTOBLANCO) OR (d.nombrePartido=VOTONULO) THEN
		Writeln('	++++ Error de Ilegalizacion: No se pueden ilegalizar ni VOTONULO ni VOTOBLANCO. ') // Notificamos que no se pueden ilegalizar esos partidos	
	ELSE
		BEGIN
			pDato:=buscarDato(d,L);
			IF pDato=NULO THEN // Aqui compruebo  que existe el partido. Si no existe muestra el error tanto en colegio abierto como en cerrado
				Writeln('	++++ Error de Ilegalizacion: No existe el partido ',d.nombrePartido) // Notificamos que no existe un partido con ese nombre
			ELSE
				IF (colegioCerrado=TRUE) THEN     // Si en colegio esta cerrado solo eliminamos el partido ya que no puede tener votos para transferir.
					BEGIN							
						Writeln('	**** Ilegalizacion: El voto al partido ',d.nombrePartido,' ha sido anulado.');//Damos de baja el partido
						eliminarPosicion(pDato,L);		
					END
				ELSE	// Si el colegio esta abierto y como ya hemos comprobado que existe el partido transferimos los votos y eliminamos el partido.
					BEGIN
						Writeln('	**** Ilegalizacion: El voto al partido ',d.nombrePartido,' ha sido anulado.');	// Notificamos la operacion
						q.nombrePartido:=VOTONULO;																// Ahora hay que sumar los votos del partido ilegalizado a VOTONULO
						pNulo:=buscarDato(q,L); // Posicion que ocupa VOTONULO
						v:=obtenerDato(pDato,L).numVotos+obtenerDato(pNulo,L).numVotos;	// Sumamos los votos del partido ilegalizado con los de VOTONULO
						actualizarDato(L,pNulo,v); // Actualizamos la posicion de VOTONULO con el nuevo valor
						eliminarPosicion(pDato,L);	// Ahora es cuando podemos eliminar el partido ilegalizado
					END;
		END;
END;


// ------------------------------------------------------------------------------------
	
	
PROCEDURE Estadistica(electorado:string; L:tListaOrdenada);
(*************************************************************************************
Objetivo: Listar los partidos que haya introducidos y el número de votos de cada uno. 
		  Además, para cada uno se muestra su porcentaje sobre voto válido y al final de la lista 
		  también se muestra la participación. Si no hay partidos o hay irregularidad se muestra una advertencia.
Entrada:
  	Lista: Lista donde tenemos a los partidos.
Salida:
 	Lista: Lista alfabética con el número de votos para cada partido político su porcentaje  sobre voto válido y dato de participación.
Precondición:
  	Posición es una posición válida de la lista o es una posición nula.
**************************************************************************************)
VAR
	participacion:REAL;
	censo,v:Integer;
	votosTotales,auxVotos,votoValido:Integer;
	p:tPosL;
	q:tDato;
	nPart:tNombrePartido;
	
BEGIN

// comprobar que no haya divisiones por cero
	
	votosTotales:=0;
	censo:=strtoint(electorado); // El dato de electorado que leemos como STRING lo pasamos a INTEGER
	
	IF NOT esListaVacia(L) THEN	// Si la lista no esta vacia es que hay partidos
		BEGIN
			p:=primera(L);
			WHILE (p<>NULO) DO // Calculo los votos emitidos a los partidos (votosTotales)
				BEGIN
					auxVotos:=obtenerDato(p,L).numVotos;
					votosTotales:=votosTotales+auxVotos;
					p:=siguiente(p,L);
			END;

			IF censo>=votosTotales THEN	// Si el censo previsto iguala o supera a los votos emitidos, todo va bien
				BEGIN
					participacion:=(votosTotales*100/censo); // Segun comentaste el censo no va a ser 0 nunca así que no lo compruebo
					q.nombrePartido:=VOTONULO;
					votoValido:=votosTotales-obtenerDato(buscarDato(q,L),L).numVotos; // votosTotales-VOTONULO
					
					IF votoValido>0 THEN     // compruebo que no hay divisiones por cero
						BEGIN
							p:=primera(L);
							WHILE (p<>NULO) DO  //Doy los datos para cada partido
								BEGIN
									v:=obtenerDato(p,L).numVotos;
									nPart:=obtenerDato(p,L).nombrePartido;
									IF p=buscarDato(q,L) THEN
										Writeln('	Partido: ',nPart,' Votos: ',v) // Si es el VOTONULO no doy porcentaje sobre votoValido
									ELSE
										Writeln('	Partido: ',nPart,' Votos: ',v,' Porcentaje: (',(v*100/votoValido):0:2,' %).'); // Si NO es el VOTONULO doy porcentaje sobre votoValido
							
									p:=siguiente(p,L);
							END;

							Writeln('	Participacion: ',votosTotales,' de ',censo,' (',participacion:0:2,' %).');
						END	
					ELSE
						BEGIN
							p:=primera(L);
							WHILE (p<>NULO) DO  //Doy los datos para cada partido
								BEGIN
									v:=obtenerDato(p,L).numVotos;
									nPart:=obtenerDato(p,L).nombrePartido;
									Writeln('	Partido: ',nPart,' Votos: ',v); // No doy porcentaje porque el voto valido es 0 es decir, solo quedaría VOTOBLANCO=0 y VOTONULO lo que sea y de este ultimo no hay que dar %.
									p:=siguiente(p,L);
							END;

							Writeln('	Participacion: ',votosTotales,' de ',censo,' (',participacion:0:2,' %).');
							Writeln('	++++ El voto valido es 0.'); // No lo pide explicitamente en la páctica, pero lo señalo.
						END		
						
				END
			ELSE	// En caso de que la participacion supere el censo previsto, hay irregularidad
				BEGIN
					participacion:=(votosTotales*100/censo); // Segun comentaste el censo no va a ser 0 nunca así que no lo compruebo
					q.nombrePartido:=VOTONULO;
					votoValido:=votosTotales-obtenerDato(buscarDato(q,L),L).numVotos; // votosTotales-VOTONULO
					
					IF votoValido>0 THEN     // compruebo que no hay divisiones por cero
						BEGIN
							p:=primera(L);
							WHILE (p<>NULO) DO  //Doy los datos para cada partido
								BEGIN
									v:=obtenerDato(p,L).numVotos;
									nPart:=obtenerDato(p,L).nombrePartido;
									IF p=buscarDato(q,L) THEN
										Writeln('	Partido: ',nPart,' Votos: ',v) // Si es el VOTONULO no doy porcentaje sobre votoValido
									ELSE
										Writeln('	Partido: ',nPart,' Votos: ',v,' Porcentaje: (',(v*100/votoValido):0:2,' %).'); // Si NO es el VOTONULO doy porcentaje sobre votoValido
							
									p:=siguiente(p,L);
							END;

							Writeln('	Participacion: ',votosTotales,' de ',censo,' (',participacion:0:2,' %).');
						END	
					ELSE
						BEGIN
							p:=primera(L);
							WHILE (p<>NULO) DO  //Doy los datos para cada partido
								BEGIN
									v:=obtenerDato(p,L).numVotos;
									nPart:=obtenerDato(p,L).nombrePartido;
									Writeln('	Partido: ',nPart,' Votos: ',v); // No doy porcentaje porque el voto valido es 0 es decir, solo quedaría VOTOBLANCO=0 y VOTONULO lo que sea y de este ultimo no hay que dar %.
									p:=siguiente(p,L);
							END;

							Writeln('	Participacion: ',votosTotales,' de ',censo,' (',participacion:0:2,' %).');
							Writeln('	++++ El voto valido es 0.'); // No lo pide explicitamente en la páctica, pero lo señalo.
						END;
					
					Writeln('	++++ Irregularidad detectada.');
				END
				
		END
	ELSE  // Si la lista esta vacia...
		Writeln('	++++ No hay partidos de los que mostrar estadisticas.');
			
END;

// ------------------------------------------------------------------------------------

PROCEDURE borrarLista(VAR L:tListaOrdenada);
(*************************************************************************************
 * Objetivo: Elimina todos los elementos de la lista
 * Entrada:
 * 		lista: lista a borrar
 * Salida: lista vacía
 * Precondición: La posición de la lista es válida o nula.
 *************************************************************************************)
VAR
	p: tPosL;

BEGIN
	
	p:=primera(L);
	WHILE esListaVacia(L)=FALSE DO
		BEGIN
			eliminarPosicion(p,L);
			p:=siguiente(p,L);
	END;		


END;	


// ------------------------------------------------------------------------------------


PROCEDURE cabeceraOperacion(operacion:CHAR; d:tDato; electorado:STRING; L:tListaOrdenada);	
(**************************************************************************************
Objetivo: Sacar por pantalla el tipo de operación y los parámetros necesarios según ese 
		  tipo de operación.
Entrada:
	operacion: CHAR correspondiente al tipo de operación.
	dato: nombre partido.
	electorado: numero de votantes.
Salida:
	Cabecera según lo descrito en la práctica.
Precondición:
	Ha de ser una operación válida.

****************************************************************************************)
	
BEGIN	
	
	CASE operacion OF
		
		'c','C': BEGIN
					Writeln;
					Writeln('	**********************************************');
					Writeln('	Nueva operacion ',operacion,': Apertura de Colegio');
					Writeln('	**********************************************');
				 END;
		'a','A',
		'b','B',
		'v','V',
		'i','I': BEGIN
					Writeln;
					Writeln('	**********************************************');
					Writeln('	Nueva operacion ',operacion,': Partido ',d.nombrePartido);
					Writeln('	**********************************************');
				 END;
															
		's','S': BEGIN
					Writeln;
					Writeln('	**********************************************');
					Writeln('	Nueva operacion ',operacion,': Num. Votantes ',electorado);
					Writeln('	**********************************************');
				 END;
				 
		'l','L': BEGIN
					Writeln;
					Writeln('	**********************************************');
					Writeln('	Nueva operacion listado');
					Writeln('	**********************************************');
				 END;				

	END;	

	
END;


// ------------------------------------------------------------------------------------


procedure leerOperaciones(nombreFichero:string);
(************************************************************************************* 
 * NOMBRE:   leerOperaciones
 * OBJETIVO: leer de un fichero de entrada las operaciones a realizar y mostrarlas por
 *           pantalla a modo de ejemplo. El formato del fichero debe ser de 1 operacion
 *           por linea con el siguiente formato a su vez:
 *                      C param
 *           donde: 
 *		- El codigo de operacion C debe constar de 1 caracter                
 *	       	- El segundo parametro (nombre de partido/no. votantes) puede tener  
 *                como maximo 9 caracteres y debe comenzar en la tercera columna      
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
   param             : STRING;
   L: tListaOrdenada;
   colegioCerrado:BOOLEAN;
   electorado:STRING;
   d: tDato;

begin

   colegioCerrado:=TRUE;  // Inicializamos el estado del Colegio a cerrado.
   listaVacia(L); // Inicializamos la lista por precondicion.

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
      param := trim(copy(linea,3,9));  { trim elimina los espacios al comienzo y final de un string}
                                              { copy(s, i, j) copia j caracteres del string s }
                                              { a partir de la posicion i }

      { Ahora procesamos los elementos leidos, en este caso de ejemplo mostrarlos por pantalla }
 		
 		//writeln('[',operacion, '] --> [', param,']');     	
 		//lecturabatch(operacion,param);

        Writeln;
        
        CASE operacion OF
				
		'c','C':BEGIN
					d.nombrePartido:=param; // No interviene y evita warning de falta de inicializacion
					electorado:=param;		// No interviene y evita warning de falta de inicializacion
					cabeceraOperacion(operacion,d,electorado,L);
					aperturaColegio(colegioCerrado,L);		
				END;
				
		'a','A':BEGIN
					d.nombrePartido:=param;
					cabeceraOperacion(operacion,d,electorado,L);
					agregarPartido(colegioCerrado,d,L);
				END;
		
		'b','B':BEGIN
					d.nombrePartido:=param;
					cabeceraOperacion(operacion,d,electorado,L);
					eliminarPartido(colegioCerrado,d,L);
				END; 
		
		'v','V': BEGIN
					d.nombrePartido:=param;
					cabeceraOperacion(operacion,d,electorado,L);
					Votar(colegioCerrado,d,L);
				 END;
							
		'i','I': BEGIN
					d.nombrePartido:=param;
					cabeceraOperacion(operacion,d,electorado,L);
					ilegalizarPartido(colegioCerrado,d,L);
				 END; 
							
		's','S': BEGIN
					electorado:=param;
					cabeceraOperacion(operacion,d,electorado,L);
					Estadistica(electorado,L);
				 END; 	

		'l','L': BEGIN
					cabeceraOperacion(operacion,d,electorado,L);
					Listar(L);
				 END;	
							 					
	OTHERWISE
		Writeln('	++++ No ha escrito una opcion valida...')
	END;	
 		

   end;

   { CERRAMOS FICHERO }
   Close(fichero);

   borrarLista(L); // Vaciamos la lista segun nos dice el enunciado de la practica. 
				   // Ojo, si se quiere trabajar de nuevo hay que poner colegioCerrado:=TRUE
	Writeln;
	
	Writeln('	Es lista vacia? ',esListaVacia(L)); // Comprobamos que efectivamente está vacia
	
	Writeln;
end;

// ------------------------------------------------------------------------------------

	

(*************************************************************************************	
******************************   PROGRAMA PRINCIPAL **********************************
**************************************************************************************)

BEGIN
	leerOperaciones('operaciones.txt');
END.	



