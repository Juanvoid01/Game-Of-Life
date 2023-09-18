package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import simulator.control.Controller;

import simulator.model.Grid;
import simulator.model.SimulatorObserver;

public class RulesChangerDialog extends JDialog implements SimulatorObserver{
private static final long serialVersionUID = 1L;
	
	private DefaultComboBoxModel<String> _reproductionRulesModel;
	private DefaultComboBoxModel<String> _survivalRulesModel;
	private DefaultTableModel _dataTableModel;
	private Controller _ctrl;

	private String[] _headers = { "Rule Name", "Description" };
	
	private List<String> reproductionRulesInfo;
	private List<String> survivalRulesInfo;
	
	private JLabel texto_inicio1;
	private JComboBox<String> CBsurvivalRules;
	private JComboBox<String> CBreproductionRules;
	
	RulesChangerDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		
		ctrl.addObserver(this);
		initGUI();
		

	}
	
	private void initGUI() {
		setTitle("Game Rules Selection");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
	
		

		texto_inicio1 = new JLabel("<html><p>Select the desired rules, you can see their descriptions on the table</p></html>");
		texto_inicio1.setAlignmentX(CENTER_ALIGNMENT);	
		mainPanel.add(texto_inicio1);
	

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

		
		JScrollPane scroll_pane = new JScrollPane(t);

		scroll_pane.getViewport().setBackground(Color.white);
		mainPanel.add(scroll_pane);
		

		
		mainPanel.add(Box.createRigidArea(new Dimension(0,20))); 
		
		_reproductionRulesModel = new DefaultComboBoxModel<>();
		_survivalRulesModel = new DefaultComboBoxModel<>();


		
		for(String it : reproductionRulesInfo) {
			_reproductionRulesModel.addElement(it.split(" ", 2)[0]);
		}
		
		for(String it : survivalRulesInfo) {
			_survivalRulesModel.addElement(it.split(" ", 2)[0]);
		}
		
		_reproductionRulesModel.setSelectedItem("ClassicReproduction");
		_survivalRulesModel.setSelectedItem("ClassicSurvival");
		
		fillInfo();
		
		CBsurvivalRules = new JComboBox<String> (_survivalRulesModel);
		CBreproductionRules = new JComboBox<String> (_reproductionRulesModel);
		
		CBsurvivalRules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		
		JPanel selectores = new JPanel(new FlowLayout());
		selectores.add(new JLabel("Survival rule: "));
		CBsurvivalRules.setBackground(Color.WHITE);
		selectores.add(CBsurvivalRules);
		selectores.add(new JLabel("Reproduction rule: "));
		CBreproductionRules.setBackground(Color.WHITE);
		selectores.add(CBreproductionRules);
		
		mainPanel.add(selectores);
	
		
		JButton cancel= new JButton("Cancel");
		
		JButton OK= new JButton("OK");
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				setVisible(false);
			}
			
		});
		
		OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

				String r = (String)CBreproductionRules.getSelectedItem();
				String s = (String)CBsurvivalRules.getSelectedItem();
				
				_ctrl.setReproductionRules(r);
				_ctrl.setSurvivalRules(s);
				
				setVisible(false);
				
			}
			
		});
		
		JPanel botones = new JPanel(new FlowLayout());
		
		botones.add(cancel);
		botones.add(OK);
		
		mainPanel.add(new JPanel()); 

		mainPanel.add(botones);
		
		setPreferredSize(new Dimension(700, 400));

		setLocationRelativeTo(getParent());
		pack();
		setResizable(true);
		setVisible(false);
		
		ImageIcon icon = new ImageIcon("resources/icons/rules.png");
		this.setIconImage(icon.getImage());
		
		int offsetX = getSize().width/2;
		int offsetY = getSize().height/2;
        setLocation(getLocation().x - offsetX, getLocation().y - offsetY);
	}
	
	public void open() {
		pack();
		setVisible(true);
	}
	
	public void fillInfo() {
		if(_dataTableModel==null) {
			return;
		}
		
		for(String rule:survivalRulesInfo) {
			_dataTableModel.addRow(rule.split(" ",2));
		}
		
		for(String rule:reproductionRulesInfo) {
			_dataTableModel.addRow(rule.split(" ",2));
		}

		
		
	}

	@Override
	public void onAdvance(Grid grid, int generationCount) {
		
		
	}

	@Override
	public void onReset() {
		
		reproductionRulesInfo = _ctrl.getReproductionRulesInfo();
		survivalRulesInfo = _ctrl.getSurvivalRulesInfo();
		fillInfo();
	}

	@Override
	public void onRegister(Grid grid) {
	
		reproductionRulesInfo = _ctrl.getReproductionRulesInfo();
		survivalRulesInfo = _ctrl.getSurvivalRulesInfo();
		fillInfo();
	}

	@Override
	public void onCellAdded(Grid grid, int row, int col) {
		
	}

	@Override
	public void onCellRemoved(Grid grid, int row, int col) {

		
	}

}
