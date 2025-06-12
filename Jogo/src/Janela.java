import javax.swing.*;
public class Janela extends JFrame {

    public Janela() {
        //adicionar na janela
        add(new LoginInterface());

        setTitle("Wasteland Space");
        setSize(1024,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(true);
        setVisible(true);
    }
}
