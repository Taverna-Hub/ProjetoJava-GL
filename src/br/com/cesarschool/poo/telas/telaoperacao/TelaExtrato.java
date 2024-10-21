package br.com.cesarschool.poo.telas.telaoperacao;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaExtrato extends JPanel {

    private final MediatorOperacao mediatorOperacao = MediatorOperacao.getInstancia();

    private JComboBox<String> idExtratoComboBox;
    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaExtrato(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
        setLayout(new BorderLayout());
        add(extratoIdentificador(), BorderLayout.CENTER);
    }

    public JPanel extratoIdentificador() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode(BotaoArredondado.FUNDO));
        panel.setLayout(null);

        JLabel heading = new JLabel("Insira o Identificador da Entidade");
        heading.setBounds(266, 37, 68, 24);
        panel.add(heading);

        idExtratoComboBox = new JComboBox<>();
        idExtratoComboBox.setBounds(190, 134, 220, 38);
        idExtratoComboBox.setBorder(BorderFactory.createLineBorder(Color.decode(BotaoArredondado.LARANJA)));
        idExtratoComboBox.setBackground(Color.decode(BotaoArredondado.FUNDO));
        panel.add(idExtratoComboBox);

        BotaoArredondado btnBuscar = new BotaoArredondado("Buscar", 20);
        btnBuscar.setBounds(251, 200, 90, 29);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e ){
                String identificadorStr = idExtratoComboBox.getSelectedItem() != null ?
                        idExtratoComboBox.getSelectedItem().toString().split(" - ")[0] : "";

                if (identificadorStr.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Identificador inválido selecionado. Por favor, selecione um identificador válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                List<Transacao> extrato = null;
                try {
                    extrato = mediatorOperacao.buscarTodos();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                if (extrato.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Nenhum extrato encontrado para o identificador selecionado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                gerarExtrato(extrato);
            }
        });

        panel.add(btnBuscar);
        return panel;

    }

    public void gerarExtrato(List<Transacao> extrato) {
        removeAll();
        revalidate();
        repaint();

        String[] columnNames = {"Credor", "Debitor", "Modo", "Valor", "Data"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Transacao transacao : extrato) {
            Object[] row = {transacao.getEntidadeCredito(), transacao.getEntidadeDebito(),
                            transacao.getAcao() != null ? transacao.getAcao() : transacao.getTituloDivida(),
                            transacao.getValorOperacao(), transacao.getDataHoraOperacao()};
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        add(scrollPane, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e-> {
           cardLayout.show(painelPrincipal, "Tela Operacoes");
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnVoltar);
        add(btnPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}
