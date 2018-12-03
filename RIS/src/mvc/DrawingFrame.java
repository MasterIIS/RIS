package mvc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DrawingFrame extends JFrame{
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private JList lstLog;

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
		
	}
	
	public DrawingFrame() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(view);
		
		JPanel pnlLog = new JPanel();
		getContentPane().add(pnlLog, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		pnlLog.add(scrollPane);
		
		lstLog = new JList();
		scrollPane.setViewportView(lstLog);
		
		JPanel pnlToolbar = new JPanel();
		getContentPane().add(pnlToolbar, BorderLayout.NORTH);
		
		JButton btnOpen = new JButton("Open log");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openLog();
			}
		});
		pnlToolbar.add(btnOpen);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.mouseClicked(arg0);
			}
		});
	}

	public JList getLstLog() {
		return lstLog;
	}

}
