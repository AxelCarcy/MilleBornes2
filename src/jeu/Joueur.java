package jeu;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur mainJoueur;

	public Joueur(String nom) {
		this.nom = nom;
		zoneDeJeu = new ZoneDeJeu();
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur joueur) {
			return nom.equals(joueur.nom);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31 * nom.hashCode();
	}
	
	public void donner(Carte carte) {
		mainJoueur.prendre(carte);
	}
	
	public Carte prendreCartes(Sabot sabot) {
		if (sabot.estVide())
			return null;

		Carte carte = sabot.piocher();
		mainJoueur.prendre(carte);
		return carte;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
}
