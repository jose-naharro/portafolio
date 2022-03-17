
/*
 * Titulo: Practica 4. Monticulos.
 * 
 * Asignatura: Algoritmos.
 * 
 * Titulacion: Grado en Ingenieria Informatica.
 * 
 * Autores: Natalia Iglesias Torres
 *          Jose Carlos Naharro Gordillo
 *          Isaac Cores Lopez
 * 
 * Grupo de practicas: Martes 10:30-12:30
 * 
 * Fecha: 9 de Diciembre de 2015
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <sys/time.h>
#include <time.h>

#define TAM 128000

typedef struct {
    int vector[TAM];
    int ultimo;
} monticulo;

void inicializar_monticulo(monticulo *);
void crear_monticulo(int [], int, monticulo *);
void insertar(monticulo *, int);
int eliminar_menor(monticulo *);

void inicializar_semilla() {
//se establece la semilla de una nueva serie de enteros pseudo-aleatorios.
    srand(time(NULL));
}

double microsegundos() {
//obtiene la hora del sistema en microsegundos.
    struct timeval t;
    if (gettimeofday(&t, NULL) < 0 )
        return 0.0;
    return (t.tv_usec + t.tv_sec * 1000000.0);
}

void ascendente(int v [], int n) {
//se genera un vector de n numeros en progresion ascendente.
    int i;
    
    for (i=0; i < n; i++)
        v[i] = i;
}

void descendente(int v [], int n) {
//se genera un vector de n numeros en progresion descendente.
    int i;
    int j = n;
    
    for (i=0; i < n; i++){
        v[i] = j;
        j--;
    }
}

void aleatorio(int v [], int n) {
//se generan un vector de n numeros pseudo-aleatorios entre -n y +n.
    int i, m=2*n+1;
    
    for (i=0; i < n; i++)
        v[i] = (rand() % m) - n;
}

int monticulo_vacio(monticulo m) {
//devuelve 0 si no es vacio y 1 si lo es.
    return m.ultimo == -1;
}

void inicializar_monticulo(monticulo *m) {
//inicializa el monticulo m poniendo la ultima posicion a -1.
    m->ultimo = -1;
}

void intercambiar(int v[], int j, int i) {
//intercambia las posiciones j e i en el vector v.
    int aux;
    aux = v[j];
    v[j] = v[i];
    v[i] = aux;
}

static void hundir(monticulo *m, int i) {
//funcion auxiliar para hundir un elemento en el monticulo m.
    int j, hijoDer, hijoIzq;
    do {
        hijoIzq = 2*i+1;
        hijoDer = 2*i+2;
        j = i;
        if ((hijoDer <= m->ultimo) && (m->vector[hijoDer] < m->vector[i]))
            i = hijoDer;
        if ((hijoIzq <= m->ultimo) && (m->vector[hijoIzq] < m->vector[i]))
            i = hijoIzq;
        intercambiar(m->vector, j, i);
    } while (j != i);
}

void crear_monticulo(int v[], int n, monticulo *m) {
//crea un monticulo m a partir de un vector v de tamano n.
    int i;
    for (i = 0; i < n; i++)
        m->vector[i] = v[i];
    
    m->ultimo = n-1;
    
    for (i = (n-1)/2; i >= 0 ; i--)
        hundir(m,i);
}

int eliminar_menor(monticulo *m) {
//elimina la menor de las claves del monticulo m.
    int x;
    
    if (m->ultimo == -1) {
        printf("Es un monticulo vacio.\n");
        exit(EXIT_FAILURE);
    } else {
        x = m->vector[0];
        m->vector[0] = m->vector[m->ultimo];
        m->ultimo = m->ultimo -1;
        if (m->ultimo > -1)
            hundir(m, 0);
        return x;
    }
}

static void flotar(monticulo *m, int i) {
//funcion auxiliar para flotar un elemento en el monticulo m.
    while (i > 0 && m->vector[(i-1)/2] > m->vector[i]) {
        intercambiar(m->vector, (i-1)/2, i);
        i = (i-1)/2;
    }
}

void insertar(monticulo *m, int x) {
//inserta un entero x en el monticulo m.
    if (m->ultimo == (TAM-1) ) {
        printf("Es un monticulo lleno.\n");
        exit(EXIT_FAILURE);
    } else {
        m->ultimo = m->ultimo + 1;
        m->vector[m->ultimo] = x;
        flotar(m, m->ultimo);
    }
}

void visualizar_monticulo (monticulo m) {
//imprime el contenido del monticulo m.
    int i;
    if (monticulo_vacio(m) == 1) {
        printf("Visualizar monticulo: El monticulo esta vacio.\n");
    } else {
        printf("Visualizar monticulo: ");
        printf("[");
        for (i = 0; i < m.ultimo+1 ;i++)
            printf(" %d ", m.vector[i]);
        printf("]\n");
    }
}

void listar_vector( int v[], int n){
//recorre un vector y va mostrando sus elementos por pantalla.
    int i;
    
    printf("[");
    for (i = 0; i < n; i++){
        printf("%4d" , v[i] );
    }
    printf("  ]\n");
}

void ord_monticulo(int v[], int n) {
//algoritmo de ordenacion por monticulo.
    int i;
    monticulo m;
    
    inicializar_monticulo(&m);
    crear_monticulo(v, n, &m);
    
    for(i = 0; i < n; i++)
        v[i] = eliminar_menor(&m);
}

int ordenado(int v [],int n){
    //comprueba si el vector esta ordenado 
    int ord = 1;
    int i = 0;
    
    while  ((ord == 1) && (i<(n-1))) {
        if (v[i] > v[i+1])
            ord = 0;
        i++;
    }
    return ord;
}

void comprobarOrden(int v [], int n){
//comprueba si el vector esta ordenado y lo lista.
//si no esta ordenado lo ordena y vuelve a comprobar.
    if (ordenado(v,n) == 0){
        listar_vector(v,n);
        printf("Ordenado: 0.  Ordenamos...\n");
        
        ord_monticulo(v, n);
        
        comprobarOrden(v, n);
    } else {
        listar_vector(v, n);
        printf("Ordenado: 1\n\n");
    }    
}


void test1 () {
//prueba de las funciones a implementar.
    monticulo m;
    int n = 5;
    int v[5] = {12,7,10,8,20};
    
    printf("\nInicializamos un monticulo.\n");
    inicializar_monticulo(&m);
    printf("Es monticulo vacio? %d\n",monticulo_vacio(m));
    visualizar_monticulo(m);
    
    printf("\nOperacion crear_monticulo (implica hundir).\n");
    printf("Creamos monticulo con el siguiente vector: v = {12,7,10,8,20}\n");
    crear_monticulo(v, n, &m);
    printf("Es monticulo vacio? %d\n",monticulo_vacio(m));
    visualizar_monticulo(m);
    
    printf("\nInsertar en monticulo con elementos (implica flotar).\n");
    printf("Insertamos el 6.\n"); insertar(&m, 6);
    visualizar_monticulo(m);
    
    printf("\nOperacion eliminar_menor (implica hundir).\n");
    printf("Elimino el %d. ",eliminar_menor(&m)); visualizar_monticulo(m);
    printf("Elimino el %d. ",eliminar_menor(&m)); visualizar_monticulo(m);
    printf("Elimino el %d. ",eliminar_menor(&m)); visualizar_monticulo(m);
    printf("Elimino el %d. ",eliminar_menor(&m)); visualizar_monticulo(m);
    printf("Elimino el %d. ",eliminar_menor(&m)); visualizar_monticulo(m);
    printf("Elimino el %d. ",eliminar_menor(&m)); visualizar_monticulo(m);
    printf("\nEs monticulo vacio? %d\n",monticulo_vacio(m));
    
    printf("\nInsertar en monticulo vacio.\n");

    printf("Insertamos el 54.\n"); insertar(&m, 54);
    printf("Es monticulo vacio? %d\n",monticulo_vacio(m));
    visualizar_monticulo(m);
    printf("Elimino el %d. ",eliminar_menor(&m));
    printf("\nEs monticulo vacio? %d\n\n",monticulo_vacio(m));
}

void test2(){
    int n = 5;
    int v[5] = {12,7,10,8,20};
    
    printf("\nCreamos con el siguiente vector: v = {12,7,10,8,20}\n");
    printf("Comprobamos si esta ordenado, si no es asi lo ordenamos.\n");
    comprobarOrden(v,n);
}


void cabeceraTiempos1(char tipoCreacionMonticulo){
//Imprime la cabecera de la tabla
    printf("\nTABLA DE TIEMPOS ");
    switch (tipoCreacionMonticulo) {
        case 'i':
            printf("CREACION DE MONTICULOS INSERTANDO.\n");
            break;
        case 'v': 
            printf("CREACION DE MONTICULOS A PARTIR DE VECTOR.\n");
    }
    printf("------------------------------------------------------------");
    printf("-------\n");
    printf("%7c%15s%15s",'n',"T(n)","T(n)/f(n)");
    printf("%15s%15s\n","T(n)/g(n)","T(n)/h(n)");
    printf("------------------------------------------------------------");
    printf("-------\n");
}

void crecionMonticulo(monticulo *m,int v[],int n,char tipoCreacionMonticulo){
//elige entre uno de los dos algoritmos para crear monticulos.
    int i;
    switch (tipoCreacionMonticulo) {
        case 'i': 
            for(i = 0; i < n; i++)
                insertar(m, v[i]);   
            break;
        case 'v': 
            crear_monticulo(v, n, m);
    }
}

void imprimeTiempos (int n, double t, char tipoCreacionMonticulo){
//valores de las cotas sobrestimada, ajustada y subestimada.
//imprime los tiempos y cotas.
    double x, y, z;
    if (tipoCreacionMonticulo == 'i'){
        x = t / n;
        y = t / (n*(log (n)));//pow(n, 1.125);//En el de pc de casa.
        z = t / pow(n, 1.5);
    } else{
        x = t / pow(n, 0.8);
        y = t / n;
        z = t / pow(n, 1.2);
    }
    if (t < 500) printf("*%6d%15.3f%15.6f%15.6f%15.6f\n",n,t,x,y,z);    
    else printf(" %6d%15.3f%15.6f%15.6f%15.6f\n",n,t,x,y,z);
}

void tiempos1(char tipoCreacionMonticulo) {
//calcula tiempos para la cracion de monticulos a partir de vector o insercion
    int i, q, n, K = 1000;    
    int v[TAM];
    double t = 0, ta = 0, tb = 0;
    monticulo m;
    for (q = 0; q < 4; q++){//Bucle para repetir las tablas segun convenga
        cabeceraTiempos1(tipoCreacionMonticulo);
        for (n = 500; n <= TAM; n*=2){
            inicializar_monticulo(&m);
            descendente(v,n);
            ta = microsegundos();
            crecionMonticulo(&m, v, n, tipoCreacionMonticulo);
            tb = microsegundos();
            t = tb - ta;
            if (t < 500){ //estrategia de tiempos pequenos
                ta = microsegundos();
                for (i = 0; i < K; i++){
                    m.ultimo = -1;
                    crecionMonticulo(&m, v, n, tipoCreacionMonticulo);
                }
                tb = microsegundos();
                t = (tb - ta)/K;
            }
            imprimeTiempos(n,t,tipoCreacionMonticulo);
        }
    } 
}

void cabeceraTiempos2(char inicializacion){
//imprime la cabecera de la tabla.
    printf("\nTABLA DE TIEMPOS DE ORDENACION POR MONTICULOS PARA VECTOR ");
    switch (inicializacion) {
        case 'a':
            printf("ASCENDENTE\n");
            break;
        case 'd': 
            printf("DESCENDENTE\n");
            break;
        case 'r': 
            printf("ALEATORIO\n");
    }
    printf("------------------------------------------------------------");
    printf("-------\n");
    printf("%7c%15s%15s",'n',"T(n)","T(n)/f(n)");
    printf("%15s%15s\n","T(n)/g(n)","T(n)/h(n)");
    printf("------------------------------------------------------------");
    printf("-------\n");
}

void iniVector(int v[], int n, char inicializacion){
//inicializa un vector de manera ascendente, descendente o aleatoria.
    switch (inicializacion) {
        case 'a': 
            ascendente(v, n);
            break;
        case 'd': 
            descendente(v, n);
            break;
        case 'r': 
            aleatorio(v, n);
    }
}

void imprimeTiempos2(int n,double t,char inicializacion){
//valores de las cotas sobrestimada, ajustada y subestimada
//imprime los tiempos y cotas.
    double x, y, z;
    switch (inicializacion) {
        case 'a':
            x = t / n;
            y = t / (n*log(n));
            z = t / pow(n, 1.5);      
            break;      
        case 'd': 
            x = t / n;
            y = t / (n*log(n));
            z = t / pow(n, 1.5);
            break;         
        case 'r': 
            x = t / n;
            y = t / (n*log(n));
            z = t / pow(n, 1.5);
        }

    if (t < 500) printf("*%6d%15.3f%15.6f%15.6f%15.6f\n",n,t,x,y,z);    
    else printf(" %6d%15.3f%15.6f%15.6f%15.6f\n",n,t,x,y,z);
}

void tiempos2(char inicializacion) {
//calcula las tabla para cada uno de los algoritmos e inicializaciones.
    int i, j, q, n, K = 1000;    
    int v[TAM];
    double t = 0, ta = 0, tb = 0, t1 = 0, t2 = 0;
        
    for (q = 0; q < 4; q++){//Bucle para repetir las tablas segun convenga
        cabeceraTiempos2(inicializacion);
        for (n = 500; n <= TAM; n*=2){
            iniVector(v,n,inicializacion);
            ta = microsegundos();
            ord_monticulo(v, n);
            tb = microsegundos();
            t = tb - ta;
            if (t < 500){ //estrategia de tiempos pequenos
                ta = microsegundos();
                for (i = 0; i < K; i++){ //calculamos tiempo de ini y ord
                    iniVector(v,n,inicializacion);
                    ord_monticulo(v, n);
                }
                tb = microsegundos();
                t1 = tb - ta;
                ta = microsegundos();
                for (j = 0; j < K; j++){ //calculamos tiempo de inicializacion
                    iniVector(v,n,inicializacion);
                }
                tb = microsegundos(); 
                t2 = tb - ta;  //le restamos el tiempo de inicializacion
                t = (t1 - t2)/K;      
            }
            imprimeTiempos2(n,t,inicializacion);
        }
    }  
}

int main() {
    inicializar_semilla();
    test1();//Validacion de las funciones del apartado 1 del enunciado.
    tiempos1('i');//Demostracion pedida en el apartado 2 del enunciado.
    tiempos1('v');//Demostracion pedida en el apartado 2 del enunciado.
    
    test2();//Validacion de la implementacion de ordenacion por monticulos.
    
    tiempos2('a');//Calculo empirico de la complejidad pedida en el apartado 4.
    tiempos2('d');//Calculo empirico de la complejidad pedida en el apartado 4.
    tiempos2('r');//Calculo empirico de la complejidad pedida en el apartado 4.
    
    return 0;
}