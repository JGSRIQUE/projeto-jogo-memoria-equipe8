import java.util.UUID;
import java.util.Scanner;

public class ObjContaCorrente {
    Scanner entrada = new Scanner(System.in);
    String ID = UUID.randomUUID().toString();
    String banco;
    String agencia;
    String contaCorrente;
    String opcaoHome;
    double saldo = 0.00;

    public void Home() {
        boolean sair = false;

        while (!sair) {
            System.out.println("\nSelecione a opção que deseja");
            System.out.println("01 - Verificar saldo");
            System.out.println("02 - Depositar");
            System.out.println("03 - Sacar");
            System.out.println("04 - Sair");
            opcaoHome = entrada.nextLine();

            switch (opcaoHome) {

                case "1":
                case "01":
                    verificarSaldo();
                    break;

                case "2":
                case "02":
                    DepositarSaldo();
                    break;

                case "3":
                case "03":
                    sacarSaldo();
                    break;

                case "4":
                case  "04":
                    System.out.println("Saindo");
                    sair = true;
                    break;
            }
        }
        entrada.close();
    }
    private void verificarSaldo() {
    System.out.println("\nSeu saldo é de: R$ " + saldo);
    }
    private void DepositarSaldo() {
        System.out.println("\nDigite quanto deseja depositar");
        double saldoDepositar = entrada.nextDouble();
        entrada.nextLine();
        this.saldo= (saldo + saldoDepositar)*1.10;
        System.out.println("Seu saldo é de: " + saldo);
        }

    private void sacarSaldo(){
        System.out.println("\nSelecione o valor que deseja sacar");
        double sacar = entrada.nextDouble();
        this.saldo = saldo - sacar;
        entrada.nextLine();
        System.out.printf("Seu saldo é de: %.2f%n", saldo);
     }
    }

