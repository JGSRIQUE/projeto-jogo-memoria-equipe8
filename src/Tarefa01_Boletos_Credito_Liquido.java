import java.util.Scanner;

public class Tarefa01_Boletos_Credito_Liquido {
        public static void main(String[] args) {
            double boleto1, boleto2, boleto3,boleto4, Credito, TotalDebito, TotalCredito, ValorLiquido;
            Scanner ler = new
                    Scanner(System.in);
        // Lançamento de Boletos

            System.out.println("Digite o 1º boleto:");
            boleto1 = ler.nextDouble();

            System.out.println("Digite o 2º boleto:");
            boleto2 = ler.nextDouble();

            System.out.println("Digite o 3º boleto:");
            boleto3 = ler.nextDouble();

            System.out.println("Digite o 4º boleto:");
            boleto4 = ler.nextDouble();

        // Soma de Boletos de Debito e Escreva

            TotalDebito = boleto1+boleto2+boleto3+boleto4;
            System.out.println("Total de Debito: " + TotalDebito);

        // Lançamento de Credito

            System.out.println("\nDigite o Valor de Crédito Disponível");
            Credito = ler.nextDouble();

        // Soma de Credito

            TotalCredito = Credito;

        // Valor Líquido e Resumo

            ValorLiquido = TotalCredito - TotalDebito;
            System.out.println("\nTotal de Debito: " + TotalDebito);
            System.out.println("Total de Crédito:" + TotalCredito);
            System.out.println("\nTotal Líquido:" + ValorLiquido);
        }
    }
