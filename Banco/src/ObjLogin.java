import java.util.Scanner;

public class ObjLogin {
    Scanner entrada = new Scanner (System.in);
    String login01 = "47423578843";
    String senha01 = "@Destiny14101311";
    String logindigitado;
    String senhadigitado;

    public void chamarLogin() {
        System.out.println("Digite seu login");
        logindigitado = entrada.nextLine();
        System.out.println("Login:" + logindigitado);
        }

    public void chamarSenha(){
        System.out.println("Digite sua senha");
        senhadigitado = entrada.nextLine();
        System.out.println("Senha:" + senhadigitado);
        }
    public void VerificarLogin(){
    boolean verificadorAcesso = logindigitado.equals(login01) && senhadigitado.equals(senha01);

        if (verificadorAcesso){
        System.out.println("\nBem vindo ao JG Bank");
        } else {
        System.out.println("\nLogin ou senha invalido, tente novamente");
















































































        }
    }
}

