package jeu;

import cartes.*;

public interface Cartes {
	// Attaque
	public static Carte FEU_ROUGE = new Attaque(Type.FEU);
	
	// Bottes
	public static Carte PRIORITAIRE = new Botte(Type.FEU);

	// Parade
	public static Carte FEU_VERT = new Parade(Type.FEU);
}
