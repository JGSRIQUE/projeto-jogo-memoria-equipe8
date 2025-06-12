import java.util.Scanner;

public class TarefatresJardel {
    public static void main(String [] args) {
    Scanner entrada = new Scanner(System.in);
    numerossoma entradanumero = new numerossoma();

    System.out.println("Digite o primeiro número");
    entradanumero.primeironumero = entrada.nextDouble();

    System.out.println("Digite o segundo número");
    entradanumero.segundonumero = entrada.nextDouble();

    System.out.println("Digite o terceiro número");
    entradanumero.terceironumero = entrada.nextDouble();

    System.out.println("Digite o quarto número");
    entradanumero.quartonumero = entrada.nextDouble();

    entradanumero.calcularnumeros();
    entradanumero.quantidadedealgarismos(4);
    entradanumero.mediaaritmetica();
    }
}
