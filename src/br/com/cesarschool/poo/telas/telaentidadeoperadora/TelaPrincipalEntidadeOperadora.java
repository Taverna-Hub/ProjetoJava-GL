package br.com.cesarschool.poo.telas.telaentidadeoperadora;

import br.com.cesarschool.poo.telas.TelaUtils.BotaoArredondado;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class TelaPrincipalEntidadeOperadora {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    // Construtor para receber o CardLayout e o painelPrincipal da TelaInicial
    public TelaPrincipalEntidadeOperadora(CardLayout cardLayout, JPanel painelPrincipal) throws FileNotFoundException {
        this.cardLayout = cardLayout;
        this.painelPrincipal = painelPrincipal;

        // Adiciona as telas ao painel principal
        JPanel telaIncluirEntidade = new TelaIncluirEntidade(cardLayout, painelPrincipal).criarTelaIncluirEntidade();
        JPanel telaGerenciarEntidade = new TelaGerenciarEntidade(cardLayout, painelPrincipal).criarTelaGerenciarEntidade();

        painelPrincipal.add(telaIncluirEntidade, "Tela Incluir Entidade");
        painelPrincipal.add(telaGerenciarEntidade, "Tela Gerenciar Entidade"); // Corrigido aqui
    }

    public JPanel criarTelaEntidadeOperadora() {
        JPanel telaEntidadeOperadoraPanel = new JPanel();
        telaEntidadeOperadoraPanel.setLayout(null);
        telaEntidadeOperadoraPanel.setBackground(Color.decode(BotaoArredondado.FUNDO));

        JLabel labelEntidadeOperadora = new JLabel("Entidade Operadora");
        BotaoArredondado botaoVoltar = new BotaoArredondado("Voltar", 20);
        BotaoArredondado botaoIncluirEntidade = new BotaoArredondado("Incluir", 20);
        BotaoArredondado botaoGerenciarEntidade = new BotaoArredondado("Gerenciar", 20);

        botaoVoltar.setBounds(20, 15, 87, 40);
        botaoIncluirEntidade.setBounds(215, 149, 170, 40);
        botaoGerenciarEntidade.setBounds(215, 232, 170, 40);
        labelEntidadeOperadora.setBounds(209, 73, 181, 48);
        labelEntidadeOperadora.setForeground(Color.WHITE);
        labelEntidadeOperadora.setFont(new Font("INTER", Font.PLAIN, 20));

        botaoVoltar.setBackground(Color.decode(BotaoArredondado.LARANJA));
        botaoVoltar.setForeground(Color.WHITE);

        botaoIncluirEntidade.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoIncluirEntidade.setForeground(Color.WHITE);

        botaoGerenciarEntidade.setBackground(Color.decode(BotaoArredondado.MARROM));
        botaoGerenciarEntidade.setForeground(Color.WHITE);

        telaEntidadeOperadoraPanel.add(botaoIncluirEntidade);
        telaEntidadeOperadoraPanel.add(botaoGerenciarEntidade);
        telaEntidadeOperadoraPanel.add(labelEntidadeOperadora);
        telaEntidadeOperadoraPanel.add(botaoVoltar);

        // Ação do botão Incluir
        botaoIncluirEntidade.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Incluir Entidade"));
        botaoGerenciarEntidade.addActionListener(e-> cardLayout.show(painelPrincipal, "Tela Gerenciar Entidade"));
        // Retorna à tela inicial
        botaoVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "Tela Inicial"));

        return telaEntidadeOperadoraPanel;
    }
}
