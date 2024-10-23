package br.com.cesarschool.poo.telas;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.telaacao.TelaGerenciarAcao;
import br.com.cesarschool.poo.telas.telaacao.TelaIncluirAcao;
import br.com.cesarschool.poo.telas.telaacao.TelaPrincipalAcao;
import br.com.cesarschool.poo.telas.telaoperacao.TelaExtrato;
import br.com.cesarschool.poo.telas.telaoperacao.TelaTransacao;
import br.com.cesarschool.poo.telas.telatitulodividas.TelaPrincipalTituloDividas;
import br.com.cesarschool.poo.telas.telaoperacao.TelaPrincipalOperacoes;
import br.com.cesarschool.poo.telas.telaentidadeoperadora.TelaPrincipalEntidadeOperadora;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TelaInicial extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaInicial() throws IOException {

        setTitle("Tela Inicial");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        JPanel telaInicialPanel = criarTelaInicial();

        // Telas Principais dos CRUDs
        JPanel telaAcaoPanel = new TelaPrincipalAcao(cardLayout, painelPrincipal).criarTelaPrincipalAcao();
        JPanel telaTituloDividaPanel = new TelaPrincipalTituloDividas(cardLayout, painelPrincipal).criarTelaTituloDivida();
        JPanel telaOperacoesPanel = new TelaPrincipalOperacoes(cardLayout, painelPrincipal).criarTelaOperacoes();
        JPanel telaEntidadeOperadoraPanel = new TelaPrincipalEntidadeOperadora(cardLayout, painelPrincipal).criarTelaEntidadeOperadora();

        // Telas dos CRUDs
        JPanel telaIncluirAcaoPanel = new TelaIncluirAcao(cardLayout, painelPrincipal).criarTelaIncluirAcao();
        JPanel telaGerenciarAcaoPanel = new TelaGerenciarAcao(cardLayout, painelPrincipal).criarTelaGerenciarAcao();

        painelPrincipal.add(telaInicialPanel, "Tela Inicial");
        painelPrincipal.add(telaAcaoPanel, "Tela Ação");
        painelPrincipal.add(telaTituloDividaPanel, "Tela Titulo Divida");
        painelPrincipal.add(telaOperacoesPanel, "Tela Operações");
        painelPrincipal.add(telaEntidadeOperadoraPanel, "Tela Entidade Operadora");

        painelPrincipal.add(telaIncluirAcaoPanel, "Incluir Acao");
        painelPrincipal.add(telaGerenciarAcaoPanel, "Gerenciar Acao");

        add(painelPrincipal);

        cardLayout.show(painelPrincipal, "Tela Inicial");
    }

    private JPanel criarTelaInicial() {

        JPanel telaInicialPanel = new JPanel();
        telaInicialPanel.setLayout(null);
        telaInicialPanel.setBackground(Color.decode("#17181D"));
        JLabel label = new JLabel("Bem vindo!", JLabel.CENTER);

        BotaoArredondado botaoAcao = new BotaoArredondado("Acao", 20);
        BotaoArredondado botaoTituloDivida = new BotaoArredondado("Titulo Divida", 20);
        BotaoArredondado botaoOperacao = new BotaoArredondado("Operacao", 20);
        BotaoArredondado botaoEntidadeOperadora = new BotaoArredondado("Entidade Operadora", 20);
        BotaoArredondado botaoEncerrar = new BotaoArredondado("Encerrar", 20);

        // posições
        botaoAcao.setBounds(118, 113, 170, 40);
        botaoTituloDivida.setBounds(320, 113, 170, 40);
        botaoOperacao.setBounds(118, 217, 170, 40);
        botaoEntidadeOperadora.setBounds(320, 217, 170, 40);
        botaoEncerrar.setBounds(481, 16, 99, 40);

        //estilos
        botaoEncerrar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoEncerrar.setForeground(Color.WHITE);

        botaoAcao.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoAcao.setForeground(Color.WHITE);

        botaoTituloDivida.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoTituloDivida.setForeground(Color.WHITE);

        botaoOperacao.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoOperacao.setForeground(Color.WHITE);

        botaoEntidadeOperadora.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoEntidadeOperadora.setForeground(Color.WHITE);

        telaInicialPanel.add(label);
        telaInicialPanel.add(botaoAcao);
        telaInicialPanel.add(botaoTituloDivida);
        telaInicialPanel.add(botaoOperacao);
        telaInicialPanel.add(botaoEntidadeOperadora);
        telaInicialPanel.add(botaoEncerrar);

        // Navegar para as respectivas telas

        botaoAcao.addActionListener(e -> {
            try {
                JPanel telaAcaoPanelAtualizada = new TelaPrincipalAcao(cardLayout, painelPrincipal).criarTelaPrincipalAcao();
                painelPrincipal.add(telaAcaoPanelAtualizada, "Tela Acao");
                cardLayout.show(painelPrincipal, "Tela Acao");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar as Acoes!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoTituloDivida.addActionListener(e -> {
            try {
                JPanel telaTituloDividaPanelAtualizada = new TelaPrincipalTituloDividas(cardLayout, painelPrincipal).criarTelaTituloDivida();
                painelPrincipal.add(telaTituloDividaPanelAtualizada, "Tela Titulo Divida");
                cardLayout.show(painelPrincipal, "Tela Titulo Divida");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar os Titulos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        });
        botaoOperacao.addActionListener(e -> {
            try {
                JPanel telaTransacaoPanelAtualizada = new TelaTransacao(cardLayout, painelPrincipal).criarTelaTransacao();
                JPanel telaExtratoAtualizada = new TelaExtrato(cardLayout, painelPrincipal).criarTelaExtrato();
                JPanel telaOperacaoAtualizada = new TelaPrincipalOperacoes(cardLayout, painelPrincipal).criarTelaOperacoes();
                painelPrincipal.add(telaOperacaoAtualizada, "Tela Operacoes");
                painelPrincipal.add(telaTransacaoPanelAtualizada, "Tela Transacao");
                painelPrincipal.add(telaExtratoAtualizada, "Tela Extrato");
                cardLayout.show(painelPrincipal, "Tela Operacoes");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar as Transacoes!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoEntidadeOperadora.addActionListener(e -> {
            try {
                JPanel telaEntidadeOperadoraPanelAtualizada = new TelaPrincipalEntidadeOperadora(cardLayout, painelPrincipal).criarTelaEntidadeOperadora();
                painelPrincipal.add(telaEntidadeOperadoraPanelAtualizada, "Tela Entidade Operadora");
                cardLayout.show(painelPrincipal, "Tela Entidade Operadora");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar as entidades!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Encerrar a aplicação
        botaoEncerrar.addActionListener(e -> System.exit(0));

        return telaInicialPanel;
    }

    public static void main(String[] args) throws IOException {
        TelaInicial tela = new TelaInicial();
        tela.setVisible(true);
    }
}
