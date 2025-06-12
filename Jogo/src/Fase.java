import javax.swing.*;
import java.awt.*;

public class Fase extends JPanel {

    private Image Fundo;

    public Fase() {
        ImageIcon referencia = new ImageIcon("C:\\Users\\Jorge\\IdeaProjects\\Primeiro Projeto\\Jogo\\res\\fundosistema.png");
        Fundo = referencia.getImage();
    }

    public void paint (Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(Fundo,0,0,null);
        g.dispose();
    }
}
