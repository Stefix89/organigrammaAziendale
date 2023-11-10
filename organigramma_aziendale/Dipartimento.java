package organigramma_aziendale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dipartimento extends UnitaOrganizzativa implements Serializable  {

	private static final long serialVersionUID = 1L;
	private List<GruppoDiLavoro> sottounita;
	private Dipendente direttore;
	private String nomeDipartimento;
	
	
	public Dipartimento(String nome) {
		this.nomeDipartimento = nome;
		sottounita = new ArrayList<>();
	}
	
	
	public UnitaOrganizzativa addGruppo(GruppoDiLavoro g) { //si assume che i dipartimenti siano tutti allo stesso livello
		sottounita.add(g);
		return g;
	}

	@Override
	public void setCapo(Dipendente d) {
		if(this.direttore != null) {
			direttore = null;
		}
		direttore = d;
	}


	public List<GruppoDiLavoro> getSottounita() {
		return sottounita;
	}


	@Override
	public String getNome() {
		return nomeDipartimento;
	}


	@Override
	public String toString() {
		return "Dipartimento [nomeDipartimento=" + nomeDipartimento + "]";
	}
	
	
	
	

}
