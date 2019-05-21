package vue;

import modele.Joueur;

public class SpriteJoueur extends Sprite {
	
	private Joueur joueur;
	
	public SpriteJoueur(Joueur joueur) {
		super("file:../Sprites/Joueur/JoueurBase/JoueurBaseRight.gif");
		this.joueur = joueur;
	}
	
	public void changerSprite() {
		char ancienSpriteCode = this.getSpriteCode();
		
		if(this.joueur.getDroite() && ancienSpriteCode != 'h') {
			this.setSpriteCode('d');
		} else if(this.joueur.getGauche() && ancienSpriteCode != 'h') {
			this.setSpriteCode('g');
		} else if(this.joueur.getHaut() && ancienSpriteCode != 'h') {
			this.setSpriteCode('h');
		}
		
		if(this.getSpriteCode() != ancienSpriteCode) {
			switch(this.getSpriteCode()) {
				
				case 'g' : this.setSprite("file:../Sprites/Joueur/JoueurBase/JoueurBaseLeft.gif");
				   		   break;
		   		   		   
				case 'h' : this.setSprite("file:../Sprites/Joueur/JoueurJump/JoueurJumpAir.gif");
						   break;
						   
				default : this.setSprite("file:../Sprites/Joueur/JoueurBase/JoueurBaseRight.gif");
				   		  break;
			
			}
		}
		
	}

}
