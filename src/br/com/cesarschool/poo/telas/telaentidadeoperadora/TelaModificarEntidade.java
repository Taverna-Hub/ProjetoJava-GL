package br.com.cesarschool.poo.telas.telaentidadeoperadora;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.TelaUtils.StyledCheckBox;
import br.com.cesarschool.poo.telas.TelaUtils.TextFieldComPlaceholder;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        BotaoArredondado botaoModificar = new BotaoArredondado("Modificar", 20);



        idField.setBounds(41, 83, 162, 24);
        idField.setForeground(Color.WHITE);
        idField.setFont(new Font("Inter", Font.PLAIN, 20));
        nomeField.setBounds(81, 158, 187, 34);

        autorizadoAcao.setBounds(325, 161, 240, 22);
        autorizadoAcao.setForeground(Color.WHITE);
        autorizadoAcao.setSelected(entidadeOperadora.getAutorizadoAcao());
        botaoModificar.setBounds(215,253 , 170, 40);
        botaoModificar.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoModificar.setForeground(Color.WHITE);
        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        modificarEntidadePanel.add(botaoVoltar);
        modificarEntidadePanel.add(idField);
        modificarEntidadePanel.add(nomeField);
        modificarEntidadePanel.add(autorizadoAcao);
        modificarEntidadePanel.add(botaoModificar);

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));
        botaoModificar.addActionListener(e -> {

            String nome = nomeField.getText();
            if (nome.isBlank()){
                nome = entidadeOperadora.getNome();
            }
            boolean podeAcao = autorizadoAcao.isSelected();
            if (nome.equals(entidadeOperadora.getNome()) && podeAcao == entidadeOperadora.getAutorizadoAcao()){
                cardLayout.show(painelPrincipal, "Tela Inicial");

            }
            else {
                EntidadeOperadora e1 = new EntidadeOperadora(entidadeOperadora.getIdentificador(), nome, podeAcao);
                try {
                    String mensagem = MediatorEntidadeOperadora.getInstancia().alterar(e1);
                    if (mensagem == null){
                        mensagem = "Sucesso!";
                    }
                    JOptionPane.showMessageDialog(null, mensagem,  "Confirmacao", JOptionPane.INFORMATION_MESSAGE);
                    if (mensagem.equals("Sucesso!")){
                        cardLayout.show(painelPrincipal, "Tela Inicial");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return modificarEntidadePanel;
    }
}
