package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import command.CmdAddPoint;
import command.Command;
import introduction.Point;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	public final static Logger LOGGER = Logger.getLogger("logger");
	private FileHandler fileHandler;
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private ArrayList<Command> commands = new ArrayList<Command>();
	private int indexCmd = -1;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		this.frame.getLstLog().setModel(dlm);

		try {
			File dir = new File("D:\\Asistenti\\Vukmanovic\\RIS");
			dir.mkdirs();
			String fileName = "D:\\Asistenti\\Vukmanovic\\RIS\\"+System.currentTimeMillis()+".log";
			File file = new File(fileName);
			file.createNewFile();
			fileHandler = new FileHandler(fileName);
			LOGGER.addHandler(fileHandler);
			LOGGER.setLevel(Level.INFO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		Point p = new Point(arg0.getX(), 
				arg0.getY(), Color.RED);
		//model.add(p);
		CmdAddPoint cmdAddPoint = new CmdAddPoint(p, model);
		doCmd(cmdAddPoint);
		frame.getView().repaint();

	}

	public void doCmd(Command cmd) {
		// TODO Auto-generated method stub
		if (indexCmd < commands.size()-1) {
			for (int i = commands.size()-1; i > indexCmd; i--) {
				commands.remove(i);
			}

			cmd.execute();
			commands.add(cmd);
			LOGGER.log(Level.INFO, cmd.toString());
			dlm.add(0, cmd.toString());
		}else {
			cmd.execute();
			commands.add(cmd);
			LOGGER.log(Level.INFO, cmd.toString());
			dlm.add(0, cmd.toString());
		}
		indexCmd++;
	}
	
	public void undo() {
		if(indexCmd > -1) {
			Command cmd = commands.get(indexCmd);
			cmd.unexecute();
			LOGGER.log(Level.INFO, "Undo - "+cmd.toString());
			dlm.add(0, "Undo - "+cmd.toString());
			indexCmd--;
		}
		frame.getView().repaint();
	}
	
	public void redo() {
		if(indexCmd < commands.size()-1) {
			indexCmd++;
			Command cmd = commands.get(indexCmd);
			cmd.execute();
			LOGGER.log(Level.INFO, "Undo - "+cmd.toString());
			dlm.add(0, "Redo - "+cmd.toString());
			
		}
		frame.getView().repaint();
	}

	public void openLog() {
		// TODO Auto-generated method stub
		// The name of the file to open.
		JFileChooser jfc = new JFileChooser("D:\\Asistenti\\Vukmanovic\\RIS\\");
		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			// This will reference one line at a time
			String line = null;
			//model.getAll().clear();
			//dlm.clear();
			try {
				// FileReader reads text files in the default encoding.
				FileReader fileReader = new FileReader(selectedFile.getAbsolutePath());
				// Always wrap FileReader in BufferedReader.
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while((line = bufferedReader.readLine()) != null) {
					if(line.contains("message")) {
						int x = Integer.parseInt(line.substring(line.indexOf("(")+1,line.indexOf(",")));
						int y = Integer.parseInt(line.substring(line.indexOf(",")+1,line.indexOf(")")));
						CmdAddPoint cmd = new CmdAddPoint(new Point(x, y, Color.BLUE), model);
						cmd.execute();
						LOGGER.log(Level.INFO, cmd.toString());
						dlm.add(0,cmd.toString());
					}
					frame.getView().repaint();	
				}   
				// Always close files.
				bufferedReader.close();         
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void remove(int selectedIndex) {
		// TODO Auto-generated method stub

		//dlm.getElementAt(selectedIndex).unexecute();
		//dlm.remove(selectedIndex);
		//frame.getView().repaint();
		//potrebno je kreirati metodu koja ce da parsira selektovanu komandu
	}

	public void saveModel() {
		// TODO Auto-generated method stub
		/*String path = "D:\\Asistenti\\Vukmanovic\\RIS\\";
		String fileName = "model.ser";
		File dir = new File(path);
		dir.mkdirs();
		File file = new File(path+fileName);
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			ObjectOutputStream outObject = new ObjectOutputStream(fileOut);
			outObject.writeObject(model);
			outObject.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		JFileChooser jfc = new JFileChooser("C:\\RIS");
		jfc.addChoosableFileFilter(new SerFilter());
		jfc.setAcceptAllFileFilterUsed(false);
		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			File dir = new File("c:/RIS/");
			dir.mkdirs();	
			if (!selectedFile.exists())
				try {
					selectedFile.createNewFile();
					FileOutputStream fileOut;
					if(selectedFile.getAbsolutePath().endsWith(".ser"))
						fileOut = new FileOutputStream(selectedFile.getAbsolutePath());
					else
						fileOut = new FileOutputStream(selectedFile.getAbsolutePath()+".ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(model);
					out.close();
					fileOut.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public void openModel() {/*
		// TODO Auto-generated method stub
		String path = "D:\\Asistenti\\Vukmanovic\\RIS\\";
		String fileName = "model.ser";
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(path+fileName);
			ObjectInputStream inObject = new ObjectInputStream(fileIn);
			DrawingModel model = (DrawingModel) inObject.readObject();
			this.model = model;
			frame.getView().setModel(model);
			frame.getView().repaint();
			inObject.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		JFileChooser fileopen = new JFileChooser("c:/RIS/");
		fileopen.addChoosableFileFilter(new SerFilter());
		fileopen.setAcceptAllFileFilterUsed(false);
		int ret = fileopen.showDialog(null, "Učitaj");
		if (ret == JFileChooser.APPROVE_OPTION) {

			try {
				File file = fileopen.getSelectedFile();
				FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
				ObjectInputStream in = new ObjectInputStream(fileIn);
				DrawingModel model = (DrawingModel) in.readObject();			
				this.model = model;
				frame.getView().setModel(model);
				in.close();
				fileIn.close();
				frame.getView().repaint();
				JOptionPane.showMessageDialog(null, "Uspešno učitani podaci!", "",JOptionPane.INFORMATION_MESSAGE);						
			}catch(Exception i) {
				i.printStackTrace();
				JOptionPane.showMessageDialog(null, "Nisu učitani podaci!", "Greška",JOptionPane.ERROR_MESSAGE);
			} 
		}
	}
}
