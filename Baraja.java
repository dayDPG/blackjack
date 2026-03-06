
import java.util.ArrayList;
import java.util.Collections;

public class Baraja extends Mano{
    private static String[] SIMBOLOS={"p", "c", "d", "t"};
    public Baraja(){
        carta = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (String simbolo : SIMBOLOS) {
                carta.add(new Carta(i, simbolo));
            }
        }
    }
    public void mezclarCartas(){
        Collections.shuffle(carta);
    }
    public Mano repartirMano(int numCartas){
        Mano mano = new Mano();
        for (int i = 0; i < numCartas; i++) {
            Carta c = carta.remove(0);
            mano.añadirCarta(c);
        }
        return mano;
        
    }
    public Carta repartirUnaCarta() {
        return carta.remove(0);
    }
}

