package mvc;

import java.util.ArrayList;

import introduction.Point;

public class DrawingModel {
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public ArrayList<Point> getAll(){
		return points;
	}
	
	public void add(Point p) {
		points.add(p);
	}
	
	public void remove(Point p) {
		points.remove(p);
	}
	
	public Point get(int i) {
		return points.get(i);
	}

}
