package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import introduction.Point;

public class DrawingView extends JPanel{
	private DrawingModel model;

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			Iterator<Point> it = model.getAll().iterator();
			while(it.hasNext()) {
				it.next().draw(g);
			}
		}
	}

}
