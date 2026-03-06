import java.util.Scanner;

public class Jugador {
    // variables privadas para que no se puedan modificar desde fuera
    private int dinero;
    private Mano mano; // heredada de la clase mano anterior

    // empezamos definiendo con cuanto dinero empieza el jugador
    // creando el constructor jugador y asignándole el dinero con el que empieza
    // la mano debe empezar vacía
    // en la clase mano, se iran guardando las cartas que ira recibiendo
    public Jugador(int dineroInicial) {
        this.dinero = dineroInicial;
        this.mano = new Mano(); 
    }

    // método para apostar 
    public int apostar(int cantidad) {
        if (cantidad > this.dinero) {
            System.out.println("No tienes suficiente dinero");
            return 0; // no prodrá apostar
        } else {
            this.dinero = this.dinero - cantidad; 
            return cantidad; // devolvemos la cantidad apostada
        }
    }

    // método de lo que recibe (solo si gana)
    public void ganarDinero(int cantidad) {
        this.dinero = this.dinero + cantidad;
    }

    // método para guardar la carta en mano
    public void recibirCarta(Carta carta) {
        this.mano.añadirCarta(carta);
    }

    // al definir la variable dinero como privado, creamos el métodoget para leerlo sin que sea modificado
    public int getDinero() {
        return dinero;
    }

    // igual que lo del dinero
    public Mano getMano() {
        return mano;
    }
    
    // al empezar otra ronda, la mano debe volver a estar vacía
    public void limpiarMano() {
        this.mano = new Mano(); 
    }
}