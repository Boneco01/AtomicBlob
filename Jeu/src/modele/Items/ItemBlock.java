package modele.Items;

import modele.Monde;
import modele.Portee;
import modele.Blocks.Block;

public abstract class ItemBlock extends Item {

	private Block blockCorrespondant;
	
	public ItemBlock(int id, Block blockCorrespondant) {
		super(id, 64, 2);
		this.blockCorrespondant = blockCorrespondant;
	}
	
	
	public boolean verificationPointBlock(String point, Monde monde) {
		int largeur=0;
		int hauteur=0;
		switch (point) {
		 case "hd" : largeur=monde.getJoueur().getLargeur(); break;
		 case "bg" : hauteur=monde.getJoueur().getHauteur(); break;
		 case "bd" : largeur=monde.getJoueur().getLargeur(); hauteur=monde.getJoueur().getHauteur(); break;
		}
		if (monde.getJoueur().getXCible()!=(monde.getJoueur().getXProperty().getValue()+largeur)/64 ||
				monde.getJoueur().getYCible()!=(monde.getJoueur().getYProperty().getValue()+hauteur)/64) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean pasSurLeJoueur(Monde monde) {
		if (verificationPointBlock("", monde) && verificationPointBlock ("hd",monde) && verificationPointBlock("bg",monde) && verificationPointBlock("bd",monde)) {
			return true;
		}
		return false;
	}
	
	public void utiliser(Monde monde) {
		if(this.pasSurLeJoueur(monde) && 
				Portee.estAPortee(2,monde.getJoueur().getXProperty().getValue(), 
				monde.getJoueur().getYProperty().getValue(), monde.getJoueur().getXCible(),
				monde.getJoueur().getYCible())) {		
			
			monde.getMap().remplacerBlock(this.blockCorrespondant, monde.getJoueur().getXCible(), monde.getJoueur().getYCible());
			this.setQuantitee(this.getQuantitee()-1);
		}
		
	}
	
}
