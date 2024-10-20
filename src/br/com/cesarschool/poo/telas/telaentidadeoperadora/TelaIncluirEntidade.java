package br.com.cesarschool.poo.telas.telaentidadeoperadora;

import br.com.cesarschool.poo.telas.BotaoArredondado;

import javax.swing.*;
import java.awt.*;

public class TelaIncluirEntidade {
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    public TelaIncluirEntidade(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;

    }

    public JPanel criarTelaIncluirEntidade() {
        JPanel incluirEntidadePanel = new JPanel(null);
        incluirEntidadePanel.setBackground(Color.decode(BotaoArredondado.FUNDO));

        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);

        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        incluirEntidadePanel.add(botaoVoltar);
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));


        return incluirEntidadePanel;

    }


}
