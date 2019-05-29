package modele.Items;

import modele.Monde;
import modele.Blocks.Block;

public abstract class ItemBlock extends Item {

	private Block blockCorrespondant;
	
	public ItemBlock(int id, Block blockCorrespondant) {
		super(id, 64, 2);
		this.blockCorrespondant = blockCorrespondant;
	}
	
	
	public boolean verificationPointBlock(String point) {
		int largeur=0;
		int hauteur=0;
		switch (point) {
		 case "hd" : largeur=this.monde.getJoueur().getLargeur(); break;
		 case "bg" : hauteur=this.monde.getJoueur().getHauteur(); break;
		 case "bd" : largeur=this.monde.getJoueur().getLargeur(); hauteur=this.monde.getJoueur().getHauteur(); break;
		}
		if (this.monde.getJoueur().getXCible()!=(this.monde.getJoueur().getXProperty().getValue()+largeur)/64 ||
				this.monde.getJoueur().getYCible()!=(this.monde.getJoueur().getYProperty().getValue()+hauteur)/64) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean pasSurLeJoueur() {
		if (verificationPointBlock("") && verificationPointBlock ("hd") && verificationPointBlock("bg") && verificationPointBlock("bd")) {
			return true;
		}
		return false;
	}
	
	public void utiliser(Monde monde) {
		if (monde.getJoueur().getUtiliser() && this.pasSurLeJoueur() && 
				this.getPortee().estAPortee(monde.getJoueur().getXProperty().getValue(), 
						monde.getJoueur().getYProperty().getValue(), monde.getJoueur().getXCible(),
						monde.getJoueur().getYCible())) {		
			monde.getJoueur().getMonde().getMap().remplacerBlock(this.blockCorrespondant, monde.getJoueur().getXCible(), monde.getJoueur().getYCible());
		}
		
	}
	
}
