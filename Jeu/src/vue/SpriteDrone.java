package vue;

import modele.Drone;

public class SpriteDrone extends Sprite{

private Drone drone;
	
	public SpriteDrone(Drone drone) {
		super("file:../Sprites/Joueur/JoueurBase/JoueurBaseRight.gif");
		this.drone = drone;
	}

	@Override
	public void changerSprite() {
		char ancienSpriteCode = this.getSpriteCode();
		
		if(this.drone.getVie()==0 ) {
			this.setSpriteCode('m');
		} else if(this.drone.getDroite()) {
			this.setSpriteCode('d');
		} else if(this.drone.getGauche()) {
			this.setSpriteCode('g');
		}
		
		if(this.getSpriteCode() != ancienSpriteCode) {
			switch(this.getSpriteCode()) {
				
			case 'm' : this.setSprite("file:../Sprites/Joueur/JoueurDie/JoueurDie.gif");
	   		   		   break;	
			
			case 'g' : this.setSprite("file:../Sprites/Joueur/JoueurBase/JoueurBaseRight.gif");
				   	   break;
						   
				default : this.setSprite("file:../Sprites/Joueur/JoueurBase/JoueurBaseRight.gif");
				   		  break;
			
			}
			
			this.resetTempsAnime();
		}
		
		
	}

}

