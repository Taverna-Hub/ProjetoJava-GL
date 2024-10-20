package br.com.cesarschool.poo.telas.telaoperacao;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipalOperacoes {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    // Construtor para receber o CardLayout e o painelPrincipal da TelaInicial
    public TelaPrincipalOperacoes(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel criarTelaOperacoes() {
        JPanel telaOperacoesPanel = new JPanel();
        telaOperacoesPanel.setLayout(new BoxLayout(telaOperacoesPanel, BoxLayout.Y_AXIS));

        JLabel labelOperacoes = new JLabel("Tela Operações", JLabel.CENTER);
        JButton botaoVoltar = new JButton("Voltar");

        telaOperacoesPanel.add(labelOperacoes);
        telaOperacoesPanel.add(botaoVoltar);

        // Retorna à tela inicial
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        return telaOperacoesPanel;
    }
}
