package command;

import introduction.Point;

public class CmdUpdatePoint implements Command{
	private Point original;
	private Point newState;
	private Point oldState = new Point();
	
	public CmdUpdatePoint(Point original, Point newState) {
		this.original = original;
		this.newState = newState;
	}
 	@Override
	public void execute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setColor(original.getColor());
		
		original.moveTo(newState.getX(), newState.getY());
		original.setColor(newState.getColor());
	}
 	@Override
	public void unexecute() {
		original.setX(oldState.getX());
		original.setY(oldState.getY());
		original.setColor(oldState.getColor());
	}
}