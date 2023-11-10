package organigramma_aziendale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConsiglioAmministrazione extends UnitaOrganizzativa implements Serializable {

	private static final long serialVersionUID = 1L;
	private Dipendente direttoreGenerale;
	private Dipendente CEO;
	private List<Dipendente> consiglieri;
	private String nome;
	
	public ConsiglioAmministrazione(Dipendente direttore, Dipendente ceo) {
		this.direttoreGenerale = direttore;
		this.CEO = ceo;
		consiglieri = new ArrayList<>();
		nome = "Consiglio d'Amministrazione";
	}

	@Override
	public void setCapo(Dipendente d) {
		if(direttoreGenerale != null) {
			direttoreGenerale = null;
		}
		direttoreGenerale = d;
	}
	
	public void setCEO(Dipendente d) {
		if(CEO != null) {
			this.CEO = null;
		}
		CEO = d;
	}
	
	public Dipendente addConsigliere(Dipendente d) {
		if(!consiglieri.contains(d)) {
			consiglieri.add(d);
			return d;
		}else {
			throw new IllegalArgumentException("Consigliere già presente");
		}
	}
	
	public boolean removeConsigliere(Dipendente d) {
		for(Dipendente consigliere : consiglieri) {
			if(d.equals(consigliere)) {
				consiglieri.remove(consigliere);
			}
		}
		throw new IllegalArgumentException("Consigliere non presente!");
	}

	@Override
	public String toString() {
		return "ConsiglioAmministrazione [direttoreGenerale=" + direttoreGenerale + ", CEO=" + CEO + ", consiglieri="
				+ consiglieri + "]";
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}
	
}
