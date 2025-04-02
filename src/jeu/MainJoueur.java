package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class MainJoueur {
	private List<Carte> main;
	
	public MainJoueur() {
		main = new ArrayList<>();
	}
	
	void prendre(Carte carte) {
		main.add(carte);
	}
	
	void jouer(Carte carte) {
		assert main.contains(carte);
		main.remove(carte);
	}
	
	@Override
	public String toString() {
		StringBuilder chaine = new StringBuilder("Cartes en main : ");
		for (Carte carte : main) {
			chaine.append(carte).append(" ");
		}
		return chaine.toString();
	}
	
	

}
