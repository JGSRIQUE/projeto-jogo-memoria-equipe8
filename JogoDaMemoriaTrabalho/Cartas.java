public class Cards {
    private int id;
    private boolean virada = false;
    private ImageIcon imagemFrente;
    private ImageIcon imagemVerso;
    private JButton botao;
    private Tela tela;

    public Cards(int id, Tela tela) {
        this.id = id;
        this.tela = tela;

        imagemFrente = new ImageIcon(getClass().getResource("/" + id + ".png"));
        imagemVerso = new ImageIcon(getClass().getResource("/verso.png"));

        botao = new JButton(imagemVerso);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setOpaque(false);

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tela.cartaClicada(Cards.this); // deixa a Tela decidir se pode clicar
            }
        });
    }

    public void virar() {
        virada = !virada;
        botao.setIcon(virada ? imagemFrente : imagemVerso);
    }

    public void desativar() {
        botao.setEnabled(false);
    }

    public boolean estaVirada() {
        return virada;
    }

    public int getId() {
        return id;
    }

    public JButton getBotao() {
        return botao;
    }
}