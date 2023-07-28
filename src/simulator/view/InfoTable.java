package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

public class InfoTable extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	
	String _title;
	TableModel _tableModel;
	
	InfoTable(String title, TableModel tableModel) {
		_title = title;
		_tableModel = tableModel;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		
		Border bordejpanel = BorderFactory.createLineBorder(Color.black, 1);
		this.setBorder(BorderFactory.createTitledBorder(bordejpanel,_title,TitledBorder.LEFT,TitledBorder.TOP));
		
		JTable barra = new JTable(_tableModel);
		JScrollPane scroll = new JScrollPane(barra);
		scroll.getViewport().setBackground(Color.white);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scroll);
	}
}
