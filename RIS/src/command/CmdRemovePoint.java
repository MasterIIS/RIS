package command;

import introduction.Point;
import mvc.DrawingModel;

public class CmdRemovePoint implements Command {

	private Point point;
	private DrawingModel drawingModel;

	public CmdRemovePoint(Point point, DrawingModel drawingModel) {
		this.point = point;
		this.drawingModel = drawingModel;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawingModel.remove(point);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		drawingModel.add(point);
	}

}
