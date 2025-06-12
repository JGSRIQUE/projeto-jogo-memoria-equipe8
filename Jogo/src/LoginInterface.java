import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface extends JPanel {
    private ListaLogin chamarListaLogin = new ListaLogin();

    public LoginInterface() {

        setLayout(null);
// login
     JLabel textLogin = new JLabel("Usuario: ");
             textLogin.setBounds(50, 50, 100, 20);
             add(textLogin);

      JTextField campoLogin = new JTextField();
        campoLogin.setBounds(110, 50, 150, 20);
        add(campoLogin);

// senha

        JLabel textSenha = new JLabel("Senha: ");
        textSenha.setBounds(50, 80, 150, 20);
        add(textSenha);

        JPasswordField campoSenha = new JPasswordField ();
        campoSenha.setBounds(110, 80, 150, 20);
        add(campoSenha);

       JButton botaoEntrar = new JButton("Entrar");
       botaoEntrar.setBounds(110, 110, 150, 30);
       botaoEntrar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {;
               String usuario = campoLogin.getText();
               String senha = new String(campoSenha.getPassword());

               boolean verificadorLogin = (usuario.equals(chamarListaLogin.usuario01) && senha.equals(chamarListaLogin.senha01));
               if (verificadorLogin) {
                   JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                   JFrame janelaSistema = new SistemaBanco();
                   SwingUtilities.getWindowAncestor(LoginInterface.this).dispose();

               } else {
                   JOptionPane.showMessageDialog(null, "Usuario e/ou senha incorreto");
               }
               campoLogin.setText("");
               campoSenha.setText("");
           }
       });
     add(botaoEntrar);

    }
}
