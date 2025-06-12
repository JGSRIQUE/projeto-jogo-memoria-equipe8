import java.util.Scanner;

public class FolhaPagamento {

    public static void main (String [] args) {
        Scanner entrada = new Scanner(System.in);
        double salarioBruto = -1;

        System.out.println("\nDigite o valor do salário bruto do colaborador");
        while (salarioBruto < 0) {
            salarioBruto = entrada.nextDouble();

            if (salarioBruto < 0) {
                System.out.println("Valor inválido. Tente novamente:");
            }
        }

        // Tabela de IRRF

        double descontoIR = 0;
        String  isencaoIR = "";

        if (salarioBruto >= 0 && salarioBruto <= 1200) {
              isencaoIR = "Isento";
              descontoIR = 0;

        } else if (salarioBruto >= 1200.01 && salarioBruto <= 2500) {
            descontoIR = salarioBruto * 0.05;

        } else if (salarioBruto >= 2500.01 && salarioBruto <= 3500) {
            descontoIR = salarioBruto * 0.1;

        } else if (salarioBruto >= 3500.01 && salarioBruto <= 5500) {
            descontoIR = salarioBruto * 0.2;

        } else {
            descontoIR = salarioBruto * 0.2;
        }


    // Desconto Sindical

        double descontoSindical = 0;

        descontoSindical = salarioBruto * 0.03;

    // Holerite simplificado
        System.out.println();
        System.out.printf("Salário Bruto: R$ %.2f%n", salarioBruto);

        if (isencaoIR.equals("Isento")) {
            System.out.println("Desconto de IRRF Isento: R$" + descontoIR);
        } else {
            System.out.printf("Desconto de IRRF: R$ %.2f%n", descontoIR);
        }

        System.out.printf("Desconto Sindical: R$ %.2f%n", descontoSindical);

        // Calculo de salario liquido

        double salarioLiquido = 0;

        salarioLiquido = salarioBruto - descontoIR - descontoSindical;
        System.out.println("-----------------------------------------");
        System.out.printf("Salário Líquido: R$ %.2f%n", salarioLiquido);
    }
}
