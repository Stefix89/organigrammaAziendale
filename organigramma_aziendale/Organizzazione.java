package organigramma_aziendale;

import java.util.ArrayList;
import java.util.List;

public class Organizzazione {
    private List<UnitaOrganizzativa> unitaOrganizzative;
    private ConsiglioAmministrazione consiglio;
    private List<Ruolo> ruoli;
    
    public Organizzazione() {
    	unitaOrganizzative = new ArrayList<>();
    	consiglio = null;
    	ruoli = new ArrayList<>();
    	ruoli.add(new Ruolo("Direttore Generale"));
    	ruoli.add(new Ruolo("CEO"));
    	ruoli.add(new Ruolo("Direttore"));
    	ruoli.add(new Ruolo("Responsabile"));
    	ruoli.add(new Ruolo("Dipendente"));
    	ruoli.add(new Ruolo("Consigliere"));
    }
    
    public UnitaOrganizzativa addUnitaOrganizzativa(UnitaOrganizzativa u) {
    		unitaOrganizzative.add(u);
    		return u;
    	}
    
    public boolean removeUnitaOrganizzativa(UnitaOrganizzativa u) {
    		unitaOrganizzative.remove(u);
    		return true;
    	}
    
    
    public UnitaOrganizzativa findUnitaOrganizzativa(String u) {
    	for(UnitaOrganizzativa unita : this.unitaOrganizzative) {
    		if(unita.getNome().equalsIgnoreCase(u)) {
    			return unita;
    		}
    	}
    	throw new IllegalArgumentException("Unità non presente");
    }
    
	
	public ConsiglioAmministrazione addConsiglioAmministrazione(ConsiglioAmministrazione c) {
		if(this.consiglio == null) {
			consiglio = c;
			return c;
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	public boolean removeConsiglioAmministrazione(ConsiglioAmministrazione c) {
		if(this.consiglio != null) {
			this.consiglio = null;
			return true;
		}else {
			return false;
		}
	}
	
	public void createRuolo(String ruolo) {
		Ruolo r = new Ruolo(ruolo);
		this.ruoli.add(r);
	}
	
	public Ruolo getRuolo(String ruolo) {
		for(int i = 0; i < ruoli.size(); i++) {
			if(this.ruoli.get(i).getNome().equals(ruolo)) {
				return ruoli.get(i);
			}
		}
		throw new IllegalArgumentException("Ruolo non esistente!");
	}
	
	public UnitaOrganizzativa getUnita(String nomeUnita) { //restituisce l'unità a partire dal nome
		for(int i = 0; i < unitaOrganizzative.size(); i++) {
			if(this.unitaOrganizzative.get(i).getNome().equalsIgnoreCase(nomeUnita)) {
				return unitaOrganizzative.get(i);
			}
		}
		throw new IllegalArgumentException("Unità non esistente!");
	}
	


	public List<UnitaOrganizzativa> getUnitaOrganizzative() {
		return unitaOrganizzative;
	}

	public List<Ruolo> getRuoli() {
		return ruoli;
	}
	
	public ConsiglioAmministrazione getConsiglio() {
		return this.consiglio;
	}

	
}