package introduction;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlDrawing extends JPanel {
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	
	/**
	 * Create the panel.
	 */
	public PnlDrawing() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point point = new Point(e.getX(), e.getY(), Color.RED);
				shapes.add(point);
				repaint();
			}
		});

	}
	
	public void paint(Graphics g) {
		Iterator<Shape> it = shapes.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}


	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

}
