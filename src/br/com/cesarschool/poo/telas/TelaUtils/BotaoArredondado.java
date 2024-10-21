package br.com.cesarschool.poo.telas.TelaUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class BotaoArredondado extends JButton {
    public static final String LARANJA = "#B76E2D";
    public static final String MARROM = "#623F2B";
    public static final String FUNDO = "#17181D";
    private int raio;
    private boolean mouseDentro;  // Flag para verificar se o mouse está sobre o botão

    public BotaoArredondado(String texto, int raio) {
        super(texto);
        this.raio = raio;
        setFocusPainted(false);  // Remove o contorno ao clicar
        setContentAreaFilled(false);  // Remove o fundo padrão do botão
        setBorderPainted(false);  // Remove a borda padrão do botão

        // Listener para detectar a posição do mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseDentro = true;
                repaint();  // Atualiza o componente
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseDentro = false;
                repaint();  // Atualiza o componente
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  // Suaviza as bordas
        Shape arredondado = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), raio, raio);  // Cria bordas arredondadas
        g2.setColor(getBackground());
        g2.fill(arredondado);  // Preenche o botão com a cor de fundo
        // Se o mouse estiver sobre o botão, desenha uma borda de destaque
        if (mouseDentro) {
            g2.setColor(Color.BLACK);  // Define a cor da borda ao passar o mouse (pode ser alterada)
            g2.setStroke(new BasicStroke(2));  // Define a espessura da borda
            g2.draw(arredondado);  // Desenha a borda arredondada
        }
        setForeground(Color.WHITE);
        g2.setColor(getForeground());  // Define a cor do texto

        g2.dispose();

        super.paintComponent(g);
    }
}


