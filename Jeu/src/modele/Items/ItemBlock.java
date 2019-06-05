package modele.Items;

import modele.Monde;
import modele.Personnage;
import modele.Portee;
import modele.Blocks.Block;
import modele.Blocks.Air;

public abstract class ItemBlock extends Item {

	private char blockCorrespondant;
	
	public ItemBlock(int id, char blockCorrespondant) {
		super(id, 64, 2);
		this.blockCorrespondant = blockCorrespondant;
	}
	
	
	public boolean verificationPointBlock(String point, Monde monde, Personnage personnage) {
		int largeur=0;
		int hauteur=0;
		switch (point) {
		 case "hd" : largeur=personnage.getLargeur(); break;
		 case "bg" : hauteur=personnage.getHauteur(); break;
		 case "bd" : largeur=personnage.getLargeur(); hauteur=personnage.getHauteur(); break;
		}
		if (monde.getJoueur().getXCible()!=(personnage.getXProperty().getValue()+largeur)/64 ||
				monde.getJoueur().getYCible()!=(personnage.getYProperty().getValue()+hauteur)/64) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verificationPointParPoint(Monde monde, Personnage personnage) {
		if (verificationPointBlock("", monde, personnage) && verificationPointBlock ("hd",monde, personnage) && verificationPointBlock("bg",monde,personnage) 
				&& verificationPointBlock("bd",monde,personnage)) {
			return true;
		}
		return false;
	}
	
	public boolean pasSurUnAutreBlock(Monde monde) {
		int xCible=monde.getJoueur().getXCible();
		int yCible=monde.getJoueur().getYCible();
		if (monde.getMap().blockParCord(xCible, yCible) instanceof Air) {
			return true;
		}
		return false;
	}
	
	
	
	public void utiliser(Monde monde) {
		int xCible=monde.getJoueur().getXCible();
		int yCible=monde.getJoueur().getYCible();
		int xJoueur=monde.getJoueur().getXProperty().getValue();
		int yJoueur=monde.getJoueur().getYProperty().getValue();
		if(verificationPointParPoint(monde,monde.getJoueur()) 
			&& verificationPointParPoint(monde,monde.getSentinelle()) && Portee.estAPortee(2,xJoueur, yJoueur, xCible,yCible) && pasSurUnAutreBlock(monde)) {		
			Block block = monde.getMap().blockDe(blockCorrespondant);
			monde.getMap().remplacerBlock(block, xCible, yCible);
			this.setQuantitee(this.getQuantitee()-1);
		}
	}
	
}
