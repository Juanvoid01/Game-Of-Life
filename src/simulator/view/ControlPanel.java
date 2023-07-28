package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Grid;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller _ctrl;
	
	private boolean stopped;
	
	private InfoDialog infoDialog = null;
	private RulesChangerDialog rulesChangerDialog = null;
	private JSpinner delay;
    private JLabel delayText;

	JButton quitButton;
	JButton runButton;
	JButton stopButton;
	JButton infoButton;
	JButton resetButton;
	JButton changeRulesButton;
	
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
		infoButton.setIcon(new ImageIcon("resources/icons/info.png"));
		
		changeRulesButton = new JButton();
		changeRulesButton.setToolTipText("Change Rules");
		changeRulesButton.setIcon(new ImageIcon("resources/icons/rules.png"));
		
		resetButton = new JButton();
		resetButton.setToolTipText("Reset");
		resetButton.setIcon(new ImageIcon("resources/icons/reset.png"));
		
		JSpinner generations = new JSpinner(new SpinnerNumberModel(100, 1, 10000, 1));
		JLabel generationsText = new JLabel(" Generations: ");
	    
		 delay = new JSpinner(new SpinnerNumberModel(100,0,1000,1));
         delayText = new JLabel(" Delay: ");
         
		generations.setPreferredSize(new Dimension(80, 35));
		generations.setMaximumSize(new Dimension(80, 35));
		
		delay.setPreferredSize(new Dimension(80, 35));
		delay.setMaximumSize(new Dimension(80, 35));
		
		
		toolBar.add(infoButton);
		toolBar.addSeparator();
		
		toolBar.add(changeRulesButton);
		toolBar.addSeparator();
		
		toolBar.add(runButton);
		toolBar.add(stopButton);
		toolBar.addSeparator();
		toolBar.add(resetButton);
		
		toolBar.addSeparator();
		toolBar.add(generationsText);
		toolBar.add(generations);
		toolBar.addSeparator();
		toolBar.add(delayText);
		toolBar.add(delay);
		
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
		
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				_ctrl.reset();
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
		
		changeRulesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(rulesChangerDialog == null){

					rulesChangerDialog = new RulesChangerDialog((JFrame) SwingUtilities.getWindowAncestor(ControlPanel.this), _ctrl);
                }

				rulesChangerDialog.open();
				
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
			try {
				Thread.sleep((Integer) delay.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
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
	public void onAdvance(Grid grid, int generationCount) {
		
	}

	@Override
	public void onReset() {
		
	}

	@Override
	public void onRegister(Grid grid) {
		
	}

	@Override
	public void onCellAdded(Grid grid, int row, int col) {
		
	}

	@Override
	public void onCellRemoved(Grid grid, int row, int col) {
		
	}

}
