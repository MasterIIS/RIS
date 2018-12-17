package mvc;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SerFilter extends FileFilter {

	public boolean accept(File f) {
		String extension = f.getAbsolutePath();
		if (f.isDirectory() || (extension != null && extension.substring(extension.lastIndexOf(".") + 1).equals("ser")))
			return true;
		return false;
	}

	public String getDescription() {
		return "ser";
	}

}