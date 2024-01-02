package Listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import GuiElements.ScrollBar;

public class ScrollBarListener extends MouseAdapter{
	private ScrollBar scroll;
	private JScrollPane scrollBar;

    public ScrollBarListener(ScrollBar scroll, JScrollBar scrollBar) {
        this.scroll = scroll;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = scroll.getDataTable();
        JTableHeader header = table.getTableHeader();
        int columnIndex = header.columnAtPoint(e.getPoint());

        // Implementieren Sie hier die gewünschte Logik für den Spaltenklick
        // columnIndex gibt die Indexnummer der geklickten Spalte an
        // Beispiel: System.out.println("Spalte geklickt: " + columnIndex);
    }
}