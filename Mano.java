
import java.util.ArrayList;
public class Mano {
    private int valorTotal;
    private boolean mostrarCartaOculta;
    ArrayList<Carta> carta;
    
    public Mano(){
        this.carta = new ArrayList<Carta>();
        this.mostrarCartaOculta = false;
    }
    public void añadirCarta(Carta carta){
        this.carta.add(carta);
    }
    public void quitarCarta(Carta carta){
        this.carta.remove(carta);
    }
    public int calcularValor(){
        valorTotal = 0;
        int ases=0;
        for (Carta c : carta) {
            if (c.getValor() == 1) {
                
                if (valorTotal <= 10) {
                    valorTotal += 11; //as como 11
                    ases++;
                } else {
                    valorTotal += 1;  //as como 1
                }
            } else {
                valorTotal += c.getValor();
            }
        }
        while (valorTotal >21 && ases>0) {
                valorTotal -= 10; //Si luego aumenta el valor total, se cambia el valor del As a 1 (restandolte 10)
                ases--;
        }
        return valorTotal;
    }

    public void setMostrarCartaOculta(boolean mostrar) {
        this.mostrarCartaOculta = mostrar;
    }
    public boolean isMostrarCartaOculta() {
        return mostrarCartaOculta;
    }
    public void imprimirMano(){
        int numLineas = 4;
        for (int i = 0; i < numLineas; i++) {
            for (int j = 0; j < carta.size(); j++) {
                Carta c = carta.get(j);
                if (j == 0 && !mostrarCartaOculta) {
                    // Imprimir carta oculta (con #)
                    System.out.print(imprimirCartaOculta()[i] + "   ");
                } else {
                    // Imprimir carta normal
                    System.out.print(c.imprimirCartas()[i] + "   ");
                }
            }
            System.out.println();
        }
    }
    private String[] imprimirCartaOculta() {
        return new String[]{
            "_____",
            "|#  |",
            "| # |",
            "|__#|"
        };
    }
}
