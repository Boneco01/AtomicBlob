package vue;

import modele.Joueur;
import modele.Poubelle;

public class SpritePoubelle extends Sprite {
	
	private Poubelle p;
	
	public SpritePoubelle(Poubelle p) {
		super("file:../Sprites/Joueur/JoueurBase/JoueurBaseRight.gif");
		this.p = p;
	}
	
	public void changerSprite() {
		char ancienSpriteCode = this.getSpriteCode();
		this.setSpriteCode(' ');
		
		if(this.getSpriteCode() != ancienSpriteCode) {
			switch(this.getSpriteCode()) {
			
				default : this.setSprite("file:../Sprites/Joueur/JoueurBase/JoueurBaseRight.gif");
				   		  break;
			
			}
		}
		
	}

}

