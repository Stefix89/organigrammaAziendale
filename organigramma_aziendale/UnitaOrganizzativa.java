package organigramma_aziendale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class UnitaOrganizzativa implements Serializable {
    
	private static final long serialVersionUID = 1L;
	protected String nome;
    protected List<Dipendente> dipendenti = new ArrayList<>();

    public abstract void setCapo(Dipendente d);
    
    public Dipendente addDipendente(Dipendente d) {
    		dipendenti.add(d);
    		return d;
    }
    
    public void removeDipendente(Dipendente d) {
    		dipendenti.remove(d);
    }
    
    public String getNome() {
    	return this.nome;
    }
    
    public List<Dipendente> getDipendenti(){
    	return this.dipendenti;
    }

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitaOrganizzativa other = (UnitaOrganizzativa) obj;
		return other.getNome().equals(this.getNome());
	}
    
}
