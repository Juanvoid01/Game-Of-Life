package simulator.view;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller _ctrl;
	
	public MainWindow(Controller ctrl) {
		super("Game of life");
		_ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		setContentPane(mainPanel);
		
		JPanel controlPanel = new ControlPanel(_ctrl);
		
		mainPanel.add(controlPanel, BorderLayout.PAGE_START);
		
		JPanel footBar = new FootBar(_ctrl);
		
		mainPanel.add(footBar, BorderLayout.PAGE_END);
		
		JComponent viewer = new Viewer(_ctrl);
		
		mainPanel.add(viewer, BorderLayout.CENTER);
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				Utils.quit(MainWindow.this);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}

}
