package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import simulator.control.Controller;
import simulator.model.Grid;
import simulator.model.SimulatorObserver;

public class FootBar extends JPanel implements SimulatorObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller _ctrl;
	
	private JLabel generationLabel;
	private JLabel cellsAliveLabel;

	
	public FootBar(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI(){
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		generationLabel = new JLabel("Generation: 0    ");
		cellsAliveLabel = new JLabel("Cells Alive: 0 ");
		generationLabel.setMaximumSize(new Dimension(150, 35));
		cellsAliveLabel.setMaximumSize(new Dimension(150, 35));
		
		
		JSeparator s1 = new JSeparator(JSeparator.VERTICAL);
		s1.setPreferredSize(new Dimension(10, 20));
		
		this.add(generationLabel);
		this.add(s1);
		this.add(cellsAliveLabel);

	}

	@Override
	public void onAdvance(Grid grid, int generationCount) {
		
		generationLabel.setText("Generation: " + generationCount + "   ");
		cellsAliveLabel.setText("Cells Alive: " + grid.getCellsAlive());
	}

	@Override
	public void onReset() {
		
		generationLabel.setText("Generation: 0   ");
		cellsAliveLabel.setText("Cells Alive: 0");
	}

	@Override
	public void onRegister(Grid grid) {
		
		generationLabel.setText("Generation: " + 0 + "   ");
		cellsAliveLabel.setText("Cells Alive: " + grid.getCellsAlive());
	}

	@Override
	public void onCellAdded(Grid grid, int row, int col) {
		
		cellsAliveLabel.setText("Cells Alive: " + grid.getCellsAlive());
	}

	@Override
	public void onCellRemoved(Grid grid, int row, int col) {
		
		cellsAliveLabel.setText("Cells Alive: " + grid.getCellsAlive());
	}
}
