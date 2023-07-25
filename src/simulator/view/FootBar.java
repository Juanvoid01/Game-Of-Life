package simulator.view;

import java.util.Map;

import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.miscellany.Vector2D;
import simulator.model.Cell;
import simulator.model.SimulatorObserver;

public class FootBar extends JPanel implements SimulatorObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private Controller _ctrl;
	
	public FootBar(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI(){
		
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
