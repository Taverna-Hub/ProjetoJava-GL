package br.com.cesarschool.poo.telas.telaentidadeoperadora;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipalEntidadeOperadora {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    // Construtor para receber o CardLayout e o painelPrincipal da TelaInicial
    public TelaPrincipalEntidadeOperadora(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel criarTelaEntidadeOperadora() {
        JPanel telaEntidadeOperadoraPanel = new JPanel();
        telaEntidadeOperadoraPanel.setLayout(new BoxLayout(telaEntidadeOperadoraPanel, BoxLayout.Y_AXIS));

        JLabel labelEntidadeOperadora = new JLabel("Tela Entidade Operadora", JLabel.CENTER);
        JButton botaoVoltar = new JButton("Voltar");

        telaEntidadeOperadoraPanel.add(labelEntidadeOperadora);
        telaEntidadeOperadoraPanel.add(botaoVoltar);

        // Retorna à tela inicial
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        return telaEntidadeOperadoraPanel;
    }
}
