package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> pileLimites;
	private List<Bataille> pileBataille;
	private List<Borne> pileBornes;

	public ZoneDeJeu() {
		pileLimites = new ArrayList<>();
		pileBataille = new ArrayList<>();
		pileBornes = new ArrayList<>();
	}
	
	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty())
			return 200;
		else if (getSommetLimite() instanceof DebutLimite)
			return 50;
		return 200;
	}
	
	public Carte getSommetLimite() {
		if (pileLimites.isEmpty())
			return null;
		
		return pileLimites.get(pileLimites.size() - 1);
	}
	
	public Bataille getSommetBataille() {
		if (pileBataille.isEmpty())
			return null;

		return pileBataille.get(pileBataille.size() - 1);
	}
	
	protected Borne getSommetBorne() {
		if (pileBornes.isEmpty())
			return null;

		return pileBornes.get(pileBornes.size() - 1);
	}
	
	
	public int donnerKmParcourus() {
		int kmParcourus = 0;
		if (!pileBornes.isEmpty()) {
			for (Borne carte : pileBornes) {
				kmParcourus += carte.getKm();
			}
		}
		return kmParcourus;
	}
	
	public void deposer(Carte carte) {
		if (carte instanceof Limite lim)
			pileLimites.add(lim);
		else if (carte instanceof Bataille bat)
			pileBataille.add(bat);
		else if (carte instanceof Borne bor)
			pileBornes.add(bor);
		else
			System.out.println("La carte n'a pas pu être ajoutée à une pile");
	}
	
	public boolean peutAvancer() {
		Carte sommet = getSommetBataille();
		return !pileBataille.isEmpty() && sommet.equals(Cartes.FEU_VERT);
	}
	
	
	
	private boolean estDepotFeuVertAutorise() {
		if (pileBataille.isEmpty())
			return true;
		Carte sommet = getSommetBataille();
		return sommet.equals(Cartes.FEU_ROUGE) || 
				sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT);
	}
	
	private boolean estDepotBorneAutoriser(Borne borne) {
	return (!pileBataille.isEmpty() &&
			!(getSommetBataille() instanceof Attaque) &&
			(donnerKmParcourus() + borne.getKm() < 1000) &&
			(borne.getKm() <= donnerLimitationVitesse())
			);
	}
	
	private boolean estDepotLimiteAutorise (Limite limite) {
		if(limite instanceof DebutLimite) {
            return pileLimites.isEmpty() ||
                    getSommetLimite().equals(Cartes.FIN_LIMITE);
            
        } else if(limite instanceof FinLimite) {
            return getSommetLimite().equals(Cartes.LIMITE);
            
        } else {
            System.out.println("La carte donné n'est pas correcte");
            return false;
        }
	}
	
	private boolean isSameType(Attaque attaque, Parade parade) {
    	return attaque.getType().equals(parade.getType());
    }
	
	private boolean estBloque() {
		if (pileBataille.isEmpty())
			return true;
		Carte sommetBataille = getSommetBataille();
		return sommetBataille.equals(Cartes.FEU_ROUGE) || 
				sommetBataille.equals(Cartes.ACCIDENT) ||
				sommetBataille.equals(Cartes.CREVAISON) ||
				sommetBataille.equals(Cartes.PANNE_ESSENCE);
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if(bataille instanceof Attaque)
            return !estBloque();
            
        else if(bataille instanceof Parade parade) {
            // Parade parade = (Parade) bataille;
            Carte sommet = getSommetBataille();
            if(parade.equals(Cartes.FEU_VERT)) {
                return pileBataille.isEmpty() ||
                        sommet.equals(Cartes.FEU_ROUGE) ||
                        (sommet instanceof Parade &&
                                !sommet.equals(Cartes.FEU_VERT));
                
            } else {
                return !pileBataille.isEmpty() && isSameType((Attaque)sommet, parade);
    
            }
        } else
            return false;
	}
	
public boolean estDepotAutorise(Carte carte) {
	if (carte instanceof Limite lim) {
		return estDepotLimiteAutorise(lim);

	} else if (carte instanceof Bataille bat) {
		return estDepotBatailleAutorise(bat);

	} else if (carte instanceof Borne bor) {
		return estDepotBorneAutoriser(bor);

	} else 
		System.out.println("La carte donné n'est pas correcte");
	return false;
}
}
	
	
