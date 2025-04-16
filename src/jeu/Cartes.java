package jeu;

import cartes.*;

public interface Cartes {
	// Attaque
	public static Carte FEU_ROUGE = new Attaque(Type.FEU);
	public static Carte LIMITE = new DebutLimite();
	public static Carte PANNE_ESSENCE = new Attaque(Type.ESSENCE);
    public static Carte CREVAISON = new Attaque(Type.CREVAISON);
    public static Carte ACCIDENT = new Attaque(Type.ACCIDENT);
	
	// Bottes
	public static Carte PRIORITAIRE = new Botte(Type.FEU);

	// Parade
	public static Carte FEU_VERT = new Parade(Type.FEU);
	public static Carte FIN_LIMITE = new FinLimite();
}
