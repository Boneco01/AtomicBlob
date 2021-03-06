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
		
		if(this.joueur.getDroite()) {
			this.setSpriteCode('d');
		} else if(this.joueur.getGauche()) {
			this.setSpriteCode('g');
		}
		
		if(this.getSpriteCode() != ancienSpriteCode) {
			switch(this.getSpriteCode()) {
				
				case 'g' : this.setSprite("file:../Sprites/Joueur/JoueurBase/JoueurBaseLeft.gif");
				   		   break;
						   
				default : this.setSprite("file:../Sprites/Joueur/JoueurBase/JoueurBaseRight.gif");
				   		  break;
			
			}
		}
		
	}

}
