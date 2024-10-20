package br.com.cesarschool.poo.telas.telaacao;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipalAcao extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public TelaPrincipalAcao(CardLayout cardLayout, JPanel painelPrincipal) {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel getTelaAcaoPanel() {

        JPanel painelAcao = new JPanel();
        painelAcao.setLayout(new BoxLayout(painelAcao, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Escolha uma acao:", JLabel.CENTER);
        JButton botaoIncluirAcao = new JButton("Incluir Acao");
        JButton botaoGerenciarAcao = new JButton("Gerenciar Acao");

        painelAcao.add(label);
        painelAcao.add(botaoIncluirAcao);
        painelAcao.add(botaoGerenciarAcao);

        botaoIncluirAcao.addActionListener(e -> cardLayout.show(painelPrincipal, "Incluir Acao"));
        botaoGerenciarAcao.addActionListener(e -> cardLayout.show(painelPrincipal, "Gerenciar Acao"));

        return painelAcao;
    }
}
