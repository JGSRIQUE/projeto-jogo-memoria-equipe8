public class SistemaBanco2 {
    public static void main(String[] args) {
        ObjLogin chamarobjlogin = new ObjLogin();
        ObjContaCorrente chamarCorrente = new ObjContaCorrente();

        while (true) {
            chamarobjlogin.chamarLogin();
            chamarobjlogin.chamarSenha();
            chamarobjlogin.VerificarLogin();

            if (chamarobjlogin.logindigitado.equals(chamarobjlogin.login01) && chamarobjlogin.senhadigitado.equals(chamarobjlogin.senha01)) {
                break;
            }
        }
            chamarCorrente.Home();
    }
}