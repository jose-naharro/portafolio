import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	
	// Distintos mensajes de salida por pantalla
	private static final String MSG_MENU = 	"Realiza una nueva acción\n" +
											"1 Introduce un nuevo gasto\n" +
											"2 Introduce un nuevo ingreso\n" +
											"3 Mostrar gastos\n" +
											"4 Mostrar ingresos\n" +
											"5 Mostrar saldo\n" +
											"0 Salir\n" ;
	
	private static final int INTRODUCE_GASTO = 1;
	private static final int INTRODUCE_INGRESO = 2;
	private static final int MOSTRAR_GASTOS = 3;
	private static final int MOSTRAR_INGRESOS = 4;
	private static final int MOSTRAR_SALDO = 5;
	private static final int SALIR = 0;
	private static final String MSG_OPCION_NO_VALIDA = "Opción no válida.";
		
	private static final String MSG_SALIDA = "Fin del programa.\n"
									+  "Gracias por utilizar la aplicación.";
	
	// Usuario y cuenta
	private static Usuario usuario; //Usuario a crear
    private static Cuenta cuenta; //Cuenta del usuario
    
	//condición de salida del bucle del menu
    private static boolean condicionSalir = true;
	
	public static void main(String[] args) {
		int opcionElegida;
		
		//Creación del usuario e introducción datos del usuario
		usuario = new Usuario();
		solicitarDatos();
		
		//Creación de la cuenta
		cuenta = new Cuenta(usuario);
		
		//Menú
		do {
			mostrarMenu();
			opcionElegida = leerOpcion();
			condicionSalir = ejecutarOpcionElegida(opcionElegida);
		} while (!condicionSalir);
	}
	
	private static void solicitarDatos() {
		solicitarNombreUsuario();
		solicitarEdadUsuario();
		solicitarDNIusuario();
	}
	
	private static void solicitarNombreUsuario() {
		String nombre;
		Scanner escanerNombre= new Scanner(System.in);
		
		System.out.println("Introduce el nombre de usuario");
		nombre = escanerNombre.nextLine();
		
		// Comprobamos que el nombre no esté vacío o en blanco
		if (nombre.isEmpty() || nombre.isBlank()) {
			solicitarNombreUsuario();
		} else {
			// si no esta en blanco, se introduce en el usuario.
			usuario.setNombre(nombre);
		}
		
	}
	
	private static void solicitarEdadUsuario() throws InputMismatchException{
		int edad;
		Scanner escanerEdad= new Scanner(System.in);
		
		System.out.println("Introduce la edad del usuario");
		try {
			// Comprobamos que lo introducido por teclado sea un int,
			// si no, lanzará el error y saldra el menu de nuevo.
			edad = escanerEdad.nextInt();
			if (edad > 0) { // Comprobamos que la edad, al menos, sea mayor que 0
				usuario.setEdad(edad);
			} else {
				solicitarEdadUsuario();//si no, volveremos a pedir la edad.
			}
		} catch (InputMismatchException e) {		
			//No lanzamos mensaje de error porque no lo pide explicitamente,
			//solo volvemos a lanzar el método
			solicitarEdadUsuario();
		}
	}
	
	private static void solicitarDNIusuario() {
		String dni;
		Scanner escanerDNI= new Scanner(System.in);
		
		System.out.println("Introduce el DNI de usuario");
		dni = escanerDNI.nextLine();
		
		// La comprobación de un DNI válido la hace la propia clase Usuario
		// mediante el método setDNI()
		while (!usuario.setDNI(dni)){ 
			System.out.println ("DNI introducido incorrecto");
			System.out.println ("Introduce el DNI del usuario válido");
			dni= escanerDNI.nextLine();
		}
	}

	private static void mostrarMenu() throws InputMismatchException {
		System.out.print(MSG_MENU);
	}
	
	private static int leerOpcion() {
		int opcion = -1;
		Scanner escanerTeclado= new Scanner(System.in);
		
		//leemos la opcion del menu elegida mientras 
		try {
			// Comprobamos que lo introducido por teclado sea un int,
			// si no, lanzará el error y saldra el menu de nuevo.
			if (escanerTeclado.hasNextInt()) {
				opcion = escanerTeclado.nextInt();
			}
		} catch (InputMismatchException e) {		
			System.err.println(MSG_OPCION_NO_VALIDA);//si no es una opcion valida lanzamos el mensaje de error
			leerOpcion();//tratamos de volver leer la opción.
		}
		
		return opcion;
	}
	
	private static boolean ejecutarOpcionElegida(int opcion){
		switch (opcion) {
			case INTRODUCE_GASTO:
			try {
				introduceGasto();
			} catch (GastoException e) {
				e.toString();
			} 
				break;
			case INTRODUCE_INGRESO:
				introduceIngreso();
				break;
			case MOSTRAR_GASTOS:
				mostrarGastos();
				break;
			case MOSTRAR_INGRESOS:
				mostrarIngresos();
				break;
			case MOSTRAR_SALDO:
				mostrarSaldo();
				break;
			case SALIR:
				mostrarMensajeSalida();//Finalización del programa
				return true;
			default:
				System.out.println(MSG_OPCION_NO_VALIDA);
				break;
		}
		return false;
	}
	
	
	private static void introduceIngreso() {
		String descripcion;
		double cantidad = 0;
		
		Scanner escanerIngreso= new Scanner(System.in);
		
		//solicitar descripción, esta puede ser en blanco
		System.out.println("Introduce la descripción");
		descripcion = escanerIngreso.nextLine();

		try {
			while (cantidad <= 0) {
				System.out.println("Introduce la cantidad");
				cantidad = escanerIngreso.nextDouble();
			}
			cuenta.addIngresos(descripcion, cantidad);
			
			System.out.println("Saldo restante: " + cuenta.getSaldo());
		} catch (Exception e) {
			
		}
	}
	
	private static void introduceGasto() throws GastoException {
		String descripcion; 
		double cantidad = 0;
		
		Scanner escanerGasto= new Scanner(System.in);
		
		//solicitar descripción, esta puede ser en blanco
		System.out.println("Introduce la descripción");
		descripcion = escanerGasto.nextLine();
		
		// Comprobamos que el gasto sea una cantidad positiva
		while (cantidad <= 0) {
			System.out.println("Introduce la cantidad");
			cantidad = escanerGasto.nextDouble();
		}
		// Añadimos el gasto a la cuenta y se muestra el nuevo saldo
		// Lanza una GastoException si el saldo es insuficiente
		cuenta.addGastos(descripcion, cantidad);
		System.out.println("Saldo restante: " + cuenta.getSaldo());
	}
	
	private static void mostrarGastos() {
		// Traemos una copia de la lista de gastos de la cuenta
		List <Gasto> gastos = cuenta.getGastos();
		
		//Iteramos en la lista de gastos mediante un iterador
		Iterator <Gasto> it = gastos.iterator(); 
		while(it.hasNext()){
			Gasto gasto = it.next();
			System.out.println(gasto.toString());
		}
	}
	
	private static void mostrarIngresos() {
		// Traemos una copia de la lista de ingresos de la cuenta
		List <Ingreso> ingresos = cuenta.getIngresos();
		
		//Iteramos en la lista de ingresos mediante un iterador
		Iterator <Ingreso> it = ingresos.iterator(); 
		while(it.hasNext()){
			Ingreso ingreso = it.next();
			System.out.println(ingreso.toString());
		}
	}
	
	private static void mostrarSaldo() {
		System.out.println("El saldo actual de la cuenta es: " + cuenta.getSaldo() + "€");
	}

	private static void mostrarMensajeSalida() {
		System.out.println(MSG_SALIDA);
	}

}
