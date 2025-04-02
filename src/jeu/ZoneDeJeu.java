package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.*;

public class ZoneDeJeu {
	private List<Carte> pileLimites;
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
		else if (getSommetPile() instanceof DebutLimite)
			return 50;
		return 200;
	}
	
	public Carte getSommetPile() {
		if (pileLimites.isEmpty())
			return null;
		
		return pileLimites.get(pileLimites.size() - 1);
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
		if (carte instanceof Limite limite)
			pileLimites.add(limite);
		else if (carte instanceof Bataille bataille)
			pileBataille.add(bataille);
		else if (carte instanceof Borne borne)
			pileBornes.add(borne);
		else
			System.out.println("La carte n'a pas pu être ajoutée à une pile");
	}
	
	
}
