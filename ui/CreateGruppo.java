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

public class CreateGruppo extends JPanel{
	private ControllerOrganigramma controller = ControllerOrganigramma.getInstance();
	private static final long serialVersionUID = 1L;
	private TextListener tl;
	
	public CreateGruppo() {
		
		
        setLayout(new GridBagLayout()); 

        // Aggiungi campi di testo
        JTextField nomeGruppoField = new JTextField(15);
        JTextField nomeDipartimentoField = new JTextField(15);
        
    
        // Etichette per i campi
        JLabel nomeGruppoLabel = new JLabel("Nome Gruppo di Lavoro:"); 
        JLabel nomeDipartimentoLabel = new JLabel("Unità sovrastante:"); 
        
        //Bottone invio dati
        JButton inviaButton = new JButton("Crea");

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
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeDipartimentoLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(nomeDipartimentoField,gbc);
		
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
				if(controller.createGruppoDiLavoro(nomeGruppoField.getText(), nomeDipartimentoField.getText()) && !nomeGruppoField.getText().isBlank()) {
					tl.testoEmesso(nomeGruppoField.getText(), nomeDipartimentoField.getText());
					JOptionPane.showMessageDialog(CreateGruppo.this,"Operazione andata a buon fine");
					nomeGruppoField.setText("");
					nomeDipartimentoField.setText("");
				}else {
					JOptionPane.showMessageDialog(CreateGruppo.this,"Qualcosa è andato storto");
				}
				
			}
		});
		
		
        // Crea un bordo con titolo per il pannello del form
        setBorder(BorderFactory.createTitledBorder("Crea Gruppo di Lavoro"));
	}
	
	public void setTextistener(TextListener textListener) {
		this.tl = textListener;
	}

}
