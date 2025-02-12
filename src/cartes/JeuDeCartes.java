package cartes;

public class JeuDeCartes {
	private static int typesDeCartes = 19;
	private Configuration[] listeCarte;

	public JeuDeCartes() {
		this.listeCarte = new Configuration[typesDeCartes];
		
		//Bornes
		listeCarte[0] = new Configuration(10, new Borne(25));
		listeCarte[1] = new Configuration(10, new Borne(50));
		listeCarte[2] = new Configuration(10, new Borne(75));
		listeCarte[3] = new Configuration(12, new Borne(100));
		listeCarte[4] = new Configuration(4, new Borne(200));
		
		//Feu
		listeCarte[5] = new Configuration(14, new Parade(Type.FEU)); //feu vert
		listeCarte[6] = new Configuration(5, new Attaque(Type.FEU)); //feu rouge
		listeCarte[7] = new Configuration(1, new Botte(Type.FEU)); //véhicule prioritaire
		
		//Essence
		listeCarte[8] = new Configuration(6, new Parade(Type.ESSENCE)); //Bidon d'essence
		listeCarte[9] = new Configuration(3, new Attaque(Type.ESSENCE)); //Panne d'essence
		listeCarte[10] = new Configuration(1, new Botte(Type.ESSENCE)); //Citerne
		
		//Accident
		listeCarte[11] = new Configuration(6, new Parade(Type.ACCIDENT)); //Réparation
		listeCarte[12] = new Configuration(3, new Attaque(Type.ACCIDENT)); //Accident
		listeCarte[13] = new Configuration(1, new Botte(Type.ACCIDENT)); //As du volant
		
		//Crevaison
		listeCarte[14] = new Configuration(6, new Parade(Type.CREVAISON)); //Roue de secours
		listeCarte[15] = new Configuration(3, new Attaque(Type.CREVAISON)); //Crevaison
		listeCarte[16] = new Configuration(1, new Botte(Type.CREVAISON)); //Increvable
		
		//Limite
		listeCarte[17] = new Configuration(6, new FinLimite()); //Fin de limite
		listeCarte[18] = new Configuration(4, new DebutLimite()); //Limite 50
	}
	
	public String affichageJeuDeCartes() {
		StringBuilder chaine = new StringBuilder();
		int nbCarte = -1;
		String nomCarte;
		
		for (int i = 0; i < typesDeCartes; i++) {
			nbCarte = listeCarte[i].getNbExemplaires();
			nomCarte = listeCarte[i].getCarte().toString();
			chaine.append(nbCarte + " " + nomCarte + "\n");
		}
		return chaine.toString();
	}
	
	public Carte[] donnerCartes() {
		int nbCartes = 0;
		for (int i = 0; i < typesDeCartes; i++) {
			nbCartes += listeCarte[i].getNbExemplaires();
		}
		
		Carte [] cartes = new Carte[nbCartes];
		int cpt = 0;
		for (int i = 0; i < typesDeCartes; i++) {
			for (int j = 0; j < listeCarte[i].getNbExemplaires(); j++) {
				cartes[cpt] = listeCarte[i].getCarte();
				cpt++;
			}
		}
		return cartes;
	}
	
	public boolean checkCount() {
		Carte[] tabCartes = donnerCartes();
		int nbExemplaires = 0;
		for(int i = 0; i < listeCarte.length; i++) {
			for (Carte carte : tabCartes) {
				if (carte.equals(listeCarte[i].getCarte())) {
					nbExemplaires++;
				}
			}
			if (nbExemplaires != listeCarte[i].getNbExemplaires()) {
				return false;
			}
			nbExemplaires = 0;
		}
		return true;
	}
	
	private static class Configuration {
		private int nbExemplaires;
		private Carte carte;
		
		public Configuration(int nbExemplaires, Carte carte) {
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
		
		
	}

}
