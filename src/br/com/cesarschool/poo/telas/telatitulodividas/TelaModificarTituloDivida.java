package br.com.cesarschool.poo.telas.telatitulodividas;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.TelaUtils.TextFieldComPlaceholder;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaModificarTituloDivida {
        private CardLayout cardLayout;
        private JPanel painelPrincipal;

        public TelaModificarTituloDivida(CardLayout cardLayout, JPanel painelPrincipal) {
            this.cardLayout = cardLayout;
            this.painelPrincipal = painelPrincipal;
        }

    public JPanel criarTelaModificarTituloDivida(TituloDivida tituloDivida) {
        JPanel modificarTituloPanel = new JPanel(null);

        modificarTituloPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));

        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        JLabel idField = new JLabel("Identificador: " + tituloDivida.getIdentificador());
        TextFieldComPlaceholder nomeField = new TextFieldComPlaceholder(tituloDivida.getNome());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        TextFieldComPlaceholder dataField = new TextFieldComPlaceholder(tituloDivida.getDataDeValidade().format(formatter));
        TextFieldComPlaceholder jurosField = new TextFieldComPlaceholder(String.valueOf(tituloDivida.getTaxaJuros()));
        BotaoArredondado botaoModificar = new BotaoArredondado("Modificar", 20);

        idField.setBounds(41, 83, 162, 24);
        idField.setForeground(Color.WHITE);
        idField.setFont(new Font("Inter", Font.PLAIN, 20));
        nomeField.setBounds(102, 124, 187, 34);

        dataField.setBounds(311, 124, 187, 34);
        jurosField.setBounds(206, 183, 187, 34);


        botaoModificar.setBounds(215,253 , 170, 40);
        botaoModificar.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoModificar.setForeground(Color.WHITE);
        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        modificarTituloPanel.add(botaoVoltar);
        modificarTituloPanel.add(idField);
        modificarTituloPanel.add(nomeField);
        modificarTituloPanel.add(dataField);
        modificarTituloPanel.add(jurosField);
        modificarTituloPanel.add(botaoModificar);

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));
        botaoModificar.addActionListener(e -> {

            String nome = nomeField.getText();
            String dataString = dataField.getText();
            String jurosString = jurosField.getText();


            LocalDate data = dataString.isEmpty() ? tituloDivida.getDataDeValidade() :  LocalDate.parse(dataString, formatter);
            double juros = jurosString.isEmpty() ? tituloDivida.getTaxaJuros() : Double.parseDouble(jurosString);


            if (nome.isBlank()){
                nome = tituloDivida.getNome();
            }
            if (nome.equals(tituloDivida.getNome()) && data == tituloDivida.getDataDeValidade()
            && juros == tituloDivida.getTaxaJuros()){
                cardLayout.show(painelPrincipal, "Tela Inicial");

            }
            else {
                TituloDivida td1 = new TituloDivida(tituloDivida.getIdentificador(), nome, data, juros);
                try {
                    String mensagem = MediatorTituloDivida.getInstancia().alterar(td1);
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
        return modificarTituloPanel;
    }
}
