import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Movimentos extends JFrame {


    private JLabel fundo;
    private JLabel parado = new JLabel(new ImageIcon(getClass().getResource("/Data/frente.gif")));
    private boolean teclas[] = new boolean[256];
    private int ultimaTecla = -1;

    public Movimentos(){
        setLayout(null);
        setTitle("Movimentos Teste");
        setSize(500,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);

        config();
        teclas();



        setVisible(true);
    }


    public void teclas() {

        parado.setBounds(60,60,64,64);
        add(parado);
    }

    public void config() {

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                teclas[key] = true;
                ultimaTecla = key;

                switch (key) {

                    case KeyEvent.VK_A:
                        parado.setIcon(new ImageIcon((getClass().getResource("Data/CorrendoEsquerdo.gif"))));
                        parado.setLocation(parado.getX() - 5, parado.getY());
                        break;

                    case KeyEvent.VK_D:
                        parado.setIcon(new ImageIcon((getClass().getResource("Data/CorrendoDireito.gif"))));
                        parado.setLocation(parado.getX() + 5, parado.getY());
                        break;

                    case KeyEvent.VK_S:
                        parado.setIcon(new ImageIcon((getClass().getResource("Data/CorrendoFrente.gif"))));
                        parado.setLocation(parado.getX(), parado.getY() + 5);
                        break;

                    case KeyEvent.VK_W:
                        parado.setIcon(new ImageIcon((getClass().getResource("Data/Costas.gif"))));
                        parado.setLocation(parado.getX(), parado.getY() - 5);
                        break;

                    case KeyEvent.VK_C:
                        parado.setIcon(new ImageIcon((getClass().getResource("Data/FrenteAtacando.gif"))));
                        break;
                }
            }
                // Define sprite de parado com base na última tecla pressionada

                @Override
                public void keyReleased(KeyEvent e) {
                    teclas[e.getKeyCode()] = false;

                switch (ultimaTecla) {
                    case KeyEvent.VK_W:
                        parado.setIcon(new ImageIcon(getClass().getResource("/Data/CostasParado.png")));
                        break;
                    case KeyEvent.VK_S:
                        parado.setIcon(new ImageIcon(getClass().getResource("/Data/FrenteComEfeito.gif")));
                        break;
                    case KeyEvent.VK_A:
                        parado.setIcon(new ImageIcon(getClass().getResource("/Data/EsquerdoParado.png")));
                        break;
                    case KeyEvent.VK_D:
                        parado.setIcon(new ImageIcon(getClass().getResource("/Data/DireitoParado.png")));
                        break;
                    default:
                        parado.setIcon(new ImageIcon(getClass().getResource("/Data/FrenteComEfeito.gif"))); // fallback
                        break;
            }

            }
        });
    }

}
class Main {
    public static void main(String[] args) {
        new Movimentos();
    }
}