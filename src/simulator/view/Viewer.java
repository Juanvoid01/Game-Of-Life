package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import simulator.control.Controller;
import simulator.model.Grid;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final int _WIDTH = 500;
	private static final int _HEIGHT = 500;
	private static final int _NORMAL_CELL_SIZE = 10;
	
	private int _cell_size = _NORMAL_CELL_SIZE;
	//private int _grid_rows = 300;
	//private int _grid_cols = 300;
	
	private int _originX = 0;
	private int _originY = 0;

	private boolean _showHelp = true;
	
	private Grid grid;
	private Controller _ctrl;
	
	
	public Viewer(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI(){
		setMinimumSize(new Dimension(_WIDTH, _HEIGHT));
		setPreferredSize(new Dimension(_WIDTH, _HEIGHT));
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case '-':
					
					_cell_size-=2;
					
					if(_cell_size < 5) {
		        		_cell_size = 5;
		        	}
					
					repaint();
					break;
				case '+':
					
					_cell_size+= 2;

					if(_cell_size > 50) {
		        		_cell_size = 50;
		        	}
					repaint();
					break;
				case '=':
					_cell_size = _NORMAL_CELL_SIZE;
					repaint();
					break;
				case 'a': 
					Viewer.this._originX+=2;
					repaint();
					break;
				case 'd':
					Viewer.this._originX-=2;
					repaint();
					break;
				case 'w':
					Viewer.this._originY+=2;
					repaint();
					break;
				case 's':
					Viewer.this._originY-=2;
					repaint();
					break;
				case 'r':
					Viewer.this._originX = 0;
					Viewer.this._originY = 0;
					Viewer.this._cell_size =_NORMAL_CELL_SIZE;
					repaint();
					break;
				case 'h':
					_showHelp = _showHelp? false : true;
					repaint();
					break;
				default:
				}
			}
		});

		addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();

		        if (notches < 0) {
		            // Zoom in
		        	
					_cell_size+=2;
					
		        	if(_cell_size > 50) {
		        		_cell_size = 50;
		        	}
		        } else {
		            // Zoom out
		        	
					_cell_size-=2;
					
		        	if(_cell_size < 5) {
		        		_cell_size = 5;
		        	}
		        }
		        repaint();
			}
			
		});
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
		
					int x = e.getX() / _cell_size - _originX;
					int y = e.getY() / _cell_size - _originY;
					
					x = grid.wrapRow(x);
					y = grid.wrapCol(y);
					
					if(grid.isAlive(x, y)) {
						_ctrl.killCell(x, y);
					}
					else {
						_ctrl.addCell(x, y);
					}
					
				
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
					
				requestFocus();
				

			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
	
		});

		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// calculate the center
		//_centerX = getWidth() / 2 - _originX;
		//_centerY = getHeight() / 2 - _originY;
	
		drawGrid(g2D);
		
		if(_showHelp) {
			showHelp(g2D);
		}
		
	}
	
	private void showHelp(Graphics2D g) {
		
		String s1 = "w: move camera up";
		String s2 = "a: move camera left";
		String s3 = "s: move camera down";
		String s4 = "d: move camera right";
		String s5 = "+/mouse: zoom in";
		String s6 = "-/mouse: zoom out";
		String s7 = "r: reset view";
		String s8 = "h: show or hide help";
		
		Color c_anterior = g.getColor();
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(6, 5, 127, 117);
		
		g.setColor(Color.BLACK);
		
		g.drawString(s1, 10, 15);
		g.drawString(s2, 10, 30);
		g.drawString(s3, 10, 45);
		g.drawString(s4, 10, 60);
		g.drawString(s5, 10, 75);
		g.drawString(s6, 10, 90);
		g.drawString(s7, 10, 105);
		g.drawString(s8, 10, 120);
		g.setColor(c_anterior);
	}
	
	private void drawGrid(Graphics2D g2D) {
		
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2D.setColor(Color.GRAY);
        int w = this.getSize().width;
        int h = this.getSize().height;
        
        int wl = w/_cell_size;
        int hl = h/_cell_size;
        
        int x = 0;
        for (int i = 0; i <= wl; i++) {
        	x = i * _cell_size;
        	g2D.drawLine(x, 0, x,  h);
        }
        
    	for (int j = 0; j <= hl; j++) {
    		x = j * _cell_size;
    		g2D.drawLine(0, x, w, x);
    	}

        g2D.setColor(Color.BLACK);
        for (int i = 0; i <= grid.getRows(); i++) {
        	for (int j = 0; j <= grid.getCols(); j++) {
        		if(grid.isAlive(i, j)) {
        			g2D.fillRect(_cell_size * (grid.wrapCol(i + _originX)), _cell_size * (grid.wrapCol(j + _originY)), _cell_size, _cell_size);
        		}
        	}
        }
	}
	

	

	@Override
	public void onAdvance(Grid grid, int generationCount) {
		this.grid = grid;
		//this._grid_rows = grid.getRows();
		//this._grid_cols = grid.getCols();
		repaint();
	}

	@Override
	public void onReset() {
		this.grid.reset();
		//_cell_size = _NORMAL_CELL_SIZE;
		repaint();
	}

	@Override
	public void onRegister(Grid grid) {
		this.grid = grid;
		//this._grid_rows = grid.getRows();
		//this._grid_cols = grid.getCols();
		
		_cell_size = _NORMAL_CELL_SIZE;
		repaint();
	}

	@Override
	public void onCellAdded(Grid grid, int row, int col) {
		repaint();
	}

	@Override
	public void onCellRemoved(Grid grid, int row, int col) {
		repaint();
	}
	
}
