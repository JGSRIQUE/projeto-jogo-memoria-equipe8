import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        double boleto1, boleto2, boleto3,boleto4, total;
        Scanner ler = new
                Scanner(System.in);

        System.out.println("Digite o 1º boleto:");
        boleto1 = ler.nextDouble();

        System.out.println("Digite o 2º boleto:");
        boleto2 = ler.nextDouble();

        System.out.println("Digite o 3º boleto:");
        boleto3 = ler.nextDouble();

        System.out.println("Digite o 4º boleto:");
        boleto4 = ler.nextDouble();

        total = boleto1+boleto2+boleto3+boleto4;
        System.out.print("o TOTAL DA FACADA É: " + total);
    }
}
