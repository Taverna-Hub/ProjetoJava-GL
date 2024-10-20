package br.com.cesarschool.poo.telas.telaacao;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class TelaIncluirAcao extends JFrame {

    private MediatorAcao mediatorAcao;
    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaIncluirAcao(CardLayout cardLayout, JPanel painelPrincipal) {
        mediatorAcao = MediatorAcao.getInstancia();
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel getIncluirAcao() {

        JPanel painelAcao = new JPanel();
        painelAcao.setLayout(new BoxLayout(painelAcao, BoxLayout.Y_AXIS));

        JButton botaoConfirmar = new JButton("Confirmar");
        JButton botaoVoltar = new JButton("Voltar");

        // Campos para Entrada de Dados
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField(5);

        JLabel valorLabel = new JLabel("Valor:");
        JTextField valorField = new JTextField(5);

        JLabel identificadorLabel = new JLabel("Identificacao:");
        JTextField identificadorField = new JTextField(5);

        JLabel mensagemLabel = new JLabel("Mensagem:");
        mensagemLabel.setForeground(Color.RED);

        painelAcao.add(nomeLabel); painelAcao.add(nomeField);
        painelAcao.add(valorLabel); painelAcao.add(valorField);
        painelAcao.add(identificadorLabel); painelAcao.add(identificadorField);
        painelAcao.add(botaoConfirmar); painelAcao.add(botaoVoltar);
        painelAcao.add(mensagemLabel);

        botaoConfirmar.addActionListener(e -> {

            try {
                String nome = nomeField.getText();
                double valor = Double.parseDouble(valorField.getText());
                int identificador = Integer.parseInt(identificadorField.getText());
                LocalDate validade = LocalDate.now().plusDays(31);

                Acao novaAcao = new Acao(identificador, nome, validade, valor);
                String resultadoInclusao = mediatorAcao.incluir(novaAcao);

                if (resultadoInclusao == null) {
                    mensagemLabel.setForeground(Color.GREEN);
                    mensagemLabel.setText("Acao incluida com sucesso!");

                } else {
                    mensagemLabel.setForeground(Color.RED);
                    mensagemLabel.setText("Erro: " + resultadoInclusao);
                }
            } catch (IOException ex) {

                mensagemLabel.setForeground(Color.RED);
                mensagemLabel.setText("Erro ao incluir a acao.");

            } catch (NumberFormatException ex) {

                mensagemLabel.setForeground(Color.RED);
                mensagemLabel.setText("Erro, valores informados invalidos.");

            }

        });
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        return painelAcao;
    }

}
