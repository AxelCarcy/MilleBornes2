package jeu;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu= new ZoneDeJeu();
	private MainJoueur mainJoueur = new MainJoueur();

	public Joueur(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	public String getNom() {
		return nom;
	}

	public ZoneDeJeu getZoneDeJeu() {
		return zoneDeJeu;
	}

	public MainJoueur getMainJoueur() {
		return mainJoueur;
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
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		Set<Coup> coupPossibles = new HashSet<>();
		
		List<Carte> listeCarteEnMain = mainJoueur.getMain();
		
		for(Carte carte : listeCarteEnMain) {
			for (Joueur jouerCible : participants) {
				Coup coup = new Coup(this, carte, jouerCible);
				if (coup.estValide())
					coupPossibles.add(coup);
			}
		}
		return coupPossibles;
	}
	
	public Set<Coup> coupsDefausse() {
		Set<Coup> coupsDefausse = new HashSet<Coup>();

		List<Carte> listeCarteEnMain = mainJoueur.getMain();

		for (Carte carte : listeCarteEnMain) {
			Coup coup = new Coup(this, carte, null);
			coupsDefausse.add(coup);
		}
		return coupsDefausse;
	}
}
