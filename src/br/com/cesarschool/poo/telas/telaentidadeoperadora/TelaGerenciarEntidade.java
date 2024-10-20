package br.com.cesarschool.poo.telas.telaentidadeoperadora;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;

import javax.swing.*;
import java.awt.*;

public class TelaGerenciarEntidade {
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    public TelaGerenciarEntidade(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;

    }

    public JPanel criarTelaGerenciarEntidade() {
        JPanel gerenciarEntidadePanel = new JPanel(null);
        gerenciarEntidadePanel.setBackground(Color.decode(BotaoArredondado.FUNDO));

        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);

        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        gerenciarEntidadePanel.add(botaoVoltar);
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));


        return gerenciarEntidadePanel;

    }


}
