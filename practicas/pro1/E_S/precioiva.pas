PROGRAM PrecioConIva (input,output);

(**********************************************
   Este programa devuelve un precio al que le hemos sumado el iva
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  preciobase,precioiva:Real;

BEGIN

  Write('Precio del producto (sin IVA): ');Readln(preciobase);
  
  precioiva:=1.18*preciobase;
  
  Writeln('El importe total (IVA incluido) es de ',precioiva:0:2,' €');
  
END.
