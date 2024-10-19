package br.com.cesarschool.poo.telas;

import br.com.cesarschool.poo.titulos.mediators.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        JPanel telaAcaoPanel = new TelaAcao().getTelaAcaoPanel();

        painelPrincipal.add(telaInicialPanel, "Tela Inicial");
        painelPrincipal.add(telaAcaoPanel, "Tela Ação");

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

        botaoAcao.setBounds(120, 113,20, 20);

        telaInicialPanel.add(label);
        telaInicialPanel.add(botaoAcao);
        telaInicialPanel.add(botaoTituloDivida);
        telaInicialPanel.add(botaoOperacao);
        telaInicialPanel.add(botaoEntidadeOperadora);
        telaInicialPanel.add(botaoEncerrar);

        botaoAcao.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Ação"));

        botaoEncerrar.addActionListener(e -> System.exit(0));

        return telaInicialPanel;

    }

    public static void main(String[] args) {
        TelaInicial tela = new TelaInicial();
        tela.setVisible(true);
    }
}
