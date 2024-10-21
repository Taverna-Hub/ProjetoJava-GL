package br.com.cesarschool.poo.telas.telaacao;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.TelaUtils.TextFieldComPlaceholder;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TelaModificarAcao {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaModificarAcao(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel criarTelaModificarAcao(Acao acao) {

        JPanel modificarAcaoPanel = new JPanel(null);

        modificarAcaoPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));
        JLabel idField = new JLabel("Identificador: " + acao.getIdentificador());
        TextFieldComPlaceholder nomeField = new TextFieldComPlaceholder(acao.getNome());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        TextFieldComPlaceholder dataField = new TextFieldComPlaceholder(acao.getDataDeValidade().format(formatter));
        TextFieldComPlaceholder valorField = new TextFieldComPlaceholder(String.valueOf(acao.getValorUnitario()));
        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        BotaoArredondado botaoModificar = new BotaoArredondado("Modificar", 20);

        idField.setBounds(41, 83, 162, 24);
        idField.setForeground(Color.WHITE);
        idField.setFont(new Font("Inter", Font.PLAIN, 20));
        nomeField.setBounds(102, 124, 187, 34);

        dataField.setBounds(311, 124, 187, 34);
        valorField.setBounds(206, 183, 187, 34);


        botaoModificar.setBounds(215,253 , 170, 40);
        botaoModificar.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoModificar.setForeground(Color.WHITE);
        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        modificarAcaoPanel.add(botaoVoltar);
        modificarAcaoPanel.add(idField);
        modificarAcaoPanel.add(nomeField);
        modificarAcaoPanel.add(dataField);
        modificarAcaoPanel.add(valorField);
        modificarAcaoPanel.add(botaoModificar);

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));
        botaoModificar.addActionListener(e -> {

            String nome = nomeField.getText();
            String dataString = dataField.getText();
            String valorString = valorField.getText();


            LocalDate data = dataString.isEmpty() ? acao.getDataDeValidade() :  LocalDate.parse(dataString, formatter);
            double valor = valorString.isEmpty() ? acao.getValorUnitario() : Double.parseDouble(valorString);


            if (nome.isBlank()){
                nome = acao.getNome();
            }
            if (nome.equals(acao.getNome()) && data == acao.getDataDeValidade()
                    && valor == acao.getValorUnitario()){
                cardLayout.show(painelPrincipal, "Tela Inicial");

            }
            else {
                Acao td1 = new Acao(acao.getIdentificador(), nome, data, valor);
                try {
                    String mensagem = MediatorAcao.getInstancia().alterar(td1);
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

        return modificarAcaoPanel;

    }

}
