package organigramma_aziendale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GruppoDiLavoro extends UnitaOrganizzativa implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private Dipendente responsabile;
	private UnitaOrganizzativa unitaSovrastante;
	private List<GruppoDiLavoro> gruppiSottostanti;
	
	public GruppoDiLavoro(String nome, UnitaOrganizzativa u) {
		this.nome = nome;
		this.unitaSovrastante = u;
		this.gruppiSottostanti = new ArrayList<>();
	}

	@Override
	public void setCapo(Dipendente d) {
		this.responsabile = d;
	}

	public String getNome() {
		return nome;
	}


	public Dipendente getResponsabile() {
		return responsabile;
	}


	public UnitaOrganizzativa getUnitaSovrastante() {
		return unitaSovrastante;
	}
	

	public List<GruppoDiLavoro> getGruppiSottostanti() {
		return gruppiSottostanti;
	}
	
	public void addGruppoDiLavoro(GruppoDiLavoro g) {
		this.gruppiSottostanti.add(g);
	}
	
	public UnitaOrganizzativa getPadre() {
		return this.unitaSovrastante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nome);
		return result;
	}


	@Override
	public String toString() {
		return "GruppoDiLavoro [nome=" + "]";
	}

	
	

}
