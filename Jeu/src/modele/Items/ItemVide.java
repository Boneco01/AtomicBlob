package modele.Items;

import modele.Monde;
import modele.Blocks.Air;

public class ItemVide extends Item {
	
	public ItemVide(Monde monde) {
		super(0, 1, 1, monde);
	}

	@Override
	public void utiliser(Monde monde) {
		if(this.getPortee().estAPortee(monde.getJoueur().getXProperty().getValue(), 
				monde.getJoueur().getYProperty().getValue(), monde.getJoueur().getXCible(),
				monde.getJoueur().getYCible())) {
			Air blockAir=new Air();
			monde.getJoueur().ramasseBlock(monde.getJoueur().getMonde().getMap().blockParCord(monde.getJoueur().getXCible(), monde.getJoueur().getYCible()));
			monde.getJoueur().getMonde().getMap().remplacerBlock(blockAir, monde.getJoueur().getXCible(), monde.getJoueur().getYCible());
		}
	}
	
}
