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
import java.util.HashMap;

import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.miscellany.Vector2D;

import simulator.model.Cell;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final int _WIDTH = 500;
	private static final int _HEIGHT = 500;

	// (_centerX,_centerY) is used as the origin when drawing
	// the bodies
	private int _centerX;
	private int _centerY;

	// values used to shift the actual origin (the middle of
	// the window), when calculating (_centerX,_centerY)
	private int _originX = 0;
	private int _originY = 0;

	// the scale factor, used to reduce the bodies coordinates
	// to the size of the component
	private double _scale = 1.0;
	
	//metrics
	
	private int cellWidth = 16;
	
	private Map<Vector2D,Cell> cells;
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
					_scale = _scale * 1.1;
					repaint();
					break;
				case '+':
					_scale = Math.max(1000.0, _scale / 1.1);
					repaint();
					break;
				case '=':
					autoScale();
					repaint();
					break;
				case 'j': 
					Viewer.this._originX-=10;
					repaint();
					break;
				case 'l':
					Viewer.this._originX+=10;
					repaint();
					break;
				case 'i':
					Viewer.this._originY-=10;
					repaint();
					break;
				case 'm':
					Viewer.this._originY+=10;
					repaint();
					break;
				case 'k':
					Viewer.this._originX = 0;
					Viewer.this._originY = 0;
					repaint();
					break;

				default:
				}
			}
		});

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
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
		_centerX = getWidth() / 2 - _originX;
		_centerY = getHeight() / 2 - _originY;
		
		//draw red cross at center
		g.setColor(Color.RED);
		g.drawLine(_centerX - 10, _centerY, _centerX + 10, _centerY);
		g.drawLine(_centerX, _centerY - 10, _centerX, _centerY + 10);
		
	
		drawGrid(g2D);
		drawCells(g2D);
	}
	
	private void drawGrid(Graphics2D g2D) {
		
	}
	
	private void drawCells(Graphics2D g2D) {
		
		int x,y;
		double invScale = (1.0/_scale);
		
		int halfWidth = cellWidth/2;
		 g2D.setColor(Color.BLACK);
		// g2D.fillRect(250 - halfWidth, 250 - halfWidth,250 + halfWidth, 250 + halfWidth);
		
		for(Vector2D cellPos: cells.keySet()) {
			
			x = _centerX - halfWidth;
			y = _centerY - halfWidth;
			
			x += (int)(cellPos.getX() *cellWidth* invScale);
			y += (int)(-cellPos.getY() *cellWidth* invScale);
			g2D.fillRect(x,y, cellWidth, cellWidth);
		}
	}
	
	private void autoScale() {

		double max = 1.0;

		for (Cell c : cells.values()) {
			Vector2D p = c.getPos();
			max = Math.max(max, Math.abs(p.getX()));
			max = Math.max(max, Math.abs(p.getY()));
		}

		double size = Math.max(1.0, Math.min(getWidth(), getHeight()));

		_scale = max > size ? 4.0 * max / size : 1.0;
	}
	

	@Override
	public void onAdvance(Map<Vector2D, Cell> cells, int generationCount) {
		// TODO Auto-generated method stub
		this.cells = cells;
		autoScale();
		repaint();
	}

	@Override
	public void onReset() {
		// TODO Auto-generated method stub
		this.cells.clear();
		repaint();
	}

	@Override
	public void onRegister(Map<Vector2D, Cell> cells) {
		// TODO Auto-generated method stub
		this.cells = cells;
		autoScale();
		repaint();
	}
	
}
