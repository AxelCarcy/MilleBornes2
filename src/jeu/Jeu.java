package jeu;
import java.util.Collections;
import java.util.List;
import cartes.*;
import jeu.Sabot;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;

	public Jeu() {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		Carte[] cartes = jeuDeCartes.donnerCartes();
		List<Carte> listeCartes = GestionCartes.tabToList(cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		Collections.addAll(listeCartes, cartes);
		
		sabot = new Sabot(GestionCartes.listToTab(listeCartes));
	}
	
	public Carte piocherCarte() {
		return sabot.piocher();
	}

	public Sabot getSabot() {
		return sabot;
	}
	
}
