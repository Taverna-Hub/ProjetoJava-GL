package br.com.cesarschool.poo.telas.telaoperacao;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.TelaUtils.CentralizedTable;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaExtratoTabela {

    private Transacao[] transacoes;
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private int id;

    public TelaExtratoTabela(CardLayout cardLayout, JPanel painelPrincipal, int id) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
        this.id = id;

    }

    private void carregarExtrato() throws IOException {
        this.transacoes = MediatorOperacao.getInstancia().gerarExtrato(id);
    }


    public JPanel criarTelaTabela() throws IOException {
        // Painel para abrigar a tabela
        JPanel painelTabela = new JPanel();
        painelTabela.setBackground(Color.decode(BotaoArredondado.FUNDO));
        painelTabela.setLayout(null); // Layout absoluto
        BotaoArredondado botaoVoltar = new BotaoArredondado("voltar", 20);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setBounds(20, 15, 87, 40);

        carregarExtrato();

        Object[][] dados = new Object[transacoes.length][5];
        for (int i = 0; i < transacoes.length; i++) {
            Transacao transacao = transacoes[i];
            dados[i][0] = (transacao.getEntidadeCredito().getIdentificador() != id) ?
                    transacao.getEntidadeCredito().getNome() : transacao.getEntidadeDebito().getNome();
            dados[i][1] = (transacao.getEntidadeCredito().getIdentificador() != id) ? "Credito" : "Debito";
            dados[i][2] = (transacao.getAcao() != null) ? transacao.getAcao().getNome() : transacao.getTituloDivida().getNome();
            dados[i][3] = String.valueOf(transacao.getValorOperacao());
            dados[i][4] = String.valueOf(transacao.getDataHoraOperacao());
        }

        String[] colunas = {"Entidade", "Tipo", "Ativo", "Valor", "Data/Hora"};

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        CentralizedTable tabela = new CentralizedTable(model);

        painelTabela.add(tabela);
        painelTabela.setPreferredSize(new Dimension(800, 600)); // Define o tamanho do painel
        tabela.centralizarTabela(painelTabela);

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(27, 84, 546, 347); // Ajustar o tamanho do JScrollPane
        painelTabela.add(scrollPane);
        painelTabela.add(botaoVoltar);

        return painelTabela;
    }




}
