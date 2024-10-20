package br.com.cesarschool.poo.telas.TelaUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class StyledCheckBox extends JCheckBox {

    private final Color corSelecionada;
    private final Color corNaoSelecionada;

    public StyledCheckBox(String placeholder, Color corSelecionada, Color corNaoSelecionada) {
        super(placeholder);
        this.corSelecionada = corSelecionada;
        this.corNaoSelecionada = corNaoSelecionada;

        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);

        addItemListener(e -> {
            // Repaint when the selection state changes
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Posição da caixa de seleção
        int checkBoxSize = 16; // Tamanho do "check"
        int checkBoxX = 0; // Posição X da caixa de seleção
        int checkBoxY = (getHeight() - checkBoxSize) / 2; // Centraliza verticalmente a caixa de seleção

        // Desenha o fundo arredondado da caixa de seleção (apenas a área do check)
        if (isSelected()) {
            g2.setColor(corSelecionada);  // Cor quando selecionado
        } else {
            g2.setColor(corNaoSelecionada);  // Cor quando não selecionado
        }

        // Desenha o fundo arredondado apenas para o "check"
        int arcoBorda = 8;
        Shape fundoArredondadoCheck = new RoundRectangle2D.Double(checkBoxX, checkBoxY, checkBoxSize, checkBoxSize, arcoBorda, arcoBorda);
        g2.fill(fundoArredondadoCheck);

        // Desenha o ícone de seleção padrão
        if (isSelected()) {
            g2.setColor(Color.WHITE); // Cor do "check" (marcador)
            int padding = 4;  // Distância do check para a borda da caixa
            g2.drawLine(checkBoxX + padding, checkBoxY + (checkBoxSize / 2), checkBoxX + (checkBoxSize / 2), checkBoxY + checkBoxSize - padding);
            g2.drawLine(checkBoxX + (checkBoxSize / 2), checkBoxY + checkBoxSize - padding, checkBoxX + checkBoxSize - padding, checkBoxY + padding);
        }

        // Desenha o texto do checkbox
        g2.setColor(getForeground());
        g2.setFont(getFont());
        g2.drawString(getText(), checkBoxX + checkBoxSize + 10, getFontMetrics(getFont()).getAscent() + checkBoxY);

        g2.dispose();
    }
}
