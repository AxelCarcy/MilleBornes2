package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> pileLimites = new LinkedList<>();
	private List<Bataille> pileBataille = new LinkedList<>();
	private Collection<Borne> collectionBornes = new ArrayList<>();
	Set <Botte> bottesObtenues = new HashSet<>();  
	
	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty() || estPrioritaire())
			return 200;
		else if (getSommetLimite() instanceof DebutLimite)
			return 50;
		return 200;
	}
	
	
	public Carte getSommetLimite() {
		if (pileLimites.isEmpty())
			return null;
		
		return pileLimites.get(0);
	}
	
	public Bataille getSommetBataille() {
		if (pileBataille.isEmpty())
			return null;

		return pileBataille.get(0);
	}
	
	
	public int donnerKmParcourus() {
		int kmParcourus = 0;
		if (!collectionBornes.isEmpty()) {
			for (Borne borne : collectionBornes) {
				kmParcourus += borne.getKm();
			}
		}
		return kmParcourus;
	}
	
	public void deposer(Carte carte) {
		if (carte instanceof Limite lim)
			pileLimites.add(0, lim);
		else if (carte instanceof Bataille bat)
			pileBataille.add(0, bat);
		else if (carte instanceof Borne bor)
			collectionBornes.add(bor);
		else if (carte instanceof Botte bot) 
			bottesObtenues.add(bot);
		else
			System.out.println("La carte n'a pas pu être ajoutée à une pile");
	}
	
	public boolean peutAvancer() {
		Carte sommet = getSommetBataille();
		if(pileBataille.isEmpty() && estPrioritaire())
			return true;
		
		if(!pileBataille.isEmpty()) {
		
			if(sommet.equals(Cartes.FEU_VERT))
				return true;
			
			if(sommet instanceof Parade && estPrioritaire())
				return true;
			
			if(sommet.equals(Cartes.FEU_ROUGE) && estPrioritaire())
				return true;
			
			if(possedeBotte(sommet) && estPrioritaire())
				return true;
			
		}
		
		return false;	
	}
	
	
	
	private boolean estDepotFeuVertAutorise() {
		if (estPrioritaire())
			return false;
		
		if (pileBataille.isEmpty())
			return true;
		Carte sommet = getSommetBataille();
		return sommet.equals(Cartes.FEU_ROUGE) || 
				sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT)
				|| sommet instanceof Attaque && possedeBotte(sommet);
	}
	
	private boolean estDepotBorneAutoriser(Borne borne) {
	return (!pileBataille.isEmpty() &&
			!(getSommetBataille() instanceof Attaque) &&
			(donnerKmParcourus() + borne.getKm() < 1000) &&
			(borne.getKm() <= donnerLimitationVitesse())
			);
	}
	
	private boolean estDepotLimiteAutorise (Limite limite) {
		if (estPrioritaire())
			return false;
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
		Carte sommetBataille = getSommetBataille();
		return pileBataille.isEmpty() ? !estPrioritaire()
		: 	sommetBataille.equals(Cartes.FEU_ROUGE) || 
			sommetBataille.equals(Cartes.ACCIDENT) ||
			sommetBataille.equals(Cartes.CREVAISON) ||
			sommetBataille.equals(Cartes.PANNE_ESSENCE);
	}
	
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if(possedeBotte(bataille))
			return false;
		
		if(bataille instanceof Attaque)
            return !estBloque();
            
        else if(bataille instanceof Parade parade) {
            // Parade parade = (Parade) bataille;
            Carte sommet = getSommetBataille();
            if(parade.equals(Cartes.FEU_VERT)) {
                return estDepotFeuVertAutorise();
                
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
			return true;
			
	}
	
	public boolean estPrioritaire() {
		return bottesObtenues.contains(Cartes.PRIORITAIRE);	
	}
	
	public boolean estAsDuVolant() {
		return bottesObtenues.contains(Cartes.AS_DU_VOLANT);
	}
	
	public boolean estCiterne() {
		return bottesObtenues.contains(Cartes.CITERNE);
	}
	
	public boolean estIncrevable() {
		return bottesObtenues.contains(Cartes.INCREVABLE);
	}
	
	private boolean possedeBotte(Carte carte) {
        Type typeSommet;
        
        if(carte instanceof Attaque attaque)
            typeSommet = attaque.getType();
        
        else if(carte instanceof Parade parade)
            typeSommet = parade.getType();
        
        else
            return false;
        
        switch(typeSommet) {
        
        case ACCIDENT:
//            System.out.println("Accident, possède botte = " + estAsduVolant());
            return estAsDuVolant();
        
        case CREVAISON:
//            System.out.println("Crevaison, possède botte = " + estIncrevable());
            return estIncrevable();
        
        case ESSENCE:
//            System.out.println("Panne Essence, possède botte = " + estCiterne());
            return estCiterne();
        
        default:
//            System.out.println("Carte attaque incorrecte");
            return false;
        }
	}
}
	
	
