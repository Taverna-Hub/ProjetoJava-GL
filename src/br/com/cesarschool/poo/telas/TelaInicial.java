package br.com.cesarschool.poo.telas;

import br.com.cesarschool.poo.telas.telaacao.TelaGerenciarAcao;
import br.com.cesarschool.poo.telas.telaacao.TelaIncluirAcao;
import br.com.cesarschool.poo.telas.telaacao.TelaPrincipalAcao;
import br.com.cesarschool.poo.telas.telatitulodividas.TelaPrincipalTituloDividas;
import br.com.cesarschool.poo.telas.telaoperacao.TelaPrincipalOperacoes;
import br.com.cesarschool.poo.telas.telaentidadeoperadora.TelaPrincipalEntidadeOperadora;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaInicial() {

        setTitle("Tela Inicial");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        JPanel telaInicialPanel = criarTelaInicial();

        // Telas Principais dos CRUDs
        JPanel telaAcaoPanel = new TelaPrincipalAcao(cardLayout, painelPrincipal).getTelaAcaoPanel();
        JPanel telaTituloDividaPanel = new TelaPrincipalTituloDividas(cardLayout, painelPrincipal).criarTelaTituloDivida();
        JPanel telaOperacoesPanel = new TelaPrincipalOperacoes(cardLayout, painelPrincipal).criarTelaOperacoes();
        JPanel telaEntidadeOperadoraPanel = new TelaPrincipalEntidadeOperadora(cardLayout, painelPrincipal).criarTelaEntidadeOperadora();

        // Telas dos CRUDs
        JPanel telaIncluirAcaoPanel = new TelaIncluirAcao(cardLayout, painelPrincipal).getIncluirAcao();
        JPanel telaGerenciarAcaoPanel = new TelaGerenciarAcao(cardLayout, painelPrincipal).getBuscarAcao();

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
        telaInicialPanel.setLayout(new BoxLayout(telaInicialPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Bem vindo!", JLabel.CENTER);

        JButton botaoAcao = new JButton("Acao");
        JButton botaoTituloDivida = new JButton("Titulo Divida");
        JButton botaoOperacao = new JButton("Operacao");
        JButton botaoEntidadeOperadora = new JButton("Entidade Operadora");
        JButton botaoEncerrar = new JButton("Encerrar");

        telaInicialPanel.add(label);
        telaInicialPanel.add(botaoAcao);
        telaInicialPanel.add(botaoTituloDivida);
        telaInicialPanel.add(botaoOperacao);
        telaInicialPanel.add(botaoEntidadeOperadora);
        telaInicialPanel.add(botaoEncerrar);

        // Navegar para as respectivas telas
        botaoAcao.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Ação"));
        botaoTituloDivida.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Titulo Divida"));
        botaoOperacao.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Operações"));
        botaoEntidadeOperadora.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Entidade Operadora"));

        // Encerrar a aplicação
        botaoEncerrar.addActionListener(e -> System.exit(0));

        return telaInicialPanel;
    }

    public static void main(String[] args) {
        TelaInicial tela = new TelaInicial();
        tela.setVisible(true);
    }
}
