package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerOrganigramma;

public class CreateTree extends JPanel {
	private static final long serialVersionUID = 1L;
	private ControllerOrganigramma controller = ControllerOrganigramma.getInstance();
	private TextListener tl;
	
	public CreateTree() {
		
		
        setLayout(new GridBagLayout()); 

        // Aggiungi campi di testo
        JTextField nomeDirettoreGeneraleField = new JTextField(15);
        JTextField cognomeDirettoreGeneraleField = new JTextField(15);
        JTextField nomeCEOField = new JTextField(15);
        JTextField cognomeCEOField = new JTextField(15);
        
    
        // Etichette per i campi
        JLabel nomeDirGenLabel = new JLabel("Nome Direttore Generale:"); 
        JLabel cognomeDirGenLabel = new JLabel("Cognome Direttore Generale:"); 
        JLabel nomeCEOLabel = new JLabel("Nome CEO:"); 
        JLabel cognomeCEOLabel = new JLabel("Cognome CEO:");
        
        //Bottone invio dati
        JButton inviaButton = new JButton("Crea Consiglio");

        // Aggiungi i componenti al pannello del form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeDirGenLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeDirettoreGeneraleField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(cognomeDirGenLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(cognomeDirettoreGeneraleField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeCEOLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeCEOField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(cognomeCEOLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(cognomeCEOField,gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weighty = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		add(inviaButton,gbc);
		
		inviaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomeDirettore = nomeDirettoreGeneraleField.getText();
				String cognomeDirettore = cognomeDirettoreGeneraleField.getText();
				String nomeCEO = nomeCEOField.getText();
				String cognomeCEO = cognomeCEOField.getText();
				if(tl != null) 
					if(!(nomeDirettore.isEmpty()||cognomeDirettore.isEmpty()||nomeCEO.isEmpty()||cognomeCEO.isEmpty())) {
						if(controller.createConsiglioAmministrazione(nomeDirettore, cognomeDirettore, nomeCEO, cognomeCEO)) {
							tl.testoEmesso("Consiglio D'Amministrazione", null);
							JOptionPane.showMessageDialog(CreateTree.this,"Operazione andata a buon fine");
							nomeDirettoreGeneraleField.setText("");
							cognomeDirettoreGeneraleField.setText("");
							nomeCEOField.setText("");
							cognomeCEOField.setText("");
					}else {
						JOptionPane.showMessageDialog(CreateTree.this,"Qualcosa è andato storto");
					}
						
				}else {
					JOptionPane.showMessageDialog(CreateTree.this,"Alcuni campi sono vuoti");
				}
			}
		});
		
        // Crea un bordo con titolo per il pannello del form
        setBorder(BorderFactory.createTitledBorder("Crea Nuovo Organigramma"));
	}
	
	public void setTextistener(TextListener textListener) {
		this.tl = textListener;
	}

}
