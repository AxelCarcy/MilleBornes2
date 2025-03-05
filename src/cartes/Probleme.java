package cartes;

public abstract class Probleme extends Carte {
	private Type type;
	
	protected Probleme(Type type) {
		this.type = type;
    }

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return type.toString();
	}
	
	public void action() {
		switch (type) {
		case FEU:
			System.out.println("Feu Rouge");
			break;
			
		case ESSENCE:
			System.out.println("Panne Essence");
			break;
			
		case CREVAISON:
			System.out.println("Crevaison");
			break;
			
		case ACCIDENT:
			System.out.println("Accident");
			break;
		
		default:
			break;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Probleme probleme) {
			return super.equals(probleme);
		}
		return false;
	}
}
