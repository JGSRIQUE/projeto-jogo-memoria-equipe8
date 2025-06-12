import java.util.Scanner;

public class Tarefa02_Boletos_Salario {
        public static void main(String[] args) {
            double boleto1, boleto2, boleto3,boleto4, DebitoBoleto, TotalDebito, TotalSal, TotalSalLiquido, DebitoEmFolha14, ValorRestante;
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

            DebitoBoleto = boleto1+boleto2+boleto3+boleto4;
            System.out.println("Total de Debito de Boletos: " + DebitoBoleto);

        // Lançamento de Salario

            System.out.println("\nDigite Seu Salário Bruto");
            TotalSal = ler.nextDouble();

        // Memoria de Calculo de Salario Líquido

            DebitoEmFolha14 = (TotalSal * 14/100);
            TotalSalLiquido = TotalSal - DebitoEmFolha14;

        // Memoria Total Debito

            TotalDebito = DebitoBoleto + DebitoEmFolha14;

        // Valor Líquido e Resumo

            ValorRestante = TotalSalLiquido - TotalDebito;
            System.out.println("\nTotal de Debito de Boletos: " + DebitoBoleto);
            System.out.println ("Valor de Debito em Folha 14%:" + DebitoEmFolha14);
            System.out.println("Total de Debitos: " + TotalDebito);
            System.out.println("Salario Líquido:" + TotalSalLiquido);
            System.out.println("\nValor Restante :" + ValorRestante);
        }
    }
