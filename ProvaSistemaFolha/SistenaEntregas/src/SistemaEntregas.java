import java.util.Scanner;

class Cliente {
Scanner entrada = new Scanner(System.in);

    String nome;
    String cpf;
    String telefone;
    String endereco;

    void cadastrarCliente(){
        System.out.println("Olá, seja bem vindo: ");
        System.out.println("Digite seu nome completo: ");
        nome = entrada.nextLine();

        System.out.println("Digite seu CPF: ");
        cpf = entrada.nextLine();

        System.out.println("Digite seu telefone: ");
        telefone = entrada.nextLine();

        System.out.println("Digite seu endereço: ");
        endereco = entrada.nextLine();

    }

    void informacoesCliente(){

        System.out.println("Nome: " + nome);
        System.out.println("Nome: " + cpf);
        System.out.println("Nome: " + telefone);
        System.out.println("Nome: " + endereco);
    }
}

public class SistemaEntregas {

    Scanner entrada = new Scanner(System.in);

    public static void main (String [] args) {
        Cliente chamarCliente = new Cliente();

        chamarCliente.cadastrarCliente();
        System.out.println();
        chamarCliente.informacoesCliente();
    }

}
