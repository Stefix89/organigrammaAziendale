package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import organigramma_aziendale.ConsiglioAmministrazione;
import organigramma_aziendale.Dipartimento;
import organigramma_aziendale.Dipendente;
import organigramma_aziendale.GruppoDiLavoro;
import organigramma_aziendale.Organizzazione;
import organigramma_aziendale.Ruolo;
import organigramma_aziendale.UnitaOrganizzativa;
import storageManagment.FileManager;
import ui.OrganigrammaTree;

public class ControllerOrganigramma {
	private static ControllerOrganigramma instance;
	private Organizzazione o;
	private boolean consiglioCreato = false;
	private FileManager fm;
	private OrganigrammaTree albero;
	
	
	private ControllerOrganigramma() {
    }
	
	public static ControllerOrganigramma getInstance() {
        if (instance == null) {
            instance = new ControllerOrganigramma();
        }
        return instance;
    }
	
	public boolean createDipendente(String nome, String cognome, String ruolo, String unita) {
		if(consiglioCreato && nonEsisteDipendente(nome,cognome) && !nonEsisteUnita(unita) && !nonEsisteRuolo(ruolo) && verificaCompatibilita(ruolo,unita)) {
			Dipendente d = new Dipendente(nome,cognome);
			Ruolo r = o.getRuolo(ruolo);
			UnitaOrganizzativa u = o.getUnita(unita);
			d.addRuolo(r, u);
			u.addDipendente(d);
			System.out.println("Dipendente creato");
			return true;
		}else {
			return false;
		}
	}
	
	
	
	public boolean createDipartimento(String nome) {
		
		if(consiglioCreato && nonEsisteUnita(nome)) {
			Dipartimento d = new Dipartimento(nome);
			o.addUnitaOrganizzativa(d);
			System.out.println(d.getNome()+" creato");
			return true;
		}else {
			return false;
		}
	}
	
	public boolean removeDipartimento(String nome) {
	    if (!nonEsisteUnita(nome)) {
	        List<UnitaOrganizzativa> unitaOrganizzazione = o.getUnitaOrganizzative();
	        List<UnitaOrganizzativa> nodiDaRimuovere = new ArrayList<>();

	        for (UnitaOrganizzativa u : unitaOrganizzazione) {
	            if (u.getNome().equalsIgnoreCase(nome)) {
	                nodiDaRimuovere.add(u);

	                Dipartimento dipartimento = (Dipartimento) u;
	                List<GruppoDiLavoro> gruppiSottostanti = dipartimento.getSottounita();

	              
	                    nodiDaRimuovere.addAll(gruppiSottostanti); 
	               

	                for (Dipendente d : u.getDipendenti()) {
	                    Map<UnitaOrganizzativa, Ruolo> associazioniDipendente = d.getAssociazioni();
	                    List<UnitaOrganizzativa> unitaDipendente = d.getUnitaOrganizzative();

	                    if (unitaDipendente.size() >= 2) {
	                        unitaDipendente.remove(u);
	                        Ruolo r = associazioniDipendente.get(u);
	                        d.getRuoli().remove(r);
	                        associazioniDipendente.remove(u);
	                    } else {
	                        unitaDipendente.remove(u);
	                        d.getRuoli().clear();
	                        associazioniDipendente.clear();
	                    }
	                }
	            }
	        }
	        unitaOrganizzazione.removeAll(nodiDaRimuovere);
	        System.out.println(nome+" rimosso");
	        return true;
	    } else {
	        return false;
	    }
	}
	
	
	public boolean createGruppoDiLavoro(String nome, String unitaSovrastante) {
		if(consiglioCreato && nonEsisteUnita(nome) && !nonEsisteUnita(unitaSovrastante)) {
			
			UnitaOrganizzativa unitaSovra = o.getUnita(unitaSovrastante);
			GruppoDiLavoro nuovoGruppo = new GruppoDiLavoro(nome,unitaSovra);
			if(unitaSovra instanceof Dipartimento) {
				Dipartimento d = (Dipartimento)unitaSovra;
				d.addGruppo(nuovoGruppo);
			}else {
				GruppoDiLavoro g = (GruppoDiLavoro)unitaSovra;
				g.addGruppoDiLavoro(nuovoGruppo);
			}
			o.addUnitaOrganizzativa(nuovoGruppo);
			System.out.println(nome+ " creato con padre "+ unitaSovrastante);
			for(UnitaOrganizzativa u : o.getUnitaOrganizzative()) {
				if(u instanceof Dipartimento) {
					Dipartimento d = (Dipartimento)u;
					System.out.println("Dipartimento "+d.getNome());
				}else if(u instanceof GruppoDiLavoro) {
					GruppoDiLavoro g = (GruppoDiLavoro)u;
					System.out.println("Gruppo di lavoro "+g.getNome()+" con padre "+g.getUnitaSovrastante());
				}
			}
			
			for(UnitaOrganizzativa u : o.getUnitaOrganizzative()) {
				if(u instanceof Dipartimento) {
					Dipartimento d = (Dipartimento)u;
					System.out.println("Gruppi Sottostanti di "+d.getNome());
					for(GruppoDiLavoro gruppo : d.getSottounita()) {
						System.out.println(gruppo.getNome());
					}
				}else if(u instanceof GruppoDiLavoro) {
					GruppoDiLavoro g = (GruppoDiLavoro)u;
					System.out.println("Gruppi Sottostanti di "+g.getNome());
					for(GruppoDiLavoro gruppo : g.getGruppiSottostanti()) {
						System.out.println(gruppo.getNome());
					}
				}
			}
			return true;
		}else {
			for(UnitaOrganizzativa u : o.getUnitaOrganizzative()) {
				if(u instanceof Dipartimento) {
					Dipartimento d = (Dipartimento)u;
					System.out.println("Dipartimento "+d.getNome());
				}else if(u instanceof GruppoDiLavoro) {
					GruppoDiLavoro g = (GruppoDiLavoro)u;
					System.out.println("Gruppo di lavoro "+g.getNome()+" con padre "+g.getUnitaSovrastante());
				}
			}
			
			for(UnitaOrganizzativa u : o.getUnitaOrganizzative()) {
				if(u instanceof Dipartimento) {
					Dipartimento d = (Dipartimento)u;
					System.out.println("Gruppi Sottostanti di "+d.getNome());
					for(GruppoDiLavoro gruppo : d.getSottounita()) {
						System.out.println(gruppo.getNome());
					}
				}else if(u instanceof GruppoDiLavoro) {
					GruppoDiLavoro g = (GruppoDiLavoro)u;
					System.out.println("Gruppi Sottostanti di "+g.getNome());
					for(GruppoDiLavoro gruppo : g.getGruppiSottostanti()) {
						System.out.println(gruppo.getNome());
					}
				}
			}
			return false;
		}
	}
	
	
	
	  public boolean removeGruppoDiLavoro(String nome) {
		    GruppoDiLavoro trovato = null;

		    for (UnitaOrganizzativa u : o.getUnitaOrganizzative()) {
		        if (u.getNome().equalsIgnoreCase(nome) && u instanceof GruppoDiLavoro) {
		            trovato = (GruppoDiLavoro) u;
		            System.out.println("TROVATO è: "+ trovato.getNome());
		            break; // Trovato il gruppo di lavoro, esci dal loop
		        }
		    }

		    if (trovato != null) {
		    	o.getUnitaOrganizzative().remove(trovato); // Rimuovi il gruppo di lavoro
		        for(UnitaOrganizzativa u : o.getUnitaOrganizzative()) {
					if(u instanceof Dipartimento) {
						Dipartimento d = (Dipartimento)u;
						System.out.println("Dipartimento "+d.getNome());
					}else if(u instanceof GruppoDiLavoro) {
						GruppoDiLavoro g = (GruppoDiLavoro)u;
						System.out.println("Gruppo di lavoro "+g.getNome()+" con padre "+g.getUnitaSovrastante());
					}
				}
				
				for(UnitaOrganizzativa u : o.getUnitaOrganizzative()) {
					if(u instanceof Dipartimento) {
						Dipartimento d = (Dipartimento)u;
						System.out.println("Gruppi Sottostanti di "+d.getNome());
						for(GruppoDiLavoro gruppo : d.getSottounita()) {
							System.out.println(gruppo.getNome());
						}
					}else if(u instanceof GruppoDiLavoro) {
						GruppoDiLavoro g = (GruppoDiLavoro)u;
						System.out.println("Gruppi Sottostanti di "+g.getNome());
						for(GruppoDiLavoro gruppo : g.getGruppiSottostanti()) {
							System.out.println(gruppo.getNome());
						}
					}
				}

		        // Ora rimuovi gli eventuali gruppi sottostanti
		        List<GruppoDiLavoro> gruppiSottostanti = trovato.getGruppiSottostanti();
		        for (GruppoDiLavoro gruppoSottostante : gruppiSottostanti) {
		        	o.getUnitaOrganizzative().remove(gruppoSottostante);
		        }

		        // Rimuovi il gruppo di lavoro dai dipendenti associati
		        for (Dipendente dipendente : trovato.getDipendenti()) {
		            Map<UnitaOrganizzativa, Ruolo> associazioniDipendente = dipendente.getAssociazioni();
		            dipendente.getRuoli().remove(associazioniDipendente.get(trovato));
		            dipendente.getUnitaOrganizzative().remove(trovato);
		            associazioniDipendente.remove(trovato);
		        }

		        trovato.getDipendenti().clear(); // Rimuovi i dipendenti dal gruppo di lavoro

	    
	    UnitaOrganizzativa padre = trovato.getUnitaSovrastante();
	    if (padre instanceof Dipartimento) {
	        Dipartimento p = (Dipartimento) padre;
	        Iterator<GruppoDiLavoro> iterator = p.getSottounita().iterator();
	        while (iterator.hasNext()) {
	            GruppoDiLavoro gruppo = iterator.next();
	            if (gruppo.getNome().equalsIgnoreCase(nome)) {
	                iterator.remove();
	            }
	        }
	    } else if (padre instanceof GruppoDiLavoro) {
	        GruppoDiLavoro g = (GruppoDiLavoro) padre;
	        Iterator<GruppoDiLavoro> iterator = g.getGruppiSottostanti().iterator();
	        while (iterator.hasNext()) {
	            GruppoDiLavoro gruppo = iterator.next();
	            if (gruppo.getNome().equalsIgnoreCase(nome)) {
	                iterator.remove();
	            }
	        }
	    }

		}
	    return true;
	}

	
	public boolean addRuoloDipendente(String ruolo, String nome, String cognome, String nomeUnita) {
	    if (!nonEsisteRuolo(ruolo) && !nonEsisteDipendente(nome, cognome) && !nonEsisteUnita(nomeUnita) && consiglioCreato && !haRuoloInUnita(nome, cognome, nomeUnita) && verificaCompatibilita(ruolo, nomeUnita)) {
	        Ruolo r = o.getRuolo(ruolo);
	        List<UnitaOrganizzativa> unita = o.getUnitaOrganizzative();

	        for (UnitaOrganizzativa u : unita) {
	            List<Dipendente> dipendentiUnita = new ArrayList<>(u.getDipendenti()); // Crea una copia dei dipendenti

	            for (Dipendente d : dipendentiUnita) {
	                if (d.getNome().equals(nome) && d.getCognome().equals(cognome)) {
	                    UnitaOrganizzativa unitaTrovata = o.findUnitaOrganizzativa(nomeUnita);
	                    unitaTrovata.addDipendente(d);
	                    d.addRuolo(r, unitaTrovata);
	                    System.out.println("Ruolo aggiunto");
	                }
	            }
	        }
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public boolean removeRuoloDipendente(String nome, String cognome, String nomeUnita) {
	    if (consiglioCreato && !nonEsisteDipendente(nome, cognome) && !nonEsisteUnita(nomeUnita)) {
	        List<UnitaOrganizzativa> unitaOrganizzative = o.getUnitaOrganizzative();
	        
	        for (UnitaOrganizzativa u : unitaOrganizzative) {
	            List<Dipendente> dipendenti = new ArrayList<>(u.getDipendenti()); // Crea una copia dei dipendenti

	            for (Dipendente d : dipendenti) {
	                if (d.getNome().equals(nome) && d.getCognome().equals(cognome)) {
	                    Map<UnitaOrganizzativa, Ruolo> associazioni = new HashMap<>(d.getAssociazioni()); // Crea una copia delle associazioni
	                    Ruolo r = associazioni.get(u);
	                    u.removeDipendente(d);
	                    d.getRuoli().remove(r);
	                    d.getUnitaOrganizzative().remove(u);
	                    d.getAssociazioni().remove(u);
	                    System.out.println("Ruolo rimosso");
	                }
	            }
	        }
	        return true;
	    } else {
	        return false;
	    }
	}


	
	public boolean createRuolo(String ruolo) {
		if(consiglioCreato && nonEsisteRuolo(ruolo)) {
			Ruolo r = new Ruolo(ruolo);
			o.getRuoli().add(r);
			System.out.println("Ruolo creato");
			return true;
		}else {
			return false;
		}
	}
	
	public boolean createConsiglioAmministrazione(String nomeDirettore, String cognomeDirettore, String nomeCEO, String cognomeCEO) {
		if(!consiglioCreato) {
			this.o = new Organizzazione();
			fm = new FileManager(this);
			Dipendente direttoreGenerale = new Dipendente(nomeDirettore, cognomeDirettore);
			Dipendente CEO = new Dipendente(nomeCEO, cognomeCEO);
			ConsiglioAmministrazione consiglio = new ConsiglioAmministrazione(direttoreGenerale, CEO);
			o.addConsiglioAmministrazione(consiglio);
			o.addUnitaOrganizzativa(consiglio);
			consiglio.addDipendente(direttoreGenerale);
			consiglio.addDipendente(CEO);
			Ruolo direttoreGen = new Ruolo("Direttore Generale");
			Ruolo ceo = new Ruolo("CEO");
			direttoreGenerale.addRuolo(direttoreGen, consiglio);
			CEO.addRuolo(ceo, consiglio);
			this.consiglioCreato = true;
			System.out.println("Consiglio creato");
			return true;
		}
		return false;
	}
	
	public List<UnitaOrganizzativa> getUnitaOrganizzazione(){
		return o.getUnitaOrganizzative();
	}
	
	public List<Ruolo> getRuoli(){
		return o.getRuoli();
	}
	
	
	private boolean nonEsisteDipendente(String nome, String cognome) {
		List<UnitaOrganizzativa> unita = o.getUnitaOrganizzative();
		for(UnitaOrganizzativa u : unita) {
			List<Dipendente> dipendenti = u.getDipendenti();
			for(Dipendente d : dipendenti) {
				if(d.getNome().equalsIgnoreCase(nome) && d.getCognome().equalsIgnoreCase(cognome)) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	private boolean nonEsisteUnita(String nome) {
		List<UnitaOrganizzativa> unita = o.getUnitaOrganizzative();
		System.out.println("");
		System.out.println("Sto verificando le unita esistenti");
		for(UnitaOrganizzativa u : unita) {
			System.out.println(u.getNome());
			}
		System.out.println("");
		for(UnitaOrganizzativa u : unita) {
			if(u.getNome().equalsIgnoreCase(nome)) {
				return false;
			}
		}
		return true;
	}
	
	
	
	private boolean nonEsisteRuolo(String ruolo) {
		List<Ruolo> ruoli = o.getRuoli();
		for(Ruolo r : ruoli) {
			if(r.getNome().equalsIgnoreCase(ruolo)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean haRuoloInUnita(String nome, String cognome, String unita) {
		UnitaOrganizzativa u = o.getUnita(unita);
		List<Dipendente> dipendenti = u.getDipendenti();
		for(Dipendente d : dipendenti) {
			if(d.getNome().equalsIgnoreCase(nome) && d.getCognome().equalsIgnoreCase(cognome)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean verificaCompatibilita(String ruolo, String unita) {
		UnitaOrganizzativa u = o.findUnitaOrganizzativa(unita);
		if(ruolo.equalsIgnoreCase("Direttore") && !(u instanceof Dipartimento)) {
			return false;
		}else if(ruolo.equalsIgnoreCase("Responsabile") && !(u instanceof GruppoDiLavoro)) {
			return false;
		}else if((ruolo.equalsIgnoreCase("Direttore Generale") || ruolo.equalsIgnoreCase("CEO") || ruolo.equalsIgnoreCase("Consigliere")) && !(u instanceof ConsiglioAmministrazione)) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean salvaSuFile(File f) throws IOException {
		fm.salvaSuFile(f);
		return true;
	}
	
	public boolean caricaDaFile(File f) throws IOException {
		fm.importaDaFile(f);
		return true;
	}
	
	public void setTree(OrganigrammaTree ot) { //setta un riferimento all'albero nell'UI
		this.albero = ot;
	}
	
	public void setStringheTree(UnitaOrganizzativa[] lista) {
		this.albero.riceviDaFile(lista);
	}
	
	
}
