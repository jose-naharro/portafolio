
/*
 * Titulo: Practica 3. Arboles binarios de busqueda.
 * 
 * Autores: Natalia 
 *          Jose Carlos Naharro Gordillo
 *          Isaac 
 * 
 * Grupo de practicas: Martes 10:30-12:30
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <time.h>
#include <math.h>
#include "p3.h"

struct complejidad{
    int n;
    double t_i;
    double t_b;
};

void inicializar_semilla() {
//se establece la semilla de una nueva serie de enteros pseudo-aleatorios
    srand(time(NULL));
}

double microsegundos() {
//obtiene la hora del sistema en microsegundos
    struct timeval t;
    if (gettimeofday(&t, NULL) < 0 )
        return 0.0;
    return (t.tv_usec + t.tv_sec * 1000000.0);
}

void aleatorio(int v [], int n) {
//se generan un vector de numeros aleatorio de tamano n.
    int i, m=2*n+1;
    
    for (i=0; i < n; i++)
        v[i] = (rand() % m) - n;
}

static struct nodo *crearnodo(int e) {
//crea un nodo en un arbol
    struct nodo *p = malloc(sizeof(struct nodo));
    if (p == NULL) {
        printf("memoria agotada\n"); exit(EXIT_FAILURE);
    }
    p->elem = e;
    p->num_repeticiones = 1;
    p->izq = NULL;
    p->der = NULL;
    return p;
}

arbol insertar(int e, arbol a){
//inserta un elemento e de tipo entero en un arbol a.
    if (a == NULL)
        return crearnodo(e);
    else if (e < a->elem){
        a->izq = insertar(e, a->izq);
    }else if (e > a->elem){
        a->der = insertar(e, a->der);
    }else
        a->num_repeticiones++;
    return a;
}

arbol creararbol(){
//devuelve un arbol vacio.
    arbol a = NULL;
    return a;
}

int esarbolvacio(arbol a){
//devuelve 0 si no es vacio y 1 si lo es.
    return (a == NULL);
}

posicion buscar(int e, arbol a){
//busca un elemento en un arbol a 
    if (a == NULL)
        return NULL;
    else if (e == a->elem)
        return a;
    else if (e < a->elem)
        return buscar(e, a->izq);
    else
        return buscar(e, a->der);
}

arbol eliminararbol(arbol a){
//borra todos los nodos liberando la memoria y devolviendo un arbol vacio.
    if(a != NULL){
        a->izq = eliminararbol(a->izq);
        a->der = eliminararbol(a->der);
        free(a);
    }
    return a = NULL; //Apuntamos a nulo. Inicializacion de punteros a nulo.
}

posicion hijoizquierdo(arbol a){
//devuelve la posicion del hijo izquierdo de un arbol a.
    if (a != NULL)
        return a->izq;
    else{
        printf("Es un arbol vacio.\n");
        exit(EXIT_FAILURE);
    }
}

posicion hijoderecho(arbol a){
//devuelve la posicion del hijo derecho de un arbol a.
    if (a != NULL)
        return a->der;
    else{
        printf("Es un arbol vacio.\n");
        exit(EXIT_FAILURE);
    }  
}

int elemento(posicion p){
//devuelve el elemento entero que hay en un nodo.
    if (p != NULL)
        return p->elem; 
    else{
        printf("Posicion pasada nula.\n");
        exit(EXIT_FAILURE);
    }
}

int numerorepeticiones(posicion p){
//devuelve el numero de repeticiones de un elemento que hay en un nodo.
    if (p != NULL)
        return p->num_repeticiones;
    else{
        printf("Posicion pasada nula.\n");
        exit(EXIT_FAILURE);
    }
}

int altura(arbol a){ 
//calcula la altura de un arbol.
    int alturaIzq, alturaDer;
    
    if (a == NULL) { 
        return -1;
    }else{ 
        //calculamos la altura de cada subarbol
        alturaIzq = altura(hijoizquierdo(a)); 
        alturaDer = altura(hijoderecho(a));
        //cogemos el mas grande 
        if (alturaIzq > alturaDer) 
            return(alturaIzq + 1); 
        else 
            return(alturaDer + 1); 
    } 
} 

void visualizarramas (arbol a){
//funcion auxiliar para visualizar. Para cuando no es arbol vacio.
    if (a != NULL) {
        printf("("); visualizarramas(hijoizquierdo(a));
        printf(" %d ", elemento(a));
        visualizarramas(hijoderecho(a)); printf(")");
    }
}

void visualizar(arbol a){
//imprime el contenido del arbol.
    
    if (a != NULL){
        printf("\narbol: ");
        visualizarramas(hijoizquierdo(a));
        printf(" %d ", elemento(a));
        visualizarramas(hijoderecho(a));
    }else{
        printf("arbol vacio: ().\n");
    }
    printf("\n");   
}

void imprimirbuscar( int e, arbol a){
//imprime si encuentra o no un elemento en un arbol.
    posicion p;
    
    p = buscar(e,a);
    
    if (p == NULL)
        printf("busco %d y no encuentro nada.\n",e);
    else{
        printf("busco %d y encuentro %d ",e,e);
        printf("repetido: %d veces.\n",numerorepeticiones(p));
    }
}

void test(){
//comprobamos el correcto funcionamiento de las funciones implementadas.
    arbol a;
    printf("\nTEST -----------------------------\n");
    a = creararbol();
    visualizar(a);
    printf("Es arbol vacio? %i\n",esarbolvacio(a));
    printf("La altura del arbol es: %i\n",altura(a));
    

    printf("inserto un 3\n"); a = insertar(3,a);
    printf("inserto un 1\n"); a = insertar(1,a);
    printf("inserto un 2\n"); a = insertar(2,a);
    printf("inserto un 5\n"); a = insertar(5,a);
    printf("inserto un 4\n"); a = insertar(4,a);
    printf("inserto un 5\n"); a = insertar(5,a);

    printf("\nEs arbol vacio? %i\n",esarbolvacio(a));
    visualizar(a);

    printf("\nLa altura del arbol es: %i\n",altura(a));

    imprimirbuscar(1,a);
    imprimirbuscar(2,a);
    imprimirbuscar(3,a);
    imprimirbuscar(4,a);
    imprimirbuscar(5,a);
    imprimirbuscar(6,a);
    
    printf("\nborramos todos los nodos liberando la memoria:\n");
    a = eliminararbol(a);
    visualizar(a);
    printf("Es arbol vacio? %i\n",esarbolvacio(a));
    printf("\nLa altura del arbol es: %i\n",altura(a));
}

void cabeceraTiempos(char tipoOperacion){
//Imprime la cabecera de las tablas.
    switch (tipoOperacion) {
        case 'i':
            printf("\nINSERCION DE n ELEMENTOS\n");
            break;
        case 'b': 
            printf("\nBUSQUEDA DE n ELEMENTOS\n");
    }
    printf("------------------------------------------------------------");
    printf("-------\n");
    printf("%7c%15s%15s",'n',"T(n)","T(n)/f(n)");
    printf("%15s%15s\n","T(n)/g(n)","T(n)/h(n)");
    printf("------------------------------------------------------------");
    printf("-------\n");
}

void imprimeTiempos (struct complejidad w[], int j){
//valores de las cotas sobrestimada, ajustada y subestimada.
//imprime los tiempos y cotas.
    int i;
    double x, y, z;

    printf("\n%7c%15s%15s\n",'n',"t_ins(n)","t_bus(n)");
    for (i = 0; i < j; i++)
        if (w[i].t_i > 500 || w[i].t_b > 500) 
            printf(" %6d%15.0f%15.0f\n",w[i].n,w[i].t_i,w[i].t_b);
    
    cabeceraTiempos('i');
    for (i = 0; i < j; i++){
        x = w[i].t_i / pow(w[i].n, 1.12);
        y = w[i].t_i / pow(w[i].n, 1.32);
        z = w[i].t_i / pow(w[i].n, 1.52);
        if (w[i].t_i > 500) 
            printf(" %6d%15.2f%15.6f%15.6f%15.6f\n",w[i].n,w[i].t_i,x,y,z);    
    }
    
    cabeceraTiempos('b');
    for (i = 0; i < j; i++){
        x = w[i].t_b / pow(w[i].n, 1.1325);
        y = w[i].t_b / pow(w[i].n, 1.3325);
        z = w[i].t_b / pow(w[i].n, 1.5325);
        if (w[i].t_b > 500) 
            printf(" %6d%15.2f%15.6f%15.6f%15.6f\n",w[i].n,w[i].t_b,x,y,z);    
    }
}

void tiempos(){
//calcula las tabla para cada uno de los algoritmos e inicializaciones.
    int i, n, x, r = 256000;
    int v[r], u[r];
    int j = 8;
    struct complejidad w[j];
    double t1 = 0, t2 = 0, t3 = 0, t4 = 0, t_i = 0, t_b = 0;
    arbol a;
    
    a = creararbol(); // arbol vacio

    for (x = 0, n = 1000; n <= r; n*=2, x++){
        //creamos dos vectores aleatorios
        aleatorio(v,n); //este para insertar en el arbol a
        aleatorio(u,n); //este para buscar una vez insertado
        //calculamos tiempos de insercion
        t1 = microsegundos();
        for (i = 0; i < n; i++)
            a = insertar(v[i],a);
        t2 = microsegundos();
        t_i = t2 - t1;
        //calculamos tiempos de busqueda
        t3 = microsegundos();
        for (i = 0; i < n; i++)
            buscar(u[i],a);
        t4 = microsegundos();
        t_b = t4 - t3;
        //solo queremos tiempos de insercion mayores de 500 microsegundos.
        if (t_i > 500)
            //guardamos los datos para inpimir los tiempos.
            w[x].n = n; w[x].t_i = t_i; w[x].t_b = t_b;
        a = eliminararbol(a);
    }
    imprimeTiempos(w,j);
}

int main() {
    int i;
    
    inicializar_semilla();    

    test();

    for (i = 0; i < 7; i++)
        tiempos();

    return 0;
}


