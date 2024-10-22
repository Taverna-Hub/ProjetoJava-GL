package br.com.cesarschool.poo.telas.TelaUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CentralizedTable extends JTable {
    public CentralizedTable(DefaultTableModel model) {
        super(model);
        this.setLayout(null); // Usar layout absoluto
    }

    // Método para centralizar a tabela no painel
    public void centralizarTabela(JPanel painel) {
        Dimension tamanhoPainel = painel.getSize();
        Dimension tamanhoTabela = this.getPreferredSize();
        int x = (tamanhoPainel.width - tamanhoTabela.width) / 2;
        int y = (tamanhoPainel.height - tamanhoTabela.height) / 2;

        this.setBounds(x, y, tamanhoTabela.width, tamanhoTabela.height);
    }
}
