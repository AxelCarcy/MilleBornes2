package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	private Joueur joueurCourant;
	private Carte carte;
	private Joueur joueurCible;
	
	public Coup(Joueur joueurCourant, Carte carte, Joueur joueurCible) {
		this.joueurCourant = joueurCourant;
		this.carte = carte;
		this.joueurCible = joueurCible;
	}

	@Override
	public String toString() {
		if (joueurCible != null)
			return "Le joueur dépose la carte " + carte + " dans la zone de jeu de " + joueurCible.getNom() + ".";
		else
			return "Le joueur défausse la carte " + carte + ".";
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Carte getCarte() {
		return carte;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public boolean estValide() {
		// Une carte Attaque ou limitation de vitesse ne peut être posée que sur un autre joueur
		if (carte instanceof Attaque || carte instanceof DebutLimite)
			return !joueurCible.equals(joueurCourant); 
		else
			return joueurCible.equals(joueurCourant);
	}
	
	
	
	
	

}
