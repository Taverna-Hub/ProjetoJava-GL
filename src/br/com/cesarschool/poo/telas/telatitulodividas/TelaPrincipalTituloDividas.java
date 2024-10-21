package br.com.cesarschool.poo.telas.telatitulodividas;

import br.com.cesarschool.poo.telas.TelaInicial;
import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;


import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TelaPrincipalTituloDividas {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaPrincipalTituloDividas(CardLayout cardLayout, JPanel painelPrincipal) throws IOException {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;

        // Adiciona as telas ao painel principal
        JPanel telaIncluirTituloDivida = new TelaIncluirTituloDivida(cardLayout, painelPrincipal).criarTelaIncluirTituloDivida();
        JPanel telaGerenciarTituloDivida = new TelaGerenciarTituloDivida(cardLayout, painelPrincipal).criarTelaGerenciarTitulo();

        painelPrincipal.add(telaIncluirTituloDivida, "Tela Incluir Titulo");
        painelPrincipal.add(telaGerenciarTituloDivida, "Tela Gerenciar Titulo"); // Corrigido aqui

    }

    public JPanel criarTelaTituloDivida() {
        JPanel telaTituloDividaPanel = new JPanel();
        telaTituloDividaPanel.setLayout(null);
        telaTituloDividaPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));

        JLabel labelTituloDivida = new JLabel("Titulo Divida");
        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        BotaoArredondado botaoIncluirTituloDivida = new BotaoArredondado("Incluir", 20);
        BotaoArredondado botaoGerenciarTitulo = new BotaoArredondado("Gerenciar", 20);

        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoIncluirTituloDivida.setBounds(215, 149, 170, 40);
        botaoGerenciarTitulo.setBounds(215, 232, 170, 40);
        labelTituloDivida.setBounds(245, 82, 128, 24);
        labelTituloDivida.setForeground(Color.WHITE);
        labelTituloDivida.setFont(new Font("INTER", Font.PLAIN, 20));

        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        botaoIncluirTituloDivida.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoIncluirTituloDivida.setForeground(Color.WHITE);

        botaoGerenciarTitulo.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoGerenciarTitulo.setForeground(Color.WHITE);

        telaTituloDividaPanel.add(botaoIncluirTituloDivida);
        telaTituloDividaPanel.add(botaoGerenciarTitulo);
        telaTituloDividaPanel.add(labelTituloDivida);
        telaTituloDividaPanel.add(botaoVoltar);

        // Ação do botão Incluir
        botaoIncluirTituloDivida.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Incluir Titulo"));
        botaoGerenciarTitulo.addActionListener(e-> cardLayout.show(painelPrincipal, "Tela Gerenciar Titulo"));
        // Retorna à tela inicial
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        return telaTituloDividaPanel;
    }
}
