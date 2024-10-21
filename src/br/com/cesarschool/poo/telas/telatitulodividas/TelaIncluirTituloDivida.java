package br.com.cesarschool.poo.telas.telatitulodividas;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.TelaUtils.StyledCheckBox;
import br.com.cesarschool.poo.telas.TelaUtils.TextFieldComPlaceholder;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaIncluirTituloDivida {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaIncluirTituloDivida(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel criarTelaIncluirTituloDivida() {
        JPanel incluirTituloPanel = new JPanel(null);
        incluirTituloPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));

        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        TextFieldComPlaceholder idField = new TextFieldComPlaceholder("Identificador");
        TextFieldComPlaceholder nomeField = new TextFieldComPlaceholder("Nome");
        TextFieldComPlaceholder dataField = new TextFieldComPlaceholder("dd/MM/yyyy");
        TextFieldComPlaceholder jurosField = new TextFieldComPlaceholder("Taxa De Juros");
        BotaoArredondado botaoIncluir = new BotaoArredondado("Incluir", 20);
        JLabel DataValidadeLabel = new JLabel("Data de Validade");

        idField.setBounds(100, 120, 187, 34);
        nomeField.setBounds(100, 189, 187, 34);
        dataField.setBounds(313, 120, 187, 34);
        jurosField.setBounds(313, 189, 187, 34);

        botaoIncluir.setBounds(215,286 , 170, 40);
        botaoIncluir.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoIncluir.setForeground(Color.WHITE);
        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        botaoIncluir.addActionListener(e -> {

            int id = (!idField.getText().isEmpty()) ? Integer.parseInt(idField.getText()) : -1;
            String nome = nomeField.getText();
            String taxaJurosString = jurosField.getText();
            String dataValidadeString = dataField.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            double juros = taxaJurosString.isBlank() ? -1 : Double.parseDouble(taxaJurosString);


            LocalDate data = dataValidadeString.isBlank() ? LocalDate.now() : LocalDate.parse(dataValidadeString, formatter);

            TituloDivida tituloDivida = new TituloDivida(id, nome, data, juros);

            try {
                String mensagem = MediatorTituloDivida.getInstancia().incluir(tituloDivida);
                if (mensagem == null){
                    mensagem = "Sucesso!";
                }
                JOptionPane.showMessageDialog(null, mensagem, "Confirmacao", JOptionPane.INFORMATION_MESSAGE);
                if (mensagem.equals("Sucesso!")){
                    cardLayout.show(painelPrincipal, "Tela Inicial");

                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));



        incluirTituloPanel.add(botaoVoltar);
        incluirTituloPanel.add(idField);
        incluirTituloPanel.add(nomeField);
        incluirTituloPanel.add(jurosField);
        incluirTituloPanel.add(dataField);
        incluirTituloPanel.add(botaoIncluir);


        return incluirTituloPanel;
    }
}