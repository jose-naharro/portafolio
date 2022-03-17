# Hola

Soy **José Carlos Naharro**, Técnico Superior en Desarrollo de aplicaciones Web, pero sobre todo un apasionado de la programación y la tecnología.

Muestro tres apartados:

- Algunas **prácticas** que he realizado en mi desarrollo como programador.
- El **proyecto fin de ciclo**.
- Un **proyecto personal**.

## Prácticas

En este apartado se pueden ver distintas prácticas que he realizado durante mi camino como programador, desde las bases de la programación utilizando **Pascal** ([pro1](https://github.com/jose-naharro/portafolio/tree/master/practicas/pro1), [pro2](https://github.com/jose-naharro/portafolio/tree/master/practicas/pro2)) y **C#** ([proA](https://github.com/jose-naharro/portafolio/tree/master/practicas/proA)), pasando por desarrollo en lenguajes como **C** ([ssoo](https://github.com/jose-naharro/portafolio/tree/master/practicas/ssoo), [alg](https://github.com/jose-naharro/portafolio/tree/master/practicas/alg)) para la ampliación de las bases de programación y complejidad algorítmica, así como **Java** ([oop](https://github.com/jose-naharro/portafolio/tree/master/practicas/oop)) para la programación orientada a objetos, **OCamel** ([profunc](https://github.com/jose-naharro/portafolio/tree/master/practicas/profunc)) para programación funcional, para terminar con **SQL** ([bbdd](https://github.com/jose-naharro/portafolio/tree/master/practicas/bbdd)) para bases de datos.

## Proyecto fin de ciclo

Aplicación web para la gestión de un club-escuela de vuelo.
El mundo de la aviación está sufriendo muchos cambios. La introducción de los drones para uso profesional está haciendo que los clubes y escuelas de vuelo estén sufriendo un incremento en la actividad formativa y de operaciones. Dada la alta demanda de profesionales, se hace necesaria una buena estructura organizativa a nivel formativo, de aeronaves y de su convivencia en el entorno aeronáutico.

Para la parte del **front-end** utilizamos **HTML, CSS** para la maquetación (con alguna ayuda de **Bootstrap**), y **JavaScript** para las distintas funcionalidades. En la parte del **back-end** utilizamos **PHP** para la programación en la parte del servidor y la comunicación con la base de datos que está basada en **MySQL**.
Utilizamos **Git** alojado en **GitHub** para un correcto control de versiones y despliegue. Para la implementación y pruebas se utiliza **Visual Studio Code**. El desarrollo se ha realizado sobre WAMPP en un entorno Windows 10 Education, y el despliegue se ha hecho en un servidor **VPS Ubuntu 20.04 LT**.
[Aquí](https://github.com/jose-naharro/portafolio/tree/master/pfc) puedes ver el código, basado en el patrón de diseño **MVC**.

Los distintos roles son administrador, jefe de instrucción, instructores, alumnos y socios (usuarios). Existen distintos tipos de aeronaves, como ultraligeros o drones con la que los usuarios habilitados podrán realizar vuelos. Las aeronaves pueden ser propiedad de un usuario o del club, las cuales han de ser reservadas. Cada rol tiene asignadas una serie de operaciones en relación a usuarios, aeronaves y vuelos.

La metodología utilizada ha sido **Scrum**. Ha sido la metodología que he empleado en la formación en los centros de trabajo y una de las metodologías más demandadas y utilizadas en las empresas del sector. Además existen certificaciones, lo que hace que sea muy atractiva de cara a profesionales y empresas.

La aplicación se puede visitar en [http://flightembl.com](http://flightembl.com/). Las credenciales de acceso me las puedes solicitar por correo electrónico.

## Proyecto personal

Mi pasión por la informática y afición por la aviación me ha llevado al desarrollo de la instrumentación de vuelo para una aeronave ligera, un EFIS (Electronic Flight Instrument System). Se monitorizan parámetros de vuelo y de motor, y se monitorizan e interactúa con actuadores y luces.
La pantalla utilizada es de alto brillo "sunlight readable" de 1200 nits. Se han hecho diseños 3D con Fusion 360 de Autodesk para la impresión de botones y soportes.

El desarrollo se esta haciendo con hardware y software libre, **Arduino** que usa librerías en **C++** y cuya sintaxis es similar a este lenguaje de programación, **Raspberry Pi** con sistema basado en **GNU/Linux** y para la parte gráfica **Processing**, un lenguaje originalmente desarrollado por el MIT que está basado en **Java**, de hecho se puede utilizar y se utiliza directamente código Java.

Está en desarrollo, por lo que no es una versión definitiva, y se están integrando cambios como la transmisión de los datos por una interfaz CAN.

[Aquí](https://github.com/jose-naharro/portafolio/tree/master/proPersonal/interfaz) puedes ver el código de la interfaz y [aquí](https://github.com/jose-naharro/portafolio/tree/master/proPersonal) el código arduino de algunas funcionalidades.
