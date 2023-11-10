package organigramma_aziendale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Dipendente implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
    private String cognome;
    private List<Ruolo> ruoli;
    private List<UnitaOrganizzativa> unitaOrganizzative;
    private Map<UnitaOrganizzativa,Ruolo> associazioni;
    
    public Dipendente(String nome, String cognome)  {
    	this.nome = nome;
    	this.cognome = cognome;
    	ruoli = new ArrayList<>();
    	unitaOrganizzative = new ArrayList<>();
    	associazioni = new HashMap<>();
    }
    
    
    public boolean addRuolo(Ruolo r, UnitaOrganizzativa u) {
    	if(r.getNome().equals("Direttore") && u instanceof Dipartimento) {
    		u.setCapo(this);
    		this.ruoli.add(r);
    		this.unitaOrganizzative.add(u);
    		this.associazioni.put(u, r);
    		return true;
    	}else if(r.getNome().equals("Responsabile") && u instanceof GruppoDiLavoro) {
    		u.setCapo(this);
    		this.ruoli.add(r);
    		this.unitaOrganizzative.add(u);
    		this.associazioni.put(u, r);
    		return true;
    	}else if(r.getNome().equals("Dipendente")) {
    		u.addDipendente(this);
    		this.ruoli.add(r);
    		this.unitaOrganizzative.add(u);
    		this.associazioni.put(u, r);
    		return true;
    	}else if(r.getNome().equals("Direttore Generale") && u instanceof ConsiglioAmministrazione) {
        		u.setCapo(this);
        		this.ruoli.add(r);
        		this.unitaOrganizzative.add(u);
        		this.associazioni.put(u, r);
        		System.out.println(this.unitaOrganizzative +" "+ this.ruoli);
        		return true;
    	}else if(r.getNome().equals("CEO") && u instanceof ConsiglioAmministrazione) {
    		ConsiglioAmministrazione consiglio = (ConsiglioAmministrazione)u;
    		consiglio.setCEO(this);
    		this.ruoli.add(r);
    		this.unitaOrganizzative.add(u);
    		this.associazioni.put(u, r);
    		return true;
    	}else {
    		this.unitaOrganizzative.add(u);
    		this.ruoli.add(r);
    		this.associazioni.put(u, r);
    		return true;
    	}
    	
    }
    
    
	public String getNome() {
		return nome;
	}


	public String getCognome() {
		return cognome;
	}


	public List<Ruolo> getRuoli() {
		return ruoli;
	}


	public List<UnitaOrganizzativa> getUnitaOrganizzative() {
		return unitaOrganizzative;
	}


	public Map<UnitaOrganizzativa, Ruolo> getAssociazioni() {
		return associazioni;
	}



	@Override
	public String toString() {
		return "nome=" + nome + ", cognome=" + cognome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome,cognome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dipendente other = (Dipendente) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(cognome, other.cognome);
				
	}

    
}