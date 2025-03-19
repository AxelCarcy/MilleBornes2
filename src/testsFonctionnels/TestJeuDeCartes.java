package testsFonctionnels;

import cartes.JeuDeCartes;

public class TestJeuDeCartes {
	public static void testAffichageJeuDeCarte() {
		JeuDeCartes jeu = new JeuDeCartes();
		System.out.println("JEU:\n" + jeu.affichageJeuDeCartes());

		if (!jeu.checkCount()) {
			System.out.println("erreur de nombre");
		}
	}

	public static void testCheckCount() {
		JeuDeCartes jeu = new JeuDeCartes();
		System.out.println("Test checkCount:");
		System.out.println(jeu.checkCount());
	}

	public static void main(String[] args) {
		testAffichageJeuDeCarte();
		testCheckCount();
	}
}