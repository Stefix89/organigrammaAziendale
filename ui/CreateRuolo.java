package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerOrganigramma;

public class CreateRuolo extends JPanel{
private static final long serialVersionUID = 1L;
private ControllerOrganigramma controller = ControllerOrganigramma.getInstance();

	public CreateRuolo() {
		
		
        setLayout(new GridBagLayout()); 

        // Aggiungi campi di testo
        JTextField nomeRuoloField = new JTextField(10);
       // JTextField nomeUnitaField = new JTextField(10);
        
    
        // Etichette per i campi
        JLabel nomeRuoloLabel = new JLabel("Nome Nuovo Ruolo:"); 
       // JLabel nomeUnitaLabel = new JLabel("Nome Unità Organizzativa:"); 
       
        
        //Bottone invio dati
        JButton inviaButton = new JButton("Crea Ruolo");
        
        
     
        // Aggiungi i componenti al pannello del form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeRuoloLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeRuoloField,gbc);
		
	/*	gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeUnitaLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeUnitaField,gbc); */
		
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weighty = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		add(inviaButton,gbc);
		
		
		
		inviaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomeRuolo = nomeRuoloField.getText();
				if(!nomeRuolo.isEmpty()) {
					if(controller.createRuolo(nomeRuolo)) {
						JOptionPane.showMessageDialog(CreateRuolo.this,"Operazione andata a buon fine");
						nomeRuoloField.setText("");
					}else {
						JOptionPane.showMessageDialog(CreateRuolo.this,"Qualcosa è andato storto");
					}
				}else {
					JOptionPane.showMessageDialog(CreateRuolo.this,"Campo vuoto");
				}
				
			}
		});
		
		
        // Crea un bordo con titolo per il pannello del form
        setBorder(BorderFactory.createTitledBorder("Crea un Ruolo"));
	}
	


}
