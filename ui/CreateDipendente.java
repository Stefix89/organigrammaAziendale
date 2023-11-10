package ui;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.ControllerOrganigramma;

public class CreateDipendente extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private ControllerOrganigramma controller = ControllerOrganigramma.getInstance();
	
	
	public CreateDipendente() {


        setLayout(new GridBagLayout()); 

        // Aggiungi campi di testo
        JTextField nomeField = new JTextField(15);
        JTextField cognomeField = new JTextField(15);
        JTextField ruoloField = new JTextField(15);
        JTextField unitaOrganizzativaField = new JTextField(15);
        JButton inviaButton = new JButton("Crea");
        

        // Etichette per i campi
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cognomeLabel = new JLabel("Cognome:");
        JLabel ruoloLabel = new JLabel("Ruolo:");
        JLabel unitaOrganizzativaLabel = new JLabel("Unità Organizzativa:");
        
       

        // Aggiungi i componenti al pannello del form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(cognomeLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(cognomeField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(ruoloLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(ruoloField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(unitaOrganizzativaLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(unitaOrganizzativaField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weighty = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		add(inviaButton,gbc);
		
		inviaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(nomeField.getText().isEmpty()||cognomeField.getText().isEmpty())) {
					if(controller.createDipendente(nomeField.getText(), cognomeField.getText(), ruoloField.getText(), unitaOrganizzativaField.getText())) {
						JOptionPane.showMessageDialog(CreateDipendente.this,"Operazione andata a buon fine");
						nomeField.setText("");
						cognomeField.setText("");
						ruoloField.setText("");
						unitaOrganizzativaField.setText("");
					}else {
						JOptionPane.showMessageDialog(CreateDipendente.this,"Qualcosa è andato storto");
					}
				}else {
					JOptionPane.showMessageDialog(CreateDipendente.this,"Alcuni campi sono vuoti");
				}
			}
		});
		

        // Crea un bordo con titolo per il pannello del form
        setBorder(BorderFactory.createTitledBorder("Crea Dipendente"));
		
	
	}
}
