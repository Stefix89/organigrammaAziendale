package storageManagment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import controller.ControllerOrganigramma;
import organigramma_aziendale.ConsiglioAmministrazione;
import organigramma_aziendale.Organizzazione;
import organigramma_aziendale.Ruolo;
import organigramma_aziendale.UnitaOrganizzativa;

public class FileManager {
	private ControllerOrganigramma controller;
	
	public FileManager(ControllerOrganigramma c) {
		this.controller = c;
	}
	
	public void salvaSuFile(File f) throws IOException {
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		UnitaOrganizzativa[] unita = controller.getUnitaOrganizzazione().toArray(new UnitaOrganizzativa[controller.getUnitaOrganizzazione().size()]);
		Ruolo[] ruoli = controller.getRuoli().toArray(new Ruolo[controller.getRuoli().size()]);
		oos.writeObject(unita);
		oos.writeObject(ruoli);
		oos.close();
		fos.close();
	}
	
	public void importaDaFile(File f) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			UnitaOrganizzativa[] unita = (UnitaOrganizzativa[]) ois.readObject();
			Ruolo[] ruoli = (Ruolo[]) ois.readObject();
			controller.getUnitaOrganizzazione().clear();
			controller.getRuoli().clear();
			controller.getUnitaOrganizzazione().addAll(Arrays.asList(unita));
			controller.getRuoli().addAll(Arrays.asList(ruoli));
			controller.setStringheTree(unita); //manda all'UI le unità per mandarle a schermo
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}

}
