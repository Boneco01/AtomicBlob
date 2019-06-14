package vue;

import modele.Drone;

public class SpriteDrone extends Sprite{

private Drone drone;
	
	public SpriteDrone(Drone drone) {
		super("file:../Sprites/Ennemis/Drone/Drone.png");
		this.drone = drone;
	}

	@Override
	public void changerSprite() {
		char ancienSpriteCode = this.getSpriteCode();
		
		if(this.drone.getDroite()) {
			this.setSpriteCode('d');
		} else if(this.drone.getGauche()) {
			this.setSpriteCode('g');
		}
		
		if(this.getSpriteCode() != ancienSpriteCode) {
			switch(this.getSpriteCode()) {
						   
				default : this.setSprite("file:../Sprites/Ennemis/Drone/Drone.png");
				   		  break;
			
			}
			
			this.resetTempsAnime();
		}
		
		
	}
	
	@Override
	public void setSpriteMort() {
		this.setSprite("file:../Sprites/Ennemis/Explosion/ExplosionDrone.gif");
	}

}

