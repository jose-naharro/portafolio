/*
 * Titulo: Practica 1. Suma de la subsecuencia maxima.
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

void aleatorio(int v [], int n) {
//se generan números pseudoaleatorio entre -n y +n 
    int i, m=2*n+1;
    for (i=0; i < n; i++)
        v[i] = (rand() % m) - n;
}

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

long sumaSubMax1 (int v[], int n){
    int i,j;
    long estaSuma, sumaMax = 0;
    for (i = 0; i < n; i++){
        estaSuma = 0;
        for (j = i; j < n; j++){
            estaSuma += v[j];
            if (estaSuma>sumaMax){
                sumaMax = estaSuma;
            }
        }
    }
    return sumaMax;
}

long sumaSubMax2(int v[], int n){
    long estaSuma = 0, sumaMax = 0;
    int j;
    for (j = 0; j < n; j++){
        estaSuma += v[j];
        if (estaSuma > sumaMax){
            sumaMax = estaSuma;
        }
        else if (estaSuma < 0){
            estaSuma = 0;
        }
    }
    return sumaMax;   
}

void listar_vector( int v[], int n){
//Recorre un vector y va mostrando sus elementos por pantalla
    int i;
    printf("[");
    for (i = 0; i < n; i++){
        printf("%4i" , v[i] );
    }
    printf("]");
}

void test1() {
    int i;
    long a, b;
    int v[][5] = {
        {-9, 2, 6, -4, -4}, 
        {4, 0, 9, 2, 5},
        {-2, -1, -9, -7, -1},
        {9, -2, 1, -7, -8},
        {15, -2, -5, -4, 16},
        {7, -5, 6, 7, -7},
    };
    printf("\ntest1\n");
    printf("%22s%15s%15s\n", "", "sumaSubMax1", "sumaSubMax2");
    for (i = 0; i < 6; i++) {
        listar_vector(v[i], 5);
        a = sumaSubMax1(v[i], 5);
        b = sumaSubMax2(v[i], 5);
        printf("%15ld%15ld\n", a, b);
    }
}

void test2(){
    long a, b;
    int i, n = 10; 
    int v[8];
    printf("\ntest2\n");
    printf("%34s%15s%15s\n", "", "sumaSubMax1", "sumaSubMax2");
    for (i=0; i<n; i++){
        aleatorio(v, 8);
        listar_vector(v, 8);
        a = sumaSubMax1(v, 8);
        b = sumaSubMax2(v, 8);
        printf("%15ld%15ld\n", a, b);
    }
}

void calcular_tiempo(){
    int i, j, n, m = 0, K = 1000;    
    int v[32000];
    double t = 0, ta = 0, tb = 0, t1 = 0, t2 = 0, aj = 0, sobre = 0, sub = 0; 
    printf("\nSumaSubMax 1\n %12s%15s%15s","n","t(n)", "t(n)/n^1.8");
    printf("%15s%15s\n", "t(n)/n^2" , "t(n)/n^2.2");
    for (n = 500; n <= 32000; n*=2){
        aleatorio(v,n);
        ta = microsegundos();
        sumaSubMax1(v, n);
        tb = microsegundos();
        t = tb - ta;
        if (t < 500){
            m = 1;
            ta = microsegundos();
            for (i = 0; i < K; i++){
                aleatorio(v,n);
                sumaSubMax1(v, n);
            }
            tb = microsegundos();
            t1 = tb - ta;
            ta = microsegundos();
            for (j = 0; j < K; j++){
                aleatorio(v,n);
            }
            tb = microsegundos();
            t2 = tb - ta;
            t = (t1 - t2)/K;        
        }
        aj = t/ pow (n,2);
        sobre = t/ pow (n,2.2);
        sub = t / pow (n,1.8);
        if (m == 1){
            printf("*%12d%15.3f%15.6f%15.6f%15.6f\n", n, t, sub , aj, sobre);
            m = 0;    
        }else{
            printf(" %12d%15.3f%15.6f%15.6f%15.6f\n", n, t, sub , aj, sobre);
        }
    }
}

void calcular_tiempo2(){
    int i, j, n, m = 0, K = 1000;    
    int v[512000];
    double t = 0, ta = 0, tb = 0, t1 = 0, t2 = 0, aj = 0, sobre = 0, sub = 0;
    printf("\nSumaSubMax 2\n %12s%15s%15s","n","t(n)", "t(n)/n^0.87");
    printf("%15s%15s\n", "t(n)/n^0.99" , "t(n)/nlogn");
    for (n = 500; n <= 512000; n*=2){
        aleatorio(v,n);
        ta = microsegundos();
        sumaSubMax2(v, n);
        tb = microsegundos();
        t = tb - ta;
        if (t < 500){
            m = 1;
            ta = microsegundos();
            for (i = 0; i < K; i++){
                aleatorio(v,n);
                sumaSubMax2(v, n);
            }
            tb = microsegundos();
            t1 = tb - ta;
            ta = microsegundos();
            for (j = 0; j < K; j++){
                aleatorio(v,n);
            }
            tb = microsegundos();
            t2 = tb - ta;
            t = (t1 - t2)/K;
        }
        aj = t/ pow(n,0.99);
        sobre = t/ (n* log(n));
        sub = t / pow (n,0.87);
        if (m == 1){
            printf("*%12d%15.3f%15.6f%15.6f%15.6f\n", n, t, sub , aj, sobre);
            m = 0;    
        }else{
            printf(" %12d%15.3f%15.6f%15.6f%15.6f\n", n, t, sub , aj, sobre);
        }
    }
}

int main(){
    inicializar_semilla();
    test1();
    test2();
    calcular_tiempo();
    calcular_tiempo2();
    return 0;
} 
