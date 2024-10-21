package br.com.cesarschool.poo.telas.telaentidadeoperadora;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TelaGerenciarEntidade {
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private final List<EntidadeOperadora> entidadesOperadoras = new ArrayList<>(); // Lista de entidades para o JComboBox

    public TelaGerenciarEntidade(CardLayout cardLayout, JPanel painelPrincipal) throws FileNotFoundException {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public void carregarEntidadesOperadoras() throws FileNotFoundException {
        entidadesOperadoras.clear(); // Limpa a lista antes de carregar novamente
        entidadesOperadoras.addAll(MediatorEntidadeOperadora.getInstancia().buscarTodos());
    }

    public void atualizarComboBoxEntidades(JComboBox<String> comboBox) throws FileNotFoundException {
        comboBox.removeAllItems(); // Limpa os itens existentes

        for (EntidadeOperadora entidade : entidadesOperadoras) {
            comboBox.addItem(entidade.getIdentificador() + " - " + entidade.getNome());
        }
    }

    public JPanel criarTelaGerenciarEntidade() throws FileNotFoundException {
        JPanel gerenciarEntidadePanel = new JPanel(null);
        gerenciarEntidadePanel.setBackground(Color.decode(BotaoArredondado.FUNDO));
        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        BotaoArredondado botaoModificar = new BotaoArredondado("Modificar", 20);
        BotaoArredondado botaoExcluir = new BotaoArredondado("Excluir", 20);

        JComboBox<String> comboBoxEntidades = new JComboBox<>();
        carregarEntidadesOperadoras(); // Garantir que a lista esteja atualizada
        atualizarComboBoxEntidades(comboBoxEntidades);

        comboBoxEntidades.setBounds(215, 131, 187, 34);

        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        botaoModificar.setBounds(224, 191, 170, 40);
        botaoModificar.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoModificar.setForeground(Color.WHITE);

        botaoExcluir.setBounds(224, 254, 170, 40);
        botaoExcluir.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoExcluir.setForeground(Color.WHITE);

        gerenciarEntidadePanel.add(comboBoxEntidades);
        gerenciarEntidadePanel.add(botaoVoltar);
        gerenciarEntidadePanel.add(botaoModificar);
        gerenciarEntidadePanel.add(botaoExcluir);

        botaoExcluir.addActionListener(e -> {
            String idString = (Objects.requireNonNull(comboBoxEntidades.getSelectedItem()).toString());
            int id = Integer.parseInt(idString.split(" - ")[0]);

            try {
                String mensagem = MediatorEntidadeOperadora.getInstancia().excluir(id);
                if (mensagem != null) {
                    JOptionPane.showMessageDialog(null, mensagem, "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Atualiza o JComboBox
                    carregarEntidadesOperadoras(); // Recarregar a lista de entidades
                    atualizarComboBoxEntidades(comboBoxEntidades);
                    JOptionPane.showMessageDialog(null, "Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(painelPrincipal, "Tela Inicial");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        botaoModificar.addActionListener(e -> {
            String idString = (Objects.requireNonNull(comboBoxEntidades.getSelectedItem()).toString());
            int id = Integer.parseInt(idString.split(" - ")[0]);
            try {
                EntidadeOperadora entidadeOperadora = MediatorEntidadeOperadora.getInstancia().buscar(id);
                JPanel TelaModificarEntidade = new TelaModificarEntidade(cardLayout, painelPrincipal).criarTelaModificarEntidade(entidadeOperadora);
                painelPrincipal.add(TelaModificarEntidade, "Tela Modificar Entidade");
                cardLayout.show(painelPrincipal, "Tela Modificar Entidade");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        return gerenciarEntidadePanel;
    }
}
