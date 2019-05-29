package modele;

public class Portee {
	private int distance;
	
	
	public Portee(int distance) {
		this.distance=distance;
		}
	
	public boolean estAPortee(int xPosition, int yPosition, int xCible, int yCible) {
		if ((valeurAbsolue(xPosition/64-xCible)<=this.distance) &&
				(valeurAbsolue(yPosition/64-yCible)<=this.distance)) {
			return true;
		}
		return false;
	}
	
	public int valeurAbsolue(int a) {
		if (a<0) {
			return -a;
		}
		return a;
	}
}
