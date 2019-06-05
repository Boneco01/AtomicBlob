package modele;


public class BoiteCollision {
	private Personnage personnage;//TODO 
	
	public BoiteCollision(Personnage personnage) {
		this.personnage=personnage;
		
	}
	
	public boolean collision( int xPoint1, int yPoint1, int xPoint2, int yPoint2) {
		Monde monde=this.personnage.getMonde();
		Terrain terrain=this.personnage.getMonde().getMap();
		if (monde.gererCollision(terrain, xPoint1, yPoint1, personnage) && monde.gererCollision(terrain, xPoint2, yPoint2, personnage)) {
			return false;
		}
		else {
			return true;
		}
	}
}
