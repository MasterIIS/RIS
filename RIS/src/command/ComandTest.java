package command;

import java.awt.Color;

import introduction.Point;
import mvc.DrawingModel;

public class ComandTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawingModel model = new DrawingModel();
		Point p1 = new Point(10, 20, Color.BLUE);
		CmdAddPoint cmdAddPoint = new CmdAddPoint(p1, model);
		cmdAddPoint.execute();
		System.out.println(model.get(0));
		cmdAddPoint.unexecute();
		cmdAddPoint.execute();
		
		CmdRemovePoint cmdRemovePoint = new CmdRemovePoint(p1, model);
		cmdRemovePoint.execute();
		cmdRemovePoint.unexecute();
		System.out.println(model.get(0));
		
		Point p2 = new Point(30, 40);
		CmdUpdatePoint cmdUpdatePoint = new CmdUpdatePoint(model.get(0), p2);
		cmdUpdatePoint.execute();
		System.out.println(model.get(0));
		cmdUpdatePoint.unexecute();
		System.out.println(model.get(0));
	}

}
