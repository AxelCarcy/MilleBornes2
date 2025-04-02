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
		return pileBataille.isEmpty() && sommet.equals(Cartes.FEU_VERT);
	}
	
	
	
	public boolean estDepotFeuVertAutorise() {
		if (pileBataille.isEmpty())
			return true;
		Carte sommet = getSommetBataille();
		return sommet.equals(Cartes.FEU_ROUGE) || 
				sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT);
	}
	
//	public boolean estDepotBorneAutoriser(Borne borne) {
//		int kmParcourus = donnerKmParcourus();
//		int kmBorne = borne.getKm();
//		int limite = 200;
//		if (getSommetLimite() instanceof DebutLimite)
//			limite = 50;
//		return !estBloque() && 
//				kmBorne <= limite &&
//				kmParcourus + kmBorne <= 1000;
//	}
	
//	private boolean estBloque() {
//		if (pileBataille.isEmpty())
//			return true;
//		Carte sommetBataille = getSommetBataille();
//		return sommetBataille.equals(Cartes.FEU_ROUGE) || 
//				sommetBataille.equals(Cartes.ACCIDENT) ||
//				sommetBataille.equals(Cartes.CREVAISON) ||
//				sommetBataille.equals(Cartes.PANNE_ESSENCE);
//	}
}
	
	
