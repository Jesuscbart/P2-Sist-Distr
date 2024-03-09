import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
public class Servidor {
	private static final int PUERTO = 1200; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Remote remote = UnicastRemoteObject.exportObject(new Interfaz() {

            @Override
            public float sumar(float[] numeros) throws RemoteException {      
                float resultado = 0;
                
                for (float numero : numeros) {
                    resultado += numero;
                }
                
                return resultado;
            };
            @Override
            public float restar(float[] numeros) throws RemoteException {
                float resultado = numeros[0];

                for (int i = 1; i < numeros.length; i++) {
                    resultado -= numeros[i];
                }
            
                return resultado;
            };
            @Override
            public float multiplicar(float[] numeros) throws RemoteException {
                float resultado = 1;
                
                for (float numero : numeros) {
                    resultado = resultado * numero;
                }
                
                return resultado;
            };
            @Override
            public float dividir(float numerador, float denominador) throws RemoteException {
                if (denominador == 0) {
                    throw new ArithmeticException("No se puede dividir entre cero");
                }

                return numerador / denominador;
            }
            @Override
            public float raiz(float numero) throws RemoteException {
                if (numero < 0) {
                    throw new IllegalArgumentException("El número no puede ser negativo");
                }

                return (float) Math.sqrt(numero);
            }
            @Override
            public float potencia(float base, float exponente) throws RemoteException {
                return (float) Math.pow(base, exponente);
            };
        }, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
       	System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO));
        registry.bind("Calculadora", remote); // Registrar calculadora
    }
}
