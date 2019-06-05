package vue;

import modele.Sentinelle;

public class SpriteSentinelle extends Sprite {
	
	private Sentinelle sentinelle;
	
	public SpriteSentinelle(Sentinelle sentinelle) {
		super("file:../Sprites/Ennemis/Sentinelle/SentilleBase/SentinelleBase.gif");
		this.sentinelle=sentinelle;
	}

	@Override
	public void changerSprite() {
		char ancienSpriteCode = this.getSpriteCode();
		
		if(this.sentinelle.getDroite()) {
			this.setSpriteCode('d');
		} else if(this.sentinelle.getGauche()) {
			this.setSpriteCode('g');
		}
		else {
			this.setSpriteCode('b');
		}
		
		if(this.getSpriteCode() != ancienSpriteCode) {
			switch(this.getSpriteCode()) {
				
				case 'g' : this.setSprite("file:../Sprites/Ennemis/Sentinelle/SentinelleWalk/SentinelleWalk.gif");
				   		   break;
				case 'd' : this.setSprite("file:../Sprites/Ennemis/Sentinelle/SentinelleWalk/SentinelleWalk.gif");
							break;
						   
				default : this.setSprite("file:../Sprites/Ennemis/Sentinelle/SentinelleFight/SentinelleFight.gif");
				   		  break;
			
			}
		}
		
	}
}