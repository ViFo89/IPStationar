import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JPanel;

class Paper extends JPanel {
	private HashSet<Point> hs = new HashSet<Point>();
	private int mouseKey = 0;
	public Paper() {
		setBackground(Color.white);
		addMouseListener(new L1());
		addMouseMotionListener(new L2());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		Iterator<Point> i = hs.iterator();
		while (i.hasNext()) {
			Point p = i.next();
			g.fillOval(p.x, p.y, 2, 2);
		}
	}

	private void addPoint(Point p) {
		hs.add(p);
		repaint();
	}
	
	private void removePoint(Point p) {
		hs.remove(p);
		repaint();
	}

	class L1 extends MouseAdapter {
		public void mousePressed(MouseEvent me) {
			mouseKey = me.getButton();
			if(me.getButton() == 3){
				removePoint(me.getPoint());
			}
			else{
				addPoint(me.getPoint());
			}
		}
	}

	class L2 extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent me) {
			if(mouseKey == 3){
				removePoint(me.getPoint());
			}
			else{
				addPoint(me.getPoint());
			}
		}
	}
}