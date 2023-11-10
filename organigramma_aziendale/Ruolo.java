package organigramma_aziendale;

import java.io.Serializable;
import java.util.Objects;

public class Ruolo implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private String nome;
	
	public Ruolo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return "Ruolo [nome=" + nome + ", descrizione=" +"]";
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
		Ruolo other = (Ruolo) obj;
		return other.getNome().equals(this.getNome());
	}
	
	
	
}