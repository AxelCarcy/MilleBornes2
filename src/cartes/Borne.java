package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		this.km = km;
	}

	public int getKm() {
		return km;
	}

	@Override
	public String toString() {
		return "Borne de " + km + " km";
	}

	
	
	@Override
	public boolean equals (Object obj) {
		return super.equals(obj) && km == ((Borne)obj).getKm();
	}
	
	
	@Override
	public int hashCode() {
		return 31 * (super.hashCode() + getKm());
	}
	
	

}
