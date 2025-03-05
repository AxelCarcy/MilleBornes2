package cartes;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
	}
	
	public String vehiculePrioritaire() {
		return "Véhicule prioritaire";
	}
	
	public String citerne() {
		return "Citerne";
	}
	
	public String increvable() {
		return "Increvable";
	}
	
	public String asDuVolant() {
		return "As du volant";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Botte botte) {
			return super.equals(botte);
		}
		return false;
	}

}
