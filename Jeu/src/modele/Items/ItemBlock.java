package modele.Items;

import modele.Monde;
import modele.Portee;
import modele.Blocks.Block;
import modele.Blocks.Air;

public abstract class ItemBlock extends Item {

	private char blockCorrespondant;
	
	public ItemBlock(int id, char blockCorrespondant) {
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
		if (monde.getJoueur().getXCible().getValue()!=(monde.getJoueur().getXProperty().getValue()+largeur)/64 ||
				monde.getJoueur().getYCible().getValue()!=(monde.getJoueur().getYProperty().getValue()+hauteur)/64) {
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
	
	public boolean pasSurUnAutreBlock(Monde monde) {
		int xCible=monde.getJoueur().getXCible().getValue();
		int yCible=monde.getJoueur().getYCible().getValue();
		if (monde.getMap().blockParCord(xCible, yCible) instanceof Air) {
			return true;
		}
		return false;
	}
	
	
	
	public void utiliser(Monde monde) {
		int xCible=monde.getJoueur().getXCible().getValue();
		int yCible=monde.getJoueur().getYCible().getValue();
		int xJoueur=monde.getJoueur().getXProperty().getValue();
		int yJoueur=monde.getJoueur().getYProperty().getValue();
		if(this.pasSurLeJoueur(monde) && Portee.estAPortee(2,xJoueur, yJoueur, xCible,yCible) && pasSurUnAutreBlock(monde)) {		
			Block block = monde.getMap().blockDe(blockCorrespondant);
			monde.getMap().remplacerBlock(block, xCible, yCible);
			this.setQuantitee(this.getQuantitee()-1);
		}
	}
	
}
