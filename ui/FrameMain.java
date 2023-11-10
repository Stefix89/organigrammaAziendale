package ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import controller.ControllerOrganigramma;

public class FrameMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser;
	private ControllerOrganigramma controller = ControllerOrganigramma.getInstance();
	private OrganigrammaTree organigramma;
	
public FrameMain() {
		
		super("Organigramma aziendale"); //titolo
		
		setLayout(null);
		
		JButton salva = new JButton("Salva Organigramma");
		
		setJMenuBar(creaBarraMenu());
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FileFilterOrg()); //aggiunge il filtro per i file selezionabili
		fileChooser.setAcceptAllFileFilterUsed(false); //non permette di selezione tutti i file
		
		JTabbedPane tp = new JTabbedPane();
		
		JPanel panelOrganigramma = new JPanel(new BorderLayout());
		JPanel panelDipendenti = new JPanel(new BorderLayout());
		JPanel panelDipartimento = new JPanel(new BorderLayout());
		JPanel panelGruppoDiLavoro = new JPanel(new BorderLayout());
		JPanel panelRuolo = new JPanel(new BorderLayout());
		
		CreateDipartimento c = new CreateDipartimento();
		RemoveDipartimento r = new RemoveDipartimento();
		CreateGruppo cg = new CreateGruppo();
		RemoveGruppo rg = new RemoveGruppo();
		CreateTree ct = new CreateTree();
		
		this.organigramma = new OrganigrammaTree(c,r,cg,rg,ct);
		
		panelDipendenti.add(new CreateDipendente(), BorderLayout.CENTER);
		
		panelDipartimento.add(c, BorderLayout.LINE_START);
		panelDipartimento.add(r, BorderLayout.CENTER);
		
		panelGruppoDiLavoro.add(cg, BorderLayout.LINE_START);
		panelGruppoDiLavoro.add(rg, BorderLayout.CENTER);
		
		panelRuolo.add(new AddRuolo(), BorderLayout.LINE_START);
		panelRuolo.add(new RemoveRuolo(), BorderLayout.CENTER);
		panelRuolo.add(new CreateRuolo(), BorderLayout.LINE_END);
		
		panelOrganigramma.add(ct, BorderLayout.LINE_START);
		panelOrganigramma.add(organigramma, BorderLayout.CENTER);
		panelOrganigramma.add(salva, BorderLayout.PAGE_END);
		
		tp.setBounds(0, 0, 900, 460);
		
		tp.add("Organigramma", panelOrganigramma);
		tp.add("Dipendente",panelDipendenti);
		tp.add("Dipartimento", panelDipartimento);
		tp.add("Gruppo di lavoro",panelGruppoDiLavoro);
		tp.add("Ruolo", panelRuolo);
		
		add(tp); //aggiunge il tabbed pane al main frame 
		
		setLocation(100,100); //dove appare la pagina
		
		setSize(930,500); //dimensioni finestra
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //chiusura
		
		
		setVisible(true);
		
	}

	private JMenuBar creaBarraMenu() {
		
		JMenuBar barra = new JMenuBar(); //crea la barra
		
		JMenu menu = new JMenu("File"); //crea il menù
		
		JMenuItem importa = new JMenuItem("Importa");
		JMenuItem esporta = new JMenuItem("Esporta");
		
		menu.add(importa);
		menu.add(esporta);
		
		importa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(FrameMain.this) == JFileChooser.APPROVE_OPTION) {
					try {
						organigramma.clear();
						controller.caricaDaFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(FrameMain.this, "Impossibile importare", "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
			
		});
		
		esporta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showSaveDialog(FrameMain.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.salvaSuFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(FrameMain.this, "Impossibile esportare", "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
			
		});
		
		barra.add(menu); //aggiunge il menu alla barra
		
		return barra;
	}
	
	
	
	
	
	
}
