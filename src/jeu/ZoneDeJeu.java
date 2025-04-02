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
	
	
}
