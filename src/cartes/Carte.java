package cartes;

public abstract class Carte {
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass())
			return true;
		
		if(obj instanceof Carte carte) {
			return carte.toString().equals(toString());
		}
		return false;
	}
	
	
	
	@Override
	public int hashCode() {
		return 31 * (toString().hashCode() + getClass().hashCode());
	}
	
	
}
