import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        Scanner ingreso = new Scanner(System.in);
        
        do {
            imprimirMenu();
            n = ingreso.nextInt();
            switch (n) {
            case 1:
                System.out.println("¡Bienvenido!");
                juego();
                break;
            case 2:
                imprimirReglas();
                n = ingreso.nextInt();
                if (n==0) {
                    break;
                }
                else{System.out.println("Ingrese una opcion valida");}
                
            case 3:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Valor invalido, intente de nuevo!!");
                break;
        }
        } while (n!=3);
        ingreso.close();
    }
        public static void imprimirMenu(){
        System.out.println("      ______________      ");
        System.out.println("|||||   BLACKJACK   |||||");
        System.out.println("||||| 1. Jugar      |||||");
        System.out.println("||||| 2. Ver reglas |||||");
        System.out.println("||||| 3. Salir      |||||");
        System.out.println("  ---------------------  ");


    }
    public static void imprimirReglas(){
        System.out.println("Reglas Blackjack:");
        System.out.println("- Try to get close to 21 without going over");
        System.out.println("- Kings, Queens, Jacks are worth 10 points");
        System.out.println("- Aces are worth 1 or 11 points");
        System.out.println("- Cards 2-10 are worth their face value");
        System.out.println("- On the fisrt play, you can double down to increase your bet");
        System.out.println("- In case of a tie, the bet is returned to the player");
        System.out.println("- Dealer stops hitting at 17");
        System.out.println("----------------------------------------------");
        System.out.println("0 - volver al menu");
    }

    //SE ESTABLECE LAS ACCIONES
    // PLANTARSE, PEDIR CARTA
    public static boolean controles(int opt, Jugador jugador, Krupier krupier, boolean turnoJugador){
        switch (opt) {
            case 1:
                krupier.repartirCarta(jugador);
                break;
            case 2:
                System.out.println("Te plantas. Turno del krupier.");
                return false; //termina el turno si esq se planta
            default:
                System.out.println("Ingrese un valor valido...");
                break;
        }
        return turnoJugador;
    }
    //LOGICA DEL JUEGOO
    public static void juego(){
        Scanner scanner = new Scanner(System.in); 

        //Establecemos el monto total
        System.out.print("Ingrese el monto total a usar en el juego: ");
        int dineroInicial = scanner.nextInt(); //Lo guardamos en la variable dineroInicial

        Jugador jugador = new Jugador(dineroInicial);
        Krupier krupier = new Krupier();
        boolean seguirJugando = true;

        while (seguirJugando && jugador.getDinero() > 0) {
            System.out.println("Dinero: " + jugador.getDinero());

            System.out.println("¿Cuánto quieres apostar?");
            int apuesta = scanner.nextInt();
            int bote = jugador.apostar(apuesta);
            if (bote == 0) continue; // en caso de no poder apostar, se vu4lve a empezar
            System.out.println("Apuesta: " + bote);
            
            //El krupier empieza a repartir cartas
            krupier.getMano().setMostrarCartaOculta(false);//Se mantiene oculta la primera carta del Krupier
            jugador.getMano().setMostrarCartaOculta(true); 
            krupier.repartirCarta(krupier);
            krupier.repartirCarta(jugador);
            krupier.repartirCarta(krupier);
            krupier.repartirCarta(jugador);

            //Se imprime primero lo del DEALER
            System.out.println("\nDEALER: ???");
            krupier.getMano().imprimirMano();

            //Se imprime lo del jugador (PLAYER)
            System.out.println("\nPLAYER: "+ jugador.getMano().calcularValor());
            jugador.getMano().imprimirMano();

            boolean turnoJugador= true;
            //JUEGA el jugador (¿)
            while (turnoJugador) {
                if (jugador.getMano().calcularValor() > 21) {
                    System.out.println("¡Te has pasado de 21! Has perdido.");
                    turnoJugador = false;
                    break;
                    // si el booleano es falso, se sale del bucle y termina nuestro turno
                }
                //CONTROLESS
                System.out.println("\n1 - PEDIR | 2 - PLANTARSE");
                int opcion = scanner.nextInt();

                turnoJugador = controles(opcion, jugador, krupier,turnoJugador);

                System.out.println("\nDEALER: ???");
                krupier.getMano().imprimirMano();
                System.out.println("\nPLAYER: " + jugador.getMano().calcularValor());
                jugador.getMano().imprimirMano();
            }
            krupier.getMano().setMostrarCartaOculta(true); // Revelar carta oculta
            System.out.println("\nRevelando la carta oculta del krupier...");
            System.out.println("DEALER:" + krupier.getMano().calcularValor());
            krupier.getMano().imprimirMano();

            if (jugador.getMano().calcularValor()<=21) {
                //JUEGA EL KRUPIER 
                //el krupier agarrara mas cartas si es que el valor final es menor a 16
                // Regla de la imagen: Dealer stops hitting at 17
                while (krupier.getMano().calcularValor() < 17) {
                    System.out.println("\nEl krupier agarra una carta extra...");
                    krupier.repartirCarta(krupier);

                    System.out.println("DEALER:" + krupier.getMano().calcularValor());
                    krupier.getMano().imprimirMano();

                    System.out.println("\nPLAYER: " + jugador.getMano().calcularValor());
                    jugador.getMano().imprimirMano();
                } 
            }

            int puntosJugador = jugador.getMano().calcularValor();
            int puntosKrupier = krupier.getMano().calcularValor();

            if (puntosJugador > 21) {
                System.out.println("Pierdes " + apuesta + " euros");
            } else if (puntosKrupier > 21) {
                System.out.println("El Krupier se ha pasado, tu ganas " + (apuesta * 2));
                jugador.ganarDinero(apuesta * 2);
            } else if (puntosJugador > puntosKrupier) {
                System.out.println("ganas por tener mas puntos " + (apuesta * 2));
                jugador.ganarDinero(apuesta * 2);
            } else if (puntosJugador == puntosKrupier) {
                System.out.println("empate, recuperas tu dinero ");
                jugador.ganarDinero(apuesta);
            } else {
                System.out.println("El Krupier tiene más puntos, pierdes.");
            }

            // vaciamos/limpiamos las manos para la siguiente ronda
            jugador.limpiarMano();
            krupier.limpiarMano(); 
            
            if (jugador.getDinero() == 0) {
                System.out.println("No te queda dinero, se termina la partida");
                seguirJugando = false;
            }
            System.out.println("¿Quieres seguir jugando? 1 - Sí | 0 - No");
            int anws = scanner.nextInt();
            if (anws == 0) {
                seguirJugando= false;
            }
            else if (anws == 1){
                System.out.println("OTRA RONDA");
            }
            else { System.out.println("Ingrese un valor correcto...");}
        }
    }
}
