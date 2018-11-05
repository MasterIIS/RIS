package introduction;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Moveable {
	protected Color color;
	
	public abstract void draw(Graphics g);
	
	public Shape() {
		
	}

	public Shape(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
