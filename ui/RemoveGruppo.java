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

public class RemoveGruppo extends JPanel {
private static final long serialVersionUID = 1L;
private ControllerOrganigramma controller = ControllerOrganigramma.getInstance();
private TextListener tl;

	public RemoveGruppo() {
		
		
        setLayout(new GridBagLayout()); 

        // Aggiungi campi di testo
        JTextField nomeGruppoField = new JTextField(15);
        
    
        // Etichette per i campi
        JLabel nomeGruppoLabel = new JLabel("Nome Gruppo di Lavoro:"); 
        
        //Bottone invio dati
        JButton inviaButton = new JButton("Rimuovi");

        // Aggiungi i componenti al pannello del form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeGruppoLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeGruppoField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weighty = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		add(inviaButton,gbc);
		
		inviaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tl != null)
				if(controller.removeGruppoDiLavoro(nomeGruppoField.getText())) {
					tl.testoEmesso(nomeGruppoField.getText(), null);
					JOptionPane.showMessageDialog(RemoveGruppo.this,"Operazione andata a buon fine");
					nomeGruppoField.setText("");
				}else {
					JOptionPane.showMessageDialog(RemoveGruppo.this,"Qualcosa è andato storto");
				}
				
			}
		});
		
        // Crea un bordo con titolo per il pannello del form
        setBorder(BorderFactory.createTitledBorder("Rimuovi Gruppo di Lavoro"));
	}
	public void setTextistener(TextListener textListener) {
		this.tl = textListener;
	}
}
