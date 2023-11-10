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

public class RemoveRuolo extends JPanel {
private static final long serialVersionUID = 1L;
private ControllerOrganigramma controller = ControllerOrganigramma.getInstance();
	
	public RemoveRuolo() {
		
		
        setLayout(new GridBagLayout()); 

        // Aggiungi campi di testo
        JTextField nomeDipendenteField = new JTextField(10);
        JTextField cognomeDipendenteField = new JTextField(10);
        JTextField nomeUnitaField = new JTextField(10);
        
    
        // Etichette per i campi
        JLabel nomeDipendenteLabel = new JLabel("Nome Dipendente:"); 
        JLabel cognomeDipendenteLabel = new JLabel("Cognome Dipendente:"); 
        JLabel nomeUnitaLabel = new JLabel("Nome Unità Organizzativa:"); 
        
        
        //Bottone invio dati
        JButton inviaButton = new JButton("Rimuovi");

        // Aggiungi i componenti al pannello del form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeDipendenteLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeDipendenteField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(cognomeDipendenteLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(cognomeDipendenteField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeUnitaLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeUnitaField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weighty = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		add(inviaButton,gbc);
		
		inviaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(controller.removeRuoloDipendente(nomeDipendenteField.getText(), cognomeDipendenteField.getText(), nomeUnitaField.getText())) {
					JOptionPane.showMessageDialog(RemoveRuolo.this,"Operazione andata a buon fine");
					nomeDipendenteField.setText("");
					cognomeDipendenteField.setText("");
					nomeUnitaField.setText("");
				}else {
					JOptionPane.showMessageDialog(RemoveRuolo.this,"Qualcosa è andato storto");
				}
				
			}
		});
		
		
        // Crea un bordo con titolo per il pannello del form
        setBorder(BorderFactory.createTitledBorder("Rimuovi Ruolo al Dipendente"));
	}

}
