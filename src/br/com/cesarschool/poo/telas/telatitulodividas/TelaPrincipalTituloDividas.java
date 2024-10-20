package br.com.cesarschool.poo.telas.telatitulodividas;

import br.com.cesarschool.poo.telas.TelaInicial;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipalTituloDividas {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaPrincipalTituloDividas(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel criarTelaTituloDivida() {
        JPanel telaTituloDividaPanel = new JPanel();
        telaTituloDividaPanel.setLayout(new BoxLayout(telaTituloDividaPanel, BoxLayout.Y_AXIS));

        JLabel labelTituloDivida = new JLabel("Tela Titulo Divida", JLabel.CENTER);
        JButton botaoVoltar = new JButton("Voltar");

        telaTituloDividaPanel.add(labelTituloDivida);
        telaTituloDividaPanel.add(botaoVoltar);

        // Retorna à tela inicial
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        return telaTituloDividaPanel;
    }
}
