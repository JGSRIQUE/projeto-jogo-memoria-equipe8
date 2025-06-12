import javax.swing.*;


public class SistemaBanco extends JFrame {

    public SistemaBanco() {
        setTitle("Sistema do Banco");
        setSize(1024,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JLabel boasVindas = new JLabel("Bem vindo ao Banco");
        boasVindas.setBounds(0,0,20,20);
        add(boasVindas);
    }
}