package br.com.cesarschool.poo.telas.telaoperacao;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.TelaUtils.StyledCheckBox;
import br.com.cesarschool.poo.telas.TelaUtils.TextFieldComPlaceholder;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class TelaTransacao extends JPanel{
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private List<EntidadeOperadora> listaEntidade = new ArrayList<>();
    private List<Acao> listaAcao = new ArrayList<>();
    private List<TituloDivida> listaTitulo = new ArrayList<>();

    public TelaTransacao(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;

    }

    public void carregar() throws IOException {
        listaEntidade.clear();
        listaAcao.clear();
        listaTitulo.clear();
        listaTitulo.addAll(MediatorTituloDivida.getInstancia().buscarTodos());
        listaAcao.addAll(MediatorAcao.getInstancia().buscarTodos());
        listaEntidade.addAll(MediatorEntidadeOperadora.getInstancia().buscarTodos());

    }
    String toString(EntidadeOperadora e1){
        return (e1.getIdentificador() + " - " + e1.getNome() + " : " + e1.getSaldoAcao());
    }
    String toString(TituloDivida td1){
        return (td1.getIdentificador() + " - " + td1.getNome() + " : " + td1.getTaxaJuros());
    }
    String toString(Acao a1){
        return (a1.getIdentificador() + " - " + a1.getNome() + " : " + a1.getValorUnitario());
    }


    public void atualizarCombosBoxEntidades(JComboBox<String> comboBox1, JComboBox<String> comboBox2, JComboBox<String> comboBox3, JComboBox<String> comboBox4) throws FileNotFoundException {
        comboBox1.removeAllItems();
        comboBox2.removeAllItems();
        comboBox3.removeAllItems();
        comboBox4.removeAllItems();

        for (EntidadeOperadora entidade : listaEntidade) {
            comboBox1.addItem(toString(entidade));
            comboBox2.addItem(toString(entidade));
        }
        for (Acao acao : listaAcao) {
            comboBox3.addItem(toString(acao));
        }
        for (TituloDivida titulo : listaTitulo) {
            comboBox4.addItem(toString(titulo));
        }

    }

    public JPanel criarTelaTransacao() throws IOException {
        JPanel transacaoPanel = new JPanel(null);
        transacaoPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));
        AtomicBoolean action = new AtomicBoolean(true);
        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        JLabel creditoLabel = new JLabel("entidade Credido");
        JLabel debitoLabel = new JLabel("entidade Credido");
        TextFieldComPlaceholder valorField = new TextFieldComPlaceholder("Valor");
        StyledCheckBox acaoCheck = new StyledCheckBox("acao", Color.decode(BotaoArredondado.MARROM),  Color.WHITE);
        StyledCheckBox tituloCheck = new StyledCheckBox("Titulo Divida", Color.decode(BotaoArredondado.MARROM), Color.WHITE);

        acaoCheck.setBounds(76, 181, 156, 22);
        tituloCheck.setBounds(76, 205, 156, 22);
        acaoCheck.setSelected(true);
        JComboBox<String> comboDebito = new JComboBox<>();
        JComboBox<String> comboCredito = new JComboBox<>();
        JComboBox<String> comboAcao = new JComboBox<>();
        JComboBox<String> comboTitulo = new JComboBox<>();
        carregar();
        atualizarCombosBoxEntidades(comboDebito, comboCredito, comboAcao, comboTitulo);
        BotaoArredondado botaoInserir = new BotaoArredondado("transferir", 20);

        creditoLabel.setBounds(76, 72, 166, 24);
        debitoLabel.setBounds(317, 72, 166, 24);
        creditoLabel.setForeground(Color.WHITE);
        debitoLabel.setForeground(Color.WHITE);

        comboDebito.setBounds(76, 103, 187, 34);
        comboCredito.setBounds(313, 103, 187, 34);
        valorField.setBounds(206, 250, 187, 34);
        botaoInserir.setBounds(215, 300, 170, 40);

        comboAcao.setBounds(313, 175, 187, 34);
        comboTitulo.setBounds(313, 175, 187, 34);
        comboTitulo.setVisible(false);

        botaoInserir.setBackground(Color.decode(BotaoArredondado.MARROM));

        acaoCheck.setForeground(Color.WHITE);
        tituloCheck.setForeground(Color.WHITE);

        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        acaoCheck.addItemListener(e -> {
            if (acaoCheck.isSelected()) {
                tituloCheck.setSelected(false); // Desmarca o outro checkbox
                comboAcao.setVisible(true);     // Mostra o campo de ação
                comboTitulo.setVisible(false);
                action.set(true);   // Esconde o campo de ação// Esconde o campo de título de dívida
            } else {
                comboAcao.setVisible(false); // Esconde o campo se desmarcado
            }
        });

        tituloCheck.addItemListener(e -> {
            if (tituloCheck.isSelected()) {
                acaoCheck.setSelected(false); // Desmarca o outro checkbox
                comboTitulo.setVisible(true);  // Mostra o campo de título de dívida
                comboAcao.setVisible(false);
                action.set(false);  // Esconde o campo de ação
            } else {
                comboTitulo.setVisible(false); // Esconde o campo se desmarcado
            }
        });

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        botaoInserir.addActionListener(e -> {
            String idCreditoString = Objects.requireNonNull(comboCredito.getSelectedItem()).toString();
            int idCredito = Integer.parseInt(idCreditoString.split(" - ")[0]);
            String idDebitoString = Objects.requireNonNull(comboDebito.getSelectedItem()).toString();
            int idDebito = Integer.parseInt(idDebitoString.split(" - ")[0]);
            String idAtivoString = action.get() ? Objects.requireNonNull(comboAcao.getSelectedItem()).toString() : Objects.requireNonNull(comboTitulo.getSelectedItem()).toString();
            int idAtivo = Integer.parseInt(idAtivoString.split(" - ")[0]);
            double valor = Double.parseDouble(valorField.getText());
            try {
                String message = MediatorOperacao.getInstancia().realizarOperacao(action.get(), idCredito, idDebito, idAtivo, valor);
                if (message == null){
                   message = "Sucesso!";
                }
                    JOptionPane.showMessageDialog(null, message,  "Confirmacao", JOptionPane.INFORMATION_MESSAGE);
                if (message.equals("Sucesso!")){
                    cardLayout.show(painelPrincipal, "Tela Inicial");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        transacaoPanel.add(botaoVoltar);
        transacaoPanel.add(comboCredito);
        transacaoPanel.add(comboDebito);
        transacaoPanel.add(valorField);
        transacaoPanel.add(comboAcao);
        transacaoPanel.add(acaoCheck);
        transacaoPanel.add(comboTitulo);
        transacaoPanel.add(tituloCheck);
        transacaoPanel.add(botaoInserir);
        transacaoPanel.add(debitoLabel);
        transacaoPanel.add(creditoLabel);

        return transacaoPanel;
    }
}
