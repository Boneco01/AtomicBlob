package modele;

public class Portee {
	
	public static boolean estAPortee(int distance,int xPosition, int yPosition, int xCible, int yCible) {
		if ((Math.abs(xPosition/64-xCible)<=distance) &&
				Math.abs(yPosition/64-yCible)<=distance) {
			return true;
		}
		return false;
	}
}
