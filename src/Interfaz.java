import java.rmi.Remote;
import java.rmi.RemoteException;

/*
	Declarar firma de métodos que serán sobrescritos
*/
public interface Interfaz extends Remote {
    float sumar(float[] numeros) throws RemoteException;
    float restar(float[] numeros) throws RemoteException;
    float multiplicar(float[] numeros) throws RemoteException;
    float dividir(float numero1, float numero2) throws RemoteException;
    float raiz(float numero1) throws RemoteException;
    float potencia(float base, float exponente) throws RemoteException;
}