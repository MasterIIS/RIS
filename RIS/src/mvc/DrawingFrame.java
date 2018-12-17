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
import javax.swing.ListSelectionModel;

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
		lstLog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.remove(lstLog.getSelectedIndex());
			}
		});
		pnlToolbar.add(btnRemove);
		
		JButton btnOpenModel = new JButton("Open model");
		btnOpenModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.openModel();
			}
		});
		pnlToolbar.add(btnOpenModel);
		
		JButton btnSaveModel = new JButton("Save model");
		btnSaveModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.saveModel();
			}
		});
		pnlToolbar.add(btnSaveModel);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		pnlToolbar.add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		pnlToolbar.add(btnRedo);
		
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
