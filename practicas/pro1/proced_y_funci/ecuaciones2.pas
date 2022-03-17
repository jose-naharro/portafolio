PROGRAM ecuaciones (input,output);
(* Este programa calcula las raices reales e imaginarias de una ecuacion 
   de 2º grado, introduciendo sus coeficientes. Mediante procedimientos 
   y funciones.
   
   Autor: Jose
   Version: 1.0
*)

VAR       (***** Variables Globales *****)

	x1,x2,PR,PC:Real;
	discri,cdiscri:Real;
	a,b,c:Real;
	
(****** PROCEDIMIENTO pedir ******
 * pide y lee un dato
 *********************************)

PROCEDURE pedir (c:char; x:Real);
BEGIN
	Write('Introduzca el término ',c:0,': ');
	Read(x);
END;

	
(****** PROCEDIMIENTO pedir datos ******
 * da las condiciones y que dato ha de 
 * perdir el procedimiento pedir
 ***************************************)

PROCEDURE pedirdatos;
BEGIN
	a:=0;
	While a=0 DO
	pedir('A',a);
	
	pedir('B',b);
	pedir('C',c);
END;

(****** FUNCIOM cdiscri *************************
 * calcula el discriminante y saca ese resultado
 ***********************************************)
FUNCTION cdiscri(a,b,c:Real):Real	
BEGIN
	cdiscri:=(b*b-4*a*c);
END;

(****** PROCEDIMIENTO calcular reales **********
 * calcula las raices reales
 ***********************************************)	
PROCEDURE calculareales(x1,x2:Real);
BEGIN
	x1:=(-b+sqrt(discri))/(2*a);
	x2:=(-b-sqrt(discri))/(2*a);
END;

(****** PROCEDIMIENTO calcular complejas ********
 * calcula las raices complejas
 ***********************************************)	
PROCEDURE calculacomplejas(PR,PC:Real);
BEGIN
	PR:=-b/(2*a);
	PC:=sqrt(-discri)/(2*a);
END;

(****** PROCEDIMIENTO hacer calculos ********
 * compara el discriminante para saber si van a 
 * ser raices reales o complejas y llama a los 
 * procedimientos que las calculan
 ***********************************************)	
PROCEDURE hacercalculos(discri:Real);
BEGIN

	

