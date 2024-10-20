package br.com.cesarschool.poo.telas.telaacao;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaGerenciarAcao extends JFrame {

    private MediatorAcao mediatorAcao;
    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaGerenciarAcao(CardLayout cardLayout, JPanel painelPrincipal) {
        mediatorAcao = MediatorAcao.getInstancia();
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel getBuscarAcao() {

        JPanel painelAcao = new JPanel();
        painelAcao.setLayout(new BoxLayout(painelAcao, BoxLayout.Y_AXIS));

        JButton botaoConfirmar = new JButton("Confirmar");
        JButton botaoVoltar = new JButton("Voltar");

        JLabel identificadorLabel = new JLabel("Identificador:");
        JTextField identificadorField = new JTextField(5);

        JLabel mensagemLabel = new JLabel("Mensagem:");
        mensagemLabel.setForeground(Color.RED);

        painelAcao.add(identificadorLabel); painelAcao.add(identificadorField);
        painelAcao.add(mensagemLabel);

        botaoConfirmar.addActionListener(e -> {

            try {
                int identificador = Integer.parseInt(identificadorField.getText());

                Acao acaoEncontrada = mediatorAcao.buscar(identificador);

                if (acaoEncontrada != null) {
                    // redireciona
                    cardLayout.show(painelPrincipal,"DetalhesAcao");
                } else {
                    mensagemLabel.setForeground(Color.RED);
                    mensagemLabel.setText("Acao nao encontrada.");
                }
            } catch (NumberFormatException ex) {
                mensagemLabel.setForeground(Color.RED);
                mensagemLabel.setText("Error, insira um numero valido para o identificador.");
            } catch (IOException ex) {
                mensagemLabel.setForeground(Color.RED);
                mensagemLabel.setText("Erro ao buscar acao, tente novamente.");
            }
        });

        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, identificadorField.getText()));
        return painelAcao;
    }
}
