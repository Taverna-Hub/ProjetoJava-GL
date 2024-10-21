package br.com.cesarschool.poo.telas.telaoperacao;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TelaPrincipalOperacoes {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    // Construtor para receber o CardLayout e o painelPrincipal da TelaInicial
    public TelaPrincipalOperacoes(CardLayout cardLayout, JPanel painelPrincipal) throws IOException {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;

        JPanel telaTransacao = new TelaTransacao(cardLayout, painelPrincipal).criarTelaTransacao();
        painelPrincipal.add(telaTransacao, "Tela Transacao");
    }

    public JPanel criarTelaOperacoes() {
        JPanel telaOperacoesPanel = new JPanel();
        telaOperacoesPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));
        telaOperacoesPanel.setLayout(null);

        JLabel labelOperacoes = new JLabel("Tela Operacoes", JLabel.CENTER);
        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        BotaoArredondado botaoTransacao = new BotaoArredondado("Realizar Transacao", 20);
        BotaoArredondado botaoExtrato = new BotaoArredondado("Ver extrato", 20);


        botaoVoltar.setBounds(18, 15, 87, 40);
        botaoTransacao.setBounds(215, 150, 170, 40);
        botaoExtrato.setBounds(215, 230, 170, 40);
        labelOperacoes.setBounds(130, 90, 341,24);

        botaoExtrato.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoTransacao.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        labelOperacoes.setForeground(Color.WHITE);

        telaOperacoesPanel.add(labelOperacoes);
        telaOperacoesPanel.add(botaoVoltar);
        telaOperacoesPanel.add(botaoExtrato);
        telaOperacoesPanel.add(botaoTransacao);
        telaOperacoesPanel.add(labelOperacoes);


        botaoExtrato.addActionListener(e-> {
            JPanel telaExtratoPanel = new TelaExtrato(cardLayout, painelPrincipal);
            painelPrincipal.add(telaExtratoPanel, "Tela Extrato");
            cardLayout.show(painelPrincipal, "Tela Extrato");
        });
        botaoTransacao.addActionListener(e-> cardLayout.show(painelPrincipal, "Tela Transacao"));

        // Retorna à tela inicial
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        return telaOperacoesPanel;
    }
}
