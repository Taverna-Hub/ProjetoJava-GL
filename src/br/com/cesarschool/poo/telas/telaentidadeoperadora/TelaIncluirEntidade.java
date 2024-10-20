package br.com.cesarschool.poo.telas.telaentidadeoperadora;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.TelaUtils.StyledCheckBox;
import br.com.cesarschool.poo.telas.TelaUtils.TextFieldComPlaceholder;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        TextFieldComPlaceholder idField = new TextFieldComPlaceholder("Identificador");
        TextFieldComPlaceholder nomeField = new TextFieldComPlaceholder("Nome");
        StyledCheckBox autorizadoAcao = new StyledCheckBox("Autorizar transacoes com acao", Color.decode(BotaoArredondado.MARROM), Color.WHITE);
        BotaoArredondado botaoIncluir = new BotaoArredondado("Incluir", 20);

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

        botaoIncluir.addActionListener(e -> {

            int id =(!idField.getText().isEmpty()) ? Integer.parseInt(idField.getText()) : -1;
            String nome = nomeField.getText();
            boolean autirizado = autorizadoAcao.isSelected();

            EntidadeOperadora entidade = new EntidadeOperadora(id, nome, autirizado);

            try {
                String mensagem = MediatorEntidadeOperadora.getInstancia().incluir(entidade);
                if (mensagem == null){
                    mensagem = "Sucesso!";
                }
                JOptionPane.showMessageDialog(null, mensagem, "Confirmacao", JOptionPane.INFORMATION_MESSAGE);
                if (mensagem.equals("Sucesso!")){
                    cardLayout.show(painelPrincipal, "Tela Inicial");

                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));



        incluirEntidadePanel.add(botaoVoltar);
        incluirEntidadePanel.add(idField);
        incluirEntidadePanel.add(nomeField);
        incluirEntidadePanel.add(autorizadoAcao);
        incluirEntidadePanel.add(botaoIncluir);


        return incluirEntidadePanel;

    }


}