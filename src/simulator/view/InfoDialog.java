package simulator.view;

import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import simulator.control.Controller;

public class InfoDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller _ctrl;

	InfoDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
		
	}
	
	private void initGUI() {
		setTitle("Simulation Information");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		InfoTable groupTable = new InfoTable("Groups", new GridInfoTableModel(_ctrl));
		groupTable.setPreferredSize(new Dimension(500, 250));
		this.add(groupTable);
		
		setLocationRelativeTo(getParent()); 


		pack();
		setResizable(true);
		setVisible(false);
		
		ImageIcon icon = new ImageIcon("resources/icons/info.png");
		this.setIconImage(icon.getImage());
		
		int offsetX = getSize().width/2;
		int offsetY = getSize().height/2;
        setLocation(getLocation().x - offsetX, getLocation().y - offsetY);
	}
	
	public void open() {

		pack();
		setVisible(true);
	}


}
