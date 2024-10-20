package br.com.cesarschool.poo.telas.telaentidadeoperadora;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.TelaUtils.StyledCheckBox;
import br.com.cesarschool.poo.telas.TelaUtils.TextFieldComPlaceholder;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

import javax.swing.*;
import java.awt.*;

public class TelaModificarEntidade {
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    public TelaModificarEntidade(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel criarTelaModificarEntidade(EntidadeOperadora entidadeOperadora) {

        JPanel modificarEntidadePanel = new JPanel(null);

        modificarEntidadePanel.setBackground(Color.decode(BotaoArredondado.FUNDO));

        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        JLabel idField = new JLabel("Identificador: " + String.valueOf(entidadeOperadora.getIdentificador()));
        TextFieldComPlaceholder nomeField = new TextFieldComPlaceholder(entidadeOperadora.getNome());
        StyledCheckBox autorizadoAcao = new StyledCheckBox("Autorizar transacoes com acao", Color.decode(BotaoArredondado.MARROM), Color.WHITE);
        BotaoArredondado botaoIncluir = new BotaoArredondado("Modificar", 20);

        idField.setBounds(100, 120, 187, 34);
        nomeField.setBounds(313, 120, 187, 34);
        autorizadoAcao.setBounds(215, 209, 240, 22);
        autorizadoAcao.setForeground(Color.WHITE);

        botaoIncluir.setBounds(215,286 , 170, 40);
        botaoIncluir.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoIncluir.setForeground(Color.WHITE);
        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        modificarEntidadePanel.add(botaoVoltar);
        modificarEntidadePanel.add(idField);
        modificarEntidadePanel.add(nomeField);
        modificarEntidadePanel.add(autorizadoAcao);

        return modificarEntidadePanel;
    }
}
