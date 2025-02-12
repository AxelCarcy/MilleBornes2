package cartes;

public enum Type {
	FEU("Feu Rouge", "Feu Vert", "Vehicule Prioritaire"), 
	ESSENCE("Panne Essence", "Essence", "Citerne"), 
	CREVAISON("Crevaison", "Roue de Secours", "Increvable"), 
	ACCIDENT("Accident", "Reparation", "asDuVolant");
	
	private final String attaque;
	private final String parade;
	private final String botte;
	
	private Type(String attaque, String parade, String botte) {
		this.attaque = attaque;
		this.parade = parade;
		this.botte = botte;
	}

	public String getAttaque() {
		return attaque;
	}

	public String getParade() {
		return parade;
	}

	public String getBotte() {
		return botte;
	}

}
