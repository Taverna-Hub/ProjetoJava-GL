package br.com.cesarschool.poo.telas.telaacao;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TelaGerenciarAcao {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private final List<Acao> acoes = new ArrayList<>();

    public TelaGerenciarAcao(CardLayout cardLayout, JPanel painelPrincipal) throws FileNotFoundException {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public void carregarAcoes() throws IOException{
        acoes.clear();
        acoes.addAll(MediatorAcao.getInstancia().buscarTodos());
    }

    public void atualizarComboBoxAcoes(JComboBox<String> comboBox) throws FileNotFoundException {
        comboBox.removeAllItems();

        for (Acao acao : acoes) {
            comboBox.addItem(acao.getIdentificador() + " - " +  acao.getNome());
        }
    }

    public JPanel criarTelaGerenciarAcao() throws IOException{

        JPanel gerenciarAcaoPanel = new JPanel(null);
        gerenciarAcaoPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));

        BotaoArredondado botaoModificar = new BotaoArredondado("Modificar", 20);
        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        BotaoArredondado botaoExcluir = new BotaoArredondado("Excluir", 20);

        JComboBox<String> comboBoxAcao = new JComboBox<>();
        carregarAcoes();
        atualizarComboBoxAcoes(comboBoxAcao);

        comboBoxAcao.setBounds(215, 131, 187, 34);

        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        botaoModificar.setBounds(224, 191, 170, 40);
        botaoModificar.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoModificar.setForeground(Color.WHITE);

        botaoExcluir.setBounds(224, 254, 170, 40);
        botaoExcluir.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoExcluir.setForeground(Color.WHITE);

        gerenciarAcaoPanel.add(comboBoxAcao);
        gerenciarAcaoPanel.add(botaoVoltar);
        gerenciarAcaoPanel.add(botaoModificar);
        gerenciarAcaoPanel.add(botaoExcluir);

        botaoExcluir.addActionListener(e ->{

            String idString = (Objects.requireNonNull(comboBoxAcao.getSelectedItem()).toString());
            int id = Integer.parseInt(idString.split(" - ")[0]);

            try {
                String mensagem = MediatorAcao.getInstancia().excluir(id);
                if (mensagem != null) {
                    JOptionPane.showMessageDialog(null, mensagem, "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    carregarAcoes();
                    atualizarComboBoxAcoes(comboBoxAcao);
                    JOptionPane.showMessageDialog(null, mensagem, "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(painelPrincipal, "Tela Inicial");
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        botaoModificar.addActionListener(e -> {

            String idString = (Objects.requireNonNull(comboBoxAcao.getSelectedItem()).toString());
            int id = Integer.parseInt(idString.split(" - ")[0]);

            try {
                Acao acao = MediatorAcao.getInstancia().buscar(id);
                JPanel TelaModificarAcao = new TelaModificarAcao(cardLayout, painelPrincipal).criarTelaModificarAcao(acao);
                painelPrincipal.add(TelaModificarAcao, "Tela Modificar Acao");
                cardLayout.show(painelPrincipal, "Tela Modificar Acao");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));
        return gerenciarAcaoPanel;
    }
}
