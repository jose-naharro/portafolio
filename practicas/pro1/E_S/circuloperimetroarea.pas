PROGRAM circuloperimetroarea (input,output);

(**********************************************
   Este programa muestra 4 tablas
   * la primera: solo con sentencias write y ctes literales
   * la segunda: utilizando writeln
   * la tercera constantes con nombre
   * la cuarta E/S solicitamos el radio
   
   Autor: Jose
   Version: 1.0
***********************************************)
CONST
   
   pi=3.14;
   
VAR

   radio:Real;   

BEGIN

  
  Write('RADIO':6,'PERÍMETRO':15,'ÁREA':10);Writeln;
  Write('=====':6,'=========':14,'====':9);Writeln;
  Write('2':4,2*3.14*2:14:2,3.14*sqr(2):12:2);Writeln;
  Write('3':4,2*3.14*3:14:2,3.14*sqr(3):12:2);Writeln;
  Write('4':4,2*3.14*4:14:2,3.14*sqr(4):12:2);Writeln;
  
  Writeln;Writeln;Writeln;
  
  Write('RADIO':6,'PERÍMETRO':15,'ÁREA':10);Writeln;
  Write('=====':6,'=========':14,'====':9);Writeln;
  Write('2':4,2*pi*2:14:2,pi*sqr(2):12:2);Writeln;
  Write('3':4,2*pi*3:14:2,pi*sqr(3):12:2);Writeln;
  Write('4':4,2*pi*4:14:2,pi*sqr(4):12:2);Writeln;
  
  Writeln;Writeln;Writeln;
  
  Write('RADIO':6,'PERÍMETRO':15,'ÁREA':10);Writeln;
  Write('=====':6,'=========':14,'====':9);Writeln;
  Write('2':4,2*pi*2:14:2,pi*sqr(2):12:2);Writeln;
  Write('3':4,2*pi*3:14:2,pi*sqr(3):12:2);Writeln;
  Write('4':4,2*pi*4:14:2,pi*sqr(4):12:2);Writeln;
  
  Writeln;Writeln;Writeln;
  
  Write('Escriba el radio del círculo: ');Read(radio);Writeln;Writeln;
  Write('RADIO':6,'PERÍMETRO':15,'ÁREA':10);Writeln;
  Write('=====':6,'=========':14,'====':9);Writeln;
  Write(radio:6:2,2*pi*radio:12:2,pi*sqr(radio):12:2);Writeln;
  
  Writeln;Writeln;Writeln;

    
END.
