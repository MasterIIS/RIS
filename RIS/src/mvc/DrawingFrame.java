package mvc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingFrame extends JFrame{
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
		
	}
	
	public DrawingFrame() {
		getContentPane().add(view, BorderLayout.CENTER);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.mouseClicked(arg0);
			}
		});
	}

}
