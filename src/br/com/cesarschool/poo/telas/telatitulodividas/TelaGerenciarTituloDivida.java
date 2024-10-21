package br.com.cesarschool.poo.telas.telatitulodividas;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TelaGerenciarTituloDivida {
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private final List<TituloDivida> titulosDivida = new ArrayList<>(); // Lista de entidades para o JComboBox

    public TelaGerenciarTituloDivida(CardLayout cardLayout, JPanel painelPrincipal) throws FileNotFoundException {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public void carregarTitulosDivida() throws IOException {
        int cont = 0;
        titulosDivida.clear(); // Limpa a lista antes de carregar novamente
        for (int i = 1; i <= 99999; i++) {
            TituloDivida titulos = MediatorTituloDivida.getInstancia().buscar(i);
            if (titulos != null) {
                cont = 0;
                this.titulosDivida.add(titulos);
            } else {
                cont++;
                if (cont == 1000) {
                    break;
                }
            }
        }
    }

    public void atualizarComboBoxTitulos(JComboBox<String> comboBox) throws FileNotFoundException {
        comboBox.removeAllItems(); // Limpa os itens existentes

        for (TituloDivida titulos : titulosDivida) {
            comboBox.addItem(titulos.getIdentificador() + " - " + titulos.getNome());
        }
    }

    public JPanel criarTelaGerenciarTitulo() throws IOException {
        JPanel gerenciarTitulosPanel = new JPanel(null);
        gerenciarTitulosPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));
        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        BotaoArredondado botaoModificar = new BotaoArredondado("Modificar", 20);
        BotaoArredondado botaoExcluir = new BotaoArredondado("Excluir", 20);

        JComboBox<String> comboBoxTitulos = new JComboBox<>();
        carregarTitulosDivida(); // Garantir que a lista esteja atualizada
        atualizarComboBoxTitulos(comboBoxTitulos);

        comboBoxTitulos.setBounds(215, 131, 187, 34);

        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        botaoModificar.setBounds(224, 191, 170, 40);
        botaoModificar.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoModificar.setForeground(Color.WHITE);

        botaoExcluir.setBounds(224, 254, 170, 40);
        botaoExcluir.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoExcluir.setForeground(Color.WHITE);

        gerenciarTitulosPanel.add(comboBoxTitulos);
        gerenciarTitulosPanel.add(botaoVoltar);
        gerenciarTitulosPanel.add(botaoModificar);
        gerenciarTitulosPanel.add(botaoExcluir);

        botaoExcluir.addActionListener(e -> {
            String idString = (Objects.requireNonNull(comboBoxTitulos.getSelectedItem()).toString());
            int id = Integer.parseInt(idString.split(" - ")[0]);

            try {
                String mensagem = MediatorTituloDivida.getInstancia().excluir(id);
                if (mensagem != null) {
                    JOptionPane.showMessageDialog(null, mensagem, "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Atualiza o JComboBox
                    carregarTitulosDivida(); // Recarregar a lista de entidades
                    atualizarComboBoxTitulos(comboBoxTitulos);
                    JOptionPane.showMessageDialog(null, "Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(painelPrincipal, "Tela Inicial");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        botaoModificar.addActionListener(e -> {
            String idString = (Objects.requireNonNull(comboBoxTitulos.getSelectedItem()).toString());
            int id = Integer.parseInt(idString.split(" - ")[0]);
            try {
                TituloDivida tituloDivida = MediatorTituloDivida.getInstancia().buscar(id);
                JPanel TelaModificarTituloDivida = new TelaModificarTituloDivida(cardLayout, painelPrincipal).criarTelaModificarTituloDivida(tituloDivida);
                painelPrincipal.add(TelaModificarTituloDivida, "Tela Modificar Entidade");
                cardLayout.show(painelPrincipal, "Tela Modificar Entidade");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        return gerenciarTitulosPanel;
    }


}
