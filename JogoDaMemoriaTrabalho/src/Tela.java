import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.List;

import java.io.*;

class TelaHome extends JFrame {

    public TelaHome() {
        setTitle("Jogo da Memória - Menu Inicial");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanelBackgroundHome();
        painel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.gridx = 0;


        JLabel titulo = new JLabel("Jogo da Memória - Equipe 8");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE); // Contraste com o fundo
        gbc.gridy = 0;
        painel.add(titulo, gbc);

        //Botão Jogar
        JButton btnJogar = new JButton("Jogar");
        btnJogar.setPreferredSize(new Dimension(200, 40));
        gbc.gridy = 1;
        painel.add(btnJogar, gbc);

        // Botão Ranking
        JButton btnRanking = new JButton("Ranking");
        btnRanking.setPreferredSize(new Dimension(200, 40));
        gbc.gridy = 2;
        painel.add(btnRanking, gbc);

        add(painel);

        btnJogar.addActionListener(e -> {
            new TelaSelecao().setVisible(true);
            dispose();
        });

        btnRanking.addActionListener(e -> {
            new TelaRanking().setVisible(true);
        });
    }

 //Background Home

    class JPanelBackgroundHome extends JPanel {
        private Image backgroundImage;

        public JPanelBackgroundHome() {
            try {
                backgroundImage = new ImageIcon(getClass().getResource("/backhome.png")).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}


// Tela de Ranking

 class TelaRanking extends JFrame {

    public TelaRanking() {
        setTitle("Ranking de Jogadores");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea areaRanking = new JTextArea();
        areaRanking.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaRanking);
        add(scroll);

        // Lê o arquivo ranking.txt e organiza o ranking
        List<String[]> entradas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("ranking.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    entradas.add(partes);
                }
            }
        } catch (IOException e) {
            areaRanking.setText("Erro ao carregar o ranking.");
            return;
        }

        // Ordena pela pontuação (descendente)
        entradas.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));


        StringBuilder texto = new StringBuilder("Ranking de Jogadores:\n\n");
        int posicao = 1;
        for (String[] entrada : entradas) {
            texto.append(posicao++)
                    .append(". ")
                    .append(entrada[0])
                    .append(" - ")
                    .append(entrada[1])
                    .append(" pts\n");
        }

        areaRanking.setText(texto.toString());
    }
}

// Tela para usuario e dificuldade

class TelaSelecao extends JFrame {

    private JTextField campoNome;
    private JRadioButton radioNormal, radioDificil;

    public TelaSelecao() {
        setTitle("Escolha de Dificuldade");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(new JLabel("Digite seu nome:"), gbc);

        campoNome = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(campoNome, gbc);

        // Dificuldade
        gbc.gridy = 2;
        painel.add(new JLabel("Escolha a dificuldade:"), gbc);

        radioNormal = new JRadioButton("Normal");
        radioDificil = new JRadioButton("Difícil");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioNormal);
        grupo.add(radioDificil);
        radioNormal.setSelected(true);

        JPanel painelRadios = new JPanel();
        painelRadios.add(radioNormal);
        painelRadios.add(radioDificil);

        gbc.gridy = 3;
        painel.add(painelRadios, gbc);

        // Botão Iniciar
        JButton btnIniciar = new JButton("Iniciar Jogo");
        gbc.gridy = 4;
        painel.add(btnIniciar, gbc);

        add(painel);

        btnIniciar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite seu nome!");
                return;
            }

            String dificuldade = radioNormal.isSelected() ? "normal" : "dificil";

            // Inicia o jogo com nome e dificuldade
            new Tela(nome, dificuldade); // modificada abaixo
            dispose();
        });
    }
}

// Tela do jogo com a logica

public class Tela extends JFrame {
    protected String nomeJogador;
    protected String dificuldade;
    private JPanel painelCartas = new JPanel();
    private JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel painelReset = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private List<Cards> cartas = new ArrayList<>();
    private Cards primeiroClique = null;
    private Cards segundoClique = null;
    private Timer timer;
    private boolean podeClicar = false;
    private int contarPontos = 0;
    private int combo = 1;
    private ImageIcon imagemBotaoReset = new ImageIcon(getClass().getResource("/Reset.png"));
    private int maiorPontuacao = 0;
    private final String ARQUIVO_PONTUACAO = "maior_pontuacao.txt";


    private JLabel campoTempo;
    private Timer cronometro;
    private int segundosRestantes = 60;

    private JLabel campoPlacar;

    public Tela(String nome, String dificuldade) {
        this.nomeJogador = nome;
        this.dificuldade = dificuldade;

        setTitle("Jogo da Memória");
        setSize(1920, 1080);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        campoPlacar = new JLabel("Jogador: " + nomeJogador + " | Pontuação: 0");
        campoPlacar.setFont(new Font("Arial", Font.BOLD, 14));
        painelTopo.add(campoPlacar);

        if ("dificil".equals(dificuldade)) {
            campoTempo = new JLabel("Tempo restante: 60s");
            campoTempo.setFont(new Font("Arial", Font.BOLD, 14));
            painelTopo.add(Box.createHorizontalStrut(20)); // Espaçamento
            painelTopo.add(campoTempo);
            iniciarCronometro();
        }

        add(painelTopo, BorderLayout.NORTH);

        JButton botaoResetar = new JButton(imagemBotaoReset);
        botaoResetar.addActionListener(e -> resetarJogo());
        botaoResetar.setContentAreaFilled(false);
        botaoResetar.setBorderPainted(false);
        botaoResetar.setFocusPainted(false);
        botaoResetar.setOpaque(false);

        painelReset.add(botaoResetar);
        add(painelReset, BorderLayout.SOUTH);

        painelCartas.setLayout(new GridLayout(3, 5, 0, 0));
        painelCartas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(painelCartas, BorderLayout.CENTER);

        criarCartas();

        setVisible(true);

        Timer delayInicial = new Timer(500, e -> mostrarCards());
        delayInicial.setRepeats(false);
        delayInicial.start();
    }

    private void criarCartas() {
        cartas.clear();
        painelCartas.removeAll();

        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ids.add(i);
            ids.add(i);
        }
        Collections.shuffle(ids);

        for (int id : ids) {
            Cards carta;
            if (id == 2) {
                carta = new CardApocalipse(id, this);
            } else if (id == 7 || id == 8) {
                carta = new CardObjeto(id, this);
            } else {
                carta = new CardLogica(id, this);
            }
            cartas.add(carta);
            painelCartas.add(carta.getBotao());
        }

        painelCartas.revalidate();
        painelCartas.repaint();
    }

    private void iniciarCronometro() {
        segundosRestantes = 60;
        cronometro = new Timer(1000, e -> {
            segundosRestantes--;
            campoTempo.setText("Tempo restante: " + segundosRestantes + "s");
            if (segundosRestantes <= 0) {
                cronometro.stop();
                encerrarPorTempo();
            }
        });
        cronometro.start();
    }

    private void mostrarCards() {
        for (Cards carta : cartas) {
            if (!carta.estaVirada()) carta.virar();
        }

        int tempoExibicao = dificuldade.equals("dificil") ? 3000 : 5000;
        Timer esconder = new Timer(tempoExibicao, e -> {
            for (Cards carta : cartas) {
                if (carta.estaVirada()) carta.virar();
            }
            podeClicar = true;
        });

        esconder.setRepeats(false);
        esconder.start();
    }

    public void cartaClicada(Cards carta) {
        if (!podeClicar || carta.estaVirada()) return;

        carta.virar();

        if (primeiroClique == null) {
            primeiroClique = carta;
        } else if (segundoClique == null && carta != primeiroClique) {
            segundoClique = carta;
            podeClicar = false;

            if (primeiroClique.getId() == segundoClique.getId()) {
                primeiroClique.desativar();
                segundoClique.desativar();

                if (primeiroClique instanceof CardApocalipse && segundoClique instanceof CardApocalipse) {
                    ((CardApocalipse) primeiroClique).virarApocalipse(); // efeito + liberar clique
                } else if (primeiroClique instanceof CardObjeto && segundoClique instanceof CardObjeto) {
                    contarPontos += primeiroClique.getValor();
                    combo = 1;
                    mostrarFrentesAleatorias(); // efeito + liberar clique
                } else if (primeiroClique instanceof CardLogica && segundoClique instanceof CardLogica) {
                    contarPontos += primeiroClique.getValor();
                    combo++;
                    limpar();
                    podeClicar = true;
                } else {
                    contarPontos += primeiroClique.getValor();
                    combo = 1;
                    limpar();
                    podeClicar = true;
                }

                campoPlacar.setText("Jogador: " + nomeJogador + " | Pontuação: " + contarPontos);
                verificarVitoria();

            } else {
                timer = new Timer(800, e -> {
                    primeiroClique.virar();
                    segundoClique.virar();
                    contarPontos -= 1;
                    campoPlacar.setText("Jogador: " + nomeJogador + " | Pontuação: " + contarPontos);
                    limpar();
                    podeClicar = true;
                    combo = 1;
                    timer.stop();
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    public void mostrarFrentesAleatorias() {
        List<Cards> candidatos = new ArrayList<>();

        for (Cards carta : cartas) {
            if (carta.getBotao().isEnabled() && !carta.estaVirada()) {
                candidatos.add(carta);
            }
        }

        if (candidatos.isEmpty()) {
            limpar();
            setPodeClicar(true);
            return;
        }

        Collections.shuffle(candidatos);
        Cards escolhida = candidatos.get(0);
        escolhida.virar();
        limpar();
        setPodeClicar(false);

        Timer timer = new Timer(1500, e -> {
            escolhida.virar();
            setPodeClicar(true);
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void limpar() {
        primeiroClique = null;
        segundoClique = null;
    }

    public List<Cards> getCartas() {
        return cartas;
    }

    public JPanel getPainelCartas() {
        return painelCartas;
    }

    public void setPodeClicar(boolean valor) {
        this.podeClicar = valor;
    }

    public int getComboMultiplicador() {
        return combo;
    }

    public int contarParesRestantes() {
        Map<Integer, Integer> cartasAtivasPorId = new HashMap<>();

        for (Cards carta : cartas) {
            if (carta.getBotao().isEnabled()) {
                cartasAtivasPorId.put(carta.getId(), cartasAtivasPorId.getOrDefault(carta.getId(), 0) + 1);
            }
        }

        return cartasAtivasPorId.size();
    }

    public int getContarPontos() {
        return contarPontos;
    }

    public void setContarPontos(int pontos) {
        this.contarPontos = pontos;
    }

    public void atualizarPlacar() {
        campoPlacar.setText("Jogador: " + nomeJogador + " | Pontuação: " + contarPontos);
    }

    public void resetarJogo() {
        if (cronometro != null) cronometro.stop();

        contarPontos = 0;
        combo = 1;
        campoPlacar.setText("Jogador: " + nomeJogador + " | Pontuação: 0");
        podeClicar = false;

        criarCartas();

        Timer delayInicial = new Timer(500, e -> mostrarCards());
        delayInicial.setRepeats(false);
        delayInicial.start();

        if ("dificil".equals(dificuldade)) {
            iniciarCronometro();
        }
    }

    public void salvarPontuacao() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ranking.txt", true))) {
            writer.write(nomeJogador + "," + contarPontos);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void encerrarPorTempo() {
        podeClicar = false;
        salvarPontuacao();

        int resposta = JOptionPane.showConfirmDialog(this,
                "Tempo esgotado!\nSua pontuação: " + contarPontos + "\nDeseja jogar novamente?",
                "Fim de Jogo", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            resetarJogo();
        } else {
            dispose();
            new TelaHome().setVisible(true);
        }
    }

    public void verificarVitoria() {
        if (contarParesRestantes() == 0) {
            if (cronometro != null) cronometro.stop();
            salvarPontuacao();
            JOptionPane.showMessageDialog(this,
                    "Parabéns, " + nomeJogador + "! Você venceu!\nPontuação: " + contarPontos,
                    "Vitória", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new TelaHome().setVisible(true);
        }
    }
}
