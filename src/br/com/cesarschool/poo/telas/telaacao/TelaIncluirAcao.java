package br.com.cesarschool.poo.telas.telaacao;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;
import br.com.cesarschool.poo.telas.TelaUtils.TextFieldComPlaceholder;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaIncluirAcao extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaIncluirAcao(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel criarTelaIncluirAcao() {

        JPanel incluirAcao = new JPanel(null);
        incluirAcao.setBackground(Color.decode(BotaoArredondado.FUNDO));

        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        BotaoArredondado botaoIncluir = new BotaoArredondado("Incluir", 20);

        // Campos para Entrada de Dados
        TextFieldComPlaceholder nomeField = new TextFieldComPlaceholder("Nome");
        TextFieldComPlaceholder valorField = new TextFieldComPlaceholder("Valor");
        TextFieldComPlaceholder identificacaoField = new TextFieldComPlaceholder("Identificacao");
        TextFieldComPlaceholder dataField = new TextFieldComPlaceholder("dd/MM/yyyy");

        JLabel dataValidadeLabel = new JLabel("Data de Validade");

        identificacaoField.setBounds(100, 120, 187, 34);
        nomeField.setBounds(100, 189, 187, 34);
        valorField.setBounds(313, 120, 187, 34);
        dataField.setBounds(313, 189, 187, 34);

        botaoIncluir.setBounds(215,286 , 170, 40);
        botaoIncluir.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoIncluir.setForeground(Color.WHITE);
        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        botaoIncluir.addActionListener(e ->{

            int id = Integer.parseInt(identificacaoField.getText());
            String nome = nomeField.getText();
            String valorString = valorField.getText();
            String dataValidade = dataField.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            double valor = valorString.isBlank() ? -1 : Double.parseDouble(valorString);

            LocalDate data = dataValidade.isBlank() ? LocalDate.now().plusDays(31) : LocalDate.parse(dataValidade, formatter);

            Acao novaAcao = new Acao(id, nome, data, valor);

            try {
                String mensagem = MediatorAcao.getInstancia().incluir(novaAcao);

                if (mensagem == null) {
                    mensagem = "Sucesso!";
                }
                JOptionPane.showMessageDialog(null, mensagem, "Confirmacao", JOptionPane.INFORMATION_MESSAGE);
                if (mensagem.equals("Sucesso!")){
                    cardLayout.show(painelPrincipal, "Tela Inicial");
                }

            } catch(IOException ex){
                throw new RuntimeException(ex);
            }

        });

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        incluirAcao.add(nomeField);
        incluirAcao.add(valorField);
        incluirAcao.add(identificacaoField);
        incluirAcao.add(dataField);
        incluirAcao.add(botaoVoltar);
        incluirAcao.add(botaoIncluir);

        return incluirAcao;
    }

}
