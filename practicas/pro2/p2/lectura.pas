program lectura;

USES sysutils;




(************************************************************************************* 
 * NOMBRE:   leerOperaciones
 * OBJETIVO: leer de un fichero de entrada las operaciones a realizar y mostrarlas por
 *           pantalla a modo de ejemplo. El formato del fichero debe ser de 1 operacion
 *           por linea con el siguiente formato a su vez:
 *                      C PARAM1 PARAM2
 *           donde: 
 *		- C es el codigo de operacion
 *	       	- PARAM1 es el primer parametro opcional (nombre de colegio/nombre de partido)
 *	       	- PARAM2 es el segundo parametro opcional (no. de votantes/nombre de partido) 
 * ENTRADAS: 
 *	nombreFichero: nombre del fichero de entrada
 * SALIDAS:  --
 * PRECD:    --
 * POSTCD:   --
 *************************************************************************************)

procedure leerOperaciones(nombreFichero:string);
var
   fichero: Text;
// ------------------------------------------------------------------------------------
   linea             : STRING;
   operacion         : STRING;
   param1            : STRING;
   param2            : STRING;


begin

   { ABRIMOS FICHERO }
   {$i-} { Desactiva la comprobacion de errores de entrada/salida}
   assign(fichero, nombreFichero);
   reset(fichero);
   {$i+} { Activa la comprobacion de errores de entrada/salida}
   if (IOResult<>0) then begin
   		writeln('**** lectura. Error al acceder al fichero ',nombreFichero);
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
      writeln('[',operacion, '] --> [', param1,';',param2,']');
	
   end;

   { CERRAMOS FICHERO }
   Close(fichero);

end; 



// -- Cuerpo Principal -------------------------------------------------------------------------------

BEGIN
    leerOperaciones('peticiones.txt');
END.