PROGRAM FloydsinBucles (input,output);

(**********************************************
   Este programa muestra por pantalla las 6 primeras filas de un triangulo de Floyd sin utilizar bucles
   Autor: Jose
   Version: 1.0
***********************************************)

VAR

  i:Integer;

BEGIN

  i:=1;
  
  Writeln(i:0);
  Writeln(i+1:0,i+2:4);
  Writeln(i+3:0,i+4:4,i+5:4);
  Writeln(i+6:0,i+7:4,i+8:4,i+9:5);
  Writeln(i+10:0,i+11:4,i+12:4,i+13:4,i+14:4);
  Writeln(i+15:0,i+16:4,i+17:4,i+18:4,i+19:4,i+20:4);
    
END.
