package br.com.cesarschool.poo.telas.telaoperacao;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.TelaUtils.StyledCheckBox;
import br.com.cesarschool.poo.telas.TelaUtils.TextFieldComPlaceholder;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaExtrato extends JPanel {

    private  List<EntidadeOperadora> entidades = new ArrayList<>();
    private JComboBox<String> idExtratoComboBox = new JComboBox<>();
    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaExtrato(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;

    }

    private void carregarLista() throws FileNotFoundException {
        entidades = (MediatorEntidadeOperadora.getInstancia().buscarTodos());
    }

    private void carregarCombo(){
        idExtratoComboBox.removeAllItems();
        for (EntidadeOperadora entidade : entidades){
            idExtratoComboBox.addItem(entidade.getIdentificador() + " - " + entidade.getNome());
        }

    }

    public JPanel criarTelaExtrato() throws IOException {
        JPanel extratoPanel = new JPanel(null);
        extratoPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));
        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);


        carregarLista();
        carregarCombo();
        BotaoArredondado botaoBuscar = new BotaoArredondado("Buscar", 20);
        idExtratoComboBox.setBounds(207, 166, 187, 34);
        botaoBuscar.setBounds(215, 230, 170, 40);

        botaoBuscar.setBackground(Color.decode(BotaoArredondado.MARROM));

        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));


        botaoBuscar.addActionListener(e-> {
           String idString = String.valueOf(idExtratoComboBox.getSelectedItem()).split(" - ")[0];
           int id = Integer.parseInt(idString);
            try {
                if (MediatorOperacao.getInstancia().gerarExtrato(id) == null){
                    JOptionPane.showMessageDialog(null, "Erro: A entidade não tem transações", "Confirmacao", JOptionPane.INFORMATION_MESSAGE);

                }
                else {
                    TelaExtratoTabela telaTabela = new TelaExtratoTabela(cardLayout, painelPrincipal, id);
                    JPanel painelTabela = telaTabela.criarTelaTabela();

                    painelPrincipal.add(painelTabela, "TelaTabelaExtrato");
                    cardLayout.show(painelPrincipal, "TelaTabelaExtrato");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        extratoPanel.add(botaoVoltar);
        extratoPanel.add(botaoBuscar);
        extratoPanel.add(idExtratoComboBox);

        return extratoPanel;
    }


}
