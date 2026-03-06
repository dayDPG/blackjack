public class Krupier extends Jugador {
    
    private Baraja baraja;

    // creamos un constructor para crear al krupier y sus funciones
    public Krupier() { 
        // Le ponemos 0 dinero porque al Krupier no le importa el dinero, la casa siempre gana.
        super(0); //con super llamamos a la clase de jugador, heredando las mismas variables  
                                // el krupier, como no juega como tal, tiene dinero 0
        this.baraja = new Baraja(); 
        this.baraja.mezclarCartas();      
    }

    // MÉTODOS

    // Método para repartir una carta a CUALQUIER jugador (incluido él mismo)
    public void repartirCarta(Jugador alguien) {
        // Sacamos una carta de la baraja usando el método de la clase Baraja (que hace tu compa)
        Carta carta = this.baraja.repartirUnaCarta();
        
        // Se la damos al jugador
        alguien.recibirCarta(carta);
    }
    
    // Método para reiniciar la baraja si se acaban las cartas (opcional pero útil)
    public void reiniciarBaraja() {
        this.baraja = new Baraja();
        this.baraja.mezclarCartas();
    }
}