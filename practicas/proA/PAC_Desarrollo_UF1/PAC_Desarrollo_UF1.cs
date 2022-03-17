using System;

namespace PAC_Desarrollo_UF1 {
    class PAC_Desarrollo_UF1 {
        static void Main(string[] args) {
            

            /* El enunciado no especifica de que tipo han de ser los elementos de las matrices. 
             * En nuestro caso tomaremos el tipo entero.
             */

            int filasA, filasB, columnasA, columnasB;
            string buffer;
            int[,] matrizA, matrizB;
            bool matricesIguales = true;

            Console.WriteLine("El siguiente programa dice si se pueden comparar dos matrices,");
            Console.WriteLine("A y B, que, previamente, ha intoducido el usuario.");
            Console.WriteLine("En caso de serlo, dice si son o no iguales.\n");
            
            //Se deberá pedir el número de filas y de columnas que deberá tener la primera matriz
            Console.Write("Introduzca numero de filas de la matriz A: ");
            buffer = Console.ReadLine();
            while ((!int.TryParse(buffer, out filasA)) || (filasA <= 0)) {
                Console.WriteLine("Ha introducido un valor incorrecto. Ha de ser un entero positivo.");
                Console.Write("Introduzca numero de filas de la matriz A: ");
                buffer = Console.ReadLine();
            }
            Console.Write("Introduzca numero de columnas de la matriz A: ");
            buffer = Console.ReadLine();
            while ((!int.TryParse(buffer, out columnasA)) || (columnasA <= 0)) {
                Console.WriteLine("Ha introducido un valor incorrecto. Ha de ser un entero positivo.");
                Console.Write("Introduzca numero de columnas de la matriz A: ");
                buffer = Console.ReadLine();
            }
            matrizA = new int[filasA, columnasA];
            Console.WriteLine("Matriz A de {0} filas y {1} columnas.\n", filasA, columnasA);

            //Se deberá pedir el número de filas y de columnas que deberá tener la segunda matriz
            Console.Write("Introduzca numero de filas de la matriz B: ");
            buffer = Console.ReadLine();
            while ((!int.TryParse(buffer, out filasB)) || (filasB <= 0)) {
                Console.WriteLine("Ha introducido un valor incorrecto. Ha de ser un entero positivo.");
                Console.Write("Introduzca numero de filas de la matriz B: ");
                buffer = Console.ReadLine();
            }
            Console.Write("Introduzca numero de columnas de la matriz B: ");
            buffer = Console.ReadLine();
            while ((!int.TryParse(buffer, out columnasB)) || (columnasB <= 0)) {
                Console.WriteLine("Ha introducido un valor incorrecto. Ha de ser un entero positivo.");
                Console.Write("Introduzca numero de columnas de la matriz B: ");
                buffer = Console.ReadLine();
            }
            matrizB = new int[filasB, columnasB];
            Console.WriteLine("Matriz B de {0} filas y {1} columnas.\n", filasB, columnasB);

            /*
             * A partir de ahora podriamos acceder al numero de filas o columnas a traves de la funcion GetLength(Int32)
             * accesible desde cada matriz, pero al tratarse de un programa sencillo, con pocas variable, decidimos
             * hacer uso de una variable para cada una de las dimensiones de los arrays, es decir 4 variables.
             * De esta manera no tenemos que hacer las multiples llamadas a la función GetLength(Int32).
             * Tomamos esta decisión tratando de que sea más eficiente, ya que según la documentacion de microsoft,
             * aumentariamos la eficiencia en alrededor de un 30%, no siendo realmente significativo en este caso.
             * Además GetLenght(Int32) tiene una complejidad O(1), que es tambien eficiente.
             */

            //El usuario introducirá los elementos de la primera matriz
            for (int i = 0; i < filasA; i++) {
                for (int j = 0; j <columnasA; j++) {
                    Console.Write("Introduzca el elemento [{0},{1}] de la Matriz A: ", i, j);
                    buffer = Console.ReadLine();
                    while (!int.TryParse(buffer, out matrizA[i, j])) {
                        Console.WriteLine("Ha introducido un valor incorrecto. Ha de ser un entero.");
                        Console.Write("Introduzca el elemento [{0},{1}] de la Matriz A: ", i, j);
                        buffer = Console.ReadLine();
                    }
                }
            }

            Console.WriteLine();

            //El usuario introducirá los elementos de la segunda matriz
            for (int i = 0; i < filasB; i++) {
                for (int j = 0; j < columnasB; j++) {
                    Console.Write("Introduzca el elemento [{0},{1}] de la Matriz B: ", i, j);
                    buffer = Console.ReadLine();
                    while (!int.TryParse(buffer, out matrizB[i, j])) {
                        Console.WriteLine("Ha introducido un valor incorrecto. Ha de ser un entero.");
                        Console.Write("Introduzca el elemento [{0},{1}] de la Matriz B: ", i, j);
                        buffer = Console.ReadLine();
                    }
                }
            }

            Console.WriteLine();
            
            //Se mostrará por pantalla el contenido de la primera matriz
            Console.Write("Matriz A de {0} filas y {1} columnas: ", filasA, columnasA);
            for (int i = 0; i < filasA; i++) {
                Console.WriteLine();
                for (int j = 0; j < columnasA; j++) {
                    Console.Write(matrizA[i, j] + "\t");
                }
            }

            Console.WriteLine();
            Console.WriteLine();

            //Se mostrará por pantalla el contenido de la segunda matriz
            Console.Write("Matriz B de {0} filas y {1} columnas: ", filasB, columnasB);
            for (int i = 0; i < filasB; i++) {
                Console.WriteLine();
                for (int j = 0; j < columnasB; j++) {
                    Console.Write(matrizB[i, j] + "\t");
                }
            }

            Console.WriteLine();
            Console.WriteLine();

            if (((filasA == filasB)) && (columnasA == columnasB)) {
                Console.WriteLine("Las matrices pueden ser comparadas");
                //Confirmamos la igualdad uno a uno de cada elemento de la matriz, en cuanto uno sea distinto finalizamos.
                //Al saber que ya son comparables, podemos recorrer por filas y columnas tanto de la Matriz A como de la B.
                for (int i = 0; (i < filasA) && (matricesIguales); i++) {
                    for (int j = 0; (j < columnasA) && (matricesIguales); j++) {
                        if (matrizA[i, j] != matrizB[i, j]) {
                            matricesIguales = false;
                        }
                    }
                }
                
                if (matricesIguales) {
                    Console.WriteLine("Las matrices son iguales");
                } else {
                    Console.WriteLine("Las matrices no son iguales");
                }

            } else {
                Console.WriteLine("Las matrices no pueden ser comparadas");
            }
        }
    }
}
