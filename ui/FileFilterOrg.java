package ui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileFilterOrg extends FileFilter {

	@Override
	public boolean accept(File f) {
		
		if(f.isDirectory()) {
			return true;
		}
		String estensione = Utils.getExtension(f);
		
		if(estensione == null) {
			return false;
		}
		
		if(estensione.equals("txt") || estensione.equals("bin")) {
			return true;
		}
		
		return false;
	}

	@Override
	public String getDescription() {
		return ".bin - .txt";
	}

}
