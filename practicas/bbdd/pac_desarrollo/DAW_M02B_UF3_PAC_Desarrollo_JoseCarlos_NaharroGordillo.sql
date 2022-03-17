ALTER SESSION SET "_ORACLE_SCRIPT" = true;
SET SERVEROUTPUT ON;
SET VERIFY OFF;

---------------------------------------------------------------
-- 1)   GESTI�N DE USUARIOS Y TABLAS --------------------------
---------------------------------------------------------------

-- 1. Usuario "GESTOR"

CREATE USER GESTOR IDENTIFIED BY 1234; -- creación del usuario
GRANT CREATE SESSION TO GESTOR; -- permiso de conexión
GRANT ALTER ON ILERNA_PAC.alumnos_pac TO GESTOR; -- permisos sobre las tablas
GRANT ALTER ON ILERNA_PAC.asignaturas_pac TO GESTOR;

-- Nos conectamos como GESTOR
CONNECT GESTOR / 1234;
-- Realizamos las modificaciones sobre las tablas alumnos y asignaturas
ALTER TABLE ILERNA_PAC.alumnos_pac ADD "CIUDAD" VARCHAR(30);
ALTER TABLE ILERNA_PAC.asignaturas_pac MODIFY "NOMBRE_PROFESOR" VARCHAR(50);
ALTER TABLE ILERNA_PAC.asignaturas_pac DROP COLUMN "CREDITOS";
ALTER TABLE ILERNA_PAC.asignaturas_pac ADD "CICLO" VARCHAR(3);

-- 2. Usuario "DIRECTOR"

-- Nos conectamos como ILERNA_PAC, es decir nos desconectamos de gestor
CONNECT ILERNA_PAC / 1234;
ALTER SESSION SET "_ORACLE_SCRIPT" = true;
SET SERVEROUTPUT ON
-- Creamos el rol, usuario DIRECTOR y le damos los permisos requeridos en el enunciado
CREATE ROLE ROL_DIRECTOR;
CREATE USER DIRECTOR IDENTIFIED BY 1234;
GRANT ROL_DIRECTOR TO DIRECTOR;
GRANT CREATE SESSION TO DIRECTOR;
GRANT SELECT, UPDATE, INSERT ON ILERNA_PAC.alumnos_pac TO ROL_DIRECTOR;
GRANT SELECT, UPDATE, INSERT ON ILERNA_PAC.asignaturas_pac TO ROL_DIRECTOR;

-- Nos conectamos como DIRECTOR
CONNECT DIRECTOR / 1234; 
-- Realizamos las inserciones y modificación como DIRECTOR
INSERT INTO ILERNA_PAC.alumnos_pac (id_alumno, nombre, apellidos, edad, ciudad) VALUES ('JOCANAGO', 'Jose Carlos', 'Naharro Gordillo', 42, 'A Coruña');
INSERT INTO ILERNA_PAC.asignaturas_pac (id_asignatura, nombre_asignatura, nombre_profesor, ciclo) VALUES ('DAX_M02B', 'MP2.Bases de datos B', 'Claudi Godia', 'DAX');
UPDATE ILERNA_PAC.asignaturas_pac SET CICLO='DAW' WHERE id_asignatura='DAX_M02B';

-- Realizaremos los siguientes ejercicios con el usuario ILERNA_PAC
-- según lo indicado en el enunciado de la práctica
CONNECT ILERNA_PAC / 1234;
ALTER SESSION SET "_ORACLE_SCRIPT" = true;
SET SERVEROUTPUT ON
SET VERIFY OFF;
---------------------------------------------------------------
-- 2)	BLOQUES ANONIMOS -------------------------------------- 
---------------------------------------------------------------

-- 1. TABLA DE MULTIPLICAR
/
DECLARE    
    /*Parte declarativa*/
    tabladel CONSTANT NUMBER(2) := 5;
BEGIN
    /*Parte de ejecución*/
    /*Mostramos por pantalla de que numero vamos a hacer la tabla de multiplicar*/
    dbms_output.put_line('Tabla de multiplicar del ' || tabladel ||':');
    /*A continuación mostramos por pantalla la tabla mediante un bucle
    Mediante CHR(9) hacemos una tabulación segun parece indicar el enunciado*/
    FOR i IN 1..11 
    LOOP
        dbms_output.put_line(CHR(9) || tabladel || ' x ' || i || ' = ' || tabladel*i);
    END LOOP;
EXCEPTION
    /*Parte de excepciones*/
    /*Si fallara algo, mostraria una excepción*/
    WHEN OTHERS THEN
    dbms_output.put_line('Se ha producido un error');
END;

-- 2. %IRPF SALARIO BRUTO ANUAL
/
DECLARE    
    /*Parte declarativa*/
    salario_mes NUMBER(10,2);
    salario_anual NUMBER(10,2);
    porcentaje_irpf NUMBER(3,2);
BEGIN
    /*Parte de ejecución*/
    salario_mes := 1000;
    salario_anual := salario_mes * 12;/*cálculo del salario anual*/
    /*Se mete en porcentaje_irpf el valor correspondiente según el tramo que le corresponda*/
    SELECT porcentaje INTO porcentaje_irpf FROM irpf_pac WHERE salario_anual>=valor_bajo AND salario_anual<=valor_alto;
    /*Se sacan por pantalla los valores propuestos con los cálculos necesarios*/
    dbms_output.put_line('Salario mensual: ' || salario_mes || ' €');
    dbms_output.put_line('Salario anual: ' || salario_anual || ' €');
    dbms_output.put_line('IRPF aplicado: ' || porcentaje_irpf*100 || '%');
    dbms_output.put_line('IRPF a pagar: ' || salario_anual*porcentaje_irpf || ' €');
    
EXCEPTION
    /*Parte de excepciones*/
    /*Si fallara algo, mostraria una excepción, por ejemplo cuando el salario es tan alto que pasa de 99999€ anuales*/
    WHEN OTHERS THEN
        dbms_output.put_line('Se ha producido un error');
END;

---------------------------------------------------------------
-- 3)	PROCEDIMIENTOS Y FUNCIONES SIMPLES -------------------- 
---------------------------------------------------------------

-- 1. SUMA IMPARES
/
CREATE OR REPLACE PROCEDURE SUMA_IMPARES(numero IN NUMBER, sumatorio_impares OUT NUMBER)
IS
numeroMarcador NUMBER;
BEGIN
    -- Inicializamos las variables
    sumatorio_impares := 0;
    numeroMarcador := numero; -- utilizamos una auxiliar para que numero no pierda su valor si la declararamos IN OUT
    -- Realizamos el sumatorio de los impares
    WHILE ( numeroMarcador != 0 ) LOOP
        IF ( MOD(numeroMarcador,2) != 0 ) then
            sumatorio_impares := sumatorio_impares + numeroMarcador; -- Si el resto es distinto de 0 es que es impar y se puede sumar
        END IF;
        numeroMarcador := numeroMarcador - 1;
    END LOOP;
END SUMA_IMPARES;

-- 2. NUMERO MAYOR
/
CREATE OR REPLACE FUNCTION NUMERO_MAYOR(num1 IN NUMBER, num2 IN NUMBER, num3 IN NUMBER)  
RETURN NUMBER 
AS 
    numeroMayor NUMBER; 
BEGIN 
    IF num1 > num2 AND num1 > num3 THEN 
        numeroMayor := num1;
    ELSE
        IF num2 > num3 THEN
           numeroMayor := num2;
        ELSE
            numeroMayor := num3;
        END IF;
   END IF;  
   RETURN numeroMayor; 
END NUMERO_MAYOR; 

---------------------------------------------------------------
-- 4)	PROCEDIMIENTOS Y FUNCIONES COMPLEJAS ------------------ 
---------------------------------------------------------------

-- 1. DATOS DE EMPLEADO Y SU IRPF
/
CREATE OR REPLACE PROCEDURE IRPF_EMPLEADO(idEmp IN NUMBER) 
IS
    nombre empleados_pac.nombre%TYPE;
    apellidos empleados_pac.apellidos%TYPE;
    salarioAnual empleados_pac.salario%TYPE;
    tramo irpf_pac.tramo_irpf%TYPE;
    porcentaje_irpf irpf_pac.porcentaje%TYPE;
BEGIN
    SELECT nombre, apellidos, salario INTO nombre, apellidos, salarioAnual FROM empleados_pac WHERE id_empleado=idEmp;
    SELECT porcentaje, tramo_irpf INTO porcentaje_irpf, tramo FROM irpf_pac WHERE salarioAnual>=valor_bajo AND salarioAnual<=valor_alto;
    dbms_output.put_line(nombre || ' ' || apellidos || ', con salario de ' || salarioAnual || ' € en tramo ' || tramo ||', con IRPF de un ' || porcentaje_irpf*100 || '%');

END IRPF_EMPLEADO;

-- 2. NUMERO DE EMPLEADOS POR TRAMO DE IRPF
/
CREATE OR REPLACE FUNCTION EMPLEADOS_TRAMOS_IRPF(numTramo IN NUMBER)  
RETURN NUMBER 
AS
    empleadosXtramo NUMBER; --numero de empleados por tramo
    valorMax irpf_pac.valor_alto%TYPE; -- valor máximo del tramo
    valorMin irpf_pac.valor_bajo%TYPE; -- valor mínimo del tramo
BEGIN 
    empleadosXtramo := 0; --inicializamos la variable
    
    -- averiguamos el valor mínimo y máximo del tramo
    SELECT valor_bajo, valor_alto INTO valorMin, valorMax FROM irpf_pac WHERE tramo_irpf = numTramo;
    -- contamos cuantos empleados estan en ese rando de valores mínimo y máximo
    SELECT COUNT(*) INTO empleadosXtramo FROM empleados_pac WHERE salario>=valorMin AND salario<=valorMax;
    -- devolvemos el valor
    RETURN empleadosXtramo; 
END EMPLEADOS_TRAMOS_IRPF;

---------------------------------------------------------------
-- 5)	GESTI�N DE TRIGGERS ----------------------------------- 
---------------------------------------------------------------

-- 1. COMPENSACI�N SALARIO POR CAMBIO TRAMO
/
CREATE OR REPLACE TRIGGER COMPENSA_TRAMO_IRPF
BEFORE UPDATE ON empleados_pac
FOR EACH ROW
DECLARE
    oldTramo NUMBER;
    newTramo NUMBER;
BEGIN
    -- averiguamos el antiguo tramo
    SELECT tramo_irpf INTO oldTramo FROM irpf_pac WHERE :old.salario>=valor_bajo AND :old.salario<=valor_alto; 
    -- averiguamos el nuevo tramo
    SELECT tramo_irpf INTO newTramo FROM irpf_pac WHERE :new.salario>=valor_bajo AND :new.salario<=valor_alto; 
    
    -- Si el tramo no es nulo y es distinto que el antiguo le añadiremos
    -- 1000€ a su nuevo sueldo. El enunciado no habla de que sea mayor, luego, si pasa 
    -- de cobrar 30000 a 20000, por ejemplo, también se le añadirían esos 1000 al sueldo.
    IF (newTramo IS NOT NULL) AND (newTramo != oldTramo)THEN
        :new.salario := :new.salario+1000;
    END IF;
END;

-- 2. HISTORICO DE CAMBIOS DE SALARIO
/
CREATE TABLE AUDITA_SALARIOS(
    id_emp NUMBER(2)  PRIMARY KEY,
    salario_antiguo NUMBER(10, 2),
    salario_nuevo NUMBER(10, 2),
    fecha DATE,
    hora VARCHAR2(10),
    username VARCHAR2(10)
);

/
CREATE OR REPLACE TRIGGER MODIFICACIONES_SALARIOS
AFTER UPDATE OF salario ON empleados_pac 
FOR EACH ROW
DECLARE
username varchar2(10);
hora varchar2(10);
BEGIN
    -- Averiguamos el usuario que realiza la actualización
    SELECT USER INTO username FROM dual;
    -- Insertamos los valores en la tabla AUDITA_SALARIOS.
    hora := TO_CHAR(SYSDATE, 'HH24:MI:SS');
    INSERT INTO AUDITA_SALARIOS VALUES (:NEW.id_empleado, :OLD.salario, :NEW.salario, sysdate, hora, username);
END;

---------------------------------------------------------------
-- 6)   BLOQUES AN�NIMOS PARA PRUEBAS DE C�DIGO --------------- 
---------------------------------------------------------------

-- 1.	COMPROBACI�N REGISTROS DE TABLAS
/
EXECUTE dbms_output.put_line('-- 1.	COMPROBACI�N REGISTROS DE TABLAS');

/
DECLARE
    CURSOR asignaturas
    IS
        SELECT * FROM asignaturas_pac;
BEGIN
    FOR asignatura IN asignaturas
    LOOP
        dbms_output.put_line(asignatura.ID_ASIGNATURA || (', ') || asignatura.NOMBRE_ASIGNATURA || (', ') || asignatura.NOMBRE_PROFESOR || (', ') || asignatura.CICLO);
    END LOOP;
END;

/
DECLARE
    CURSOR alumnos
    IS
        SELECT * FROM alumnos_pac;
BEGIN
    FOR alumno IN alumnos
    LOOP
        dbms_output.put_line(alumno.ID_ALUMNO || (', ') || alumno.NOMBRE || (', ') || alumno.APELLIDOS || (', ') || alumno.EDAD || (', ') || alumno.CIUDAD);
    END LOOP;
END;

-- 2.	COMPROBACI�N DEL PROCEDIMIENTO �SUMA_IMPARES�
/
EXECUTE dbms_output.put_line('-- 2.	COMPROBACI�N DEL PROCEDIMIENTO �SUMA_IMPARES�');

/
DECLARE 
    entero NUMBER;
    sumaDEimpares NUMBER;
    numeroNOvalidoException EXCEPTION;
BEGIN
    entero := 6;
    -- Comprobamos que sea un numero entero (0 incluido), si no lanza una excepción
    IF entero >= 0 THEN
        SUMA_IMPARES(entero, sumaDEimpares);
        dbms_output.put_line('El resultado de sumar los impares hasta ' || entero || ' es: ' || sumaDEimpares);
    ELSE
        RAISE numeroNOvalidoException;
    END IF;
EXCEPTION
    WHEN numeroNOvalidoException THEN
        dbms_output.put_line('El numero introducido no es un entero mayor que cero.');
    WHEN OTHERS THEN 
        dbms_output.put_line('Error');
END;

-- 3.	COMPROBACI�N DE LA FUNCION �NUMERO_MAYOR�
/
EXECUTE dbms_output.put_line('-- 3.	COMPROBACI�N DE LA FUNCION �NUMERO_MAYOR�');

/
DECLARE 
    numero1 NUMBER; 
    numero2 NUMBER; 
    numero3 NUMBER;
    numeroMayor NUMBER;
    -- Declaración de Excepciones
    numerosIgualesException  EXCEPTION;
BEGIN 
    numero1:= 23;
    numero2:= 37;  
    numero3 := 32;
    IF numero1=numero2 OR numero1=numero3 OR numero2=numero3 THEN
        RAISE numerosIgualesException;
    ELSE
        numeroMayor := NUMERO_MAYOR(numero1, numero2, numero3); 
        dbms_output.put_line('El mayor de entre (' || numero1 || ', ' || numero2 || ', ' || numero3 || ') es: ' || numeroMayor);
    END IF;
EXCEPTION
    WHEN numerosIgualesException THEN
        dbms_output.put_line('No se pueden repetir números en la secuencia');
    WHEN OTHERS THEN 
        dbms_output.put_line('Error');
END; 

-- 4.	COMPROBACI�N DEL PROCEDIMIENTO �IRPF_EMPLEADO�
/
EXECUTE dbms_output.put_line('-- 4.	COMPROBACI�N DEL PROCEDIMIENTO �IRPF_EMPLEADO�');

/
DECLARE 
    idEmpleado NUMBER;
    idErroneo NUMBER;
    -- Declaración de Excepciones
    idNOvalidoException  EXCEPTION;
BEGIN
    idEmpleado := 1;
    SELECT COUNT(*) INTO idErroneo FROM empleados_pac WHERE id_empleado= idEmpleado;
    IF idErroneo > 0 THEN
        IRPF_EMPLEADO(idEmpleado);
    ELSE
        RAISE idNOvalidoException;
    END IF;
    
EXCEPTION
    WHEN idNOvalidoException THEN
        dbms_output.put_line('El número de empleado no existe en la tabla');
    WHEN OTHERS THEN 
        dbms_output.put_line('Error');
END;

-- 5.	COMPROBACI�N DE LA FUNCION �EMPLEADOS_TRAMOS_IRPF�
/
EXECUTE dbms_output.put_line('-- 5.	COMPROBACI�N DE LA FUNCION �EMPLEADOS_TRAMOS_IRPF�');

/
DECLARE
    tramoBuscado NUMBER;
    numTramos NUMBER;
    numEmpleados NUMBER;
    -- Declaración de Excepciones
    tramoNOvalidoException  EXCEPTION;
BEGIN 
    tramoBuscado := 5; --variable con el tramo buscado
    -- vemos si existe el tramo, si da 0 es que no existe, luego se lanzará una excepción
    SELECT COUNT(*) INTO numTramos FROM irpf_pac WHERE tramo_irpf = tramoBuscado;
    
    IF numTramos > 0 THEN
        -- Almacenamos el numero de empleados en ese tramo
        numEmpleados := EMPLEADOS_TRAMOS_IRPF(tramoBuscado);
        IF numEmpleados = 1 THEN
            -- Si solo hay un empleado emplearemos el singular
            dbms_output.put_line('En el tramo ' || tramoBuscado || ' de IRPF, tenemos a ' || numEmpleados || ' empleado.');
        ELSE
            -- Si hay varios empleados o ninguno emplearemos el plural
            dbms_output.put_line('En el tramo ' || tramoBuscado || ' de IRPF, tenemos a ' || numEmpleados || ' empleados.');
        END IF;
    ELSE
        RAISE tramoNOvalidoException;
    END IF;
EXCEPTION
    WHEN tramoNOvalidoException THEN
        dbms_output.put_line('El tramo buscado no es válido');
    WHEN OTHERS THEN 
        dbms_output.put_line('Error');
END;

-- 6.	COMPROBACI�N DE LOS TRIGGERS
/
EXECUTE dbms_output.put_line('-- 6.	COMPROBACI�N DE LOS TRIGGERS');

/
ACCEPT empleado NUMBER PROMPT 'Número de empleado: ';
ACCEPT salarioNuevo NUMBER PROMPT 'Nuevo salario: ';
DECLARE
    idEmpleado empleados_pac.id_empleado%TYPE := &empleado;
    newSalario empleados_pac.salario%TYPE := &salarioNuevo;
    nombreEmpleado empleados_pac.nombre%TYPE;
    apellidosEmpleado empleados_pac.apellidos%TYPE;
    fechaUpdate audita_salarios.fecha%TYPE;
    horaUpdate audita_salarios.hora%TYPE;
    numFilaUltimo NUMBER;
    oldSalario audita_salarios.salario_antiguo%TYPE;
    numeroDEempleados NUMBER;
    excepcionNumEmpleados EXCEPTION;
BEGIN
    -- Vemos si existe el empleado
    SELECT COUNT(*) INTO numeroDEempleados FROM empleados_pac WHERE id_empleado = idEmpleado;
    -- Si existe, obtenemos los datos necesarios y sacamos el mensaje por pantalla.
    -- Si no existe, mandamos una excepción.
    IF (numeroDEempleados > 0) THEN
        SELECT nombre, apellidos, salario INTO nombreEmpleado, apellidosEmpleado, oldSalario FROM empleados_pac WHERE id_empleado = idEmpleado;
        UPDATE empleados_pac SET salario = newSalario WHERE id_empleado = idEmpleado;
        SELECT salario INTO newSalario FROM empleados_pac WHERE id_empleado = idEmpleado;--Este es el salario más actualizado, con complemento, si procede.
        SELECT fecha, hora INTO fechaUpdate, horaUpdate FROM audita_salarios WHERE id_emp = idEmpleado ORDER BY fecha DESC FETCH NEXT 1 ROWS ONLY;--ultima fecha y hora
        dbms_output.put_line('El salario del empleado ' || nombreEmpleado || ' ' 
            || apellidosEmpleado || ' se ha modificado el día ' || to_char(fechaUpdate) 
            || ' ' || horaUpdate || ', antes era de ' || oldSalario || ' € y ahora es de ' 
            || newSalario || ' €');
    ELSE
        RAISE excepcionNumEmpleados;
    END IF;
EXCEPTION
    WHEN excepcionNumEmpleados THEN
        dbms_output.put_line('Número de empleado incorrecto.');
     WHEN OTHERS THEN
        dbms_output.put_line('Error.');
END;

