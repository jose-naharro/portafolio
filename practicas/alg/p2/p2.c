/*
 * Titulo: Practica 2. Ordenacion por insercion y ordenacion shell.
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

void ascendente(int v [], int n) {
//se generan números pseudoaleatorio entre -n y +n
    int i;

    for (i=0; i < n; i++)
        v[i] = i;
}

void descendente(int v [], int n) {
//se generan números de progresion descendente
    int i;
    int j = n;

    for (i=0; i < n; i++){
        v[i] = j;
        j--;
    }
}

void aleatorio(int v [], int n) {
//se generan un vector ascendente de tamaño n
    int i, m=2*n+1;

    for (i=0; i < n; i++)
        v[i] = (rand() % m) - n;
}

void listar_vector( int v[], int n){
//Recorre un vector y va mostrando sus elementos por pantalla
    int i;

    printf("[");
    for (i = 0; i < n; i++){
        printf("%4i" , v[i] );
    }
    printf("]\n");
}

int ordenado(int v [],int n){
//comprueba si el vector esta ordenado 
    int ord = 1;
    int i = 0;

        while  ((ord == 1) && (i<(n-1))) {
            if (v[i] > v[i+1]) {
                ord = 0;
            }
            i++;
        }
    return ord;
}

void ord_ins (int v[], int n){
/* Ordena los elementos de un vector, de menor 
 * a mayor, mediante el algoritmo de insercion */ 
    int i, j, x;
    
    for (i = 1; i < n; i++){
        x = v[i];
        j = i-1;
        while ((j >= 0) && (v[j] > x)){
            v[j+1] = v[j];
            j = j-1;
        }
        v[j+1] = x;
    }
}

void ord_shell (int v [], int n){
//Implementacion delalgoritmo de ordenación shell (v[1..n])
    int incremento;
    int i,j;
    int tmp;
    int seguir;

    incremento = n-1;
    do{
        incremento = (incremento/2);
        for (i = incremento; i <= n-1; i++){
            tmp = v[i];
            j = i;
            seguir = 1;
            while ((j-incremento >= 0) && (seguir)){
                if (tmp < v[j-incremento]){
                    v[j] = v[j-incremento];
                    j = j-incremento;
                }else seguir = 0;
            }
            v[j] = tmp;
        }
    }while (incremento != 1);
}

void comprobarOrden(int v [], int n, int tipoOrd){
//comprueba si el vector esta ordenado y lo lista.
//si no esta ordenado lo ordena y vuelve a comprobar
    if (ordenado(v,n) == 0){
        listar_vector(v,n);
        printf("Ordenado: 0\n");
        
        if (tipoOrd == 1)
            ord_shell(v,n);
        else
            ord_ins(v,n);
        
        comprobarOrden(v,n,tipoOrd);
    } else {
        listar_vector(v,n);
        printf("Ordenado: 1\n\n");
    }    
}

void test(){
//comprueba la correcta implementacion de los algoritmos
    int n = 17;
    int v[n];
    int tipoOrd;
    
    printf("\nORDENACION SHELL.\n");
    printf("--------------------------\n");
    tipoOrd = 1;
    printf("Vector ascendente\n");
    ascendente(v,n);
    comprobarOrden(v,n,tipoOrd);
    
    printf("Vector descendente\n");
    descendente(v,n);
    comprobarOrden(v,n,tipoOrd);
    
    printf("Vector aleatorio\n");
    aleatorio(v,n);
    comprobarOrden(v,n,tipoOrd);
    
    printf("ORDENACION INSERCION.\n");
    printf("--------------------------\n");
    tipoOrd = 2;
    printf("Vector ascendente\n");
    ascendente(v,n);
    comprobarOrden(v,n,tipoOrd);
    
    printf("Vector descendente\n");
    descendente(v,n);
    comprobarOrden(v,n,tipoOrd);
    
    printf("Vector aleatorio\n");
    aleatorio(v,n);
    comprobarOrden(v,n,tipoOrd);  
}

void cabeceraTiempos(char tipoOrdenacion, char inicializacion){
//Imprime la cabecera de la tabla
    printf("\nTABLA DE TIEMPOS ");
    switch (tipoOrdenacion) {
        case 'i':
            printf("INSERCION");
             break;
        case 's': 
            printf("SHELL");
    }
    printf(" PARA VECTOR ");
    switch (inicializacion) {
        case 'a':
            printf("ASCENDENTE\n");
             break;
        case 'd': 
            printf("DESCENDENTE\n");
            break;
        case 'r': 
            printf("ALEATORIA\n");
    }
    printf("------------------------------------------------------------");
    printf("-------\n");
    printf("%7c%15s%15s",'n',"T(n)","T(n)/f(n)");
    printf("%15s%15s\n","T(n)/g(n)","T(n)/h(n)");
    printf("------------------------------------------------------------");
    printf("-------\n");
}

void imprimeTiempos (int n,double t,char inicializacion, char tipoOrdenacion){
//valores de las cotas sobrestimada, ajustada y subestimada
//imprime los tiempos y cotas.
    double x, y, z;
    if (tipoOrdenacion == 'i')
        switch (inicializacion) {
            case 'a':
                x = t / pow(n, 0.8);
                y = t / n;
                z = t / (n*log(n));      
            break;      
            case 'd': 
                x = t / pow(n, 1.8);
                y = t / pow(n, 2.0);   
                z = t / pow(n, 2.2);
                break;      
            case 'r': 
                x = t / pow(n, 1.8);
                y = t / pow(n, 2.0);
                z = t / pow(n, 2.2);
        }
    else
        switch (inicializacion) {
            case 'a':
                x = t / n;
                y = t / pow(n, 1.1);
                z = t / pow(n, 1.33);    
                break;      
            case 'd': 
                x = t / n;
                y = t / pow(n, 1.1);
                z = t / pow(n, 1.33);
                break;      
            case 'r': 
                x = t / n;
                y = t / pow(n, 1.1575);
                z = t / pow(n, 1.27);
        }
    if (t < 500) printf("*%6d%15.3f%15.6f%15.6f%15.6f\n",n,t,x,y,z);    
    else printf(" %6d%15.3f%15.6f%15.6f%15.6f\n",n,t,x,y,z);
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

void ordenacion(int v[], int n, char tipoOrdenacion){
//elige entre uno de los dos algoritmos de ordenacion, INSERCION o SHELL
    switch (tipoOrdenacion) {
        case 'i': 
            ord_ins(v, n);
            break;
        case 's': 
            ord_shell(v, n);
    }
}

void tiempos(char tipoOrdenacion, char inicializacion) {
//calcula las tabla para cada uno de los algoritmos e inicializaciones
    int i, j, q, n, r = 32000,  K = 1000;    
    int v[512000];
    double t = 0, ta = 0, tb = 0, t1 = 0, t2 = 0;

    if (tipoOrdenacion == 's') r = 512000;       
    for (q = 0; q < 4; q++){//Bucle para repetir las tablas segun convenga
        cabeceraTiempos(tipoOrdenacion,inicializacion);
        for (n = 500; n <= r; n*=2){
            iniVector(v,n,inicializacion);
            ta = microsegundos();
            ordenacion(v, n, tipoOrdenacion);
            tb = microsegundos();
            t = tb - ta;
            if (t < 500){ //estrategia de tiempos pequeños
                ta = microsegundos();
                for (i = 0; i < K; i++){ //calculamos tiempo de ini y ord
                    iniVector(v,n,inicializacion);
                    ordenacion(v, n, tipoOrdenacion);
                }
                tb = microsegundos();
                t1 = tb - ta;
                ta = microsegundos();
                for (j = 0; j < K; j++){ //calculamos tiempo de ordenacion
                    iniVector(v,n,inicializacion);
                }
                tb = microsegundos(); 
                t2 = tb - ta;  //le restamos el tiempo de ordenacion
                t = (t1 - t2)/K;      
            }
            imprimeTiempos(n,t,inicializacion,tipoOrdenacion);
        }
    }  
}

int main(){
    inicializar_semilla();
    
    test();
    
    tiempos('i','a');
    tiempos('i','d');
    tiempos('i','r');

    tiempos('s','a');
    tiempos('s','d');
    tiempos('s','r');
    
    return 0;
}


