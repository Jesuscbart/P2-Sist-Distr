import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class TestServidorRMI {
private static final String IP = "localhost"; // Puedes cambiar a localhost
private static final int PUERTO = 1200; //Si cambias aquÃ­ el puerto, recuerda cambiarlo en el servidor

private static Interfaz interfaz = setUp();

private static Interfaz setUp() {
    try {
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        Interfaz interfaz = (Interfaz) registry.lookup("Calculadora");
        return interfaz;
    } catch (RemoteException | NotBoundException e) {
        e.printStackTrace();
        return null;
    }
}
   

    @Test
    public void testSumar() throws Exception {
        float[] numeros = { 2, 3, 5 };
        float resultado = interfaz.sumar(numeros);
        assertEquals(10, resultado, 0);
    }

    @Test
    public void testRestar() throws Exception {
        float[] numeros = { 10, 3, 5 };
        float resultado = interfaz.restar(numeros);
        assertEquals(2, resultado, 0);
    }

    @Test
    public void testRestaconNegativos() throws Exception {
        float[] numeros = { 8, -3, 5 };
        float resultado = interfaz.restar(numeros);
        assertEquals(6, resultado, 0);
    }

    @Test
    public void testMultiplicar() throws Exception {
        float[] numeros = { 2, 3, 5 };
        float resultado = interfaz.multiplicar(numeros);
        assertEquals(30, resultado, 0);
    }

    @Test
    public void testDividir() throws Exception {
        float resultado = interfaz.dividir(10, 2);
        assertEquals(5, resultado, 0);
    }

    @Test(expected = ArithmeticException.class)
    public void testDividirPorCero() throws Exception {
        interfaz.dividir(10, 0);
    }

    @Test
    public void testRaiz() throws Exception {
        float resultado = interfaz.raiz(16);
        assertEquals(4, resultado, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRaizNegativa() throws Exception {
        interfaz.raiz(-4);
    }

    @Test
    public void testPotencia() throws Exception {
        float resultado = interfaz.potencia(2, 3);
        assertEquals(8, resultado, 0);
    }

    @Test
    public void testPotenciaConCero() throws Exception {
        float resultado = interfaz.potencia(5, 0);
        assertEquals(1, resultado, 0);
    }

    @Test
    public void testPotenciaConNegativo() throws Exception {
        float resultado = interfaz.potencia(2, -3);
        assertEquals(0.125, resultado, 0);
    }
}