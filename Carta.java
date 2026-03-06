

public class Carta {
    
    private int valor;
    private String simbolo;


    public Carta(int pvalor, String psimbolo){
        this.valor= pvalor;
        this.simbolo=psimbolo;
    }

    public void setValor(int pvalor){
        this.valor = pvalor;
    }
    public int getValor() {
        if (valor > 10) { // J, Q, K
            return 10;
        }
        return valor;
    }
    public String[] imprimirCartas(){
        String valorLetra;
        switch (valor) {
            case 0:
                valorLetra="";
                break;
            case 1:
                valorLetra="A";
                break;
            case 11:
                valorLetra="J";
                break;
            case 12:
                valorLetra="Q";
                break;
            case 13:
                valorLetra="K";
                break;
            default: valorLetra =""+valor;
        }
        
        return new String[]{
            "_____",
            "|"+valorLetra+"  |",
            "| " + simbolo + " |",
            "|__"+ valorLetra +"|"
        };
    }
  
}
