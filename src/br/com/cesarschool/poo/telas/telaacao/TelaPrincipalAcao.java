package br.com.cesarschool.poo.telas.telaacao;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaPrincipalAcao extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaPrincipalAcao(CardLayout cardLayout, JPanel painelPrincipal) throws IOException {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;

        JPanel telaIncluirAcao = new TelaIncluirAcao(cardLayout, painelPrincipal).criarTelaIncluirAcao();
        JPanel telaGerenciarAcao = new TelaGerenciarAcao(cardLayout, painelPrincipal).criarTelaGerenciarAcao();

        painelPrincipal.add(telaIncluirAcao, "Tela Incluir Acao");
        painelPrincipal.add(telaGerenciarAcao, "Tela Gerenciar Acao");
    }

    public JPanel criarTelaPrincipalAcao() {

        JPanel telaAcaoPanel = new JPanel();
        telaAcaoPanel.setLayout(null);
        telaAcaoPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));

        JLabel labelAcao = new JLabel("Acao");
        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        BotaoArredondado botaoIncluirAcao = new BotaoArredondado("Incluir", 20);
        BotaoArredondado botaoGerenciarAcao = new BotaoArredondado("Gerenciar", 20);

        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoIncluirAcao.setBounds(215, 149, 170, 40);
        botaoGerenciarAcao.setBounds(215, 232, 170, 40);
        labelAcao.setBounds(245, 82, 128, 24);
        labelAcao.setForeground(Color.WHITE);
        labelAcao.setFont(new Font("INTER", Font.PLAIN, 20));

        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        botaoIncluirAcao.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoIncluirAcao.setForeground(Color.WHITE);

        botaoGerenciarAcao.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoGerenciarAcao.setForeground(Color.WHITE);

        telaAcaoPanel.add(botaoIncluirAcao);
        telaAcaoPanel.add(botaoGerenciarAcao);
        telaAcaoPanel.add(labelAcao);
        telaAcaoPanel.add(botaoVoltar);

        // Ação do botão Incluir
        botaoIncluirAcao.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Incluir Acao"));
        botaoGerenciarAcao.addActionListener(e-> cardLayout.show(painelPrincipal, "Tela Gerenciar Acao"));
        // Retorna à tela inicial
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        return telaAcaoPanel;
    }
}
