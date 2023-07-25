package simulator.view;

import java.awt.Color;
import java.awt.Frame;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import simulator.control.Controller;
import simulator.miscellany.Vector2D;
import simulator.model.Cell;
import simulator.model.SimulatorObserver;

public class InfoDialog extends JDialog implements SimulatorObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel _dataTableModel;
	private String[] _headers = { "Row", "Col", "Neighbours", "Age" };
	
	private Controller _ctrl;

	InfoDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
		
		// TODO registrar this como observer;
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		setTitle("Simulation Information");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		
		_dataTableModel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		_dataTableModel.setColumnIdentifiers(_headers);
		
		JTable t = new JTable(_dataTableModel);
		//t.setOpaque(false);
		//t.setBackground(Color.WHITE);
		
		JScrollPane scroll_pane = new JScrollPane(t);
		scroll_pane.getViewport().setBackground(Color.white);//El viewport es el componente en donde se añaden los elementos al scrollpane
		mainPanel.add(scroll_pane);
		
		
		
		
		setLocationRelativeTo(getParent()); // Para que se abra en el centro del ControlPanel
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	public void open() {

		pack();
		setVisible(true);

	}

	@Override
	public void onAdvance(Map<Vector2D, Cell> cells, int generationCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRegister(Map<Vector2D, Cell> cells) {
		// TODO Auto-generated method stub
		
	}

}
