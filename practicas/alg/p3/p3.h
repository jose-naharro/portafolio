
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

struct nodo {
    int elem;
    int num_repeticiones;
    struct nodo *izq, *der;
};

typedef struct nodo *posicion;
typedef struct nodo *arbol;



arbol creararbol();/* devuelve un arbol vacio */

int esarbolvacio(arbol);

posicion buscar(int, arbol);

arbol eliminararbol(arbol);/* borra todos los nodos liberando la
                            * memoria y devolviendo un arbol vacio */
posicion hijoizquierdo(arbol);

posicion hijoderecho(arbol);

int elemento(posicion);

int numerorepeticiones(posicion);

int altura(arbol);

void visualizar(arbol);/* imprime el contenido del arbol */

