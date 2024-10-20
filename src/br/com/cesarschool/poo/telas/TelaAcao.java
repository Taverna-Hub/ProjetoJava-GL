package br.com.cesarschool.poo.telas;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class TelaAcao extends JFrame {

    private MediatorAcao mediatorAcao;

    public TelaAcao() {
        mediatorAcao = MediatorAcao.getInstancia();
    }

    public JPanel getTelaAcaoPanel() {

        JPanel painelAcao = new JPanel();
        painelAcao.setLayout(new BoxLayout(painelAcao, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Escolha sua Acao:", JLabel.CENTER);
        JButton botaoIncluirAcao = new JButton("IncluirAcao");
        JButton botaoBuscarAcao = new JButton("Gerenciar Acao");

        painelAcao.add(label);
        painelAcao.add(botaoIncluirAcao);
        painelAcao.add(botaoBuscarAcao);

        botaoIncluirAcao.addActionListener(e -> {

            try {
                String nome = JOptionPane.showInputDialog("Digite o nome da Acao:");
                double valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor unitario:"));
                int identificador = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador:"));
                LocalDate validade = LocalDate.now().plusDays(31);

                Acao novaAcao = new Acao(identificador, nome, validade, valor);

                String resultadoInclusao = mediatorAcao.incluir(novaAcao);
                if (resultadoInclusao == null) {
                    JOptionPane.showMessageDialog(null, "Acao incluída com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, resultadoInclusao, "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao incluir a acao. Tente novamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro nos valores informados. Por favor, insira valores validos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoBuscarAcao.addActionListener(e -> {

            try {
                int identificador = Integer.parseInt(JOptionPane.showInputDialog("Informe o identificador:"));

                Acao acaoEncontrada = mediatorAcao.buscar(identificador);

                if (acaoEncontrada != null) {

                    JOptionPane.showMessageDialog(null,
                            "Acao Encontrada\n" +
                                    "Identificador: " + acaoEncontrada.getIdentificador() + "\n" +
                                    "Nome: " + acaoEncontrada.getNome() + "\n" +
                                    "Validade: " + acaoEncontrada.getDataDeValidade() + "\n" +
                                    "Valor Unitario: " + acaoEncontrada.getValorUnitario() + "\n");
                } else {
                    JOptionPane.showMessageDialog(null, "Acao nao encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número valido para o identificador", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar a acao. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return painelAcao;
    }
}
