import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class Cards {
    protected  int id;
    protected  boolean virada = false;
    protected  ImageIcon imagemFrente;
    protected  ImageIcon imagemVerso;
    protected  JButton botao;
    protected  Tela tela;

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
                tela.cartaClicada(Cards.this);
            }
        });
    }

    public int getValor() {
        return 1;
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

    public void virarParaVerso() {
        virada = false;
        botao.setIcon(imagemVerso);
    }
}

class CardLogica extends Cards {

    public CardLogica(int id, Tela tela) {
        super(id, tela);
    }

    @Override
    public int getValor() {
        return 5 * tela.getComboMultiplicador();
    }
}

class CardApocalipse extends Cards {

    public CardApocalipse(int id, Tela tela) {
        super(id, tela);
    }

    public void virarApocalipse() {
        int paresRestantes = tela.contarParesRestantes();
        int penalidade = paresRestantes * 5;
        int paresGeral = 9;
        int paresEncontrados = paresGeral - paresRestantes;
        int premio = paresEncontrados * 5;



        tela.setContarPontos(tela.getContarPontos() - penalidade + premio);
        tela.atualizarPlacar();

        Collections.shuffle(tela.getCartas());

        tela.getPainelCartas().removeAll();
        for (Cards carta : tela.getCartas()) {
            tela.getPainelCartas().add(carta.getBotao());
            if (!carta.getBotao().isEnabled()) {
                if (!carta.estaVirada()) {
                    carta.virar();
                }
            }
        }
        tela.getPainelCartas().revalidate();
        tela.getPainelCartas().repaint();

        for (Cards carta : tela.getCartas()) {
            if (carta.getBotao().isEnabled() && !carta.estaVirada()) {
                carta.virar();
            }
        }

        Timer esconder = new Timer(2000, e -> {
            for (Cards carta : tela.getCartas()) {
                if (carta.getBotao().isEnabled() && carta.estaVirada()) {
                    carta.virar();
                }
            }
            tela.setPodeClicar(true);
            tela.limpar();
        });

        esconder.setRepeats(false);
        esconder.start();
    }

    @Override
    public int getValor() {
        return 10;
    }
}

class CardObjeto extends Cards {

    public CardObjeto(int id, Tela tela) {
        super(id, tela);
    }

    @Override
    public int getValor() {
        return 1;
    }
}


