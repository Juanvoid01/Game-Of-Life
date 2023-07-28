package simulator.view;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
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
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
			
				Utils.quit(MainWindow.this);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {

				
			}
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
		ImageIcon icon = new ImageIcon("resources/icons/glider.png");
		this.setIconImage(icon.getImage());
	}

}
