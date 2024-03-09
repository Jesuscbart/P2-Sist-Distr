import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
	private static final String IP = "localhost"; // Puedes cambiar a localhost
	private static final int PUERTO = 1200; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor
	
	private static int cantidadOperandos(Scanner sc){
		int cantidad = 0;
		System.out.println("¿Cuántos números deseas operar?");
		try{
			cantidad = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e){
			cantidad = 0;
		}
		return cantidad;
	}

	private static float[] operandos(Scanner sc, int cantidad){
		float[] numeros = new float[cantidad];
		for(int i = 0; i < cantidad; i++){
			System.out.println("Ingresa el número " + (i + 1) + ": ");
			try{
				numeros[i] = Float.parseFloat(sc.nextLine());
			}catch(NumberFormatException e){
				numeros[i] = 0;
			}
		}
		return numeros;
	}


	private static float cases(int eleccion,Interfaz interfaz,Scanner sc) throws RemoteException{
		
		float resultado = 0;

		float[] numeros;

		int n_operandos = 1;
		
		switch (eleccion) {
			case 0:
				n_operandos = cantidadOperandos(sc);
				numeros = operandos(sc,n_operandos);
				resultado = interfaz.sumar(numeros);
				break;
			case 1:
				n_operandos = cantidadOperandos(sc);
				numeros = operandos(sc,n_operandos);
				resultado = interfaz.restar(numeros);
				break;
			case 2:
				n_operandos = cantidadOperandos(sc);
				numeros = operandos(sc,n_operandos);
				resultado = interfaz.multiplicar(numeros);
				break;
			case 3:
				n_operandos = 2;
				numeros = operandos(sc,n_operandos);
				resultado = interfaz.dividir(numeros[0], numeros[1]);
				break;
			case 4:
				n_operandos = 1;
				numeros = operandos(sc,n_operandos);
				resultado = interfaz.raiz(numeros[0]);
				break;
			case 5:
				n_operandos = 2;
				numeros = operandos(sc,n_operandos);
				resultado = interfaz.potencia(numeros[0], numeros[1]);
				break;
		}	

		return resultado;
	}

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        Interfaz interfaz = (Interfaz) registry.lookup("Calculadora"); //Buscar en el registro...
        Scanner sc = new Scanner(System.in);
        int eleccion;
        float resultado = 0;
        String menu = "\n\n------------------\n\n[-1] => Salir\n[0] => Sumar\n[1] => Restar\n[2] => Multiplicar\n" +
				"[3] => Dividir\n[4] => Raíz cuadrada\n[5] => Potencia\n\n------------------\n\n Elige:";
        do {
            System.out.println(menu);

            try {
                eleccion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                eleccion = -1;
            }

            if(eleccion != -1){

				resultado = cases(eleccion,interfaz,sc);
                
                System.out.println("Resultado => " + String.valueOf(resultado));
                System.out.println("Presiona ENTER para continuar");
                sc.nextLine();
            }
        } while (eleccion != -1);
		sc.close();

    }
}