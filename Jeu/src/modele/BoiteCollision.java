package modele;


public class BoiteCollision {
	private Personnage personnage;//TODO 
	
	public BoiteCollision(Personnage personnage) {
		this.personnage=personnage;
		
	}
	
	public boolean collisionGauche() {
		if (this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), -3, 0) &&
			this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), -3, this.personnage.getHauteur()-3)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean collisionDroite() {
		if(this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), this.personnage.getLargeur()+3, 0) &&
		this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), this.personnage.getLargeur()+3, this.personnage.getHauteur()-3)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean collisionHaut() {
		if(this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), 0, -3) && 
		this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), this.personnage.getLargeur(), -3)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean collisionBas() {
		if(this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), 0, this.personnage.getHauteur()) && 
		this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), this.personnage.getLargeur(), this.personnage.getHauteur())) {
			return false;
		}
		else {
			return true;
		}
	}
	
}
