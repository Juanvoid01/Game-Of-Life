package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.miscellany.Vector2D;
import simulator.model.Cell;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller _ctrl;
	
	private boolean stopped;
	
	private InfoDialog infoDialog = null;

	JButton quitButton;
	JButton runButton;
	JButton stopButton;
	JButton infoButton;
	
	public ControlPanel(Controller ctrl) {
		stopped = true;
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI(){
		setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.PAGE_START);

		quitButton = new JButton();
		quitButton.setToolTipText("Exit");
		quitButton.setIcon(new ImageIcon("resources/icons/exit.png"));
		
		runButton = new JButton();
		runButton.setToolTipText("Run");
		runButton.setIcon(new ImageIcon("resources/icons/run.png"));
		
		stopButton = new JButton();
		stopButton.setToolTipText("Stop");
		stopButton.setIcon(new ImageIcon("resources/icons/stop.png"));
		
		infoButton = new JButton();
		infoButton.setToolTipText("Info");
		infoButton.setIcon(new ImageIcon("resources/icons/physics.png"));
		
		JSpinner generations = new JSpinner(new SpinnerNumberModel(100, 1, 10000, 1));
		JLabel generationsText = new JLabel(" Generations: ");
	    
		generations.setPreferredSize(new Dimension(80, 35));
		generations.setMaximumSize(new Dimension(80, 35));
		
		
		toolBar.add(infoButton);
		toolBar.addSeparator();
		
		toolBar.add(runButton);
		toolBar.add(stopButton);
		
		toolBar.add(generationsText);
		toolBar.add(generations);
		toolBar.add(Box.createGlue()); // this aligns the button to the right
		toolBar.addSeparator();
		toolBar.add(quitButton);
		
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				stopped = false;
				
				stopButton.setEnabled(true);
				runButton.setEnabled(false);

				run_sim((Integer)generations.getValue());
				
			}});
		
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				stopped = true;
			}});
		
		infoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(infoDialog == null){

					infoDialog = new InfoDialog((JFrame) SwingUtilities.getWindowAncestor(ControlPanel.this), _ctrl);
                }

				infoDialog.open();
				
			}});
		
		quitButton.addActionListener((e) -> Utils.quit(this));
	}
	
	private void run_sim(int n) {
		if (n > 0 && !stopped) {
			try {
			_ctrl.run(1);
			} catch (Exception e) {
				Utils.showErrorMsg("Error in _ctrl.run");

				quitButton.setEnabled(true);
				stopButton.setEnabled(true);
				runButton.setEnabled(true);

				
				stopped = true;
				return;
			}
			SwingUtilities.invokeLater(() -> run_sim(n - 1));
		} else {
			//activate buttons
			quitButton.setEnabled(true);
			stopButton.setEnabled(true);
			runButton.setEnabled(true);

			
			stopped = true;
		}
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
