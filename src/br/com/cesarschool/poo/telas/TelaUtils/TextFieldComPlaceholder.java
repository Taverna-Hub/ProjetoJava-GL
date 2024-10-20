package br.com.cesarschool.poo.telas.TelaUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class TextFieldComPlaceholder extends JTextField {

    private String placeholder;

    public TextFieldComPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        setOpaque(false);  // Para desenhar as bordas customizadas
        setCaretColor(Color.BLACK);  // Define o cursor (caret) para preto
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Desenha o campo de texto com bordas arredondadas
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  // Suaviza as bordas
        Shape arredondado = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20);  // Borda arredondada
        g2.setColor(getBackground());
        g2.fill(arredondado);

        // Chama o super para garantir que o cursor seja desenhado
        super.paintComponent(g);

        // Desenha o placeholder quando o texto estiver vazio e o campo não estiver focado
        if (getText().isEmpty() && !isFocusOwner()) {
            g2.setColor(Color.GRAY); // Cor do placeholder
            g2.setFont(new Font("INTER", Font.PLAIN, 16));

            // Centraliza o placeholder verticalmente e horizontalmente
            FontMetrics fm = g2.getFontMetrics();
            int textoLargura = fm.stringWidth(placeholder);
            int textoAltura = fm.getHeight();

            int x = (getWidth() - textoLargura) / textoLargura;
            int y = (getHeight() + textoAltura) / 2 - fm.getDescent();  // Centraliza verticalmente

            g2.drawString(placeholder, x, y);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Desenha a borda arredondada
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape arredondado = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.setColor(getForeground());
        g2.draw(arredondado);
        g2.dispose();
    }
}
